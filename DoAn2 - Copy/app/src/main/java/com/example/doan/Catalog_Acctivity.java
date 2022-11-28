package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.Adapter.Catalog_adapter;
import com.example.doan.Entity.Food;
import com.example.doan.Entity.Loai;
import com.example.doan.Helper.SQL;

import java.util.ArrayList;

public class Catalog_Acctivity extends AppCompatActivity {
    public static TextView ID_cata,Name_cata;
    public static Button addCatalog,repairCat;

    GridView lvCata;

    Catalog_adapter catalog_adapter;
    ArrayList<Loai> dsloai;
    ArrayList<Food> foods;
    SQL sql = new SQL(this,  "dbDoAn.db", null,  1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_acctivity);
        getBundle();
        AddEvent();
        bottomNavigation();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_all,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnAbout:
                break;
            case  R.id.btn_exit:
                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

                break;
        }
        return super.onOptionsItemSelected(item);
    }



    private void bottomNavigation(){

        LinearLayout btnCat = findViewById(R.id.btn_Catalog_Cat);
        LinearLayout btnFood = findViewById(R.id.btn_Catalog_food);
        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Catalog_Acctivity.this,Catalog_Acctivity.class));
            }
        });
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Catalog_Acctivity.this,MainActivity.class));

            }
        });
    }
    private void AddEvent() {
        addCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themLoai();
            }
        });
        repairCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaLoai();
            }
        });


    }

    private void SuaLoai() {
        Loai loai = new Loai();
        if(KTdsloai(ID_cata.getText().toString())){
            loai.setMa(ID_cata.getText().toString());
            loai.setTen(Name_cata.getText().toString());
            int a = dsloai.indexOf(loai);
            dsloai.set(a,loai);
            catalog_adapter.notifyDataSetChanged();

            for (Food food: foods) {
                if(food.getLoai().getMa().equals(loai.getMa())){
                    food.setLoai(loai);
                    sql.updateFood(food);
                }

            }
            sql.updateLoai(loai.getMa(), loai.getTen());
            resetView();
        }
        else{
            Toast.makeText(this,"ID was not here",Toast.LENGTH_SHORT).show();
        }
    }

    private void themLoai() {
        Loai loai = new Loai();
        if(!KTdsloai(ID_cata.getText().toString())){
            loai.setMa(ID_cata.getText().toString());
            loai.setTen(Name_cata.getText().toString());
            dsloai.add(loai);
            catalog_adapter.notifyDataSetChanged();
            sql.insertLoai(loai.getMa(),loai.getTen());
            resetView();
        }
        else{
            Toast.makeText(this,"ID was here",Toast.LENGTH_SHORT).show();
        }

    }

    private void resetView() {
        ID_cata.setText("");
        ID_cata.setEnabled(true);
        Name_cata.setText("");
        addCatalog.setVisibility(View.VISIBLE);
        repairCat.setVisibility(View.GONE);
    }

    private boolean KTdsloai(String toString) {
        for (Loai a: dsloai
             ) { if (a.getMa().equals(toString))
                 return true;

        }
        return false;
    }

    private void getBundle() {

        sql.setData("Create Table If not Exists Food (id Varchar Primary Key , name Varchar, price Double, amount Integer, image Varchar , type Varchar)");

        sql.setData("Create Table If not Exists Loai (ID Varchar Primary Key , name Varchar)");

        ID_cata=findViewById(R.id.txtMa_cat);
        Name_cata=findViewById(R.id.txtTen_Cat);
        addCatalog=findViewById(R.id.btnAdd_cat);
        repairCat=findViewById(R.id.btnRepair_Cat);
        lvCata = findViewById(R.id.GvCatalog);
        dsloai= new ArrayList<>();

        Cursor cursor = sql.getData("select * From Loai");
        while (cursor.moveToNext()) {
            Loai a = new Loai();
            a.setMa(cursor.getString(0));
            a.setTen(cursor.getString(1));
            dsloai.add(a);
        }
        foods =new ArrayList<>();
        cursor = sql.getData("select * From Food");
        while (cursor.moveToNext()) {
            Food a = new Food();
            a.setMa(cursor.getString(0));
            a.setTen(cursor.getString(1));
            a.setGia(cursor.getFloat(2));
            a.setSoLuong(cursor.getInt(3));
            a.setHinh(cursor.getString(4));
            String tenLoai = cursor.getString(5);
            Loai loai=timLoai(dsloai,tenLoai);
            a.setLoai(loai);
            foods.add(a);
        }
        sql.DeleteNameLoai("Loai a");
        dsloai=ChangValueLoai();
        catalog_adapter = new Catalog_adapter(Catalog_Acctivity.this,R.layout.item_catalog_adapter,dsloai);
        lvCata.setAdapter(catalog_adapter);
    }


    private Loai timLoai(ArrayList<Loai> dsLoai,String tam){
        for (Loai a: dsLoai) {
            if(a.getTen().equals(tam))
                return a;
        }
        return null;
    }
    private ArrayList<Loai> ChangValueLoai() {
        ArrayList<Loai> dsLoainew = cleanListFoodInLoai(dsloai);
        ArrayList<Food> dsFoodnew =  foods;
        for (Food food: dsFoodnew) {
            for (Loai loai: dsLoainew) {
                if(food.getLoai().getTen().equals(loai.getTen())){
                    if(sameFood(loai.getFoods(),food.getMa())==false){
                        ArrayList<Food> foods =loai.getFoods();
                        foods.add(food);
                        break;
                    }


                }
            }
        }

        return dsLoainew;
    }

    private ArrayList<Loai> cleanListFoodInLoai(ArrayList<Loai> dsLoai) {
        ArrayList<Loai> Loais = dsLoai;
        for (Loai loai: Loais) {
            loai.setFoods(new ArrayList<Food>());
        }
        return Loais;
    }
    private boolean sameFood(ArrayList<Food> dsFood, String tam) {

        for (Food a: dsFood) {
            if(a.initMa(tam))
                return true;
        }
        return false;
    }
}