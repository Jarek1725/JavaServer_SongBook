package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchSongOnly {
    public static List<Song> searchOnlySong(String searchText){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Song> songList = new ArrayList<>();

        try {
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM `songs` WHERE title LIKE ? ORDER BY length(songs.popularity) DESC, popularity DESC LIMIT 4");
            preparedStatement.setString(1, "%" + searchText + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                songList.add(GetSongQueries.getSongQuery(rs.getString("id"), "false"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return songList;
    }
}

