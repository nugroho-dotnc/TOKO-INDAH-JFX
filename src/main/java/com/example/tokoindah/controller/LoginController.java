package com.example.tokoindah.controller;

import com.example.tokoindah.global.AppGlobal;
import com.example.tokoindah.model.User;
import com.example.tokoindah.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField username_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button login;

    @FXML
    public void initialize() {
        username_field.setPromptText("Enter your username");
        password_field.setPromptText("Enter your password");

        UserRepository userRepository = new UserRepository();

        login.setOnAction(event -> {
            String username = username_field.getText().trim();
            String password = password_field.getText().trim();

            // Validasi input kosong
            if (username.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Login Gagal", "Username dan Password tidak boleh kosong.");
                return;
            }

            try {
                User user = userRepository.getUserByUsername(username);

                if (user == null) {
                    showAlert(Alert.AlertType.ERROR, "Login Gagal", "Username tidak ditemukan.");
                    return;
                }

                if (!password.equals(user.getPassword())) {
                    showAlert(Alert.AlertType.ERROR, "Login Gagal", "Password salah.");
                    return;
                }

                AppGlobal.setLoggedInUser(user);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader;

                if (user.getRole().equalsIgnoreCase("admin")) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-view.fxml"));
                    stage.setTitle("ADMIN PAGE");
                } else if (user.getRole().equalsIgnoreCase("kasir")) {
                    fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/kasir-view.fxml"));
                    stage.setTitle("KASIR PAGE");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Login Gagal", "Role tidak dikenal: " + user.getRole());
                    return;
                }

                Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
            }
        });
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
