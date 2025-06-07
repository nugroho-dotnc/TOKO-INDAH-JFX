package com.example.tokoindah.repository;
import com.example.tokoindah.database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.example.tokoindah.model.Keranjang;

public class KeranjangRepository extends Database {
//    CREATE KERANJANG HARUS MENERIMA ARRAY LIST, PERMASALAHAN NYA ADALAH PADA MODEL
    public void createKeranjang(int nomor_transaksi, String kode_produk, String nama_produk, float harga, int quantity, float subtotal) {
        try {
            String sql = "INSERT INTO keranjang (nomor_transaksi, kode_produk, nama_produk, harga, qty, subtotal) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, nomor_transaksi);
            ps.setString(2, kode_produk);
            ps.setString(3, nama_produk);
            ps.setFloat(4, harga);
            ps.setInt(5, quantity);
            ps.setFloat(6, subtotal);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Keranjang> showKeranjang(int nomor_transaksi) {
        ArrayList<Keranjang> keranjangList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM keranjang WHERE nomor_transaksi = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, nomor_transaksi);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Keranjang keranjang = new Keranjang(
                        rs.getInt("id_keranjang"),
                        rs.getString("nomor_transaksi"),
                        rs.getString("kode_produk"),
                        rs.getString("nama_produk"),
                        rs.getFloat("harga"),
                        rs.getInt("qty"),
                        rs.getFloat("subtotal")
                );
                keranjangList.add(keranjang);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return keranjangList;
    }

    public static void main(String[] args) {
        KeranjangRepository keranjangRepository = new KeranjangRepository();
        keranjangRepository.createKeranjang(12, "PRDK32", "buku", 20000, 3, 60000);
        keranjangRepository.createKeranjang(12, "PRDK22", "tas", 50000, 2, 10000);

        ArrayList<Keranjang> keranjangList = keranjangRepository.showKeranjang(12);
    }
}