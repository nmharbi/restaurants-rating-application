package com.example.swe483restruntproject.homeFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swe483restruntproject.R;
import com.example.swe483restruntproject.adapters.MyItemRecyclerViewAdapter;
import com.example.swe483restruntproject.adapters.restuntsHomePageListItemAdapter;
import com.example.swe483restruntproject.placeholder.menuModel;
import com.example.swe483restruntproject.placeholder.restuntsHomePageListItemModel;

import java.util.ArrayList;

public class restListFragment extends Fragment {
    ArrayList<restuntsHomePageListItemModel> restuntsHomePageListItemModelv;
String username;
    public restListFragment( ArrayList<restuntsHomePageListItemModel> restuntsHomePageListItemModelv ,String username) {
        this.restuntsHomePageListItemModelv=restuntsHomePageListItemModelv;
        this.username = username;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest_menu_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            LinearLayoutManager ss= new LinearLayoutManager(context);
            ss.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(ss);
            recyclerView.setAdapter(new restuntsHomePageListItemAdapter(restuntsHomePageListItemModelv,username));
        }
        return view;
    }

}
