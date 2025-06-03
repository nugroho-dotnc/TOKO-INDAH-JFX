package com.example.tokoindah.model;

public class Keranjang {
    int id;
    private String kode_produk;
    private String nama_produk;
    private float harga;
    private int quantity;
    private float subtotal;

    public Keranjang(int id, String kode_produk, String nama_produk, float harga, int quantity, float subtotal) {
        this.kode_produk = kode_produk;
        this.id = id;
        this.nama_produk = nama_produk;
        this.harga = harga;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getKodeProduk() {
        return kode_produk;
    }

    public String getNamaProduk() {
        return nama_produk;
    }

    public float getHarga() {
        return harga;
    }
    public int getQuantity() {
        return quantity;
    }
    public float getSubtotal() {
        return subtotal;
    }

    public int getId() {
        return id;
    }
}

