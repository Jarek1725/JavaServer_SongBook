package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HandleSearchQuery {
    public static SearchEverything handleSearchQuery(String searchText){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        List<Song> songList = null;
        List<Author> authorList = null;
        List<EmptyLibrary> emptyLibraryList = null;
        List<Album> albumList = null;
        SearchEverything searchEverything = null;

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM `songs` WHERE title LIKE ? ORDER BY length(songs.popularity) DESC, popularity DESC LIMIT 8");
            preparedStatement.setString(1, "%"+searchText + "%");

            ResultSet rs = preparedStatement.executeQuery();

            songList = new ArrayList<>();
            authorList = new ArrayList<>();
            emptyLibraryList = new ArrayList<>();
            albumList = new ArrayList<>();

            while (rs.next()){
                songList.add(GetSongQueries.getSongQuery(rs.getString("id"), "false"));
            }

            preparedStatement = conn.prepareStatement("SELECT * FROM artists WHERE artists.Pseudonym LIKE ? ORDER BY length(popularity) DESC, popularity DESC LIMIT 8");
            preparedStatement.setString(1, "%"+searchText + "%");

            rs = preparedStatement.executeQuery();


            while (rs.next()){
                authorList.add(new Author(rs.getString("id"), rs.getString("First_name"), rs.getString("Last_name"), rs.getString("Pseudonym"), rs.getString("profile_photo")));
            }

            preparedStatement = conn.prepareStatement("SELECT * FROM playlist WHERE playlist_name LIKE ? ORDER BY length(popularity) DESC, popularity DESC LIMIT 8");
            preparedStatement.setString(1, "%"+searchText + "%");

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                emptyLibraryList.add(new EmptyLibrary(rs.getString("id"), rs.getString("playlist_name")));
            }

            preparedStatement = conn.prepareStatement("SELECT * FROM albums WHERE Album_name LIKE ? ORDER BY length(popularity) DESC, popularity DESC LIMIT 8");
            preparedStatement.setString(1, "%"+searchText + "%");

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                albumList.add(new Album(rs.getString("id"), rs.getString("Album_name"), rs.getString("Album_photo")));
            }

            searchEverything = new SearchEverything(songList, authorList, emptyLibraryList, albumList);


            System.out.println(preparedStatement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }

        return searchEverything;
    }
}
