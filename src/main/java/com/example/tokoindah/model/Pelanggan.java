package com.example.tokoindah.model;

public class Pelanggan {
    private String kode_pelanggan;
    private String nama;
    private String no_telp;
    private String alamat;

    public String getKode_pelanggan() {
        return kode_pelanggan;
    }

    public String getNama() {
        return nama;
    }

    public String getNotelp() {
        return no_telp;
    }
    public String getAlamat() {
        return alamat;
    }

    public Pelanggan(String kode_pelanggan, String nama, String notelp, String alamat) {
        this.kode_pelanggan = kode_pelanggan;
        this.nama = nama;
        this.no_telp = notelp;
        this.alamat = alamat;
    }
}
