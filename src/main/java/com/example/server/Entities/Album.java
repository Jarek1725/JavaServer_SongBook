package com.example.server.Entities;

import java.util.List;

public class Album {
    private String albumId;
    private String albumName;
    private String albumPhoto;
    private List<Author> authorList;


    public Album(String albumId, String albumName, String albumPhoto) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumPhoto = albumPhoto;
    }


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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
}
