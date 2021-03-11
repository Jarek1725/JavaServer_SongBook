package com.example.server.Entities;

import java.util.List;

public class FullPlaylist {
    private String name;
    private User creator_id;
    private String followers;
    private List<Song> songList;

    public FullPlaylist(String name, User creator_id, String followers, List<Song> songList) {
        this.name = name;
        this.creator_id = creator_id;
        this.followers = followers;
        this.songList = songList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(User creator_id) {
        this.creator_id = creator_id;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }
}
