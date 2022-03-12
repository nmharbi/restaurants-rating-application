package com.example.swe483restruntproject.editRestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.swe483restruntproject.DBHelper;
import com.example.swe483restruntproject.R;

public class EditRest extends AppCompatActivity {
    String restName;
    String username;
    ConstraintLayout nameButton, descButton, logoButton, addItemButton, addPhotoButton, LocationButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rest);
        DB = new DBHelper(this);

        username = getIntent().getStringExtra("username");
        //get the username's restrunt name and put in restname
        restName = DB.getRestNameFromUser(username);

        nameButton = findViewById(R.id.update_rest_name_button);
        descButton = findViewById(R.id.update_rest_desc_button);
        logoButton = findViewById(R.id.update_rest_logo_button);
        addItemButton = findViewById(R.id.add_new_item_button);
        addPhotoButton = findViewById(R.id.add_new_photo_button);
        LocationButton = findViewById(R.id.update_rest_location_button);

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditRest.this,editRestName.class);
                intent.putExtra("restName", restName);
                startActivity(intent);
            }
        });



        descButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditRest.this,editRestDec.class );
                intent.putExtra("restName", restName);
                startActivity(intent);
            }
        });



        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditRest.this, editRestLogo.class);
                intent.putExtra("restName", restName);
                startActivity(intent);
            }
        });



        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditRest.this, AddMenuItem.class);
                intent.putExtra("restName", restName);
                startActivity(intent);
            }
        });


        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditRest.this,addPhotos.class );
                intent.putExtra("restName", restName);
                startActivity(intent);
            }
        });


//
//        LocationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(EditRest.this, );
//                intent.putExtra("restName", restName);
//                startActivity(intent);
//            }
//        });


    }
}