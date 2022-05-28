package com.example.myitune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    DataBasehelper db;
    EditText fullnameenter, usernameenter, passwordenter, confirmpasswordenter;
    Button createbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullnameenter = findViewById(R.id.nameinput);
        usernameenter = findViewById(R.id.userinput);
        passwordenter = findViewById(R.id.passinput);
        confirmpasswordenter = findViewById(R.id.confirmpassword);
        createbutton = findViewById(R.id.createaccount);

        db = new DataBasehelper(this);



        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, username, password, condfirmpassword;

                fullname = fullnameenter.getText().toString();
                username = usernameenter.getText().toString();
                password = passwordenter.getText().toString();
                condfirmpassword = confirmpasswordenter.getText().toString();

                if(condfirmpassword.equals(password))
                {
                    User user = new User(fullname, username, password);
                    long insert = db.insertuser(user);

                    if(insert > 0)
                    {
                        Toast.makeText(signup.this, "Successfully  created Account!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(signup.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(signup.this, "Unsuccessful!", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(signup.this, "Passwords are incorrect", Toast.LENGTH_LONG).show();
                }




            }
        });
    }
}