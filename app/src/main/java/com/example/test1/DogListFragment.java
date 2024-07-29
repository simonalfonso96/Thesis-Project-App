package com.example.test1;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.Adapter.MyHomeAdapter;
import com.example.test1.Domain.ItemHome;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class DogListFragment extends Fragment {

    RecyclerView doglistrecycleView;
    List<ItemHome> itemHomes;
    androidx.appcompat.widget.SearchView searchbar;
    MyHomeAdapter myHomeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doglist, container, false);
        searchbar = view.findViewById(R.id.searchbar);
        searchbar.clearFocus();
        searchbar.setQuery("", false);
        searchbar.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        doglistrecycleView = view.findViewById(R.id.doglistrecyclerView);
        doglistrecycleView.setHasFixedSize(true);
        doglistrecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        itemHomes = new ArrayList<ItemHome>();

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


        myHomeAdapter = new MyHomeAdapter(getActivity(), itemHomes);
        doglistrecycleView.setAdapter(myHomeAdapter);
        doglistrecycleView.smoothScrollToPosition(0);
        doglistrecycleView.setNestedScrollingEnabled(false);
        return view;
    }
    private void filterList(String text) {
        List<ItemHome> filteredList = new ArrayList<ItemHome>();
        for (ItemHome itemhome : itemHomes) {
            if (itemhome.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(itemhome);
            }
        }
        if (filteredList==null && filteredList.isEmpty()) {
            Toast.makeText(getActivity(), "No result found", Toast.LENGTH_SHORT).show();
            myHomeAdapter.setFilteredList(itemHomes);
        } else {
            myHomeAdapter.setFilteredList(filteredList);
        }
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        // Call the showPopUp() method here to display the popup after the fragment has finished loading
//        PopUp.showPopUp(getActivity());
//    }


}
