package com.example.myapplication.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.Domain.PopularDomain;
import com.example.myapplication.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void InsertPopular(PopularDomain popularDomain){
        ArrayList<PopularDomain> listPopular =getListCart();
        boolean exist = false;
        int n=0;
        for (int i = 0 ;i <listPopular.size();i++){
            if(listPopular.get(i).getTitle().equals(popularDomain.getTitle())){
                exist = true   ;
                n=i;
                break;
            }
        }
        if(exist){
            listPopular.get(n).setNumberInCart(popularDomain.getNumberInCart());
        } else{
            listPopular.add(popularDomain);
        }
        tinyDB.putListObject("CartList",listPopular);
        Toast.makeText(context, "Add To Food Success", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<PopularDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumbericPopular(ArrayList<PopularDomain> popularDomains, int pos , ChangeNumberItemsListener changeNumberItemsListener){
        popularDomains.get(pos).setNumberInCart(popularDomains.get(pos).getNumberInCart()+1);
        tinyDB.putListObject("CartList", popularDomains);
        changeNumberItemsListener.change();

    }
    public void miniusNumbericPopular(ArrayList<PopularDomain> popularDomains, int pos , ChangeNumberItemsListener changeNumberItemsListener){
        if(popularDomains.get(pos).getNumberInCart()==1){
            popularDomains.remove(pos);
        }
        else{
            popularDomains.get(pos).setNumberInCart(popularDomains.get(pos).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", popularDomains);
        changeNumberItemsListener.change();

    }
    public double getTotalFee(){
        ArrayList<PopularDomain> popularDomains = getListCart();
        double fee = 0.0;
        for (int i = 0;i< popularDomains.size();i++){
            fee+= (popularDomains.get(i).getFee()*popularDomains.get(i).getNumberInCart());
        }
        return fee;
    }

}
