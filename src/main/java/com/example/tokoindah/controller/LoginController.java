package com.example.tokoindah.controller;

import com.example.tokoindah.HelloApplication;
import com.example.tokoindah.global.AppGlobal;
import com.example.tokoindah.model.User;
import com.example.tokoindah.repository.UserRepository;
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
        UserRepository userRepository = new UserRepository();

        login.setOnAction(event -> {
            String username = username_field.getText();
            String password = password_field.getText();
            User user = userRepository.getUserByUsername(username);
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                if(!password.equals(user.getPassword())) {
                    System.out.println("Wrong password");
                    return;
                }
                if(user.getRole().equals("admin")) {
                    AppGlobal.setLoggedInUser(user);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                    stage.setTitle("ADMIN PAGE");
                    stage.setScene(scene);
                    stage.show();
                }else if(user.getRole().equals("kasir")) {
                    AppGlobal.setLoggedInUser(user);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/kasir-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                    stage.setTitle("KASIR PAGE");
                    stage.setScene(scene);
                    stage.show();
                }else{
                    System.out.println("Wrong role");
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