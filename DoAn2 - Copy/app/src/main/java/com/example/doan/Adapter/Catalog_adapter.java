package com.example.doan.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doan.Catalog_Acctivity;
import com.example.doan.Entity.Loai;
import com.example.doan.Helper.SQL;
import com.example.doan.R;

import java.util.ArrayList;

public class Catalog_adapter extends ArrayAdapter<Loai> {
     Activity context;
     int  resource;
     ArrayList<Loai> objects;
    private  View view;
    public Catalog_adapter(@NonNull Activity context,@NonNull int resource, @NonNull ArrayList<Loai> objects) {
        super(context, resource, objects);
        this.objects=objects;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        view = inflater.inflate(R.layout.item_catalog_adapter,null);
        TextView nameLoai = view.findViewById(R.id.txtNameCata_apdater);
        TextView maLoai= view.findViewById(R.id.txtIDCata);
        Button btndel = view.findViewById(R.id.btndel_cat_adapter);
        ImageView img= view.findViewById(R.id.imageAdapterCat);
        img.setImageResource(R.drawable.rerise);
        Loai loai = objects.get(position);
        nameLoai.setText(loai.getTen());
        maLoai.setText(loai.getMa());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Catalog_Acctivity.Name_cata.setText(loai.getTen());
                Catalog_Acctivity.ID_cata.setText(loai.getMa());
                Catalog_Acctivity.ID_cata.setEnabled(false);
                Catalog_Acctivity.addCatalog.setVisibility(View.GONE);
                Catalog_Acctivity.repairCat.setVisibility(View.VISIBLE);
            }
        });
        SQL sql = new SQL(this.context,  "dbDoAn.db", null,  1);
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loai.getFoods().size()>0)
                    Dialogshow(position,sql);
                else{
                    XuLyXoa(objects.indexOf(loai));
                    sql.DeleteFoodInLoai(loai.getTen());
                    sql.DeleteLoai(loai.getMa());
                }

//                XuLyXoa(position);
//                sql.DeleteFoodInLoai(loai.getTen());
//                sql.DeleteLoai(loai.getMa());
            }
        });
        return view;




    }

    private void Dialogshow(int position, SQL sql) {
        Loai vt = this.objects.get(position);

        AlertDialog alertDialog = new AlertDialog.Builder(this.context)
//set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                .setTitle("Are you sure to Delete catalog")
//set message
                .setMessage("this is " + vt.getFoods().size() + " Food in here")
//set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked

                        XuLyXoa(objects.indexOf(vt));
                        sql.DeleteFoodInLoai(vt.getTen());
                        sql.DeleteLoai(vt.getMa());

                    }
                })
//set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(context.getApplicationContext(),"Nothing Happened",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void XuLyXoa(int position) {
        this.objects.remove(position);
        this.notifyDataSetChanged();
    }
}
