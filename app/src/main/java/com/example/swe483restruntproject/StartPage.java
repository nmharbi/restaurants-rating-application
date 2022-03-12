package com.example.swe483restruntproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        ConstraintLayout sginin = findViewById(R.id.sginInButtonInHomePage);
        ConstraintLayout sginup = findViewById(R.id.sginUpButtonInHomePage);

        sginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPage.this,LoginPage.class));
            }
        });




        sginup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartPage.this,MainActivity.class));

            }
        });
    }
}