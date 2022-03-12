package com.example.swe483restruntproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.swe483restruntproject.decFragments.BlankFragment;
import com.example.swe483restruntproject.decFragments.rest_food_menu;
import com.example.swe483restruntproject.decFragments.rest_photos_Fragment;
import com.example.swe483restruntproject.placeholder.restuntsHomePageListItemModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationBarView;

public class RestruntDescrption extends AppCompatActivity {
    ConstraintLayout reviewsButton;
    BottomNavigationView bottomNavigationView;
    String restName;
    ShapeableImageView shapeableImageView;
    TextView desc,title;
    DBHelper Db;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrunt_descrption);
        restName = getIntent().getStringExtra("restName");
        username = getIntent().getStringExtra("username");
        reviewsButton = findViewById(R.id.reviewsButton);
        bottomNavigationView = findViewById(R.id.nav_bar);
        bottomNavigationView = findViewById(R.id.nav_bar);
        Db = new DBHelper(RestruntDescrption.this);

        shapeableImageView = findViewById(R.id.logoImageInDesscrption);
        desc = findViewById(R.id.decrptionTextViewInDescPage);
        title = findViewById(R.id.restaurantNameInDesPage);
        title.setText(restName);


        // get image and descreption for the restName and put below//////
        Cursor c = Db.getRestauran(restName);
        while(c.moveToNext()) {


            byte[] s= c.getBlob(4);


            shapeableImageView.setImageBitmap(BitmapFactory.decodeByteArray(s,0,s.length));
            desc.setText(c.getString(2));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new rest_food_menu(restName)).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment s = null;
                switch (item.getItemId()) {
                    case R.id.rest_food_menu:
                        s = new rest_food_menu(restName);
                        break;
                    case R.id.rest_photos_Fragment:
                        s = new rest_photos_Fragment(restName);

                        break;
                    case R.id.rest_location_Fragment:
                        s = new BlankFragment();

                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, s).commit();
                return true;
            }
        });
        reviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RestruntDescrption.this, reviews.class);
                intent.putExtra("restName",restName);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}