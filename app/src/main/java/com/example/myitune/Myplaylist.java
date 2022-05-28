package com.example.myitune;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Myplaylist extends AppCompatActivity {

    RecyclerView playlistview;
    DataBasehelper db;
    List<Playlist> playlist = new ArrayList<>();
    List<Playlist> temp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplaylist);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        db = new DataBasehelper(this);
        temp = db.fetchplaylist(username);
        playlistview = findViewById(R.id.listview);

        for(int i=0; i<temp.size(); i++)
        {
            Playlist list = new Playlist(temp.get(i).getUrl());
            playlist.add(list);
        }

        PlaylistAdapter playlistAdapter = new PlaylistAdapter(Myplaylist.this, playlist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        playlistview.setLayoutManager(layoutManager);
        playlistview.setAdapter(playlistAdapter);

    }
}