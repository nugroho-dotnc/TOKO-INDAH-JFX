package com.example.tokoindah.repository;

import com.example.tokoindah.database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Date;

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
        pelangganRepository.createPelanggan("jamal", "082134556", "jalan solo");
    }
}
