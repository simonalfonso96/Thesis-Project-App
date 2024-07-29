package com.example.test1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InformationVH> {

    List<Information> InformationList;


    public InformationAdapter(List<Information> informationList) {
        InformationList = informationList;



    }

    @NonNull
    @Override
    public InformationAdapter.InformationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new InformationVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationAdapter.InformationVH holder, int position) {

        Information information = InformationList.get(position);
        holder.codename.setText(information.getCodename());
        holder.text1.setText(information.getTex1());

        if (information.isExpandable()) {
            holder.arrowUp.setVisibility(View.VISIBLE);
            holder.arrowDown.setVisibility(View.GONE);
            holder.expandable.setVisibility(View.VISIBLE);
        } else {
            holder.arrowUp.setVisibility(View.GONE);
            holder.arrowDown.setVisibility(View.VISIBLE);
            holder.expandable.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return InformationList.size();
    }
    public class InformationVH extends RecyclerView.ViewHolder {

        TextView codename, text1;
        LinearLayout linearLayout;
        RelativeLayout expandable;

        ImageButton arrowUp, arrowDown;

        public InformationVH(@NonNull View itemView) {
            super(itemView);

            codename = itemView.findViewById(R.id.codename);
            text1 = itemView.findViewById(R.id.text1);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandable = itemView.findViewById(R.id.expandable);
            arrowUp = itemView.findViewById(R.id.arrowUp);
            arrowDown = itemView.findViewById(R.id.arrowDown);



            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Information information = InformationList.get(getAdapterPosition());
                    information.setExpandable(!information.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                }
            });

            arrowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle the click event for the arrowUp icon
                    Information information = InformationList.get(getAdapterPosition());
                    information.setExpandable(false);
                    notifyItemChanged(getAdapterPosition());
                }
            });

            arrowDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle the click event for the arrowDown icon
                    Information information = InformationList.get(getAdapterPosition());
                    information.setExpandable(true);
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }

    }

}
