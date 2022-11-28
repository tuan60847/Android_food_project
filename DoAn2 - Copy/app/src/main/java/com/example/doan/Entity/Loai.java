package com.example.doan.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Loai implements Serializable {
    private String ma;
    private String ten;
    private ArrayList<Food> foods;
    public Loai(String ma, String ten, ArrayList<Food> foods) {
        this.ma = ma;
        this.ten = ten;
        this.foods = foods;
    }
    public Loai(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
        this.foods=new ArrayList<>();
    }


    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return ten + " " ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loai)) return false;
        Loai loai = (Loai) o;
        return ma.equals(loai.ma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ma);
    }

    public Loai() {
        this.foods=new ArrayList<>();
    }


}
