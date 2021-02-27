package com.example.server.Entities;

public class Album_Song {
    private String songId;
    private String songIndexInAlbum;

    public Album_Song(String songId, String songIndexInAlbum) {
        this.songId = songId;
        this.songIndexInAlbum = songIndexInAlbum;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongIndexInAlbum() {
        return songIndexInAlbum;
    }

    public void setSongIndexInAlbum(String songIndexInAlbum) {
        this.songIndexInAlbum = songIndexInAlbum;
    }
}
