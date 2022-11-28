package com.example.myapplication.Domain;

import java.io.Serializable;
import java.util.ArrayList;

public class CustumerDomain implements Serializable
{
    private String User;
    private String Name;
    private  String passString;
    private String Andress;
    private String Gmail;
    private String PhoneNumber;
    private ArrayList<PopularDomain> Product;

    public CustumerDomain(String user, String name, String passString, String andress, String gmail, String phoneNumber) {
        User = user;
        Name = name;
        this.passString = passString;
        Andress = andress;
        Gmail = gmail;
        PhoneNumber = phoneNumber;
        Product= null;
    }

    public CustumerDomain(String user, String name, String passString, String andress, String gmail, String phoneNumber, ArrayList<PopularDomain> product) {
        User = user;
        Name = name;
        this.passString = passString;
        Andress = andress;
        Gmail = gmail;
        PhoneNumber = phoneNumber;
        Product = product;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassString() {
        return passString;
    }

    public void setPassString(String passString) {
        this.passString = passString;
    }

    public String getAndress() {
        return Andress;
    }

    public void setAndress(String andress) {
        Andress = andress;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public ArrayList<PopularDomain> getProduct() {
        return Product;
    }

    public void setProduct(ArrayList<PopularDomain> product) {
        Product = product;
    }
}
