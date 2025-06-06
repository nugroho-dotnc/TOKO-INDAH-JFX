package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.model.Produk;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ProductController {

    @FXML
    private TableView<Produk> productTable;

    @FXML
    private TableColumn<Produk, Void> no;
    @FXML
    private TableColumn<Produk, String> kode_produk;
    @FXML
    private TableColumn<Produk, String> nama_produk;
    @FXML
    private TableColumn<Produk, String> kategori_produk;
    @FXML
    private TableColumn<Produk, Integer> stock;
    @FXML
    private TableColumn<Produk, Float> harga_modal;
    @FXML
    private TableColumn<Produk, Float> harga_jual;
    @FXML
    private TableColumn<Produk, String> tanggal_input;

    @FXML
    private TextField search_field;
    @FXML
    private Button search_btn;
    @FXML
    private Button tambah_btn;

    @FXML
    public void initialize() {

        kode_produk.setCellValueFactory(new PropertyValueFactory<>("KodeProduk"));
        nama_produk.setCellValueFactory(new PropertyValueFactory<>("NamaProduk"));
        kategori_produk.setCellValueFactory(new PropertyValueFactory<>("KategoriProduk"));
        stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        harga_modal.setCellValueFactory(new PropertyValueFactory<>("HargaModal"));
        harga_jual.setCellValueFactory(new PropertyValueFactory<>("HargaJual"));
        tanggal_input.setCellValueFactory(new PropertyValueFactory<>("TanggalInput"));
        no.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });
        ObservableList<Produk> dummyData = FXCollections.observableArrayList(
                new Produk("PR001", "Indomie Goreng", "Makanan", 100, 2500, 3500, "2025-06-01"),
                new Produk("PR002", "Aqua Botol", "Minuman", 200, 2000, 3000, "2025-06-02"),
                new Produk("PR003", "Pensil 2B", "Alat Tulis", 50, 1500, 2500, "2025-06-03")
        );
        productTable.setItems(dummyData);

        tambah_btn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-page/product/add-product-view.fxml"));
                Scene scene = new Scene(loader.load(), 1200, 800);
                stage.setTitle("Add Product");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
