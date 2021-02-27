package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.Album_Song;
import com.example.server.Entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetSongAlbum {
    public static List<Album_Song> getSongAlbum(String songAlbum){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Album_Song> songs = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM songs WHERE Album = ? ORDER BY `songs`.`index_in_album`");
            preparedStatement.setString(1, songAlbum);

            ResultSet rs = preparedStatement.executeQuery();

            songs = new ArrayList<>();

            while(rs.next()){
                String songId = rs.getString("id");
                String songIndexInAlbum = rs.getString("index_in_album");

                songs.add(new Album_Song(songId, songIndexInAlbum));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return songs;
    }
}
