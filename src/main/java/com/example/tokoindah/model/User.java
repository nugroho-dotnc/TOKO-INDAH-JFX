package com.example.tokoindah.model;

import com.example.tokoindah.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String role;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

   public User(int id, String name, String username, String password, String role) {
        this.id = id;
       this.name = name;
       this.username = username;
       this.password = password;
       this.role = role;
   }

}

