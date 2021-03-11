package com.example.server.Entities;

import java.util.List;

public class AuthorForSearch {
    private Author author;
    private List<Album> albumList;
    private List<Song> songList;

    public AuthorForSearch(Author author, List<Album> albumList, List<Song> songList) {
        this.author = author;
        this.albumList = albumList;
        this.songList = songList;
    }
}
