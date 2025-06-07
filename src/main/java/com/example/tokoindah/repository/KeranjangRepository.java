package com.example.tokoindah.repository;
import com.example.tokoindah.database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.example.tokoindah.model.Keranjang;

public class KeranjangRepository extends Database {
    public void createKeranjang(ArrayList<Keranjang> keranjangList) {
        try {
           String sql = "INSERT INTO keranjang (nomor_transaksi, kode_produk, nama_produk, harga, qty, subtotal) VALUES (?,?,?,?,?,?)";
           PreparedStatement stmt = conn.prepareStatement(sql);
           for(Keranjang keranjang : keranjangList) {
               stmt.setString(1, keranjang.getNomorTransaksi());
               stmt.setString(2, keranjang.getKodeProduk());
               stmt.setString(3, keranjang.getNamaProduk());
               stmt.setFloat(4, keranjang.getHarga());
               stmt.setInt(5, keranjang.getQty());
               stmt.setFloat(6, keranjang.getSubtotal());
               stmt.addBatch();
           }
           int[] result = stmt.executeBatch();
           System.out.println(result + " rows inserted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Keranjang> getKeranjangByNomorTransaksi(String nomorTransaksi) {
        try {
           ArrayList<Keranjang> keranjangList = new ArrayList<>();
           String sql = "SELECT * FROM keranjang WHERE nomor_transaksi = ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nomorTransaksi);
           ResultSet rs = stmt.executeQuery();
           while(rs.next()) {
               int id = rs.getInt("id_keranjang");
               String nomor_transaksi = rs.getString("nomor_transaksi");
               String nama_produk = rs.getString("nama_produk");
               float harga = rs.getFloat("harga");
               int qty = rs.getInt("qty");
               float subtotal = rs.getFloat("subtotal");
               String kodeProduk = rs.getString("kode_produk");
               keranjangList.add(new Keranjang(id, nomor_transaksi, kodeProduk, nama_produk, harga, qty, subtotal));
           }
           return keranjangList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        KeranjangRepository keranjangRepository = new KeranjangRepository();
        ArrayList<Keranjang> k = keranjangRepository.getKeranjangByNomorTransaksi("TR004");
        for(Keranjang keranjang : k) {
            System.out.println(keranjang.getIdKeranjang());
        }
    }
}