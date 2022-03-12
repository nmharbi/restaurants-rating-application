package com.example.swe483restruntproject.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swe483restruntproject.R;
import com.example.swe483restruntproject.placeholder.menuModel;

import java.util.ArrayList;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private ArrayList<menuModel> mValues;

    public MyItemRecyclerViewAdapter(ArrayList<menuModel> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restrunt_descrption_item, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        menuModel cmenuModel = mValues.get(position);
        holder.setData(cmenuModel.getItemName(),cmenuModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName,price;

        public ViewHolder(View view ) {
            super(view);
            itemName = view.findViewById(R.id.menu_item_name);
            price = view.findViewById(R.id.menu_item_price);
        }

        public void  setData(String itemName,double price){
            this.itemName.setText(itemName);
            this.price.setText(price+"");
        }

    }
}