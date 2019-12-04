package com.example.kitchen.my_model;

import com.example.kitchen.fragments.lunchFragment;

public class LunchModel {

    private String title;
    private String deskripsi;

    public LunchModel() {}

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

    public LunchModel(String title, String deskripsi) {
        this.title = title;
        this.deskripsi = deskripsi;
    }
}
