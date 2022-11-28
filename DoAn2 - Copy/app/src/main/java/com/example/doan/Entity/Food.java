package com.example.doan.Entity;

import java.io.Serializable;
import java.util.Objects;

public class Food implements Serializable {
    private String Ma;
    private String Ten;
    private  float Gia;
    private int SoLuong;
    private String Hinh;
    private  Loai loai;

    public Food(String ma, String ten, float gia, Loai loai) {
        Ma = ma;
        Ten = ten;
        Gia = gia;
        SoLuong=0;
        this.loai = loai;
    }

    public Food() {
        Hinh="";
        SoLuong=0;
    }

    public Food(String ma, String ten, float gia, int soLuong, String hinh, Loai loai) {
        Ma = ma;
        Ten = ten;
        Gia = gia;
        SoLuong = soLuong;
        Hinh = hinh;
        this.loai = loai;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float gia) {
        Gia = gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public Loai getLoai() {
        return loai;
    }

    public void setLoai(Loai loai) {
        this.loai = loai;
    }

    @Override
    public String toString() {
        return "Food{" +
                "Ma='" + Ma + "\n" +
                ", Ten='" + Ten + "\n" +
                ", Gia=" + Gia + "\n"+
                ", loai=" + loai + "\n"+

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return Ma.equals(food.Ma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Ma);
    }

    public boolean initMa(String text){
        return this.Ma.equals(text);
    }
    public boolean initTen(String text){
        return this.Ten.equals(text);
    }

}
