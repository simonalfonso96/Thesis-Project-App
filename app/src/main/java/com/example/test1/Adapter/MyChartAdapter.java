package com.example.test1.Adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test1.Domain.ItemChart;
import com.example.test1.R;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyChartAdapter extends RecyclerView.Adapter<ChartAdapter> {
    Context context;
    List<ItemChart> items;

    public MyChartAdapter(Context context, List<ItemChart> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ChartAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChartAdapter(LayoutInflater.from(context).inflate(R.layout.chart_percentage,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChartAdapter holder, int position) {

        //Set descending order
        Collections.sort(items, new Comparator<ItemChart>() {
            @Override
            public int compare(ItemChart item1, ItemChart item2) {
                float progressValue1 = item1.getProgressBarValue();
                float progressValue2 = item2.getProgressBarValue();
                return Float.compare(progressValue2, progressValue1);
            }
        });
        ItemChart item = items.get(position);
        if (Math.abs(item.getProgressBarValue() - 0.0f) > 0.5f) {

            holder.chart_text0.setText(item.getName());
            holder.chart_circleImageView.setImageResource(item.getImage());

            ProgressBar progressBar = holder.chart_progress0;
            float currentProgress = progressBar.getProgress();
            float targetProgress = item.getProgressBarValue();

            ValueAnimator animator = ObjectAnimator.ofFloat(currentProgress, targetProgress);
            animator.setDuration(1000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float progress = (float) animation.getAnimatedValue();
                    progressBar.setProgress((int) progress);
                    holder.chart_percent0.setText(String.format("%.1f%%", progress));
                }
            });
            animator.start();
        } else {
            // If the progress value is zero, schedule the removal outside the layout operation
            holder.chart_layout.post(new Runnable() {
                @Override
                public void run() {
                    int adapterPosition = holder.getAdapterPosition();
                    items.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
