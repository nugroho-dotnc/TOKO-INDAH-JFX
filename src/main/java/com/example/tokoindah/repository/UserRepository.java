package com.example.tokoindah.repository;

import com.example.tokoindah.database.Database;
import com.example.tokoindah.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.example.tokoindah.model.User;

public class UserRepository extends Database {
    public void createUser(String name, String username, String password, String role) {
        try {
            String sql = "INSERT INTO user (nama, username, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {
        try {
            ArrayList<User> users = new ArrayList<>();
            String sql = "SELECT * FROM user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nama");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                User user = new User(id, name, username, password, role);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nama");
                String uname = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                User userData = new User(id, name, uname, password, role);
                return userData;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(int id, String nama, String username, String password, String role) {
        try {
           String sql = "UPDATE user SET nama = ?, username = ?, password = ?, role = ? WHERE id = ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1, nama);
           stmt.setString(2, username);
           stmt.setString(3, password);
           stmt.setString(4, role);
           stmt.setInt(5, id);
           int rowAffected = stmt.executeUpdate();
           if(rowAffected > 0) {
               System.out.println("Data updated");
           } else {
               System.out.println("No data updated");
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            String sql = "DELETE FROM user WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowAffected = stmt.executeUpdate();
            if(rowAffected > 0) {
                System.out.println("Data has been deleted");
            } else {
                System.out.println("No data deleted");
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        userRepository.createUser("farrelililarila", "anomarel", "relariliro", "admin");
    }
}
