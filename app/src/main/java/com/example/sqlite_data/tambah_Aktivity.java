package com.example.sqlite_data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sqlite_data.basisdata.myDB;

import java.util.ArrayList;

public class tambah_Aktivity extends AppCompatActivity {
    ImageButton tambah;
    EditText judul, subjudul, isi;
    myDB basisdata;
    RadioGroup opsistatus;
    int data_status;
    CheckBox ckcatatan,ckkeuangan,ckpendidikan;
    ArrayList<String> datagenre;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah__aktivity);

        basisdata = new myDB(this);
        tambah = findViewById(R.id.btntambah);
        judul = findViewById(R.id.etjudul);
        subjudul = findViewById(R.id.etsubjudul);
        isi = findViewById(R.id.etisi);
        ckcatatan = findViewById(R.id.ckcatatan);
        ckkeuangan = findViewById(R.id.ckkeuangan);
        ckpendidikan = findViewById(R.id.ckpendidikan);
        datagenre = new ArrayList<>();
        opsistatus = findViewById(R.id.status_catatan);
        opsistatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_penting:
                        data_status = 1;
                        break;
                    case R.id.rb_tdk_penting:
                        data_status = 0;
                        break;
                }
            }
        });


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckcatatan.isChecked()){
                    datagenre.add(ckcatatan.getText().toString());
                }
                if (ckkeuangan.isChecked()){
                    datagenre.add(ckkeuangan.getText().toString());
                }
                if (ckpendidikan.isChecked()){
                    datagenre.add(ckpendidikan.getText().toString());
                }
                Boolean hasil = basisdata.tambahdata(judul.getText().toString(),
                        subjudul.getText().toString(),
                        isi.getText().toString(),data_status,datagenre.toString());

                if (hasil == true){
                    Toast.makeText(tambah_Aktivity.this, "data berhasil di simpan",
                            Toast.LENGTH_SHORT).show();
                    tambah_Aktivity.this.finish();
                } else {
                    Toast.makeText(tambah_Aktivity.this, "data tidak dapat di simpan",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
}
