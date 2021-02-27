package com.example.server.Entities;

public class LastListened {
    private String songId;
    private String songTime;

    public LastListened(String songId, String songTime) {
        this.songId = songId;
        this.songTime = songTime;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongTime() {
        return songTime;
    }

    public void setSongTime(String songTime) {
        this.songTime = songTime;
    }
}
