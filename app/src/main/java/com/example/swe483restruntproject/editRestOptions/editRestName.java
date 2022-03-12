package com.example.swe483restruntproject.editRestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swe483restruntproject.DBHelper;
import com.example.swe483restruntproject.R;

public class editRestName extends AppCompatActivity {
        EditText newRestName;
        TextView okresponse;
        ConstraintLayout updateButton;
        String restname;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rest_name);
        DB = new DBHelper(this);

        newRestName = findViewById(R.id.editText_restnameInEditRestName);
        updateButton = findViewById(R.id.updateNamebuttonInEditName);
        okresponse = findViewById(R.id.textView21);

        restname = getIntent().getStringExtra("restName");


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newRestName.getText().equals("")){
                    newRestName.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                return;
                }
                ////check if new name already exist
                boolean checkIfNameExists = DB.checkRestaurantNameExist(newRestName.getText().toString());
                if(checkIfNameExists){
                    newRestName.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    return;
                }


                ////update restname in DB with the new name
                DB.updateRestaurantNameExist(restname,newRestName.getText().toString());
                okresponse.setVisibility(View.VISIBLE);

                okresponse.setVisibility(View.VISIBLE);
                newRestName.setText("");
                newRestName.setBackground(getDrawable(R.drawable.custom_login_inputs));


            }
        });
        newRestName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                okresponse.setVisibility(View.INVISIBLE);
            }
        });

    }


}