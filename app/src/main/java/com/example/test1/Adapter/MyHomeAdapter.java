package com.example.test1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test1.Domain.ItemHome;
import com.example.test1.R;
import com.example.test1.ResultActivity;

import java.util.List;

public class MyHomeAdapter extends RecyclerView.Adapter<HomeAdapter> {
    Context context;
    List<ItemHome> items;
    public MyHomeAdapter(Context context, List<ItemHome> items) {
        this.context = context;
        this.items = items;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(List<ItemHome> filteredList){
        this.items = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeAdapter(LayoutInflater.from(context).inflate(R.layout.viewholder_dogs,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter holder, @SuppressLint("RecyclerView") int position) {

        holder.textView.setText(items.get(position).getName());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.layout.setOnClickListener(null);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ResultActivity.class);
                intent.putExtra("textView",items.get(position).getName());
                intent.putExtra("imageView",items.get(position).getImage());
                intent.putExtra("breed",items.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//                Pair<View, String> pair = new Pair<>(holder.imageView, "resultimageview");
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), pair);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
