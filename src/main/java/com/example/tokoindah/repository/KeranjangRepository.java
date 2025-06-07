package com.example.tokoindah.repository;
import com.example.tokoindah.database.Database;
import com.example.tokoindah.model.Keranjang;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Date;

public class KeranjangRepository extends Database {

    public void createKeranjang(int nomor_transaksi, String kode_produk, String nama_produk, float harga, int quantity, float subtotal) {
        try {
            String sql = "INSERT INTO keranjang (id_keranjang, nomor_transaksi, kode_produk, nama_produk, harga, quantity, subtotal) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, generateIdKeranjang());
            ps.setInt(2, nomor_transaksi);
            ps.setString(3, kode_produk);
            ps.setString(4, nama_produk);
            ps.setFloat(5, harga);
            ps.setInt(6, quantity);
            ps.setFloat(7, subtotal);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String generateIdKeranjang() {
        int idAcak = generateRandomId(4);
        return "KRJ" + idAcak;
    }

    public static int generateRandomId(int panjang) {
        Random rand = new Random();
        return rand.nextInt(9000) + 1000;
    }

    public static void main(String[] args) {
        KeranjangRepository keranjangRepository = new KeranjangRepository();
        keranjangRepository.createKeranjang(12, "PRDK12", "sepatu", 10000, 3, 3000);
    }
}