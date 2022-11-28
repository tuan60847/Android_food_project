package com.example.doan.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doan.MainActivity;
import com.example.doan.R;


public class Food_fragment extends Fragment {
    View view;
     public static TextView Fm_id,Fm_cost,Fm_Name,Fm_Numberic,Fm_Cata;
     public static ImageView Fm_pic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_fragment, container, false);
        getBundle();
        return view;

    }

    private void getBundle() {
        Fm_id=view.findViewById(R.id.txtFragment_id);
        Fm_Name=view.findViewById(R.id.txtFragment_name);
        Fm_cost=view.findViewById(R.id.txtFragment_cost);
        Fm_Numberic=view.findViewById(R.id.txtFragment_numberic);
        Fm_Cata=view.findViewById(R.id.txtFragment_Catalog);
        Fm_pic=view.findViewById(R.id.img_food_fragmet);
    }
}