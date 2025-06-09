package com.example.tokoindah.repository;
import com.example.tokoindah.database.Database;

import java.net.DatagramSocket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardRepository extends Database{
    public int countPelanggan() {
       try {
           int count = 0;
           String sql = "SELECT COUNT(*) as count FROM pelanggan";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet res = stmt.executeQuery();
           if(res.next()) {
               count = res.getInt("count");
               System.out.println(count);
           }
           return count;
       } catch (SQLException e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
       return 0;
    }

    public int countTransaksi() {
        try {
            int count = 0;
            String sql = "SELECT COUNT(*) as count FROM transaksi  WHERE deleted = false";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                count = res.getInt("count");
                System.out.println(count);
            }
            return count;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public float countRevenue() {
        try {
           float revenue = 0;
           String sql = "SELECT sum(total) as revenue FROM transaksi";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet res = stmt.executeQuery();
           if(res.next()) {
               revenue = res.getFloat("revenue");
               System.out.println("Rp." + revenue);
           }
           return revenue;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.getMessage();
        }
        return 0;
    }

    public int countBarangTerjual() {
        try {
           int terjual = 0;
           String sql = "SELECT sum(qty) as terjual FROM keranjang";
           PreparedStatement stmt = conn.prepareStatement(sql);
           ResultSet res = stmt.executeQuery();
           if(res.next()) {
             terjual = res.getInt("terjual");
             return terjual;
           }
           return terjual;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }


    public static void main(String[] args) {
        DashboardRepository d = new DashboardRepository();
        int rev = d.countBarangTerjual();
        System.out.println(rev);
    }

}
