package com.example.tokoindah.repository;

import com.example.tokoindah.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import com.example.tokoindah.model.Pelanggan;

public class PelangganRepository extends Database {
    public void createPelanggan(String nama, String no_telpon, String alamat) {
        try {
            String sql = "INSERT INTO pelanggan (kode_pelanggan, nama, telepon, alamat) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, generateKodePelanggan());
            ps.setString(2, nama);
            ps.setString(3, no_telpon);
            ps.setString(4, alamat);
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Pelanggan> getPelangggan() {
        try {
            ArrayList<Pelanggan> pelangganList = new ArrayList<>();
            String sql = "SELECT * FROM pelanggan";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String kodePelanggan = rs.getString("kode_pelanggan");
                String namaPelanggan = rs.getString("nama");
                String telepon = rs.getString("telepon");
                String alamat = rs.getString("alamat");
                Pelanggan pelanggan = new Pelanggan(kodePelanggan, namaPelanggan, telepon, alamat);
                pelangganList.add(pelanggan);
            }
            return pelangganList;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void deletePelanggan(String kode_pelanggan) {
        try {
            String sql = "DELETE FROM pelanggan WHERE kode_pelanggan = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kode_pelanggan);
            int rowAffected = ps.executeUpdate();
            if(rowAffected > 0) {
                System.out.println("Pelanggan has been deleted");
            } else {
                System.out.println("Pelanggan has not been deleted");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Pelanggan getPelangganByKode(String kode_pelanggan) {
        try {
            String sql = "SELECT * FROM pelanggan WHERE kode_pelanggan = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kode_pelanggan);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String kodePelanggan = rs.getString("kode_pelanggan");
                String nama = rs.getString("nama");
                String telepon = rs.getString("telepon");
                String alamat = rs.getString("alamat");
                Pelanggan pelanggan = new Pelanggan(kodePelanggan, nama, telepon, alamat);
                return pelanggan;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Pelanggan> searchPelanggan(String namaPelanggan) {
        try {
            ArrayList<Pelanggan> pelangganList = new ArrayList<>();
            String sql = "SELECT * FROM pelanggan WHERE nama LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + namaPelanggan + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String kodePelanggan = rs.getString("kode_pelanggan");
                String nama = rs.getString("nama");
                String telepon = rs.getString("telepon");
                String alamat = rs.getString("alamat");
                Pelanggan pelanggan = new Pelanggan(kodePelanggan, nama, telepon, alamat);
                pelangganList.add(pelanggan);
            }
            return pelangganList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String generateKodePelanggan() {
        String tanggal = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String karakterAcak = getRandomAlphaNumeric(4);
        return "PLG" + tanggal + karakterAcak;
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
        PelangganRepository pelangganRepository = new PelangganRepository();
        pelangganRepository.createPelanggan("yanto sujatmiko", "08926877923", "Bekasi bagian ngalor sitik");
    }
}
