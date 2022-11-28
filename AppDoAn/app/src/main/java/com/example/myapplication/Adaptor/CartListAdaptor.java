package com.example.myapplication.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.PopularDomain;
import com.example.myapplication.Helper.ManagementCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CartListAdaptor extends RecyclerView.Adapter<CartListAdaptor.ViewHolder> {
    ArrayList<PopularDomain> popularDomains ;
     private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdaptor(ArrayList<PopularDomain> popularDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.popularDomains = popularDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(popularDomains.get(position).getTitle());


        holder.feeEachItem.setText(String.valueOf(popularDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round(popularDomains.get(position).getFee()*popularDomains.get(position).getNumberInCart()*100)/100));
        holder.num.setText(String.valueOf(popularDomains.get(position).getNumberInCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(popularDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
        holder.plusItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumbericPopular(popularDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();

                    }
                });

            }
        });
        holder.miniusItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.miniusNumbericPopular(popularDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem;
        ImageView pic,plusItems,miniusItems;
        TextView totalEachItem,num;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            title=(TextView)itemView.findViewById(R.id.titleCartPopular);
            feeEachItem= itemView.findViewById(R.id.forEachItem);
            pic = itemView.findViewById(R.id.imgCart);
            totalEachItem = itemView.findViewById(R.id.total_Item);
            num=itemView.findViewById(R.id.numberItemTxt);
            plusItems=itemView.findViewById(R.id.plusCartBtn);
            miniusItems=itemView.findViewById(R.id.miniusCartBtn);
        }
    }
}
