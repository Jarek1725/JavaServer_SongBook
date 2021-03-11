package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.Album;
import com.example.server.Entities.Author;
import com.example.server.Entities.AuthorForSearch;
import com.example.server.Entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetArtistQuery {

    public static AuthorForSearch getArtist(String artistId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Author author = null;

        List<Album> authorAlbumList = null;
        List<Song> authorMostPopularSong = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM `artists` WHERE artists.id = ?");
            preparedStatement.setString(1, artistId);

            ResultSet rs = preparedStatement.executeQuery();

            String fName = null;
            String lName = null;
            String pseudonym = null;

            while (rs.next()){
                fName = rs.getString("First_name");
                lName = rs.getString("Last_name");
                pseudonym = rs.getString("Pseudonym");

                author = new Author(artistId, rs.getString("First_name"), rs.getString("Last_name"), rs.getString("Pseudonym"), rs.getString("profile_photo"));
            }

            preparedStatement = conn.prepareStatement("SELECT albums.* FROM `album_main_artists` INNER JOIN albums ON albums.id = album_main_artists.album_id WHERE artist_id = ? ORDER BY `albums`.`release_date` DESC");
            preparedStatement.setString(1, artistId);
            rs=preparedStatement.executeQuery();

            authorAlbumList = new ArrayList<>();

            while(rs.next()){
                authorAlbumList.add(new Album(rs.getString("id"), rs.getString("Album_name"), rs.getString("Album_photo")));
            }

            preparedStatement = conn.prepareStatement("SELECT songs.id FROM `song_artist` INNER JOIN songs ON songs.id = song_artist.song_id WHERE song_artist.artist_id = ? ORDER BY length(popularity) DESC, popularity DESC LIMIT 15");
            preparedStatement.setString(1, artistId);
            rs=preparedStatement.executeQuery();

            authorMostPopularSong = new ArrayList<>();


            while(rs.next()){
                authorMostPopularSong.add(GetSongQueries.getSongQuery(rs.getString("id"), "false"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return new AuthorForSearch(author, authorAlbumList, authorMostPopularSong);
    }
}
