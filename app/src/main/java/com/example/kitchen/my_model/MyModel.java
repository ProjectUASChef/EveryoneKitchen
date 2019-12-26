package com.example.kitchen.my_model;

public class MyModel {

    public String bahan,judul, gambar,langkah;

    public String getBahan()
    {
        return bahan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setBahan(String bahan)
    {
        this.bahan = bahan;
    }

    public String getJudul()
    {
        return judul;
    }

    public void setJudul(String judul)
    {
        this.judul = judul;
    }

    public String getLangkah() {
        return langkah;
    }

    public void setLangkah(String langkah) {
        this.langkah = langkah;
    }

    public MyModel(String bahan, String nama) {
        this.bahan = bahan;
        this.judul = nama;
        this.gambar = gambar;
        this.langkah = langkah;
    }


    public MyModel() {}

}
