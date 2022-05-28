package com.example.myitune;

public class User {
    String fullame;
    String username;
    String password;
    int uid;

    public String getFullame() {
        return fullame;
    }

    public void setFullame(String fullame) {
        this.fullame = fullame;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public User(String fullame, String username, String password) {
        this.fullame = fullame;
        this.username = username;
        this.password = password;
        this.uid = uid;
    }

    public int getu_id()
    {
        return uid;
    }





}
