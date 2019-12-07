package com.example.kitchen.my_model;

public class DinnerModel {

    public String bahan;

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public DinnerModel(String bahan, String nama) {
        this.bahan = bahan;
        this.nama = nama;
    }

    public String nama;

    public DinnerModel() {}

}
