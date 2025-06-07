package com.example.tokoindah.model;

import java.util.Date;
import java.util.ArrayList;

public class Transaksi {
    private String nomor_transaksi;
    private Date tanggal_transaksi;
    private String nama_pelanggan;
    private String catatan_transaksi;
    private float total;
    private ArrayList<Keranjang> list_keranjang;
    private float pembayaran;
    private float kembalian;
    private Pelanggan pelanggan;

    public Transaksi(String nomor_transaksi, Date tanggal_transaksi, String catatan_transaksi, float total, ArrayList<Keranjang> list_keranjang, float pembayaran, float kembalian, Pelanggan pelanggan) {
        this.nomor_transaksi = nomor_transaksi;
        this.tanggal_transaksi = tanggal_transaksi;
        this.catatan_transaksi = catatan_transaksi;
        this.total = total;
        this.list_keranjang = list_keranjang;
        this.pembayaran = pembayaran;
        this.kembalian = kembalian;
        this.pelanggan = pelanggan;
    }

    public String getNomorTransaksi() { return nomor_transaksi; }
    public Date getTanggalTransaksi() {
        return tanggal_transaksi;
    }
    public String getCatatanTransaksi() {
        return catatan_transaksi;
    }
    public float getTotal() {
        return total;
    }
    public ArrayList<Keranjang> getListKeranjang() {
        return list_keranjang;
    }
    public float getPembayaran() {
        return pembayaran;
    }
    public float getKembalian() {
        return kembalian;
    }
    public Pelanggan getPelanggan() {
        return pelanggan;
    }
}
