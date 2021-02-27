package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.LastListened;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetLastListened {
    public static void setLastListened(String userId, String songId, String time){

        System.out.println(time+" - Time");

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        LastListened lastListened = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("UPDATE `users` SET `last_song_listen_id` = ?, `last_song_listen_time` = ? WHERE `users`.`id` = ?;");
            preparedStatement.setString(1, songId);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, userId);

            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
