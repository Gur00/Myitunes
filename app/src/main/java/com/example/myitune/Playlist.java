package com.example.myitune;

public class Playlist {
    private int playlistID;
    private String username, url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Playlist(String username, String url) {
        this.username = username;
        this.url = url;
    }
    public Playlist(String url)
    {
        this.url = url;
    }

    public int getPlaylistID()
    {
        return playlistID;
    }
}
