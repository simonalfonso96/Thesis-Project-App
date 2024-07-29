package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
public class  BaseActivity extends AppCompatActivity {
    private FloatingActionButton scanbtn;
    BottomNavigationView bottomNav;
    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;
    private OnFragmentInteractionListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setBackground(null);
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homeFragment)
                .commit();
        scanbtn = findViewById(R.id.scanbtn);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = 0; // Default position for HomeFragment

                switch (item.getItemId()) {
                    case R.id.home:
                        break;
                    case R.id.dogs:
                        position = 1;
                        break;
                    case R.id.about:
                        position = 2;
                        break;
                    case R.id.exit:
                        onBackPressed();
                        break;
                }
                // Set the current item of the ViewPager
                viewPager.setCurrentItem(position);
                return true;
            }
        });
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, ScanActivity.class);
                startActivity(intent);

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Not needed for this implementation
            }
            @Override
            public void onPageSelected(int position) {
                // Update the selected item in the BottomNavigationView
                bottomNav.setSelectedItemId(getBottomNavItem(position));
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                // Not needed for this implementation
            }
        });
    }
    @Override
    public void onBackPressed() {
        DialogUtils.showExitDialog(this);

    }
    public NavigationBarView getBottomNavigationView() {
        return bottomNav;
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {
        public CustomPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            // Return the appropriate Fragment for each position
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new DogListFragment();
                case 2:
                    return new AboutFragment();
                // Add more cases for additional fragments
            }
            return null;
        }
        @Override
        public int getCount() {
            return 3; // Number of fragments
        }
    }
    private int getBottomNavItem(int position) {
        switch (position) {
            case 0:
                return R.id.home;
            case 1:
                return R.id.dogs;
            case 2:
                return R.id.about;
            default:
                return -1; // Invalid
        }
    }

    public interface OnFragmentInteractionListener {
        void onDogListFragmentSelected();
    }

    // Add a method to set the listener
    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        this.listener = listener;
    }

    // Method to get the ViewPager
    public ViewPager getViewPager() {
        return viewPager;
    }

}