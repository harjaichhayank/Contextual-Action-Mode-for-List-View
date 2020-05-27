package com.example.contextualactionmodeforlistview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String>{

    private String[] Titles;
    CustomAdapter(@NonNull Context context, String[] Titles) {
        super(context, R.layout.content_main,Titles);
        this.Titles = Titles;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder")
        View customView = inflater.inflate(R.layout.content_main, parent,false);

        TextView titleText = customView.findViewById(R.id.movieName);

        titleText.setText(Titles[position]);
        return customView;
    }
}
