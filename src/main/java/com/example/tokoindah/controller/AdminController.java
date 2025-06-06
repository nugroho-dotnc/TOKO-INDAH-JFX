package com.example.tokoindah.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    public BorderPane mainBorderPane;
    @FXML
    private Button pelangganButton;
    @FXML
    private Button transactionButton;
    @FXML
    private Button userButton;
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
        pelangganButton.setStyle("-fx-background-color: transparent;");
        transactionButton.setStyle("-fx-background-color: transparent;");
        userButton.setStyle("-fx-background-color: transparent;");
    }

    @FXML
    private void handleProductClick() {
        loadPage("product-page.fxml");
        System.out.println("Tombol Product diklik!");
        dashboardButton.setStyle("-fx-background-color: transparent;");
        productButton.setStyle("-fx-background-color: #34495e;");
        pelangganButton.setStyle("-fx-background-color: transparent;");
        transactionButton.setStyle("-fx-background-color: transparent;");
        userButton.setStyle("-fx-background-color: transparent;");
    }
    public void handlePelangganClick(ActionEvent actionEvent) {
        loadPage("pelanggan-page.fxml");
        System.out.println("Tombol Product diklik!");
        dashboardButton.setStyle("-fx-background-color: transparent;");
        productButton.setStyle("-fx-background-color: transparent;");
        pelangganButton.setStyle("-fx-background-color: #34495e;");
        transactionButton.setStyle("-fx-background-color: transparent;");
        userButton.setStyle("-fx-background-color: transparent;");
    }
    public void handleTransactionClick(ActionEvent actionEvent) {
        loadPage("transaction-page.fxml");
        System.out.println("Tombol Product diklik!");
        dashboardButton.setStyle("-fx-background-color: transparent;");
        productButton.setStyle("-fx-background-color: transparent;");
        pelangganButton.setStyle("-fx-background-color: transparent;");
        transactionButton.setStyle("-fx-background-color: #34495e;");
        userButton.setStyle("-fx-background-color: transparent;");
    }
    public void handleUserButton(ActionEvent actionEvent) {
        loadPage("user-page.fxml");
        System.out.println("Tombol Product diklik!");
        dashboardButton.setStyle("-fx-background-color: transparent;");
        productButton.setStyle("-fx-background-color: transparent;");
        pelangganButton.setStyle("-fx-background-color: transparent;");
        transactionButton.setStyle("-fx-background-color: transparent;");
        userButton.setStyle("-fx-background-color: #34495e;");
    }
    @FXML
    private void handleLogout(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            stage.setTitle("LOGIN PAGE!");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.err.println(e);
        }
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