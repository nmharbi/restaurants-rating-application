package com.example.swe483restruntproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText username,password;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = (EditText) findViewById(R.id.EditText_usernameInSgininPage);
        password = (EditText) findViewById(R.id.EditText_passwordInSgininPage);
        DB = new DBHelper(this);



        ConstraintLayout signin = findViewById(R.id.sginInButton);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                Boolean isvalid = true;
                if (user.equals("")) {
                    username.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isvalid = false;
                }
                if (pass.equals("")) {
                    password.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isvalid = false;
                }
                if (!isvalid) {
                    Toast.makeText(LoginPage.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

//                  check if username and password exists

//                if( "if username and password exists false"){
//                   Toast.makeText(LoginPage.this, "invalid username or password", Toast.LENGTH_SHORT).show();
//                   return;
//            }




//                Toast.makeText(LoginPage.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(view.getContext(), homePage.class);
//                intent.putExtra("username",user);
//                startActivity(intent);



//                    delete from here
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass ){
                        Toast.makeText(LoginPage.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(view.getContext(), homePage.class);
                         intent.putExtra("username",user);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginPage.this, "invalid username or password", Toast.LENGTH_SHORT).show();

                    }
//                    to here





            }
        });



        ConstraintLayout reg2 = findViewById(R.id.reviewsButton);
        reg2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }

        });


    }
}