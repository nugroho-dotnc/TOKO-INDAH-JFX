package com.example.tokoindah.controller.adminPage.product;

import com.example.tokoindah.repository.ProdukRepostiory;
import javafx.event.ActionEvent;
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
    private void initialize() {
        tambah_btn.setOnAction(event -> {
           try{
               ProdukRepostiory produkRepostiory = new ProdukRepostiory();
               produkRepostiory.createProduk(kode_produk.getText(), nama_produk.getText(), kategori.getText(), Integer.parseInt(stock.getText()), Integer.parseInt(harga_modal.getText()), Integer.parseInt(harga_jual.getText()));
               Back(event);
           }catch (Exception e){
                System.out.println(e);
           }
        });
        back_btn.setOnAction(this::Back);
    }
    private void Back(ActionEvent event) {
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
    }
}
