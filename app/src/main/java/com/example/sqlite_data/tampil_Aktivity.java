package com.example.sqlite_data;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlite_data.basisdata.myDB;

import java.util.Objects;

public class tampil_Aktivity extends AppCompatActivity {
TextView tvjudul,tvsubjudul,tvisi,tvgenre,tvstatus;
ImageButton btnupdate,hapus;
myDB database;
Cursor datamentah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil__aktivity);
        database = new myDB(this);
        tvjudul = findViewById(R.id.tvjudul);
        tvsubjudul = findViewById(R.id.tvsubjudul);
        tvisi = findViewById(R.id.tvisi);
        btnupdate = findViewById(R.id.btnupdate);
        hapus = findViewById(R.id.hapusdata);
        tvgenre = findViewById(R.id.genre);
        tvstatus = findViewById(R.id.status);
        refresh();


    }
    @Override
    protected void onResume(){
        refresh();
        super.onResume();
    }
    private void refresh(){
        final Bundle bundle = getIntent().getExtras();
        datamentah = database.tampil_data();
        datamentah.moveToPosition(Objects.requireNonNull(bundle).getInt("ID"));
        tvjudul.setText(datamentah.getString(1));
        tvsubjudul.setText(datamentah.getString(2));
        tvisi.setText(datamentah.getString(3));
        tvgenre.setText(datamentah.getString(5));
        if (datamentah.getInt(4) == 0){
            tvstatus.setVisibility(View.GONE);
        }
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(tampil_Aktivity.this,update_Aktivity.class);
                update.putExtra("judul",tvjudul.getText().toString());
                update.putExtra("subjudul",tvsubjudul.getText().toString());
                update.putExtra("isi",tvisi.getText().toString());
                startActivity(update);
            }
        });
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder hapusdata = new AlertDialog.Builder(tampil_Aktivity.this);
                hapusdata.setTitle("hapus data");
                hapusdata.setMessage("apakah anda yakin ingin menghapus catatan");
                hapusdata.setPositiveButton("ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean delete = database.deletedata(datamentah.getString(1));
                        if (delete){
                            Toast.makeText(tampil_Aktivity.this, "data berhasil di hapus", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(tampil_Aktivity.this, "data tidak terhapus", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                hapusdata.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusdata.setCancelable(false);
                    }
                });
                hapusdata.show();
            }
        });
    }
}
