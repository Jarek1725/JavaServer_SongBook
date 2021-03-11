package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowPlaylistQuery {
    public static void followPlaylist(String playlistId, String userId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();

            preparedStatement = conn.prepareStatement("SELECT * FROM user_playlist WHERE user_id = ? AND playlist_id = ?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, playlistId);

            ResultSet rs = preparedStatement.executeQuery();

            boolean isInDatabase = false;

            while (rs.next()){
                isInDatabase = true;
            }

            if(!isInDatabase){
                preparedStatement = conn.prepareStatement("INSERT INTO `user_playlist` (`id`, `user_id`, `playlist_id`) VALUES (NULL, ?,?);");
            }else{
                preparedStatement = conn.prepareStatement("DELETE FROM `user_playlist` WHERE `user_playlist`.`user_id`=? AND playlist_id = ?");
            }

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, playlistId);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
