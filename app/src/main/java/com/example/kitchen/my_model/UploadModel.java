package com.example.kitchen.my_model;

public class UploadModel {
    String gambar,judul, langkah, bahan, kunci;

    public UploadModel(String gambar, String judul, String langkah, String bahan, String kunci) {
        this.gambar = gambar;
        this.judul = judul;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kunci = kunci;
    }

    public UploadModel(String judul, String langkah, String bahan, String kunci) {
        this.judul = judul;
        this.langkah = langkah;
        this.bahan = bahan;
        this.kunci = kunci;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
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
}
