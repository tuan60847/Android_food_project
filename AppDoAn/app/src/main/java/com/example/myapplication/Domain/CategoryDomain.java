package com.example.myapplication.Domain;

import java.util.ArrayList;

public class CategoryDomain {
    private String title;
    private String pic;
    private ArrayList<CategoryDomain> product;

    public CategoryDomain(String title, String pic, ArrayList<CategoryDomain> product) {
        this.title = title;
        this.pic = pic;
        this.product = product;
    }

    public ArrayList<CategoryDomain> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<CategoryDomain> product) {
        this.product = product;
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

    public CategoryDomain(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }
}
