package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.test1.Adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class TutorialScan extends AppCompatActivity {

    ViewPager viewpager;

    ImageButton btnclose;
    CircleIndicator circleIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorialscan);

        viewpager = findViewById(R.id.viewpager);
        btnclose = findViewById(R.id.btnclose);
        circleIndicator = findViewById(R.id.circleindicator);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.tutorial1);
        imageList.add(R.drawable.tutorscan2);
        imageList.add(R.drawable.tutorial3);

        MyAdapter myAdapter = new MyAdapter(imageList);
        viewpager.setAdapter(myAdapter);

        circleIndicator.setViewPager(viewpager);


        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}