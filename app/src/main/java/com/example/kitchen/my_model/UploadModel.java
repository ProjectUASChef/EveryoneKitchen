package com.example.kitchen.my_model;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

public class UploadModel {
    String gambar, judul, langkah, bahan, spinner;

//    public UploadModel(Task<Uri> downloadUrl, String trim, String langkah, String bahan, String spinner) {
//
//    }

    public UploadModel(String gambar, String judul, String langkah, String bahan, String spinner) {
        this.gambar = gambar;
        this.judul = judul;
        this.langkah = langkah;
        this.bahan = bahan;
        this.spinner = spinner;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getLangkah() {
        return langkah;
    }

    public void setLangkah(String langkah) {
        this.langkah = langkah;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getKunci() {
        return spinner;
    }

    public void setKunci(String kunci) {
        this.spinner = kunci;
    }
}