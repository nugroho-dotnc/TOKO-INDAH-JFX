package com.example.tokoindah.model;

public class Keranjang {
    int id_keranjang;
    String nomor_transaksi;
    private String kode_produk;
    private String nama_produk;
    private float harga;
    private int quantity;
    private float subtotal;

    public Keranjang(int id_keranjang, String nomor_transaksi, String kode_produk, String nama_produk, float harga, int quantity, float subtotal) {
        this.id_keranjang = id_keranjang;
        this.nomor_transaksi = nomor_transaksi;
        this.kode_produk = kode_produk;
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    // Getters
    public String getKodeProduk() {
        return kode_produk;
    }

    public String getNamaProduk() {
        return nama_produk;
    }

    public float getHarga() {
        return harga;
    }

    public int getQty() {
        return quantity;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public int getIdKeranjang() {
        return id_keranjang;
    }

    public String getNomorTransaksi() {
        return nomor_transaksi;
    }

    // Setters
    public void setKodeProduk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    public void setNamaProduk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public void setQty(int quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public void setIdKeranjang(int id_keranjang) {
        this.id_keranjang = id_keranjang;
    }

    public void setNomorTransaksi(String nomor_transaksi) {
        this.nomor_transaksi = nomor_transaksi;
    }
}
