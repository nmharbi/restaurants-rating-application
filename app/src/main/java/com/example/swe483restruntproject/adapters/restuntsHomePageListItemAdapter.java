package com.example.swe483restruntproject.adapters;

import static android.app.Activity.RESULT_OK;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe483restruntproject.R;
import com.example.swe483restruntproject.RestruntDescrption;
import com.example.swe483restruntproject.placeholder.restuntsHomePageListItemModel;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

public class restuntsHomePageListItemAdapter extends RecyclerView.Adapter<restuntsHomePageListItemAdapter.ViewHolder>{
    String username;
    ArrayList<restuntsHomePageListItemModel> restuntsHomePageListItemModel;
    public restuntsHomePageListItemAdapter(ArrayList<restuntsHomePageListItemModel> restuntsHomePageListItemModel , String username) {
    this.restuntsHomePageListItemModel = restuntsHomePageListItemModel;
    this.username = username;
    }

    @NonNull
    @Override
    public restuntsHomePageListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new restuntsHomePageListItemAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull restuntsHomePageListItemAdapter.ViewHolder holder, int position) {
        restuntsHomePageListItemModel s = restuntsHomePageListItemModel.get(position);
        holder.setData(s.getImage(),s.getName(),s.getRating(),s.getSpeiclty());
    }

    @Override
    public int getItemCount() {
        return restuntsHomePageListItemModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView imageView;
        TextView restName,rating,specilty;
        ConstraintLayout rectangle;
        Uri imagepath;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.logoImageInHomeitem);
            restName = itemView.findViewById(R.id.restaurantTitle);
            rating = itemView.findViewById(R.id.rating);
            specilty = itemView.findViewById(R.id.specialty);
            rectangle = itemView.findViewById(R.id.itemRectangle);
            rectangle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext() ,RestruntDescrption.class);
                    intent.putExtra("restName",restName.getText().toString());
                    intent.putExtra("username",username);
                    itemView.getContext().startActivity(intent);
                }
            });
        }


        public void setData(Bitmap image , String restName , double rating , String specilty){

            this.imageView.setImageBitmap(image);
            String ratingString = String.valueOf(rating);

            this.restName.setText( restName);
            if(ratingString.length()>4)
                ratingString=ratingString.substring(0,4);
            this.rating.setText(ratingString);
            this.specilty.setText( specilty);

        }


    }
}
