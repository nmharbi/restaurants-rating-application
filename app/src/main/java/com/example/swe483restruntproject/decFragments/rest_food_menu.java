package com.example.swe483restruntproject.decFragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swe483restruntproject.DBHelper;
import com.example.swe483restruntproject.R;
import com.example.swe483restruntproject.adapters.MyItemRecyclerViewAdapter;
import com.example.swe483restruntproject.placeholder.menuModel;

import java.util.ArrayList;


public class rest_food_menu extends Fragment {
    String restName;
    DBHelper Db;

    public rest_food_menu(String restName) {
        this.restName=restName;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_food_menu_list, container, false);
        Db = new DBHelper(this.getContext());

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

                LinearLayoutManager ss= new LinearLayoutManager(context);
                ss.setOrientation(RecyclerView.VERTICAL);
                recyclerView.setLayoutManager(ss);

            ArrayList<menuModel> menuModels= new ArrayList<>();

            //add all menu items that belong to restName to menuModels above
            //using new menuModel(itemName,price)
            int restId = Db.getRestaurantId(restName);
            Cursor c = Db.getMenuItems(restId);
            if(c.getCount() > 0)
                while(c.moveToNext()){
                    menuModels.add( new menuModel(c.getString(1),c.getInt(2)));
                }


            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(menuModels));
        }
        return view;
    }
}