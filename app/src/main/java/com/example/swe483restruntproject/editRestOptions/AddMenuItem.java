package com.example.swe483restruntproject.editRestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swe483restruntproject.DBHelper;
import com.example.swe483restruntproject.R;

public class AddMenuItem extends AppCompatActivity {
    EditText newName,price;
    ConstraintLayout addButton;
    String restname;
    TextView okresponse;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);

        newName = findViewById(R.id.new_item_name);
        price = findViewById(R.id.new_item_price);
        addButton = findViewById(R.id.add_new_item_Button);
        okresponse = findViewById(R.id.pathgffgf3);
        restname = getIntent().getStringExtra("restName");
        DB = new DBHelper(this);
        okresponse.setVisibility(View.INVISIBLE);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid=true;
                if(newName.getText().equals("")){
                    newName.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isValid=false;
                }
                if(newName.getText().equals("")){
                    newName.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isValid=false;
                }



                //add new item in  restname in DB///
                int restId = DB.getRestaurantId(restname);
                DB.insertMenuItem(newName.getText().toString(),price.getText().toString(),restId);
                okresponse.setVisibility(View.VISIBLE);

                newName.setText("");
                price.setText("");

            }
        });
        newName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                okresponse.setVisibility(View.INVISIBLE);
            }
        });


    }
}