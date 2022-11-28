package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adaptor.CateloryAdaptor;
import com.example.myapplication.Adaptor.PopularAdaptor;
import com.example.myapplication.Domain.CategoryDomain;
import com.example.myapplication.Domain.PopularDomain;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private  RecyclerView recyclerViewCatelogyList,recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_acctivity);
        recyclerViewCatelogy();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        ArrayList<PopularDomain> Foodlist = new ArrayList<>();
        Foodlist.add(new PopularDomain("Pepporoni pizza","pop_1","Slices pepperoni , mozzerela chese ,flesh oregano ,ground black pepper, pizza sauces",9.76));
        Foodlist.add(new PopularDomain("Whopper","pop_2","A 1/4 lb* of flame-grilled beef patty topped with juicy tomatoes, crisp lettuce, creamy mayonnaise, ketchup,",8.79));
        Foodlist.add(new PopularDomain("Vegetable pizza","pop_3","Olive oil , Vegetable oil, pite kalamata, cherry tomatoes ,fresh oregano ,basil",8.59));
        Foodlist.add(new PopularDomain("Pepporoni pizza","pop_1","Slices pepperoni , mozzerela chese ,flesh oregano ,ground black pepper, pizza sauces",9.76));
        Foodlist.add(new PopularDomain("Whopper","pop_2","A 1/4 lb* of flame-grilled beef patty topped with juicy tomatoes, crisp lettuce, creamy mayonnaise, ketchup,",8.79));
        Foodlist.add(new PopularDomain("Vegetable pizza","pop_3","Olive oil , Vegetable oil, pite kalamata, cherry tomatoes ,fresh oregano ,basil",8.59));
        adapter2 = new PopularAdaptor(Foodlist);
        recyclerViewPopularList.setAdapter(adapter2);


    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cart_Btn);
        LinearLayout homebtn = findViewById(R.id.Home_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }
    private void recyclerViewCatelogy() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCatelogyList = findViewById(R.id.recyclerView);
        recyclerViewCatelogyList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> catelory = new ArrayList<>();
        catelory.add(new CategoryDomain("Pizza","cat_1"));
        catelory.add(new CategoryDomain("Burger","cat_2"));
        catelory.add(new CategoryDomain("HotDog","cat_3"));
        catelory.add(new CategoryDomain("Dinks","cat_4"));
        catelory.add(new CategoryDomain("Donut","cat_5"));

        adapter = new CateloryAdaptor(catelory);
        recyclerViewCatelogyList.setAdapter(adapter);

    }
}