package com.example.swe483restruntproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, conformPassword;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.EditText_usernameInRegisterPage);
        password = findViewById(R.id.EditText_passwordInRegisterPage);
        conformPassword = findViewById(R.id.EditText_conformPasswordInRegisterPage);

        DB = new DBHelper(this);

        ConstraintLayout reg = findViewById(R.id.upload_logo);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setBackground(getDrawable(R.drawable.custom_login_inputs));
                password.setBackground(getDrawable(R.drawable.custom_login_inputs));
                conformPassword.setBackground(getDrawable(R.drawable.custom_login_inputs));

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String conPass = conformPassword.getText().toString();
                boolean isvalid = true;
                if (user.equals("")) {
                    username.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                     isvalid = false;

                }
                if (pass.equals("")) {
                    password.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isvalid = false;

                }
                if (conPass.equals("")) {
                    conformPassword.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isvalid = false;

                }
                if(!isvalid)
                    return;
                if (!pass.equals(conPass)) {
                    conformPassword.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    Toast.makeText(MainActivity.this, "passwords doesn't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                //cheack if username exists

//                if("if username exist true"){
//                   username.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
//                   Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
//                   return;
//                }



                // upload username and password


//                Toast.makeText(MainActivity.this, "Registed successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(view.getContext(), LoginPage.class);
//                startActivity(intent);




                //delete from here
                Boolean checkuser = DB.checkusername(user);
                if (!checkuser) {
                    boolean insert = DB.insertUser(user, pass,0);
                    if (insert) {
                        Toast.makeText(MainActivity.this, "Registed successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), LoginPage.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(MainActivity.this, "Registration faild", Toast.LENGTH_SHORT).show();
                } else
                    username.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                //to here

            }
        });

        Button signin1 = findViewById(R.id.button_signinInRegisterPage);
        signin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginPage.class);
                startActivity(intent);
            }
        });
    }
}