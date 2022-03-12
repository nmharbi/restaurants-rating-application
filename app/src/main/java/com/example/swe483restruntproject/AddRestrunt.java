package com.example.swe483restruntproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;

public class AddRestrunt extends AppCompatActivity {
    EditText name, des;
    ConstraintLayout uploadButton, addButton;
    Uri imagepath;
    TextView pathText;
    String username;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String specitly;
    DBHelper DB;
    Bitmap imageinBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restrunt);
        specitly = "Arabic";
        username = getIntent().getStringExtra("username");
        name = findViewById(R.id.editText_restnameInAddPage);
        des = findViewById(R.id.editText_desInAddPage);
        uploadButton = findViewById(R.id.upload_logo);
        addButton = findViewById(R.id.add_restruntButton);
        DB = new DBHelper(this);
        pathText = findViewById(R.id.added_label);
        radioGroup = findViewById(R.id.radioGroup);

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    return;

                //here check if name.getText().toString() in data base/////
                boolean exist = DB.checkIfRestExist(name.getText().toString());

                if(exist){
                    name.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    return;
                }else
                    name.setBackground(getDrawable(R.drawable.custom_login_inputs));
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(intent, 1);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathText.setText("");


                boolean isvalid = true;
                if (name.getText().toString().equals("")) {
                    name.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isvalid = false;
                }

                if (des.getText().toString().equals("")) {
                    des.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isvalid = false;
                }

                if (imagepath == null) {
                    pathText.setText("you have to upload a logo");
                    pathText.setTextColor(getColor(R.color.red));
                    isvalid = false;
                }

                if (!isvalid)
                    return;

                //copy paste of the above or make a method/////
                boolean exist = DB.checkIfRestExist(name.getText().toString());

                //here check if name.getText().toString() in data base


                if(exist){
                    name.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    return;
                }




                //upload name,dec, specitly and imagepath values with username
                // specitly is string
                DB.insertRestaurants(name.getText().toString(),des.getText().toString(),specitly,imageinBitmap,null,null);
                int userID = DB.getUserId(username);
                int restId = DB.getRestaurantId(name.getText().toString());
                DB.makeAdmin(userID,restId);

                pathText.setText("");
                des.setText("");
                name.setText("");
                pathText.setVisibility(View.VISIBLE);
                pathText.setText("Added!");
                pathText.setTextColor(getColor(R.color.green));

            }



        });
    }

    public void checkButton(View v){

        int id = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(id);
        specitly = radioButton.getText().toString();
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
            pathText.setText("Path: " + imagepath.toString());
            pathText.setTextColor(getColor(R.color.black));


        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}