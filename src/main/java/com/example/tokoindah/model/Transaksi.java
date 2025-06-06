package com.example.tokoindah.model;

import java.util.Date;
import java.util.ArrayList;

public class Transaksi {
    int id_transaksi;
    private String nomor_transaksi;
    private Date tanggal_transaksi;
    private String nama_pelanggan;
    private String catatan_transaksi;
    private int total;
    private ArrayList<Keranjang> list_keranjang;
    private int pembayaran;
    private int kembalian;
    private Pelanggan pelanggan;

    public Transaksi(int id_transaksi,String nomor_transaksi, Date tanggal_transaksi, String nama_pelanggan, String catatan_transaksi, int total, ArrayList<Keranjang> list_keranjang, int pembayaran, int kembalian, Pelanggan pelanggan) {
        this.id_transaksi = id_transaksi;
        this.nomor_transaksi = nomor_transaksi;
        this.tanggal_transaksi = tanggal_transaksi;
        this.nama_pelanggan = nama_pelanggan;
        this.catatan_transaksi = catatan_transaksi;
        this.total = total;
        this.list_keranjang = list_keranjang;
        this.pembayaran = pembayaran;
        this.kembalian = kembalian;
        this.pelanggan = pelanggan;
    }

    public int getIdTransaksi() {
        return id_transaksi;
    }
    public String getNomorTransaksi() {
        return nomor_transaksi;
    }
    public Date getTanggalTransaksi() {
        return tanggal_transaksi;
    }
    public String getNamaPelanggan() {
        return nama_pelanggan;
    }
    public String getCatatanTransaksi() {
        return catatan_transaksi;
    }
    public int getTotal() {
        return total;
    }
    public ArrayList<Keranjang> getListKeranjang() {
        return list_keranjang;
    }
    public int getPembayaran() {
        return pembayaran;
    }
    public int getKembalian() {
        return kembalian;
    }
    public Pelanggan getPelanggan() {
        return pelanggan;
    }
}
