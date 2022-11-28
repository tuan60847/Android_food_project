package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    ImageButton Login;
    int tam = 0;
    String User = "admin",Pass="123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getBunde();
        AddEvent();
    }

    private void AddEvent() {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equals(User)){
                    if (password.getText().toString().equals(Pass)){
                        int a = 5;
                        startActivity(new Intent(LoginActivity.this,Catalog_Acctivity.class));
                    }
                    else {
                        password.setText("");

                    }
                }
            }
        });
    }

    private void getBunde() {
        username=findViewById(R.id.edt_username);
        password=findViewById(R.id.edt_password);
        Login=findViewById(R.id.btn_success_account);

    }
}