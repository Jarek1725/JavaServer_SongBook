package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.Author;
import com.example.server.Entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetSongQueries {
    public static Song getSongQuery(String songId, String isPlus){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Song song = null;
        List<Author> authorList = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM songs WHERE songs.id = ?");
            preparedStatement.setString(1, songId);

            ResultSet rs = preparedStatement.executeQuery();

            String song_album = null;

            int popularity = 0;

            while(rs.next()){
                String song_title = rs.getString("Title");
                if (song_title == null){
                    return null;
                }
                popularity = rs.getInt("popularity");
                System.out.println(rs.getString("Title"));
                song_album = rs.getString("Album");
                String indexInAlbum = rs.getString("index_in_album");
                String song_src = rs.getString("Song_src");
                song = new Song(songId, song_title, song_album, indexInAlbum, song_src, null, "", "");
            }
            if(isPlus.equals("true")){
                popularity = popularity+1;
                preparedStatement = conn.prepareStatement("UPDATE `songs` SET `popularity` = ? WHERE `songs`.`id` = ?;");
                preparedStatement.setString(1, String.valueOf(popularity));
                preparedStatement.setString(2, songId);

                preparedStatement.executeUpdate();
            }


            preparedStatement = conn.prepareStatement("SELECT * FROM song_artist WHERE song_id = ?");
            preparedStatement.setString(1, songId);

            rs = preparedStatement.executeQuery();

            authorList = new ArrayList<>();
            ResultSet rs2 = null;

            while (rs.next()){
                preparedStatement = conn.prepareStatement("SELECT * FROM artists WHERE id = ?");
                preparedStatement.setString(1, rs.getString("artist_id"));

                rs2 = preparedStatement.executeQuery();

                while (rs2.next()){
                    authorList.add(new Author(rs2.getString("id"), rs2.getString("First_name"), rs2.getString("Last_name"), rs2.getString("Pseudonym"), rs2.getString("profile_photo")));
                }
            }

            song.setSongAutor(authorList);

            preparedStatement = conn.prepareStatement("SELECT * FROM albums WHERE id = ?");
            preparedStatement.setString(1, song_album);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                song.setAlbumPhoto(rs.getString("Album_photo"));
                song.setAlbumName(rs.getString("Album_name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return song;
    }
}
