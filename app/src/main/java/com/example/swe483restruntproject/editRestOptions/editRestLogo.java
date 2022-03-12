package com.example.swe483restruntproject.editRestOptions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.swe483restruntproject.DBHelper;
import com.example.swe483restruntproject.R;

import java.io.IOException;

public class editRestLogo extends AppCompatActivity {
    TextView responseText;
    ConstraintLayout upload,update;
    Uri imagepath;
    Bitmap imageinBitmap;
    DBHelper DB;
    String restname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rest_logo);
        responseText = findViewById(R.id.responseText);
        upload = findViewById(R.id.upload_logo);
        update = findViewById(R.id.update_logo);
        DB = new DBHelper(this);
        restname = getIntent().getStringExtra("restName");

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(intent, 1);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseText.setText("");
                if (imagepath== null){
                    responseText.setText("you have to upload a logo");
                    responseText.setTextColor(getColor(R.color.red));
                return;
                }
                Log.e("logo",restname);
                DB.updateLogo(imageinBitmap,restname);




                responseText.setText("updated!");
                responseText.setTextColor(getColor(R.color.green));


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagepath = data.getData();
            try {
                imageinBitmap=  MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            responseText.setText("Path: " + imagepath.toString());
            responseText.setTextColor(getColor(R.color.black));


        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}