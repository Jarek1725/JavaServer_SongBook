package com.example.server.GetMusics;

import com.example.server.Entities.Album_Song;

import java.util.Collections;
import java.util.List;

public class GetNextSong {
    public static String getNextSong(String songId, String albumId, String nextOrPrev, String isRandom){

        List<Album_Song>songs = GetSongAlbum.getSongAlbum(albumId);



        int songIdInt = Integer.parseInt(songId);
        int songIdInAlbum = 0;


        if(isRandom.equals("true")){
            int random = (int) ((Math.random())*songs.size()-1);
            if(!songs.get(random).getSongIndexInAlbum().equals(String.valueOf(random))){
                return songs.get(random).getSongId();
            }
        }

        if(nextOrPrev.equals("next")) {
            if(songIdInt>=Integer.parseInt(songs.get(songs.size()-1).getSongIndexInAlbum())){
                return songs.get(0).getSongId();
            }
            for (Album_Song song : songs) {
                songIdInAlbum = Integer.parseInt(song.getSongIndexInAlbum());
                if(songIdInAlbum>songIdInt){
                    return song.getSongId();
                }
            }
        }else{
            Collections.reverse(songs);
            for (Album_Song song : songs) {
                songIdInAlbum = Integer.parseInt(song.getSongIndexInAlbum());
                if(songIdInAlbum<songIdInt){
                    return song.getSongId();
                }
            }
        }

        return songs.get(0).getSongId();
    }
}
