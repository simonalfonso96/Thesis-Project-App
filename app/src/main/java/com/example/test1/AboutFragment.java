package com.example.test1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.Adapter.Information;
import com.example.test1.Adapter.InformationAdapter;
import com.example.test1.Domain.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        recyclerView = view.findViewById(R.id.aboutItem);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // Add this line
        List<Information> informationList = new ArrayList<>();

        InformationAdapter informationAdapter = new InformationAdapter(informationList);
        recyclerView.setAdapter(informationAdapter);


        informationList.add(new Information("About", getString(R.string.abt1),true));
        informationList.add(new Information("Key Features:", getString(R.string.abt2),false));
        informationList.add(new Information("Why Doggo Dex?", getString(R.string.abt3),false));
        informationList.add(new Information("How It Works:", getString(R.string.abt4),false));



        return view;
    }


}
