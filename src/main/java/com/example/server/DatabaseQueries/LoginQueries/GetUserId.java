package com.example.server.DatabaseQueries.LoginQueries;

import com.example.server.DatabaseConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserId {
    public static String getUserId(String login){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String userId = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE login = ?");
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                userId = rs.getString("id");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return userId;
    }
}
