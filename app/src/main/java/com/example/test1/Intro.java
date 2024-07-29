package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.test1.Adapter.IntroViewPagerAdapter;
import com.example.test1.Domain.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import org.tensorflow.lite.support.metadata.schema.Content;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class Intro extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    CircleIndicator tabLayout;
    Button Nextbtn, getStartbtn;
    int position = 0;
    Animation btnAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (restorePrefData()){
            Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_intro);

        tabLayout = findViewById(R.id.tableLayout);
        Nextbtn = findViewById(R.id.Nextbtn);
        getStartbtn = findViewById(R.id.Getstartbtn);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Welcome to DoggoDex!", "Use your camera or Upload your\n own photo from the gallery to\n identify dog breeds within seconds!", R.drawable.newdog));
        mList.add(new ScreenItem("We need Access", "To identify dog breeds, we need\n access to the camera and gallery of\n your device.", R.drawable.we_need_access));
        mList.add(new ScreenItem("Ensure image quality!", "Try to take a picture of\n face.\nAt least the face should be front", R.drawable.ensure_image_quality));

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabLayout.setViewPager(screenPager);

        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size()-1) {

                    // TODO : show the GETSTARTED Button and hide indicator and the next button

                    loadLastScreen();
                }

            }
        });

        tabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (position == mList.size() - 1) {
                    loadLastScreen();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        getStartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(intent);
                savePrefsDaa();
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenBefore = pref.getBoolean("isIntroOpen", false);
        return  isIntroActivityOpenBefore;
    }

    private void savePrefsDaa() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpen",true);
        editor.commit();
    }
    private void loadLastScreen() {
        Nextbtn.setVisibility(View.INVISIBLE);
        getStartbtn.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);

        getStartbtn.setAnimation(btnAnim);
    }
}