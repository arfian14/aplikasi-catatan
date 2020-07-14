package com.example.sqlite_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sqlite_data.basisdata.myDB;

public class update_Aktivity extends AppCompatActivity {
EditText upjudul,upsubjdul,upisi;
ImageButton updata;
myDB basisdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__aktivity);
        upjudul = findViewById(R.id.etjudulupdate);
        upsubjdul = findViewById(R.id.etsubjudulupdate);
        upisi = findViewById(R.id.etisiupdate);
        updata = findViewById(R.id.btnupdate);
        final Bundle bundle = getIntent().getExtras();
        upjudul.setText(bundle.getString("judul"));
        upsubjdul.setText(bundle.getString("subjudul"));
        upisi.setText(bundle.getString("isi"));
        basisdata = new myDB(this);
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updatedata = basisdata.updatedata(bundle.getString("judul"),upjudul.getText().toString(),
                        upsubjdul.getText().toString(),upisi.getText().toString());
                if (updatedata == true){
                    Toast.makeText(update_Aktivity.this, "data berhasil di update"+upjudul.getText().toString(),
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(update_Aktivity.this, "data tidak dapat di update",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
