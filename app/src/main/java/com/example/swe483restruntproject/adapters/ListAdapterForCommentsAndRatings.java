package com.example.swe483restruntproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.swe483restruntproject.placeholder.CommentHolder;
import com.example.swe483restruntproject.R;

import java.util.ArrayList;

public class ListAdapterForCommentsAndRatings extends BaseAdapter {
    ArrayList<CommentHolder> list;
    Context context;

    public ListAdapterForCommentsAndRatings(ArrayList<CommentHolder> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.comment_item_layout,null);
        TextView username = view.findViewById(R.id.username);
        TextView comment = view.findViewById(R.id.comment);
        TextView rating = view.findViewById(R.id.rating_in_comment);
        username.setText(list.get(i).getUser());
        comment.setText(list.get(i).getComment());
        rating.setText(list.get(i).getRate()+"");
        return view;
    }
}
