package com.example.myapplication.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String title;
    private String pic;
    private String decription;
    private double fee;
    private int numberInCart;

    public PopularDomain(String title, String pic, String decription, double fee) {
        this.title = title;
        this.pic = pic;
        this.decription = decription;
        this.fee = fee;
    }

    public PopularDomain(String title, String pic, String decription, double fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.decription = decription;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
