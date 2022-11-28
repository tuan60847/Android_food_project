package com.example.myapplication.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Activity.ShowDetailActivity;
import com.example.myapplication.Domain.PopularDomain;
import com.example.myapplication.R;

import java.util.ArrayList;

public class
PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
    ArrayList<PopularDomain> popularDomains ;

    public PopularAdaptor(ArrayList<PopularDomain> popularDomains) {
        this.popularDomains = popularDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ìnflates = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular ,parent,false);
        return new ViewHolder(ìnflates);
    }


    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
        holder.title.setText(popularDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(popularDomains.get(position).getFee()));

        String PicUrl = popularDomains.get(position).getPic();

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(PicUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = position;
                PopularDomain Hello = popularDomains.get(a);

//                try{
                    Intent Kai = new Intent(holder.itemView.getContext(),ShowDetailActivity.class);
                    Context context = view.getContext();
                    Kai.putExtra("Object",Hello);
//                    Kai.putExtra("Title",Hello.getTitle());
//                    Kai.putExtra("Fee",Hello.getFee());
//                    Kai.putExtra("Pic",Hello.getPic());
//                    Kai.putExtra("Decription",Hello.getDecription());
//                    Kai.putExtra("NumberInCart",Hello.getNumberInCart());

                    holder.itemView.getContext().startActivity(Kai);
                    Toast.makeText(holder.itemView.getContext(),"Thành Công :" + Hello.getTitle() ,Toast.LENGTH_LONG).show();

//                } catch (Exception e){
//                    Toast.makeText(holder.itemView.getContext(),"tHẤT BẠI :" + Hello.getTitle() ,Toast.LENGTH_LONG).show();
//                }

//                intent.putExtra("object",popularDomains.get(0));
//                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee,addbtn;
        ImageView pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            fee = itemView.findViewById(R.id.fee);
            addbtn = itemView.findViewById(R.id.addBtn);

        }
    }
}
