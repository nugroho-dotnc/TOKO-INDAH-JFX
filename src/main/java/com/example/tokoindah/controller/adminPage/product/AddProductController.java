package com.example.tokoindah.controller.adminPage.product;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductController {
    @FXML
    private TextField kode_produk;
    @FXML
    private TextField nama_produk;
    @FXML
    private TextField kategori;
    @FXML
    private TextField stock;
    @FXML
    private TextField harga_modal;
    @FXML
    private TextField harga_jual;
    @FXML
    private Button back_btn;
    @FXML
    private Button tambah_btn;

    @FXML
    public void initialize() {
        back_btn.setOnAction(event -> {
            try{
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                stage.setTitle("PRODUCT");
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                System.out.println(e);
            }
        });
    }
}
