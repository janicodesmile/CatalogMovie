package com.example.tugas3;

public class Request  {

    private String Nama, Judul, Key,Date,Time;

    public Request(){
    }

    public Request(String nama, String judul, String key, String date, String time) {
        this.Nama = nama;
        this.Judul = judul;
        this.Key = key;
        this.Date = date;
        this.Time = time;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        this.Nama = nama;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        this.Judul = judul;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        this.Key = key;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }
}



