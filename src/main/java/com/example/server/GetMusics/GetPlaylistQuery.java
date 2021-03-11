package com.example.server.GetMusics;

import com.example.server.DatabaseConnectionHelper;
import com.example.server.Entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetPlaylistQuery {
    public static FullPlaylist getPlaylist(String playlistId){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        FullPlaylist fullPlaylist = null;
        List<Song> songList = new ArrayList<>();

        try{
            conn = DatabaseConnectionHelper.getConnection();
            preparedStatement = conn.prepareStatement("SELECT *, users.id as UserId FROM `playlist` INNER JOIN users ON users.id = playlist.creator_id WHERE playlist.id = ?");
            preparedStatement.setString(1, playlistId);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()) {
                User user = new User(rs.getString("UserId"), rs.getString("login"));
                fullPlaylist = new FullPlaylist(rs.getString("playlist_name"), user, rs.getString("popularity"), null);
            }

            preparedStatement = conn.prepareStatement("SELECT songs.id as SongId FROM `songs` INNER JOIN song_playlist ON songs.id = song_playlist.song_id WHERE song_playlist.playlist_id=?");
            preparedStatement.setString(1, playlistId);
            rs=preparedStatement.executeQuery();


            while(rs.next()){
                songList.add(GetSongQueries.getSongQuery(rs.getString("SongId"), "false"));
            }

            fullPlaylist.setSongList(songList);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnectionHelper.close(conn);
        }
        return fullPlaylist;
    }
}
