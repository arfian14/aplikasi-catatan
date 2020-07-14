package com.example.sqlite_data;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Aktivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__aktivity);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //kode yang akan di eksekusi ketika timer selesai
                final Intent menuutama = new Intent(Splash_Aktivity.this, login_aktivity.class);
                startActivity(menuutama);
                finish();
            }
            //waktu timer dengan format milisecond
        },2000);
    }
}
