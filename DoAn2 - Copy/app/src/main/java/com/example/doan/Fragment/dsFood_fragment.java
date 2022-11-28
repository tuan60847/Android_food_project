package com.example.doan.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.doan.Entity.Food;
import com.example.doan.MainActivity;
import com.example.doan.R;

import java.util.ArrayList;


public class dsFood_fragment extends Fragment {

     View view;
     ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ds_food_fragment, container, false);
        listView=view.findViewById(R.id.listfragment_food);
        listView.setAdapter(MainActivity.adapter);
        return  view;
    }

    private void getBundle() {
//        listView=view.findViewById(R.id.listFragment);
    }
}