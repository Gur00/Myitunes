package com.example.myitune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Homepage extends YouTubeBaseActivity {

    Button play, addplaylist, mylist;
    EditText urltext;
    TextView textyoutube;
    DataBasehelper db;
    String username;
    Playlist playlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData myclip;

        play = findViewById(R.id.play);
        addplaylist = findViewById(R.id.addbutton);
        mylist = findViewById(R.id.myplaylist);
        urltext = findViewById(R.id.url);
        textyoutube = findViewById(R.id.textyou_tube);

        db = new DataBasehelper(this);



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url;
                url = urltext.getText().toString();
                ClipData myclip = ClipData.newPlainText("text", url);
                clipboard.setPrimaryClip(myclip);

                Intent intent = new Intent(getApplicationContext(), videoplay.class);
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });
        addplaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlist = new Playlist(username,urltext.getText().toString());
                db.insertplaylist(playlist);
                Toast.makeText(Homepage.this, "Add Successfully to the Playlist", Toast.LENGTH_LONG).show();

            }
        });
        mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Myplaylist.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
            }
        });




    }
}