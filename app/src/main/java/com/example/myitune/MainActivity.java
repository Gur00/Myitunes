package com.example.myitune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBasehelper db;
    TextView username, password;
    EditText nameinput;
    EditText passwordinput;
    Button login, signup;

    String entername, enterpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.nametext);
        password = findViewById(R.id.passwordtext);
        nameinput = findViewById(R.id.entername);
        passwordinput = findViewById(R.id.enterpass);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        db = new DataBasehelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entername = nameinput.getText().toString();
                enterpass = passwordinput.getText().toString();

                boolean found = db.fetchuser(entername, enterpass);
                if(found)
                {
                    Intent intent = new Intent(getApplicationContext(), Homepage.class);
                    intent.putExtra("username", entername);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Incorrect user name or password!", Toast.LENGTH_LONG).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), signup.class);
                startActivity(intent1);
                finish();

            }
        });


    }
}