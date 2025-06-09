package com.example.tokoindah.controller.adminPage.user;

import com.example.tokoindah.model.User;
import com.example.tokoindah.repository.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditController {

    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField role;
    @FXML
    private Button back_btn;
    @FXML
    private Button edit_user_btn;
    @FXML
    private User user;

    @FXML
    private void initialize() {
        edit_user_btn.setOnAction(event -> {
            try {
                // Validasi input kosong
                if (name.getText().isEmpty() ||
                        username.getText().isEmpty() ||
                        password.getText().isEmpty() ||
                        role.getText().isEmpty()) {

                    showAlert(Alert.AlertType.WARNING, "Validasi Gagal", "Semua field harus diisi!");
                    return;
                }

                // Validasi role
                String roleInput = role.getText().toLowerCase();
                if (!roleInput.equals("admin") && !roleInput.equals("kasir")) {
                    showAlert(Alert.AlertType.ERROR, "Role Tidak Valid", "Role hanya boleh 'admin' atau 'kasir'.");
                    return;
                }

                // Simpan update
                UserRepository userRepository = new UserRepository();
                userRepository.updateUser(user.getId(), name.getText(), username.getText(), password.getText(), roleInput);

                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data user berhasil diperbarui.");
                Back(event);

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
                e.printStackTrace();
            }
        });

        back_btn.setOnAction(this::Back);
    }

    public void setUser(User user) {
        this.user = user;
        isiDataKeForm();
    }

    private void isiDataKeForm() {
        if (user != null) {
            name.setText(user.getName());
            username.setText(user.getUsername());
            password.setText(user.getPassword());
            role.setText(user.getRole());
        }
    }

    private void Back(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            stage.setTitle("PRODUCT");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigasi Gagal", "Tidak bisa kembali ke halaman admin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
