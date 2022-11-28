package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.PopularDomain;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView priceTxt,tiltleTxt,numberOrderTxt,decriptionTxt;
    private ImageView plusBtn,minusBtn,picPopular,backBtn;

    private PopularDomain object;
    int numberOrder = 1;
    private ManagementCart managerCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managerCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object=(PopularDomain) getIntent().getSerializableExtra("Object");
//        String title = getIntent().getExtras().getString("Title");
//       String pic = getIntent().getExtras().getString("Pic");
//        int numberOrder=getIntent().getExtras().getInt("NumberInCart");
//        String decription =getIntent().getExtras().getString("Decription");
//        double  price= getIntent().getExtras().getInt("Fee");



        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picPopular);
        tiltleTxt.setText(object.getTitle());
        priceTxt.setText("$" + String.valueOf(object.getFee()));
        decriptionTxt.setText(object.getDecription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder+=1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1) {
                    numberOrder-=1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managerCart.InsertPopular(object);

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowDetailActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        priceTxt=findViewById(R.id.priceTxt);
        tiltleTxt=findViewById(R.id.tiltleTxt);
        numberOrderTxt=findViewById(R.id.numberOrderTxt);
        decriptionTxt=findViewById(R.id.decriptionTxt);
        plusBtn=findViewById(R.id.plusBtn);
        minusBtn=findViewById(R.id.minusBtn);
        picPopular=findViewById(R.id.picPopular);
        backBtn = findViewById(R.id.BackBtn);
    }


}