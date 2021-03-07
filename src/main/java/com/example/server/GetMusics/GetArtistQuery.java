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

public class GetArtistQuery {

    public static List<Author> getArtist(String artistId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Author> authorList = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM songs WHERE songs.id = ?");
            preparedStatement.setString(1, artistId);

            ResultSet rs = preparedStatement.executeQuery();

            authorList = new ArrayList<>();

            String fName = null;
            String lName = null;
            String pseudonym = null;

            while (rs.next()){
                fName = rs.getString("First_name");
                lName = rs.getString("Last_name");
                pseudonym = rs.getString("Pseudonym");


//                NIE SKOńCZONE !!!
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return authorList;
    }
}
