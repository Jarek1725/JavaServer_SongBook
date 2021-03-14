package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatePlaylistQuery {
    public static void createPlaylist(String userId, String playlistName){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();

            preparedStatement = conn.prepareStatement("INSERT INTO `playlist` (`id`, `playlist_name`, `creator_id`, `popularity`) VALUES (NULL, ?, ?, '0')");
            preparedStatement.setString(1, playlistName);
            preparedStatement.setString(2, userId);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
