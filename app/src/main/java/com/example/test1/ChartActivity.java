package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.example.test1.Adapter.MyChartAdapter;
import com.example.test1.Domain.ItemChart;
import com.example.test1.Domain.Spacing;



import java.util.ArrayList;

import java.util.List;

public class ChartActivity extends AppCompatActivity {

    ImageView chart_backbtn;
    RecyclerView chart_recycleview;

    ProgressBar loading_chart;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chart_recycleview = findViewById(R.id.chart_recycleview);
        chart_backbtn = findViewById(R.id.chart_backbtn);
        loading_chart = findViewById(R.id.loading_chart);

        handler = new Handler();

        chart_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

        chart_recycleview.setVisibility(View.INVISIBLE);
        loading_chart.setVisibility(View.VISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loading_chart.setVisibility(View.GONE);
                chart_recycleview.setVisibility(View.VISIBLE);

            }
        },1000);


        String result = getIntent().getStringExtra(Classification.A);
        String result1 = getIntent().getStringExtra(Classification.B);
        String result2 = getIntent().getStringExtra(Classification.C);
        String result3 = getIntent().getStringExtra(Classification.D);
        String result4 = getIntent().getStringExtra(Classification.E);
        String result5 = getIntent().getStringExtra(Classification.F);
        String result6 = getIntent().getStringExtra(Classification.G);
        String result7 = getIntent().getStringExtra(Classification.H);
        String result8 = getIntent().getStringExtra(Classification.I);
        String result9 = getIntent().getStringExtra(Classification.J);
        String result10 = getIntent().getStringExtra(Classification.K);
        String result11 = getIntent().getStringExtra(Classification.L);
        String result12 = getIntent().getStringExtra(Classification.M);
        String result13 = getIntent().getStringExtra(Classification.N);
        String result14 = getIntent().getStringExtra(Classification.O);
        String result15 = getIntent().getStringExtra(Classification.P);
        String result16 = getIntent().getStringExtra(Classification.Q);
        String result17 = getIntent().getStringExtra(Classification.Z);
        String result18 = getIntent().getStringExtra(Classification.S);
        String result19 = getIntent().getStringExtra(Classification.U);

        List<ItemChart> itemChart = new ArrayList<>();

        itemChart.add(new ItemChart("Airedale terrier", R.drawable.icon_airedale));
        itemChart.add(new ItemChart("American Bully", R.drawable.icon_americanbully));
        itemChart.add(new ItemChart("Australian Shepherd", R.drawable.icon_aussheperd));
        itemChart.add(new ItemChart("Basset hound", R.drawable.icon_basset));
        itemChart.add(new ItemChart("Beagle", R.drawable.icon_beagle));
        itemChart.add(new ItemChart("Bichon Frise", R.drawable.icon_bitchon));
        itemChart.add(new ItemChart("Chihuahua", R.drawable.icon_chihuahua));
        itemChart.add(new ItemChart("ChowChow", R.drawable.icon_chowchow));
        itemChart.add(new ItemChart("Corgi", R.drawable.icon_corgi));
        itemChart.add(new ItemChart("Dalmatian", R.drawable.icon_dalmatian));
        itemChart.add(new ItemChart("Doberman Pinscher", R.drawable.icon_doberman));
        itemChart.add(new ItemChart("German Shepherd", R.drawable.icon_sheperd));
        itemChart.add(new ItemChart("Golden Retriever", R.drawable.icon_goldenretri));
        itemChart.add(new ItemChart("Jack Russell", R.drawable.icon_jackrussell));
        itemChart.add(new ItemChart("Pomeranian", R.drawable.icon_pome));
        itemChart.add(new ItemChart("Pug", R.drawable.icon_pug));
        itemChart.add(new ItemChart("Shiba Inu", R.drawable.icon_shiba));
        itemChart.add(new ItemChart("Shih-Tzu", R.drawable.icon_shiz));
        itemChart.add(new ItemChart("Siberian Husky", R.drawable.icon_sirberianhusky));
        itemChart.add(new ItemChart("White Swiss Shepherd", R.drawable.icon_whiteswiss));

        double value = Double.parseDouble(result) * 100;
        double value1 = Double.parseDouble(result1) * 100;
        double value2 = Double.parseDouble(result2) * 100;
        double value3 = Double.parseDouble(result3) * 100;
        double value4 = Double.parseDouble(result4) * 100;
        double value5 = Double.parseDouble(result5) * 100;
        double value6 = Double.parseDouble(result6) * 100;
        double value7 = Double.parseDouble(result7) * 100;
        double value8 = Double.parseDouble(result8) * 100;
        double value9 = Double.parseDouble(result9) * 100;
        double value10 = Double.parseDouble(result10) * 100;
        double value11 = Double.parseDouble(result11) * 100;
        double value12 = Double.parseDouble(result12) * 100;
        double value13 = Double.parseDouble(result13) * 100;
        double value14 = Double.parseDouble(result14) * 100;
        double value15 = Double.parseDouble(result15) * 100;
        double value16 = Double.parseDouble(result16) * 100;
        double value17 = Double.parseDouble(result17) * 100;
        double value18 = Double.parseDouble(result18) * 100;
        double value19 = Double.parseDouble(result19) * 100;

        String progressString = String.format("%.1f", value);
        String progressString1 = String.format("%.1f", value1);
        String progressString2 = String.format("%.1f", value2);
        String progressString3 = String.format("%.1f", value3);
        String progressString4 = String.format("%.1f", value4);
        String progressString5 = String.format("%.1f", value5);
        String progressString6 = String.format("%.1f", value6);
        String progressString7 = String.format("%.1f", value7);
        String progressString8 = String.format("%.1f", value8);
        String progressString9 = String.format("%.1f", value9);
        String progressString10 = String.format("%.1f", value10);
        String progressString11 = String.format("%.1f", value11);
        String progressString12 = String.format("%.1f", value12);
        String progressString13 = String.format("%.1f", value13);
        String progressString14 = String.format("%.1f", value14);
        String progressString15 = String.format("%.1f", value15);
        String progressString16 = String.format("%.1f", value16);
        String progressString17 = String.format("%.1f", value17);
        String progressString18 = String.format("%.1f", value18);
        String progressString19 = String.format("%.1f", value19);


        double progressValue = Double.parseDouble(progressString);
        double progressValue1 = Double.parseDouble(progressString1);
        double progressValue2 = Double.parseDouble(progressString2);
        double progressValue3 = Double.parseDouble(progressString3);
        double progressValue4 = Double.parseDouble(progressString4);
        double progressValue5 = Double.parseDouble(progressString5);
        double progressValue6 = Double.parseDouble(progressString6);
        double progressValue7 = Double.parseDouble(progressString7);
        double progressValue8 = Double.parseDouble(progressString8);
        double progressValue9 = Double.parseDouble(progressString9);
        double progressValue10 = Double.parseDouble(progressString10);
        double progressValue11 = Double.parseDouble(progressString11);
        double progressValue12 = Double.parseDouble(progressString12);
        double progressValue13 = Double.parseDouble(progressString13);
        double progressValue14 = Double.parseDouble(progressString14);
        double progressValue15 = Double.parseDouble(progressString15);
        double progressValue16 = Double.parseDouble(progressString16);
        double progressValue17 = Double.parseDouble(progressString17);
        double progressValue18 = Double.parseDouble(progressString18);
        double progressValue19 = Double.parseDouble(progressString19);

        itemChart.get(0).setProgressBarValue((float) progressValue);
        itemChart.get(1).setProgressBarValue((float) progressValue1);
        itemChart.get(2).setProgressBarValue((float) progressValue2);
        itemChart.get(3).setProgressBarValue((float) progressValue3);
        itemChart.get(4).setProgressBarValue((float) progressValue4);
        itemChart.get(5).setProgressBarValue((float) progressValue5);
        itemChart.get(6).setProgressBarValue((float) progressValue6);
        itemChart.get(7).setProgressBarValue((float) progressValue7);
        itemChart.get(8).setProgressBarValue((float) progressValue8);
        itemChart.get(9).setProgressBarValue((float) progressValue9);
        itemChart.get(10).setProgressBarValue((float) progressValue10);
        itemChart.get(11).setProgressBarValue((float) progressValue11);
        itemChart.get(12).setProgressBarValue((float) progressValue12);
        itemChart.get(13).setProgressBarValue((float) progressValue13);
        itemChart.get(14).setProgressBarValue((float) progressValue14);
        itemChart.get(15).setProgressBarValue((float) progressValue15);
        itemChart.get(16).setProgressBarValue((float) progressValue16);
        itemChart.get(17).setProgressBarValue((float) progressValue17);
        itemChart.get(18).setProgressBarValue((float) progressValue18);
        itemChart.get(19).setProgressBarValue((float) progressValue19);



        MyChartAdapter adapter = new MyChartAdapter(this, itemChart);
        chart_recycleview.setAdapter(adapter);
        chart_recycleview.setLayoutManager(new LinearLayoutManager(this));
        chart_recycleview.addItemDecoration(new Spacing(10));
        chart_recycleview.setNestedScrollingEnabled(false);
    }


}