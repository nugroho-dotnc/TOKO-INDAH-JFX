package com.example.tokoindah.controller;
import com.example.tokoindah.global.AppGlobal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class KasirController {
    @FXML
    private Button historyButton;
    @FXML
    private StackPane contentPane;

    @FXML
    private Button transactionButton;
    @FXML
    private Button productButton;

    @FXML
    public void initialize() {
        loadPage("transaction-page.fxml");
        transactionButton.setStyle("-fx-background-color: #34495e;");
        productButton.setStyle("-fx-background-color: transparent;");
    }

    public void handleTransaction(ActionEvent actionEvent) {
        loadPage("transaction-page.fxml");
        System.out.println("Tombol Dashboard diklik!");
        transactionButton.setStyle("-fx-background-color: #34495e;");
        productButton.setStyle("-fx-background-color: transparent;");
    }
    public void handleProduct(ActionEvent actionEvent) {
        loadPage("product-page.fxml");
        System.out.println("Tombol Dashboard diklik!");
        transactionButton.setStyle("-fx-background-color: transparent;");
        productButton.setStyle("-fx-background-color: #34495e;");
    }
    @FXML
    private void handleLogout(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try{
            AppGlobal.clearLoggedInUser();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/kasir-page/" + fxmlFileName));
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


    public void handleHistory(ActionEvent actionEvent) {

    }
}
