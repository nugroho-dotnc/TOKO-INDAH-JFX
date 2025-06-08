package com.example.tokoindah.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection conn;

    public Database() {
            String url = "jdbc:mysql://localhost:3307/toko_indah";
            String user = "root";
            String password = "";
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Error connecting to database : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
