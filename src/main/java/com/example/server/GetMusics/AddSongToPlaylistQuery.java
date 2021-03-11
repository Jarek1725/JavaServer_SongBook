package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddSongToPlaylistQuery {
    public static void ddSongToPlaylistQuery(String playlistId, String userId, String songId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT * FROM playlist WHERE playlist.id = ?");
            preparedStatement.setString(1, playlistId);

            ResultSet rs = preparedStatement.executeQuery();

            boolean isCreator = false;

            while (rs.next()) {
                String CreatorId = rs.getString("creator_id");
                if (CreatorId.equals(userId)){
                    isCreator = true;
                }
            }

            if (isCreator){
                preparedStatement = conn.prepareStatement("INSERT INTO `song_playlist` (`id`, `song_id`, `playlist_id`) VALUES (NULL, ?, ?);");
                preparedStatement.setString(1, songId);
                preparedStatement.setString(2, playlistId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
    }
}
