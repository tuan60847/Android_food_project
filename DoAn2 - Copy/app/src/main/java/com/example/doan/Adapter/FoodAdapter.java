package com.example.doan.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan.Entity.Food;
import com.example.doan.Entity.Loai;
import com.example.doan.Fragment.Food_fragment;
import com.example.doan.Helper.SQL;
import com.example.doan.MainActivity;
import com.example.doan.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends ArrayAdapter<Food>    {
    private final Activity context;
    private final int  resource;
    private final ArrayList<Food> objects;
    int i = -1;
    private View view;

    public FoodAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public ArrayList<Food> getObjects() {
        return objects;
    }

    @Nullable
    @Override
    public Food getItem(int position) {
        return super.getItem(position);
    }


    public Food getItem() {
        return super.getItem(i);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        LayoutInflater inflater = this.context.getLayoutInflater();
        view = inflater.inflate(R.layout.item_food_adapter, null);

        TextView Ten = view.findViewById(R.id.txt_name_adp);

        TextView Gia = view.findViewById(R.id.txt_price_adapter);
        ImageView Hinhadapter = view.findViewById(R.id.imgfoodadapter);
        TextView Cata = view.findViewById(R.id.txt_Cataloge_adapter);
        Food food = this.objects.get(position);

        Ten.setText(food.getTen());
        Gia.setText(food.getGia()+"");
        Cata.setText(food.getLoai().getTen());
        Bitmap bitmap = MainActivity.decodeBase64(food.getHinh());
        Hinhadapter.setImageBitmap(bitmap);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity.txtMa.setText(food.getMa());
                MainActivity.txtTen.setText(food.getTen());
                MainActivity.txtGia.setText(food.getGia()+"");
                MainActivity.txtCata.setText(food.getLoai().getTen());
                MainActivity.imgFood.setImageBitmap(bitmap);
                MainActivity.Hinh=food.getHinh();
                Food_fragment.Fm_id.setText(food.getMa());
                Food_fragment.Fm_Name.setText(food.getTen());
                Food_fragment.Fm_cost.setText(food.getGia()+"");
                Food_fragment.Fm_Numberic.setText(food.getSoLuong()+"");
                Food_fragment.Fm_pic.setImageBitmap(bitmap);
                Food_fragment.Fm_Cata.setText(food.getLoai().getTen());

            }
        });
        Button btnXoa= view.findViewById(R.id.btnDel);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        SQL sql = new SQL(this.context,  "dbDoAn.db", null,  1);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyXoa(position);
                sql.DeleteFood(food.getMa());

            }
        });
        return view;

    }


    private void XuLyChon(int position) {
        Food food = this.objects.get(position);
        i=position;

        this.notifyDataSetChanged();
    }

    private void XuLyXoa(int position) {

        this.objects.remove(position);
        this.notifyDataSetChanged();

    }


}
