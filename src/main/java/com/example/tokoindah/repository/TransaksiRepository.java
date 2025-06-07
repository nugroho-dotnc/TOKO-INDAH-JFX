package com.example.tokoindah.repository;

import com.example.tokoindah.database.Database;
import com.example.tokoindah.model.Pelanggan;
import com.example.tokoindah.model.Transaksi;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransaksiRepository extends Database {

    public String createTransaksi() {
        try {
           String sql = "INSERT INTO transaksi (nomor_transaksi, tanggal_transaksi) VALUES (?, ?)";
           String kode = generateKodeTransansaksi();
           Date tanggalTransaksi = Date.valueOf(LocalDate.now());
           PreparedStatement stm = conn.prepareStatement(sql);
           stm.setString(1, kode);
           stm.setDate(2, tanggalTransaksi);
           stm.executeUpdate();
           return kode;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Transaksi> getTransaksi() {
        try {
           ArrayList<Transaksi> dataTransaksi = new ArrayList<>();
           String sql = "SELECT * FROM transaksi LEFT JOIN pelanggan ON transaksi.kode_pelanggan = pelanggan.kode_pelanggan";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet result = stmt.executeQuery();
           while(result.next()) {
               String nomorTransaksi = result.getString("nomor_transaksi");
               Date tanggalTransaksi = result.getDate("tanggal_transaksi");
               String catatanTransaksi = result.getString("catatan_transaksi");
               float total = result.getFloat("total");
               float pembayaran = result.getFloat("pembayaran");
               float kembalian = result.getFloat("kembalian");
               String kodePelanggan = result.getString("kode_pelanggan");
               String nama = result.getString("nama");
               String noTelepon = result.getString("telepon");
               String alamat = result.getString("alamat");
               Pelanggan pelanggan = new Pelanggan(kodePelanggan, nama, noTelepon, alamat);
               Transaksi t = new Transaksi(nomorTransaksi, tanggalTransaksi, catatanTransaksi, total, null, pembayaran, kembalian, pelanggan);
               dataTransaksi.add(t);
               return dataTransaksi;
           };
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void updateTransaksi(String kode_transaksi, String catatan_transaksi, float total, float pembayaran, float kembalian, String kode_pelanggan) {
        try {
           String sql = "UPDATE transaksi SET catatan_transaksi = ?, total = ?, pembayaran = ?, kembalian = ?, kode_pelanggan = ? WHERE nomor_transaksi = ?";
           PreparedStatement stm = conn.prepareStatement(sql);
           stm.setString(1, catatan_transaksi);
           stm.setFloat(2, total);
           stm.setFloat(3, pembayaran);
           stm.setFloat(4, kembalian);
           stm.setString(5, kode_pelanggan);
           stm.setString(6, kode_transaksi);
           int rowAffected = stm.executeUpdate();
           System.out.println(rowAffected);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void countTransaksi() {
        try {
            String sql = "SELECT COUNT(*) FROM transaksi";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet result = stm.executeQuery();
            if(result.next()) {
                int count = result.getInt(1);
                System.out.println(count);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static String generateKodeTransansaksi() {
        try {
           String sql = "SELECT COUNT(*) from transaksi";
           PreparedStatement stm = conn.prepareStatement(sql);
           ResultSet result = stm.executeQuery();
           if(!result.next()) {
              return "Cant generate transaction code!";
           }
           int count = result.getInt(1);
           String convertedCount = String.valueOf(count + 1);
           String kodeTransaksi = "TR00" + convertedCount;
           return kodeTransaksi;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        TransaksiRepository t = new TransaksiRepository();
        ArrayList<Transaksi> transaksiData = t.getTransaksi();
        for(Transaksi transaksi : transaksiData) {
            System.out.println("Nama pelanggan : " + transaksi.getPelanggan().getNama());
        }

    }



}
