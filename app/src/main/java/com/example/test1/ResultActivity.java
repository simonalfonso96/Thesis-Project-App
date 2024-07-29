package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.test1.Adapter.Information;
import com.example.test1.Adapter.InformationAdapter;
import com.example.test1.Adapter.SliderAdapter;
import com.example.test1.Domain.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

        ImageView ResultimageView;
        TextView Resultclassified, titleResult;
        ViewPager2 Resultviewpager2;
        ImageButton Resultbackbtn;
        RecyclerView recyclerView;
        RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4, ratingBar5, ratingBar6, ratingBar7;

        ImageButton arrowUp, arrowDown;

        private LinearLayout linear_expand;
        private CoordinatorLayout Cardview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);


            ResultimageView= findViewById(R.id.ResultimageView);
            Resultclassified = findViewById(R.id.Resultclassified);
            Resultviewpager2 = findViewById(R.id.Resultviewpager2);
            Resultbackbtn = findViewById(R.id.Resultbackbtn);
            titleResult = findViewById(R.id.titleResult);
            recyclerView = findViewById(R.id.recyclerView);
            ratingBar1 = findViewById(R.id.ratingbar1);
            ratingBar2 = findViewById(R.id.ratingbar2);
            ratingBar3 = findViewById(R.id.ratingbar3);
            ratingBar4 = findViewById(R.id.ratingbar4);
            ratingBar5 = findViewById(R.id.ratingbar5);
            ratingBar6 = findViewById(R.id.ratingbar6);
            ratingBar7 = findViewById(R.id.ratingbar7);

            Cardview = findViewById(R.id.cardview);
            linear_expand = findViewById(R.id.linear_expand);
            arrowUp = findViewById(R.id.arrow_Up);
            arrowDown = findViewById(R.id.arrow_Down);

            Cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (linear_expand.getVisibility() == View.GONE) {
                        linear_expand.setVisibility(View.VISIBLE);
                        arrowUp.setVisibility(View.VISIBLE);
                        arrowDown.setVisibility(View.GONE);
                    } else {
                        linear_expand.setVisibility(View.GONE);
                        arrowUp.setVisibility(View.GONE);
                        arrowDown.setVisibility(View.VISIBLE);
                    }
                }
            });
            arrowUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (linear_expand.getVisibility() == View.GONE) {
                        linear_expand.setVisibility(View.VISIBLE);
                        arrowUp.setVisibility(View.VISIBLE);
                        arrowDown.setVisibility(View.GONE);
                    } else {
                        linear_expand.setVisibility(View.GONE);
                        arrowUp.setVisibility(View.GONE);
                        arrowDown.setVisibility(View.VISIBLE);
                    }
                }
            });
            arrowDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (linear_expand.getVisibility() == View.GONE) {
                        linear_expand.setVisibility(View.VISIBLE);
                        arrowUp.setVisibility(View.VISIBLE);
                        arrowDown.setVisibility(View.GONE);
                    } else {
                        linear_expand.setVisibility(View.GONE);
                        arrowUp.setVisibility(View.GONE);
                        arrowDown.setVisibility(View.VISIBLE);
                    }
                }
            });

            Resultbackbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            titleResult.setText(getIntent().getExtras().getString("textView"));
            Resultclassified.setText(getIntent().getExtras().getString("textView"));
            int image = getIntent().getIntExtra("imageView",0);
            ResultimageView.setImageResource(image);
            String breed = getIntent().getStringExtra("breed");

            recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Add this line
            List<Information> informationList = new ArrayList<>();

            InformationAdapter informationAdapter = new InformationAdapter(informationList);
            recyclerView.setAdapter(informationAdapter);
            recyclerView.setNestedScrollingEnabled(false);


            List<SliderItem> sliderItems = new ArrayList<>();
            switch (breed) {

                // display information for Airedale terrier
                case "Airedale terrier":
                    sliderItems.add(new SliderItem(R.drawable.at1));
                    sliderItems.add(new SliderItem(R.drawable.at2));
                    sliderItems.add(new SliderItem(R.drawable.at3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.at1),true));
                    informationList.add(new Information("Description", getString(R.string.at2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.at4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.at3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.at5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                // display information for American Bully
                case "American Bully":
                    sliderItems.add(new SliderItem(R.drawable.ab1));
                    sliderItems.add(new SliderItem(R.drawable.ab2));
                    sliderItems.add(new SliderItem(R.drawable.ab3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About1),true));
                    informationList.add(new Information("Description", getString(R.string.Traits1),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food1),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note1),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health1),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(1);
                    ratingBar3.setRating(2);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(2);
                    break;

                // display information for Australian Shepperd
                case "Australian Shepherd":
                    sliderItems.add(new SliderItem(R.drawable.as1));
                    sliderItems.add(new SliderItem(R.drawable.as2));
                    sliderItems.add(new SliderItem(R.drawable.as3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.as1),true));
                    informationList.add(new Information("Description", getString(R.string.as2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.as4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.as3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.as5),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(4);
                    break;

                // display information for Basset hound
                case "Basset hound":
                    sliderItems.add(new SliderItem(R.drawable.bh1));
                    sliderItems.add(new SliderItem(R.drawable.bh2));
                    sliderItems.add(new SliderItem(R.drawable.bh3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.bh1),true));
                    informationList.add(new Information("Description", getString(R.string.bh2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.bh4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.bh3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.bh5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;



                // display information for Beagle
                case "Beagle":
                    sliderItems.add(new SliderItem(R.drawable.beagle1));
                    sliderItems.add(new SliderItem(R.drawable.beagle2));
                    sliderItems.add(new SliderItem(R.drawable.beagle3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About2),true));
                    informationList.add(new Information("Description", getString(R.string.Traits2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food2),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note2),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health2),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                // display information for Bichon Frise
                case "Bichon Frise":
                    sliderItems.add(new SliderItem(R.drawable.bf1));
                    sliderItems.add(new SliderItem(R.drawable.bf2));
                    sliderItems.add(new SliderItem(R.drawable.bf3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.bf1),true));
                    informationList.add(new Information("Description", getString(R.string.bf2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.bf4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.bf3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.bf5),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(2);
                    ratingBar3.setRating(2);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(2);
                    break;

                // display information for Chihuahua
                case "Chihuahua":
                    sliderItems.add(new SliderItem(R.drawable.chi1));
                    sliderItems.add(new SliderItem(R.drawable.chi2));
                    sliderItems.add(new SliderItem(R.drawable.chi3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About4),true));
                    informationList.add(new Information("Description", getString(R.string.Traits4),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note4),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health4),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(3);
                    break;

                // display information for Chow chow
                case "Chow chow":
                    sliderItems.add(new SliderItem(R.drawable.chow1));
                    sliderItems.add(new SliderItem(R.drawable.chow2));
                    sliderItems.add(new SliderItem(R.drawable.chow3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About5),true));
                    informationList.add(new Information("Description", getString(R.string.Traits5),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food5),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note5),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(1);
                    ratingBar3.setRating(3);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(4);
                    break;

                // display information for Corgi
                case "Corgi":
                    sliderItems.add(new SliderItem(R.drawable.cor1));
                    sliderItems.add(new SliderItem(R.drawable.cor2));
                    sliderItems.add(new SliderItem(R.drawable.cor3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About6),true));
                    informationList.add(new Information("Description", getString(R.string.Traits6),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food6),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note6),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health6),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(4);
                    break;

                // display information for Dalmatian
                case "Dalmatian":
                    sliderItems.add(new SliderItem(R.drawable.dal1));
                    sliderItems.add(new SliderItem(R.drawable.dal2));
                    sliderItems.add(new SliderItem(R.drawable.dal3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About8),true));
                    informationList.add(new Information("Description", getString(R.string.Traits8),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food8),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note8),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health8),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                // display information for Doberman
                case "Doberman Pinscher":
                    sliderItems.add(new SliderItem(R.drawable.dp1));
                    sliderItems.add(new SliderItem(R.drawable.dp2));
                    sliderItems.add(new SliderItem(R.drawable.dp3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About9),true));
                    informationList.add(new Information("Description", getString(R.string.Traits9),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food9),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note9),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health9),false));
                    ratingBar1.setRating(5);
                    ratingBar2.setRating(3);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(5);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(2);
                    break;

                // display information for German Shepherd
                case "German Shepherd":
                    sliderItems.add(new SliderItem(R.drawable.gs1));
                    sliderItems.add(new SliderItem(R.drawable.gs2));
                    sliderItems.add(new SliderItem(R.drawable.gs3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About10),true));
                    informationList.add(new Information("Description", getString(R.string.Traits10),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food10),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note10),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health10),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(3);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(5);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(2);
                    break;

                // display information for Golden Retriever
                case "Golden Retriever":
                    sliderItems.add(new SliderItem(R.drawable.gr1));
                    sliderItems.add(new SliderItem(R.drawable.gr2));
                    sliderItems.add(new SliderItem(R.drawable.gr3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About11),true));
                    informationList.add(new Information("Description", getString(R.string.Traits11),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food11),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note11),false));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food11),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health11),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(1);
                    ratingBar3.setRating(3);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(4);
                    break;

                // display information for Jack Russell
                case "Jack Russell":
                    sliderItems.add(new SliderItem(R.drawable.jr1));
                    sliderItems.add(new SliderItem(R.drawable.jr2));
                    sliderItems.add(new SliderItem(R.drawable.jr3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.jr1),true));
                    informationList.add(new Information("Description", getString(R.string.jr2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.jr4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.jr3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.jr5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                // display information for Pomeranian
                case "Pomeranian":
                    sliderItems.add(new SliderItem(R.drawable.pom1));
                    sliderItems.add(new SliderItem(R.drawable.pom2));
                    sliderItems.add(new SliderItem(R.drawable.pom3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About12),true));
                    informationList.add(new Information("Description", getString(R.string.Traits12),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food12),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note12),false));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food12),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health12),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(3);
                    break;

                // display information for Pug
                case "Pug":
                    sliderItems.add(new SliderItem(R.drawable.pug1));
                    sliderItems.add(new SliderItem(R.drawable.pug2));
                    sliderItems.add(new SliderItem(R.drawable.pug3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About14),true));
                    informationList.add(new Information("Description", getString(R.string.Traits14),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food14),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note14),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health14),false));
                    ratingBar1.setRating(1);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(3);
                    break;

                // display information for Shiba Inu
                case "Shiba Inu":
                    sliderItems.add(new SliderItem(R.drawable.si1));
                    sliderItems.add(new SliderItem(R.drawable.si2));
                    sliderItems.add(new SliderItem(R.drawable.si3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About15),true));
                    informationList.add(new Information("Description", getString(R.string.Traits15),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food15),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note15),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health15),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(4);
                    break;

                // display information for Shih-Tzu
                case "Shih Tzu":
                    sliderItems.add(new SliderItem(R.drawable.st1));
                    sliderItems.add(new SliderItem(R.drawable.st2));
                    sliderItems.add(new SliderItem(R.drawable.st3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.About16),true));
                    informationList.add(new Information("Description", getString(R.string.Traits16),true));
                    informationList.add(new Information("Did you know?", getString(R.string.Note16),false));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food16),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health16),false));
                    ratingBar1.setRating(1);
                    ratingBar2.setRating(2);
                    ratingBar3.setRating(2);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(2);

                    break;

                // display information for Siberian Husky
                case "Siberian Husky":
                    sliderItems.add(new SliderItem(R.drawable.husky1));
                    sliderItems.add(new SliderItem(R.drawable.husky2));
                    sliderItems.add(new SliderItem(R.drawable.husky3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.huskyAbout),true));
                    informationList.add(new Information("Description", getString(R.string.huskytraits),true));
                    informationList.add(new Information("Appropriate Food",getString(R.string.huskyFood),false));
                    informationList.add(new Information("Did you know?", getString(R.string.huskyNote),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.huskhealth),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(5);
                    break;

                // display information for White Swiss Shepperd
                case "White Swiss Shepherd":
                    sliderItems.add(new SliderItem(R.drawable.ws1));
                    sliderItems.add(new SliderItem(R.drawable.ws2));
                    sliderItems.add(new SliderItem(R.drawable.ws3));
                    Resultviewpager2.setAdapter(new SliderAdapter(sliderItems,Resultviewpager2));
                    informationList.add(new Information("History", getString(R.string.ws1),true));
                    informationList.add(new Information("Description", getString(R.string.ws2),true));
                    informationList.add(new Information("Appropriate Food",getString(R.string.ws3),false));
                    informationList.add(new Information("Did you know?", getString(R.string.ws4),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.ws5),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(5);
                    break;
            }

            Resultviewpager2.setClipToPadding(false);
            Resultviewpager2.setClipChildren(false);
            Resultviewpager2.setOffscreenPageLimit(3);
            Resultviewpager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(40));
            compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.14f);
                }
            });

            Resultviewpager2.setPageTransformer(compositePageTransformer);


        }


}