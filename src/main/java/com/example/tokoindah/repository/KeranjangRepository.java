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
            // Mulai transaksi manual
            conn.setAutoCommit(false);

            String insertSql = "INSERT INTO keranjang (nomor_transaksi, kode_produk, nama_produk, harga, qty, subtotal) VALUES (?,?,?,?,?,?)";
            String updateStockSql = "UPDATE produk SET stock = stock - ? WHERE kode_produk = ?";

            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            PreparedStatement updateStockStmt = conn.prepareStatement(updateStockSql);

            for (Keranjang keranjang : keranjangList) {
                // Simpan ke tabel keranjang
                insertStmt.setString(1, keranjang.getNomorTransaksi());
                insertStmt.setString(2, keranjang.getKodeProduk());
                insertStmt.setString(3, keranjang.getNamaProduk());
                insertStmt.setFloat(4, keranjang.getHarga());
                insertStmt.setInt(5, keranjang.getQty());
                insertStmt.setFloat(6, keranjang.getSubtotal());
                insertStmt.addBatch();

                // Kurangi stok produk
                updateStockStmt.setInt(1, keranjang.getQty());
                updateStockStmt.setString(2, keranjang.getKodeProduk());
                updateStockStmt.addBatch();
            }

            // Eksekusi batch insert & update
            insertStmt.executeBatch();
            updateStockStmt.executeBatch();

            // Commit transaksi
            conn.commit();
            conn.setAutoCommit(true);

            System.out.println("Keranjang berhasil ditambahkan dan stok diperbarui.");

        } catch (SQLException e) {
            try {
                // Rollback jika gagal
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            System.out.println("Gagal menyimpan keranjang atau memperbarui stok: " + e.getMessage());
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