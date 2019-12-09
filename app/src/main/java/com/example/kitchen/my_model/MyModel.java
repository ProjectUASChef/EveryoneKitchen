package com.example.kitchen.my_model;

public class MyModel {

    public String bahan,nama, gambar;

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

    public String getNama()
    {
        return nama;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }

    public MyModel(String bahan, String nama) {
        this.bahan = bahan;
        this.nama = nama;
        this.gambar = gambar;
    }


    public MyModel() {}

}
