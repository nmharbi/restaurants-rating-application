package com.example.swe483restruntproject.decFragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swe483restruntproject.DBHelper;
import com.example.swe483restruntproject.R;
import com.example.swe483restruntproject.adapters.MyItemRecyclerViewAdapter;
import com.example.swe483restruntproject.adapters.MyItemRecyclerViewAdapter2;
import com.example.swe483restruntproject.placeholder.PlaceholderContent;
import com.example.swe483restruntproject.placeholder.menuModel;
import com.example.swe483restruntproject.placeholder.photoModel;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class rest_photos_Fragment extends Fragment {

    String restName;
    DBHelper Db;

    public rest_photos_Fragment(String restName) {
        this.restName=restName;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_photos__list, container, false);
        Db = new DBHelper(this.getContext());
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            LinearLayoutManager ss= new LinearLayoutManager(context);
            ss.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(ss);

            ArrayList<photoModel> photoModel= new ArrayList<>();

            //add all photos that belong to restName to photoModel above
            //using new photoModel(URI photo)
            int restId = Db.getRestaurantId(restName);
            Cursor c = Db.getPhotos(restId);
            if(c.getCount() > 0)
                while(c.moveToNext()){
                    byte[] d= c.getBlob(1);
                   Bitmap bb= BitmapFactory.decodeByteArray(d,0,d.length);

                    photoModel.add(new photoModel(bb));
                }

            recyclerView.setAdapter(new MyItemRecyclerViewAdapter2(photoModel));
        }
        return view;
    }
}