package com.example.sqlite_data.basisdata;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDB extends SQLiteOpenHelper{
    private static final String NamaDB = "Notebook.db";
    private static final String Namatabelcatatan = "catatan";
    private static final String kolom1 = "ID";
    private static final String kolom2 = "JUDUL";
    private static final String kolom3 = "SUBJUDUL";
    private static final String kolom4 = "ISI";
    private static final String kolom5 = "STATUS";
    private static final String kolom6 = "GENRE";




    private static final String Namatabeluser = "user";
    private static final String user1 = "username";
    private static final String user2 = "password";


    public myDB(Context context) {
        super(context, NamaDB, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ Namatabelcatatan +" ("+kolom1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ""+kolom2+" TEXT, "+kolom3+" TEXT,"+kolom4+" TEXT,"+kolom5+" INTEGER,"+kolom6+" TEXT)");


        db.execSQL("create table "+Namatabeluser+" ("+user1+" TEXT PRIMARY KEY,"+
                ""+user2+" TEXT)");

        ContentValues data = new ContentValues();
        data.put(user1,"arfiansyah");
        data.put(user2, "123456");
        db.insert(Namatabeluser,null,data);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Namatabelcatatan);


        db.execSQL("DROP TABLE IF EXISTS "+Namatabeluser);
        onCreate(db);
    }

    public Boolean tambahdata(String judul, String subjudul, String isi,int status,String genre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(kolom2,judul);
        data.put(kolom3, subjudul);
        data.put(kolom4,isi);
        data.put(kolom5,status);
        data.put(kolom6,genre);
        long hasil = db.insert(Namatabelcatatan,null,data);
        if (hasil == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor tampil_data(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + Namatabelcatatan, null);
        return data;
    }

    public Cursor login(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor pass = db.rawQuery("SELECT * FROM "+ Namatabeluser+" WHERE "+user1+"=?",
                new String[]{username});
        return pass;
    }

    public Boolean deletedata(String judul){
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(Namatabelcatatan,kolom2+" = ?",new String[]{judul});
        if (delete == -1){
            return false;
        } else {
            return true;
        }
    }

    public Boolean updatedata(String judullama, String judulbaru, String subjudul, String isi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataupdate = new ContentValues();
        dataupdate.put(kolom2,judulbaru);
        dataupdate.put(kolom3,subjudul);
        dataupdate.put(kolom4,isi);
        long hasil = db.update(Namatabelcatatan,dataupdate,kolom2+" = ?",new String[]{judullama});
        if (hasil == -1){
            return false;
        } else {
            return true;
        }
    }

}

