package com.example.swe483restruntproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.swe483restruntproject.editRestOptions.EditRest;
//import com.example.swe483restruntproject.homeFragments.location_fragmet;
import com.example.swe483restruntproject.homeFragments.restListFragment;
import com.example.swe483restruntproject.placeholder.restuntsHomePageListItemModel;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {
    String username;
    TextView nameLabel,editAddButtonText;
    EditText searchET;
    boolean hasRest;
    DBHelper DB;
    ConstraintLayout burgerSort,pizzaSort,bestRSort,editAddButton,locationSort,arabicSort;

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();

        //here we get all the restrunt  orderes by averege(rating)  asc and put in restlist above
        //use restlist.add(new restuntsHomePageListItemModel(Uri image,String name, String speiclty, double rating))
        //averege(rating)
        Cursor c = DB.getAllRestAverageASC();
        if(c!=null)
            while(c.moveToNext()){
                byte[]s=  c.getBlob(2);
                restlist.add(new restuntsHomePageListItemModel(BitmapFactory.decodeByteArray(s,0,s.length),c.getString(0), c.getString(1), c.getDouble(3)));

            }//change the null inside the loop

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView22, new restListFragment(restlist,username) ).commit();

        //search if user have restrunt linked to his name or no/////
        int userId = DB.getUserId(username);
        hasRest = DB.hasRest(userId);
        // hasRest = true if has rest;
        if(hasRest){
            editAddButtonText.setText("Edit you Restrunt  ");
            editAddButtonText.setCompoundDrawables(getDrawable(R.drawable.ic_baseline_edit_24),null,null,null);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        username = getIntent().getStringExtra("username");
        nameLabel =findViewById(R.id.name_label);
        nameLabel.setText("Hi "+username);
        searchET = findViewById(R.id.editTextSearchRest);
        burgerSort = findViewById(R.id.burgers_sort_button);
        pizzaSort = findViewById(R.id.pizza_sort_button);
        bestRSort = findViewById(R.id.best_rating_sort_button);
        locationSort= findViewById(R.id.location_sort);
        arabicSort = findViewById(R.id.arabic_sort_button);
        editAddButton = findViewById(R.id.add_edit_button_home_page);
        editAddButtonText = findViewById(R.id.add_edit_rest_text_home_page);
        DB = new DBHelper(this);

        //search if user have restrunt linked to his name or no/////
        int userId = DB.getUserId(username);
        hasRest = DB.hasRest(userId);
        // hasRest = true if has rest;
        if(hasRest){
            editAddButtonText.setText("Edit you Restrunt  ");
            editAddButtonText.setCompoundDrawables(getDrawable(R.drawable.ic_baseline_edit_24),null,null,null);
        }


        editAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(hasRest) {
                    intent = new Intent(homePage.this, EditRest.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }else {
                    intent = new Intent(homePage.this, AddRestrunt.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            }
        });


        ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();

        //here we get all the restrunt  orderes by averege(rating)  asc and put in restlist above
        //use restlist.add(new restuntsHomePageListItemModel(Uri image,String name, String speiclty, double rating))
        //averege(rating)
        Cursor c = DB.getAllRestAverageASC();

        if(c!=null)
        while(c.moveToNext()){
            byte[]s=  c.getBlob(2);
            restlist.add(new restuntsHomePageListItemModel(BitmapFactory.decodeByteArray(s,0,s.length),c.getString(0), c.getString(1), c.getDouble(3)));


        }//change the null inside the loop

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView22, new restListFragment(restlist,username) ).commit();


        arabicSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();

                //here we get all the restrunt with specilty Arabic ordered by price asc and put in restlist above
                //use restlist.add(new restuntsHomePageListItemModel(Uri image,String name, String speiclty, double rating))
                //averege(rating)
                Cursor c = DB.getAllRestArabian();
                if(c!=null)
                    while(c.moveToNext()){
                        byte[]s=  c.getBlob(2);
                        restlist.add(new restuntsHomePageListItemModel(BitmapFactory.decodeByteArray(s,0,s.length),c.getString(0), c.getString(1), c.getDouble(3)));

                    }


                Fragment s = new restListFragment(restlist,username);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView22, s).commit();
            }
        });
        burgerSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();
                //here we get all the restrunt with specilty burger ordered by price asc and put in restlist above
                //use restlist.add(new restuntsHomePageListItemModel(Uri image,String name, String speiclty, double rating))
                //averege(rating)
                Cursor c = DB.getAllRestBurgers();
                if(c!=null)
                    while(c.moveToNext()){
                        byte[]s=  c.getBlob(2);
                        restlist.add(new restuntsHomePageListItemModel(BitmapFactory.decodeByteArray(s,0,s.length),c.getString(0), c.getString(1), c.getDouble(3)));

                    }

                Fragment s = new restListFragment(restlist,username);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView22, s).commit();
            }
        });
        pizzaSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();

                //here we get all the restrunt with specilty pizza ordered by price asc and put in restlist above
                //use restlist.add(new restuntsHomePageListItemModel(Uri image,String name, String speiclty, double rating))
                //averege(rating)
                Cursor c = DB.getAllRestPizza();
                if(c!=null)
                while(c.moveToNext()){
                    byte[]s=  c.getBlob(2);
                    restlist.add(new restuntsHomePageListItemModel(BitmapFactory.decodeByteArray(s,0,s.length),c.getString(0), c.getString(1), c.getDouble(3)));

                }


                Fragment s = new restListFragment(restlist,username);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView22, s).commit();
            }
        });
        bestRSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();

                //here we get all the restrunt  orderes by averege(rating)  asc and put in restlist above
                //use restlist.add(new restuntsHomePageListItemModel(Bitmap image,String name, String speiclty, double rating))
                //averege(rating)
                Cursor c = DB.getAllRestAverageASC();
                if(c!=null)
                while(c.moveToNext()){
                    byte[]s=  c.getBlob(2);
                    restlist.add(new restuntsHomePageListItemModel(BitmapFactory.decodeByteArray(s,0,s.length),c.getString(0), c.getString(1), c.getDouble(3)));

                }


                Fragment s = new restListFragment(restlist,username);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView22, s).commit();
            }
        });

        locationSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();

                //leave


//                Fragment s = new location_fragmet();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, s).commit();
            }
        });

        searchET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
              if (hasFocus)
              return;

                String searchKeyWord = searchET.getText().toString();

                ArrayList<restuntsHomePageListItemModel> restlist = new ArrayList<>();
                Log.e("sssss","s");
                //here we get all the restrunt with searchKeyWord%  name and put in restlist above
                //use restlist.add(new restuntsHomePageListItemModel(Uri image,String name, String speiclty, double rating))
                //averege(rating)
                Cursor c = DB.getRestByName(searchKeyWord);
                if(c!=null)
                while(c.moveToNext()){
                    byte[]s=  c.getBlob(2);
                    restlist.add(new restuntsHomePageListItemModel(BitmapFactory.decodeByteArray(s,0,s.length),c.getString(0), c.getString(1), c.getDouble(3)));

                }


                Fragment s = new restListFragment(restlist,username);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView22, s).commit();

            }

        });


    }



}