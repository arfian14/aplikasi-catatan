package com.example.sqlite_data;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite_data.basisdata.myDB;

public class login_aktivity extends AppCompatActivity {
    TextView etusername;
    TextView etpassword;
    Button login;
    myDB basisdata;
    Cursor data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aktivity);
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        login = findViewById(R.id.btnlogin);
        basisdata = new myDB(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = basisdata.login(etusername.getText().toString());
                if (data.getCount() != 0){
                    data.moveToFirst();
                    if (data.getString(1).equals(etpassword.getText().toString())){
                        Intent main = new Intent(login_aktivity.this,MainActivity.class);
                        startActivity(main);
                        finish();
                    } else {
                        Toast.makeText(login_aktivity.this, "password salah", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(login_aktivity.this, "username salah", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
