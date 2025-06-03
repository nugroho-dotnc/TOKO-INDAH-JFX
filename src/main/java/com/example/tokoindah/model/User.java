package com.example.tokoindah.model;

import com.example.tokoindah.database.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User extends Database {
    private String name;
    private String username;
    private String password;
    private String role;

    public String create(String name, String username, String password, String role) {
        try{
            String sql = "INSERT INTO user (nama, username, password, role) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.execute();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        };

        return "Success";
    }

    public static void main(String[] args) {
        User user = new User();
        String createUser = user.create("testing", "testing", "testing", "kasir");
        System.out.println(createUser);
    }

}
