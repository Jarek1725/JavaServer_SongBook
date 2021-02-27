package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.LastListened;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLastListened {
    public static LastListened getLastListened(String userId){

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        LastListened lastListened = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()){


                lastListened = new LastListened(
                        rs.getString("last_song_listen_id"),
                        rs.getString("last_song_listen_time")
                );

                System.out.println(rs.getString("last_song_listen_id"));

                return lastListened;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return lastListened;
    }
}
