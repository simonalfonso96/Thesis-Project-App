package com.example.test1.Adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChartAdapter extends RecyclerView.ViewHolder {
    CircleImageView chart_circleImageView;
    TextView chart_text0,chart_percent0;
    ConstraintLayout chart_layout;
    ProgressBar chart_progress0;

    public ChartAdapter(@NonNull View itemView) {
        super(itemView);
        chart_circleImageView = itemView.findViewById(R.id.chart_circleImageView);
        chart_text0 = itemView.findViewById(R.id.chart_text0);
        chart_percent0 = itemView.findViewById(R.id.chart_percent0);
        chart_layout = itemView.findViewById(R.id.chart_layout);
        chart_progress0 = itemView.findViewById(R.id.chart_progress0);
    }
}
