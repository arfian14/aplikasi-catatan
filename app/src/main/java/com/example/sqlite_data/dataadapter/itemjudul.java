package com.example.sqlite_data.dataadapter;

public class itemjudul {
    private String judul;
    private String subjudul;

    public itemjudul(String judul, String subjudul) {
        this.judul = judul;
        this.subjudul = subjudul;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSubjudul() {
        return subjudul;
    }

    public void setSubjudul(String subjudul) {
        this.subjudul = subjudul;
    }
}
