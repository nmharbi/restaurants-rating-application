package com.example.swe483restruntproject.editRestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swe483restruntproject.DBHelper;
import com.example.swe483restruntproject.R;

public class editRestDec extends AppCompatActivity {
     EditText newdecName;
    TextView okresponse;
    ConstraintLayout updateButton;
    String restname;
    DBHelper Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rest_dec);
        newdecName = findViewById(R.id.editText_desInEditDec);
        updateButton = findViewById(R.id.update_dec_editDecPage);
        okresponse = findViewById(R.id.okresponedec);
        Db = new DBHelper(editRestDec.this);

        restname = getIntent().getStringExtra("restName");


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newdecName.getText().equals("")){
                    newdecName.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    okresponse.setVisibility(View.INVISIBLE);
                    return;
                }


                //update the restname decrption in DB with the new name
                Db.updateDec(newdecName.getText().toString(),restname);


                okresponse.setVisibility(View.VISIBLE);
                newdecName.setText("");
            }
        });
        newdecName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                okresponse.setVisibility(View.INVISIBLE);
            }
        });


    }
}