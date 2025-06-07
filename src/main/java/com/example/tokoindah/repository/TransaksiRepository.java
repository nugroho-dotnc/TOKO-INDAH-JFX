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
           String sql = "SELECT * FROM transaksi LEFT JOIN pelanggan ON transaksi.kode_pelanggan = pelanggan.kode_pelanggan WHERE deleted = false";
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
               Transaksi t = new Transaksi(nomorTransaksi, tanggalTransaksi, catatanTransaksi, total,  pembayaran, kembalian, pelanggan);
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

    public Transaksi getTransaksiByKode(String kode) {
        try {
           String sql = "SELECT * FROM transaksi LEFT JOIN pelanggan ON transaksi.kode_pelanggan = pelanggan.kode_pelanggan WHERE nomor_transaksi = ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, kode);
           ResultSet rs = stmt.executeQuery();
           if(rs.next()) {
               String nomorTransaksi = rs.getString(1);
               Date tanggalTransaksi = rs.getDate(2);
               String catatan_transaksi = rs.getString(3);
               float total = rs.getFloat(4);
               float pembayaran = rs.getFloat(5);
               float kembalian = rs.getFloat(6);
               String kodePelanggan = rs.getString(7);
               String nama = rs.getString(10);
               String telepon = rs.getString(11);
               String alamat = rs.getString(12);
               Pelanggan p = new Pelanggan(kodePelanggan, nama, telepon, alamat);
               return new Transaksi(nomorTransaksi, tanggalTransaksi, catatan_transaksi, total, pembayaran, kembalian, p);
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void deleteTransaksi(String nomorTransaksi) {
        try {
            String sql = "UPDATE transaksi SET deleted = true WHERE nomor_transaksi = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomorTransaksi);
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0) {
                System.out.println("Data has been deleted");
            } else {
                System.out.println("No data deleted");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Transaksi> searchByTanggal(String tanggal) {
        try {
           Date convertedDate = Date.valueOf(tanggal);
           ArrayList<Transaksi> dataTransaksi = new ArrayList<>();
           String sql = "SELECT * FROM transaksi LEFT JOIN pelanggan ON transaksi.kode_pelanggan = pelanggan.kode_pelanggan WHERE tanggal_transaksi = ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setDate(1, convertedDate);
           ResultSet rs = stmt.executeQuery();
           if(rs.next()) {
               String nomorTransaksi = rs.getString("nomor_transaksi");
               Date tanggalTransaksi = rs.getDate("tanggal_transaksi");
               String catatanTransaksi = rs.getString("catatan_transaksi");
               float total = rs.getFloat("total");
               float pembayaran = rs.getFloat("pembayaran");
               float kembalian = rs.getFloat("kembalian");
               String kodePelanggan = rs.getString("kode_pelanggan");
               String namaPelanggan = rs.getString("nama");
               String telepon = rs.getString("telepon");
               String alamat = rs.getString("alamat");
               Pelanggan p = new Pelanggan(kodePelanggan, namaPelanggan, telepon, alamat);
               Transaksi t = new Transaksi(nomorTransaksi, tanggalTransaksi, catatanTransaksi ,total, pembayaran, kembalian, p);
               dataTransaksi.add(t);
               return dataTransaksi;
           }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
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
        Transaksi transaksi = t.getTransaksiByKode("TR004");
        System.out.println(transaksi.getNomorTransaksi());
    }



}
