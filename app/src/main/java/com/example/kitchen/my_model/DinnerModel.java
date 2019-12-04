package com.example.kitchen.my_model;

public class DinnerModel {

    public String title, deskripsi;

    public DinnerModel() {}

    public DinnerModel(String title, String deskripsi) {
        this.title = title;
        this.deskripsi = deskripsi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
