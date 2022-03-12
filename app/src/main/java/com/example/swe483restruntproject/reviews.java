package com.example.swe483restruntproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.swe483restruntproject.adapters.ListAdapterForCommentsAndRatings;
import com.example.swe483restruntproject.placeholder.CommentHolder;

import java.util.ArrayList;

public class reviews extends AppCompatActivity {
    String username;
    EditText comment;
    EditText Rating;
    Button share;
    ListView listView;
    DBHelper Db;
    String restName;
    ArrayList<CommentHolder> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        username = getIntent().getStringExtra("username");
        comment = findViewById(R.id.EditText_Comment);
        Rating = findViewById(R.id.EditText_Rate);
        listView = findViewById(R.id.list);
        share = findViewById(R.id.button_share);
        Db = new DBHelper(reviews.this);
        restName= getIntent().getStringExtra("restName");
        arrayList =new ArrayList<>();
        //get comments,ratings,usernames and put in the arrayList above/////
        //use new CommentHolder(String comment, double rate, String user)
        int restaurantId =Db.getRestaurantId(restName);
        Cursor c =Db.getComments(restaurantId);
        while(c.moveToNext()){
            arrayList.add(new CommentHolder(c.getString(2),c.getInt(1), Db.getName(c.getInt(4))));
        }
        ListAdapterForCommentsAndRatings adapter = new ListAdapterForCommentsAndRatings(arrayList,reviews.this);
        listView.setAdapter(adapter);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment.setBackground(getDrawable(R.drawable.custom_login_inputs));
                Rating.setBackground(getDrawable(R.drawable.custom_login_inputs));

                String commentStr = comment.getText().toString();
                String rateString = Rating.getText().toString();
                boolean isValid= true;
                if (commentStr.equals("")){
                    Toast.makeText(reviews.this, "please write your comment", Toast.LENGTH_SHORT).show();
                    comment.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isValid = false;
                }

                if(rateString.equals("")) {
                    Toast.makeText(reviews.this, "please write your rate", Toast.LENGTH_SHORT).show();
                    Rating.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    isValid = false;

                }
                if(!isValid)
                    return;

                double r = Double.parseDouble(rateString);
                if (r > 5 || r < 1) {
                    Toast.makeText(reviews.this, "Rating should be from 1 to 5", Toast.LENGTH_SHORT).show();
                    Rating.setBackground(getDrawable(R.drawable.custome_wrong_inputs));
                    return;
                }
                Toast.makeText(reviews.this, "Thank you", Toast.LENGTH_SHORT).show();
                //insert rate and comment and username//////
                int userId =Db.getUserId(username);
                int restaurantId =Db.getRestaurantId(restName);
                Db.insertRatings(r,commentStr,restaurantId,userId);
                AlertDialog.Builder dialog = new AlertDialog.Builder(reviews.this)
                        .setTitle("Success")
                        .setMessage("Thanks for your comment")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                arrayList =new ArrayList<>();
                                //get comments,ratings,usernames and put in the arrayList above/////
                                //use new CommentHolder(String comment, double rate, String user)
                                int restaurantId =Db.getRestaurantId(restName);
                                Cursor c =Db.getComments(restaurantId);
                                while(c.moveToNext()){
                                    arrayList.add(new CommentHolder(c.getString(2),c.getInt(1), Db.getName(c.getInt(4))));
                                }
                                ListAdapterForCommentsAndRatings adapter = new ListAdapterForCommentsAndRatings(arrayList, reviews.this);
                                listView.setAdapter(adapter);
                            }
                        });
                dialog.show();

            }

        });





    }


}