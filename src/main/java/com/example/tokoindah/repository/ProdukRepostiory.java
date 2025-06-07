package com.example.tokoindah.repository;
import com.example.tokoindah.database.Database;
import com.example.tokoindah.model.Produk;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Random;

public class ProdukRepostiory extends Database{

    public void createProduk(String kode_produk, String nama_produk, String kategori_produk, int stock, float harga_modal, float harga_jual) {
        try {
            String sql = "INSERT INTO produk (kode_produk, nama_produk, kategori_produk, stock, harga_modal, harga_jual, tanggal_input) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            Date tanggal_input = Date.valueOf(LocalDate.now());
            ps.setString(1, kode_produk);
            ps.setString(2, nama_produk);
            ps.setString(3, kategori_produk);
            ps.setInt(4, stock);
            ps.setFloat(5, harga_modal);
            ps.setFloat(6, harga_jual);
            ps.setDate(7, tanggal_input);
            ps.executeUpdate();
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Produk> getAllProduk() {
        try {
            ArrayList<Produk> products = new ArrayList<>();
            String sql = "SELECT * from produk";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String kode_produk = rs.getString("kode_produk");
                String nama_produk = rs.getString("nama_produk");
                String kategori_produk = rs.getString("kategori_produk");
                int stock = rs.getInt("stock");
                float harga_modal = rs.getFloat("harga_modal");
                float harga_jual = rs.getFloat("harga_jual");
                Date tanggal_input = rs.getDate("tanggal_input");
                Produk produk = new Produk(kode_produk, nama_produk, kategori_produk, stock, harga_modal, harga_jual, tanggal_input);
                products.add(produk);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        };

        return null;
    }

    public Produk getProdukByKodeProduk(String kode_produk) {
        try {
            String sql = "SELECT * FROM produk WHERE kode_produk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kode_produk);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String kodeProduk = rs.getString("kode_produk");
                String namaProduk = rs.getString("nama_produk");
                String kategoriProduk = rs.getString("kategori_produk");
                int stock = rs.getInt("stock");
                float hargaModal = rs.getFloat("harga_modal");
                float hargaJual = rs.getFloat("harga_jual");
                Date tanggalInput = rs.getDate("tanggal_input");
                Produk produk = new Produk(kodeProduk, namaProduk, kategoriProduk, stock, hargaModal, hargaJual, tanggalInput);
                return produk;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void deleteProduk(String kodeProduk) {
        try {
            String sql = "DELETE FROM produk WHERE kode_produk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kodeProduk);
            int rowsDeleted = ps.executeUpdate();
            if(rowsDeleted > 0 ) {
                System.out.println("Data has been deleted");
            } else {
                System.out.println("Data has not been deleted");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateProduk(String kode_produk, String nama_produk, String kategori_produk, int stock, float harga_modal, float harga_jual) {
        try {
            String sql = "UPDATE produk SET nama_produk = ?, kategori_produk = ?, stock = ?, harga_modal = ?, harga_jual = ? WHERE kode_produk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama_produk);
            ps.setString(2, kategori_produk);
            ps.setInt(3, stock);
            ps.setFloat(4, harga_modal);
            ps.setFloat(5, harga_jual);
            ps.setString(6, kode_produk);
            int rowAffected = ps.executeUpdate();
            if(rowAffected > 0) {
                System.out.println("Data has been updated");
            } else {
                System.out.println("Data has not been updated");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Produk> searchProduk(String nama_produk) {
        try {
            ArrayList<Produk> products = new ArrayList<>();
            String sql = "SELECT * FROM produk WHERE nama_produk LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String kodeProduk = rs.getString("kode_produk");
                String namaProduk = rs.getString("nama_produk");
                String kategoriProduk = rs.getString("kategori_produk");
                int stock = rs.getInt("stock");
                float harga_modal = rs.getFloat("harga_modal");
                float harga_jual = rs.getFloat("harga_jual");
                Date tanggal_input = rs.getDate("tanggal_input");
                Produk produk = new Produk(kodeProduk, namaProduk, kategoriProduk, stock, harga_modal, harga_jual, tanggal_input);
                products.add(produk);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String generateKodeProduk() {
        String tanggal = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
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
        p.createProduk("SB01", "Singkong balado", "makanan", 50, 10000, 15000);
        p.createProduk("UB01","Ubi Bakar", "makanan", 50, 8000, 17000);
    }

}
