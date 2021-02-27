package com.example.server.Entities;

import java.util.List;

public class Song {
    private String songId;
    private String songTitle;
    private String songAlbum;
    private String songIndexInAlbum;
    private String songSource;
    private List<Author> songAutor;
    private String albumPhoto;
    private String albumName;


    public Song(String songId, String songTitle, String songAlbum, String songIndexInAlbum, String songSource, List<Author> songAutor, String albumPhoto, String albumName) {
        this.songId = songId;
        this.songTitle = songTitle;
        this.songAlbum = songAlbum;
        this.songIndexInAlbum = songIndexInAlbum;
        this.songSource = songSource;
        this.songAutor = songAutor;
        this.albumPhoto = albumPhoto;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumPhoto() {
        return albumPhoto;
    }

    public void setAlbumPhoto(String albumPhoto) {
        this.albumPhoto = albumPhoto;
    }

    public List<Author> getSongAutor() {
        return songAutor;
    }

    public void setSongAutor(List<Author> songAutor) {
        this.songAutor = songAutor;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongIndexInAlbum() {
        return songIndexInAlbum;
    }

    public void setSongIndexInAlbum(String songIndexInAlbum) {
        this.songIndexInAlbum = songIndexInAlbum;
    }

    public String getSongSource() {
        return songSource;
    }

    public void setSongSource(String songSource) {
        this.songSource = songSource;
    }


    @Override
    public String toString() {
        return "Song{" +
                "songId='" + songId + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", songAlbum='" + songAlbum + '\'' +
                ", songIndexInAlbum='" + songIndexInAlbum + '\'' +
                ", songSource='" + songSource + '\'' +
                '}';
    }
}
