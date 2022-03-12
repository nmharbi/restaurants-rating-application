package com.example.swe483restruntproject.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swe483restruntproject.R;
import com.example.swe483restruntproject.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.swe483restruntproject.databinding.FragmentRestPhotosBinding;
import com.example.swe483restruntproject.placeholder.photoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter2 extends RecyclerView.Adapter<MyItemRecyclerViewAdapter2.ViewHolder> {

    private  ArrayList<photoModel> photos;

    public MyItemRecyclerViewAdapter2(ArrayList<photoModel> photos) {
        this.photos = photos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_rest_photos_, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       holder.setData(this.photos.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Imageholer;

        public ViewHolder(View view ) {
            super(view);
            Imageholer = view.findViewById(R.id.Imageholer);

        }

        public void  setData(Bitmap imageuri) {
            Log.e("photo",photos.size()+"");
            Log.e("photo",imageuri.toString());

            Imageholer.setImageBitmap(imageuri);
        }
    }
}