package com.example.test1.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.Domain.ItemHome;
import com.example.test1.R;

public class HomeAdapter extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    ConstraintLayout layout;

    public HomeAdapter(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.Dogs);
        textView = itemView.findViewById(R.id.BreednameHome);
        layout = itemView.findViewById(R.id.constraint_layout);
    }
}
