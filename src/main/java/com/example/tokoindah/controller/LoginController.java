package com.example.tokoindah.controller;

import com.example.tokoindah.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label message;
    @FXML
    private TextField username_field;
    @FXML
    private PasswordField password_field;
    @FXML
    Button login;

    @FXML
    public void initialize() {
        username_field.setPromptText("Enter your username");
        password_field.setPromptText("Enter your password");

        login.setOnAction(event -> {
            String username = username_field.getText();
            String password = password_field.getText();

            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                if("admin".equals(username) && "admin".equals(password)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                    stage.setTitle("ADMIN PAGE");
                    stage.setScene(scene);
                    stage.show();
                } else if("kasir".equals(username) && "kasir".equals(password)) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/kasir-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                    stage.setTitle("KASIR PAGE");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    message.setText("Invalid username or password!");
                }
            } catch (IOException e) {
                e.printStackTrace(); // Selalu cetak stack trace
                message.setText("Gagal memuat halaman: " + e.getMessage());
                System.err.println("Error memuat FXML: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                message.setText("Terjadi kesalahan tak terduga!");
                System.err.println("Kesalahan tidak terduga: " + e.getMessage());
            }
        });
    }
}