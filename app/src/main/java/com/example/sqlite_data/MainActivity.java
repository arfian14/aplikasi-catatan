package com.example.sqlite_data;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.example.sqlite_data.adapter.adapterdataitem;
import com.example.sqlite_data.basisdata.myDB;
import com.example.sqlite_data.dataadapter.itemjudul;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    myDB basisdata;
    ListView listdata;
    ArrayList<itemjudul> dataDB;
    Cursor datacursor;
    ImageButton tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listdata = findViewById(R.id.listdata);
        tambah = findViewById(R.id.btntambah);
        refrestlist();
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambah = new Intent(MainActivity.this,tambah_Aktivity.class);
                startActivity(tambah);
            }

        });

    }

    @Override
    protected void  onResume(){
        refrestlist();
        super.onResume();
    }

    public void refrestlist(){
        basisdata = new myDB(this);
        datacursor = basisdata.tampil_data();
        dataDB = new ArrayList<>();
        datacursor.moveToFirst();
        for (int i = 0; i < datacursor.getCount(); i++) {
            datacursor.moveToPosition(i);
            dataDB.add(new itemjudul(datacursor.getString(1),datacursor.getString(2)));
        }

        listdata.setAdapter( new adapterdataitem(MainActivity.this,R.layout.adapteritem,dataDB));
        listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent tampil = new Intent(MainActivity.this,tampil_Aktivity.class);
                datacursor.moveToPosition(position);
                tampil.putExtra("ID",position);
                startActivity(tampil);
            }
        });
    }

}



