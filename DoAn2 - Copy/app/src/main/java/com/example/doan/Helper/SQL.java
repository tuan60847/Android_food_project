package com.example.doan.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.doan.Entity.Food;

public class SQL extends SQLiteOpenHelper {
    public SQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }
    public void setData(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase db=getWritableDatabase();
        return db.rawQuery(sql,null);
    }
    public void insertFoods(String id,String name, double price, int amount, String image,String type)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into Food values (?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,id);
        statement.bindString(2,name);
        statement.bindDouble(3,price);
        statement.bindLong(4,amount);
        statement.bindString(5,image);
        statement.bindString(6,type);
        statement.executeInsert();
    }
    public void insertFoods(Food food)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into Food values (?,?,?,?,?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,food.getMa());
        statement.bindString(2,food.getTen());
        statement.bindDouble(3,food.getGia());
        statement.bindLong(4,food.getSoLuong());
        statement.bindString(5,food.getHinh());
        statement.bindString(6,food.getLoai().getTen());
        statement.executeInsert();
    }
    public void updateFood(String id,String name, double price, int amount, byte[] image,String type){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update Food set name = ?, type = ?, image = ?, price = ?, amount = ?  where id = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(6,id);
        statement.bindString(1,name);
        statement.bindDouble(4,price);
        statement.bindLong(5,amount);
        statement.bindBlob(3,image);
        statement.bindString(2,type);
        statement.executeUpdateDelete();
    }
    public void updateFood(Food food){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update Food set name = ?, type = ?, image = ?, price = ?, amount = ?  where id = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(6,food.getMa());
        statement.bindString(1,food.getTen());
        statement.bindDouble(4,food.getGia());
        statement.bindLong(5,food.getSoLuong());
        statement.bindString(3,food.getHinh());
        statement.bindString(2,food.getLoai().getTen());
        statement.executeUpdateDelete();
    }
    public void DeleteFood(String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From Food   where id = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,id);
        statement.executeUpdateDelete();
    }

    public void DeleteFoodInLoai(String NameLoai){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From Food   where type = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,NameLoai);
        statement.executeUpdateDelete();
    }

    public void DeleteLoai(String id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From Loai  where ID = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,id);
        statement.executeUpdateDelete();

    }
    public void DeleteNameLoai(String name){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From Loai  where name = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.executeUpdateDelete();

    }



    public void insertLoai(String Id,String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Insert into Loai values (?,?)";
        SQLiteStatement statement=db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,Id);
        statement.bindString(2,name);
        statement.executeInsert();
    }

    public void updateLoai(String Id,String name){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update Loai set name = ? where ID = ?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1,name);
        statement.bindString(2,Id);
        statement.executeUpdateDelete();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
