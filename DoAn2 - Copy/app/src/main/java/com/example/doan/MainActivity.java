package com.example.doan;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.Adapter.FoodAdapter;
import com.example.doan.Entity.Food;
import com.example.doan.Entity.Loai;
import com.example.doan.Fragment.ViewPaper_fragment;
import com.example.doan.Helper.SQL;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String DB_NAME = "dbDoAn.db";
    final String DB_PATH_SUFIX = "/databases/";

    public static final String Tag = MainActivity.class.getName();
    SQL sql = new SQL(this,  "dbDoAn.db", null,  1);
    Button btn_add,btn_reset,btn_repair,btn_search,btn_open_Loai,btn_open_img;
    public static TextView txtMa,txtTen,txtGia,txtCata;
    public static FoodAdapter adapter;
    public static ImageView imgFood;
    public static ArrayList<Food> dsFood;
    ListView lvDSFood;
    Food food = null;
    ArrayAdapter<Loai> adapterLoai;
    ArrayList<Loai> dsLoai;
    Loai loai = null;
    ListView lvDSLoai;
    public static String Hinh;
    private ViewPager mviewViewPager;
    ViewPaper_fragment viewPagerAdapter = new ViewPaper_fragment(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    boolean visiblelvLoai=false;
    private final int My_QUEST_CODE = 10;

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(Tag,"onMainActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data==null) {
                            return;
                        }
                        Uri uri = data.getData();

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

                            imgFood.setImageBitmap(bitmap);

                            Hinh = encodeTobase64(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBundle();

        ActivtyMain();
       bottomNavigation();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_all,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnAbout:
                break;
            case  R.id.btn_exit:
                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void bottomNavigation(){

        LinearLayout btnCat = findViewById(R.id.btn_Catalog_Cat);
        LinearLayout btnFood = findViewById(R.id.btn_Catalog_food);
        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Catalog_Acctivity.class));
            }
        });
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
    }
    private void getDataFromDB() {
        adapter.clear();
        SQLiteDatabase database = openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
//         Cursor cursor = database.rawQuery("SELECT * FROM Food", null);
//         Cursor cursor = database.rawQuery("SELECT * FROM SinhVien WHERE namsinh >= ?", new String[]{"2000"});
//        Cursor cursor = database.query(
//                "SinhVien",
//                null, //new String[]{"ten", "namsinh"},
//                "namsinh >= ?",
//                new String[]{"2000"},
//                null,
//                null,
//                null
//        );
        Cursor cursor = database.rawQuery(
                "SELECT * FROM Food",
                null
        );
        while (cursor.moveToNext()) {
            String ma = cursor.getString(0);
            String ten = cursor.getString(1);
            float Gia =cursor.getFloat(2);
            int SoLuong=cursor.getInt(3);
            String Hinh = cursor.getString(4);
            String MaLoai= cursor.getString(5);
            Loai loai = GetLoaiInDSLoai(MaLoai);
            adapter.add(new Food(ma,ten,Gia,SoLuong,Hinh,loai));
        }
        cursor.close();
        database.close();
    }

    private Loai GetLoaiInDSLoai(String maLoai) {
        ArrayList<Loai> loais = dsLoai;
        for (Loai a: loais) {
            if(a.getMa().equals(maLoai)) return a;
        }
        return null;
    }

    private ArrayList<Loai> ChangValueLoai() {
        ArrayList<Loai> dsLoainew = cleanListFoodInLoai(dsLoai);
        ArrayList<Food> dsFoodnew =  dsFood;
        for (Food food: dsFoodnew) {
            for (Loai loai: dsLoainew) {
                if(food.getLoai().getTen().equals(loai.getTen())){
                    if(sameFood(loai.getFoods(),food.getMa())==false){
                        ArrayList<Food> foods =loai.getFoods();
                        foods.add(food);
                        break;
                    }


                }
            }
        }

        return dsLoainew;
    }



    private void ActivtyMain() {
        // Hàm chọn Hình đừng đụng
        btn_open_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = txtMa.getText().toString();

                String Ten = txtTen.getText().toString();
                    try {
                        float Gia;
                        if(txtGia.getText().toString().equals("")==false) {
                             Gia = Float.parseFloat(txtGia.getText().toString());
                        }
                        else {
                            Gia=0.0f;
                        }
                        String capture = txtCata.getText().toString();
                        Loai loai = new Loai();
                        for (Loai a : dsLoai) {
                            if(a.getTen().equals(capture)==true){
                                loai = a;
                                ChangeValue();

                                break;
                            }
                        }
                        ArrayList<Food> arrayListfood= timkiemdsfood(ma,Ten,Gia,loai);
                        ArrayList<Food> tam  = dsFood;
                        dsFood.clear();
                        dsFood.addAll(arrayListfood);
                        adapter.notifyDataSetChanged();


                    }catch (Exception exception){
                        Toast.makeText(MainActivity.this, "Giá Không hợp Lệ", Toast.LENGTH_SHORT).show();
                        txtGia.setText("");
                    }

            }
        });
        btn_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food=new Food();

                String tam = txtMa.getText().toString();

                if(sameFood(dsFood,tam)==true){
                    food.setMa(tam);
                    int index = dsFood.indexOf(food);
                    food.setTen(txtTen.getText().toString());
                    try {
                        food.setGia(Float.parseFloat(txtGia.getText().toString()));
                        String capture = txtCata.getText().toString();
                        for (Loai a : dsLoai) {
                            if(a.getTen().equals(capture)==true){
                                loai = a;
                                food.setLoai(loai);

                                if(Hinh!=null)
                                    food.setHinh(Hinh);
                                dsFood.set(index,food);
                                ChangeValue();
                                adapter.notifyDataSetChanged();
                                resetAll();

                                sql.updateFood(food);
                                Hinh=null;
                                break;
                            }
                        }

                    }catch (Exception exception){
                        Toast.makeText(MainActivity.this, "Giá Không hợp Lệ", Toast.LENGTH_SHORT).show();
                        txtGia.setText("");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Mã Không Tồn Tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_open_Loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visiblelvLoai==false){
                    lvDSLoai.setVisibility(View.VISIBLE);
                    visiblelvLoai = true;
                }
                else{
                    lvDSLoai.setVisibility(View.GONE);
                    visiblelvLoai = false;
                }

            }
        });
        lvDSLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Loai a = dsLoai.get(position);
                txtCata.setText(a.getTen());
                lvDSLoai.setVisibility(View.GONE);
            }
        });
        lvDSFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Hello", Toast.LENGTH_SHORT).show();

            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food=new Food();
                String tam = txtMa.getText().toString();
                if(sameFood(dsFood,tam)==false){
                    food.setMa(tam);
                    food.setTen(txtTen.getText().toString());
                    try {
                        food.setGia(Float.parseFloat(txtGia.getText().toString()));
                        String capture = txtCata.getText().toString();
                        for (Loai a : dsLoai) {
                            if(a.getTen().equals(capture)==true){
                                loai = a;
                                food.setLoai(loai);
                                if(Hinh!=null)
                                    food.setHinh(Hinh);
                                dsFood.add(food);
                                ChangeValue();
                                adapter.notifyDataSetChanged();
                                resetAll();

                                sql.insertFoods(food);
                                Hinh=null;
                                break;
                            }
                        }

                    }catch (Exception exception){
                        Toast.makeText(MainActivity.this, "Giá Không hợp Lệ", Toast.LENGTH_SHORT).show();
                        txtGia.setText("");
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Mã Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                }

            }
        });


    btn_reset.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetAll();
            dsFood=getListFood();
            dsFood=adapter.getObjects();
            dsLoai=ChangValueLoai();
            ArrayList<Food> foods = new ArrayList<>();
            foods=ChangeValueFood();
            dsFood.clear();
            dsFood.addAll(foods);
            adapter.notifyDataSetChanged();
        }
    });


    }

    private ArrayList<Food> getListFood() {
        ArrayList<Food> foods = new ArrayList<>();
        Cursor cursor = sql.getData("select * From Food");
        while (cursor.moveToNext()) {
            Food a = new Food();
            a.setMa(cursor.getString(0));
            a.setTen(cursor.getString(1));
            a.setGia(cursor.getFloat(2));
            a.setSoLuong(cursor.getInt(3));
            a.setHinh(cursor.getString(4));
            String tenLoai = cursor.getString(5);
            Loai loai=timLoai(dsLoai,tenLoai);
            a.setLoai(loai);
            dsFood.add(a);
        }
        return foods;
    }

    private ArrayList<Food> timkiemdsfood(String ma, String ten, Float gia, Loai loai) {
        ArrayList<Food> foods = new ArrayList<>();
        if(!ma.equals("") || !ten.equals("")){
        for (Food food:dsFood) {
            if(food.getMa().equals(ma)==true){
                foods.add(food);
                break;
            }else if(food.getTen().equals(ten)== true){
                foods.add(food);
                break;
            }
        }
        return foods;
        }
        else{
            for (Food food:dsFood) {
                if(food.getGia()==gia||food.getLoai().equals(loai)){
                    foods.add(food);

                }
            }
            return foods;
        }
    }

    private void Swap(ArrayList<Food> dsFood, ArrayList<Food> arrayListfood) {
        ArrayList<Food> tam  = dsFood;
        dsFood = arrayListfood;
        arrayListfood=tam;
    }


    private ArrayList<Food> ChangeValueFood() {
        ArrayList<Food> foods = new ArrayList<>();
        for (Loai l: dsLoai) {
            ArrayList<Food> food1 = l.getFoods();
            for (Food food : food1) {
                foods.add(food);
            }

        }
        int n = 5;
        return foods;
    }

    private void resetAll() {
    txtMa.setText("");
    txtTen.setText("");
    txtCata.setText("-Cataloge-");
    txtGia.setText("");
    imgFood.setImageResource(R.drawable.rerise);

    }



    private void getBundle() {



        sql.setData("Create Table If not Exists Food (id Varchar Primary Key , name Varchar, price Double, amount Integer, image Varchar , type Varchar)");

        sql.setData("Create Table If not Exists Loai (ID Varchar Primary Key , name Varchar)");
        btn_add=findViewById(R.id.btnAdd);
        btn_reset=findViewById(R.id.btnReset);
        btn_repair=findViewById(R.id.btnRepair);
        btn_search=findViewById(R.id.btnSearch);
        txtMa=findViewById(R.id.txtMa_cat);
        txtTen=findViewById(R.id.txtTen_Cat);
        txtGia=findViewById(R.id.txtGia);
        lvDSFood=findViewById(R.id.lstFood);
       lvDSLoai=findViewById(R.id.lstLoai);
        btn_open_Loai=findViewById(R.id.btn_openListLoai);
        txtCata = findViewById(R.id.txtLoai);
        btn_open_img =findViewById(R.id.btnImg);
        imgFood = findViewById(R.id.imgFood);
        mviewViewPager=findViewById(R.id.ViewFoodPager);
        mviewViewPager.setAdapter(viewPagerAdapter);




        dsLoai =new ArrayList<>();
//        sql.insertLoai("1","Loai a");
//        sql.insertLoai("2","Loai b");
        Cursor cursor = sql.getData("select * From Loai");
        while (cursor.moveToNext()) {
            Loai a = new Loai();
            a.setMa(cursor.getString(0));
            a.setTen(cursor.getString(1));
            dsLoai.add(a);
        }
        dsFood =new ArrayList<>();
        cursor = sql.getData("select * From Food");
        while (cursor.moveToNext()) {
            Food a = new Food();
            a.setMa(cursor.getString(0));
            a.setTen(cursor.getString(1));
            a.setGia(cursor.getFloat(2));
            a.setSoLuong(cursor.getInt(3));
            a.setHinh(cursor.getString(4));
            String tenLoai = cursor.getString(5);
            Loai loai=timLoai(dsLoai,tenLoai);
            a.setLoai(loai);
            dsFood.add(a);
        }

        ChangeValue();
        adapterLoai = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,dsLoai);
        lvDSLoai.setAdapter(adapterLoai);

        adapter = new FoodAdapter(MainActivity.this,R.layout.item_food_adapter,dsFood);
        lvDSFood.setAdapter(adapter);
        dsFood = adapter.getObjects();

    }

    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGrallary();
        }else{
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,My_QUEST_CODE);

        }
    }

    private void openGrallary() {
        Intent intent = new Intent();
        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);
        resultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }

    private void ChangeValue() {


        dsLoai=ChangValueLoai();
        ArrayList<Food> foods = new ArrayList<>();
        foods=ChangeValueFood();
        dsFood.clear();
        dsFood.addAll(foods);

    }
    private ArrayList<Loai> cleanListFoodInLoai(ArrayList<Loai> dsLoai) {
        ArrayList<Loai> Loais = dsLoai;
        for (Loai loai: Loais) {
            loai.setFoods(new ArrayList<Food>());
        }
        return Loais;
    }
    private boolean sameFood(ArrayList<Food> dsFood, String tam) {

        for (Food a: dsFood) {
            if(a.initMa(tam))
                return true;
        }
        return false;
    }
    private Loai timLoai(ArrayList<Loai> dsLoai,String tam){
        for (Loai a: dsLoai) {
            if(a.getTen().equals(tam))
                return a;
        }
        return null;
    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 90, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0,      decodedByte.length);
    }


}