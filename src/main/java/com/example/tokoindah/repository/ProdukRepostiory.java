package com.example.tokoindah.repository;
import com.example.tokoindah.database.Database;
import com.example.tokoindah.model.Produk;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ProdukRepostiory extends Database{
//    public Produk getProdukByKode(String kode) {
//
//    }

    public void createProduk(String nama_produk, String kategori_produk, int stock, float harga_modal, float harga_jual, String tanggal_input) {
        try {
            String sql = "INSERT INTO produk (kode_produk, nama_produk, kategori_produk, stock, harga_modal, harga_jual, tanggal_input) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            String kode_produk = generateKodeProduk();
            ps.setString(1, kode_produk);
            ps.setString(2, nama_produk);
            ps.setString(3, kategori_produk);
            ps.setInt(4, stock);
            ps.setFloat(5, harga_modal);
            ps.setFloat(6, harga_jual);
            ps.setString(7, tanggal_input);
            ps.executeUpdate();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String generateKodeProduk() {
        String tanggal = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String karakterAcak = getRandomAlphaNumeric(4);
        return "PROD" + tanggal + karakterAcak;
    }


    private static String getRandomAlphaNumeric(int panjang) {
        String karakter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < panjang; i++) {
            int index = rand.nextInt(karakter.length());
            sb.append(karakter.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ProdukRepostiory p = new ProdukRepostiory();
        p.createProduk("sampurna", "rokok", 30, 32000, 35000, "Mei 2025");
    }

}
