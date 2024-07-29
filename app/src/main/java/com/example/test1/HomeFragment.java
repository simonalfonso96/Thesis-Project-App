package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.Adapter.MyHomeAdapter;
import com.example.test1.Domain.ItemChart;
import com.example.test1.Domain.ItemHome;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    RoundedImageView roundedimageview;
    TextView seemore ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        seemore = view.findViewById(R.id.seemore);
        roundedimageview = view.findViewById(R.id.roundedimageview);

        roundedimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Tutorialphone.class);
                startActivity(intent);
            }
        });

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment selectedFragment = new DogListFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();

                if (getActivity() instanceof BaseActivity) {
                    BaseActivity baseActivity = (BaseActivity) getActivity();
                    baseActivity.getBottomNavigationView().setSelectedItemId(R.id.dogs);
                }
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        List<ItemHome> itemHomes = new ArrayList<ItemHome>();


        itemHomes.add(new ItemHome("Airedale terrier", R.drawable.airedale));
        itemHomes.add(new ItemHome("American Bully",R.drawable.americanbully));
        itemHomes.add(new ItemHome("Australian Shepherd", R.drawable.australian));
        itemHomes.add(new ItemHome("Basset hound", R.drawable.basset));
        itemHomes.add(new ItemHome("Beagle",R.drawable.beagle));
        itemHomes.add(new ItemHome("Bichon Frise", R.drawable.bichon));
        itemHomes.add(new ItemHome("Chihuahua",R.drawable.chichuachu));
        itemHomes.add(new ItemHome("Chow chow",R.drawable.chow_chow));
        itemHomes.add(new ItemHome("Corgi",R.drawable.corgi));
        itemHomes.add(new ItemHome("Dalmatian",R.drawable.dalmatian));
        itemHomes.add(new ItemHome("Doberman Pinscher",R.drawable.doberman));
        itemHomes.add(new ItemHome("German Shepherd",R.drawable.german_shepherd));
        itemHomes.add(new ItemHome("Golden Retriever",R.drawable.golden_retriever));
        itemHomes.add(new ItemHome("Jack Russell", R.drawable.jack));
        itemHomes.add(new ItemHome("Pomeranian",R.drawable.pomeranian));
        itemHomes.add(new ItemHome("Pug",R.drawable.pug));
        itemHomes.add(new ItemHome("Shiba Inu",R.drawable.shiba_inu));
        itemHomes.add(new ItemHome("Shih Tzu",R.drawable.shih_tzu));
        itemHomes.add(new ItemHome("Siberian Husky", R.drawable.husky));
        itemHomes.add(new ItemHome("White Swiss Shepherd", R.drawable.white_swiss));



        // Randomize the list
        Collections.shuffle(itemHomes);

        // Display only 4 items
        List<ItemHome> displayedItems = itemHomes.subList(0, Math.min(itemHomes.size(), 4));

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        MyHomeAdapter adapter = new MyHomeAdapter(getActivity(), displayedItems);
        recyclerView.setNestedScrollingEnabled(false); // Disable scrolling
        recyclerView.setAdapter(adapter);



        return view;
    }



}
