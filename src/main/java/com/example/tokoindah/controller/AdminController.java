package com.example.tokoindah.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;

import java.io.IOException;

public class AdminController {

    @FXML
    private StackPane contentPane;

    @FXML
    private Button dashboardButton;
    @FXML
    private Button productButton;

    @FXML
    public void initialize() {
        loadPage("dashboard-page.fxml");
        dashboardButton.setStyle("-fx-background-color: #34495e;");
        productButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    private void handleDashboardClick() {
        loadPage("dashboard-page.fxml");
        System.out.println("Tombol Dashboard diklik!");
        dashboardButton.setStyle("-fx-background-color: #34495e;");
        productButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    private void handleProductClick() {
        loadPage("product-page.fxml");
        System.out.println("Tombol Product diklik!");
        dashboardButton.setStyle("-fx-background-color: transparent;");
        productButton.setStyle("-fx-background-color: #34495e;");
    }

    // Metode untuk memuat FXML ke dalam contentPane
    private void loadPage(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-page/" + fxmlFileName));
            Parent root = loader.load();
            contentPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Gagal memuat halaman: " + fxmlFileName);
            Label errorLabel = new Label("Gagal memuat: " + fxmlFileName);
            errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            contentPane.getChildren().setAll(errorLabel);
        }
    }
}