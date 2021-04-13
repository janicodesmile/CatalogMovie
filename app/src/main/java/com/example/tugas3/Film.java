package com.example.tugas3;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Film {

    private String Judul;
    private String Sinopsis;
    private String Keterangan;
    private String Tahun;
    private String ImageUrl;
    private String key;

    public Film() {
    }

    public Film(String judul, String sinopsis, String keterangan, String tahun,  String imageurl, String key) {
        Judul = judul;
        Sinopsis = sinopsis;
        Keterangan = keterangan;
        Tahun = tahun;
        ImageUrl = imageurl;
        this.key = key;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        Sinopsis = sinopsis;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public String getTahun() {
        return Tahun;
    }

    public void setTahun(String tahun) {
        Tahun = tahun;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}