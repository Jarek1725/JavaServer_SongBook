package com.example.server.GetLibraries;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.Author;
import com.example.server.Entities.EmptyLibrary;
import com.example.server.Entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUserLibraries {

    public static List<EmptyLibrary> getAllUserLibraries(String userId) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<EmptyLibrary> playlists = null;

        try {
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT playlist.id, playlist.playlist_name FROM `user_playlist` INNER JOIN playlist ON playlist.id = user_playlist.playlist_id WHERE user_id = ?");
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            playlists = new ArrayList<>();

            while (rs.next()) {
                playlists.add(new EmptyLibrary(rs.getString("id"), rs.getString("playlist_name")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }

        return playlists;
    }

}
