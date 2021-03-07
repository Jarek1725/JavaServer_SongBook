package com.example.server.Entities;

import java.util.List;

public class SearchEverything {
    private List<Song> songs;
    private List<Author> authors;
    private List<EmptyLibrary> emptyLibraries;
    private List<Album> albumList;


    public SearchEverything(List<Song> songs, List<Author> authors, List<EmptyLibrary> emptyLibraries, List<Album> albumList) {
        this.songs = songs;
        this.authors = authors;
        this.emptyLibraries = emptyLibraries;
        this.albumList = albumList;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<EmptyLibrary> getEmptyLibraries() {
        return emptyLibraries;
    }

    public void setEmptyLibraries(List<EmptyLibrary> emptyLibraries) {
        this.emptyLibraries = emptyLibraries;
    }
}
