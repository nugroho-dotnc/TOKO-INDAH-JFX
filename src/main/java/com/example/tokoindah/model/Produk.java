package com.example.tokoindah.model;
import java.sql.Date;

public class Produk {
    private String kode_produk;
    private String nama_produk;
    private String kategori_produk;
    int stock;
    float harga_modal;
    float harga_jual;
    Date tanggal_input;

    public Produk(String kode_produk, String nama_produk, String kategori_produk, int stock, float harga_modal, float harga_jual, Date tanggal_input) {
        this.kode_produk = kode_produk;
        this.nama_produk = nama_produk;
        this.kategori_produk = kategori_produk;
        this.stock = stock;
        this.harga_jual = harga_jual;
        this.harga_modal = harga_modal;
        this.tanggal_input = tanggal_input;
    }

    public String getKodeProduk() {
        return kode_produk;
    }    

    public String getNamaProduk() {
        return nama_produk;
    }

    public String getKategoriProduk() {
        return kategori_produk;
    }

    public int getStock() {
        return stock;
    }

    public float getHargaModal() {
        return harga_modal;
    }

    public float getHargaJual() {
        return harga_jual;
    }

    public Date getTanggalInput() {
        return tanggal_input;
    }

}
