package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.controller.adminPage.product.EditProductController;
import com.example.tokoindah.model.Produk;
import com.example.tokoindah.repository.ProdukRepostiory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.Date;
import java.time.LocalDate;

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
    private TableColumn<Produk, Void> aksi;
    @FXML
    private TextField search_field;
    @FXML
    private Button search_btn;
    @FXML
    private Button tambah_btn;

    private ArrayList<Produk> items;
    private ObservableList<Produk> itemsObservable;
    @FXML
    public void initialize() {
        ProdukRepostiory p = new ProdukRepostiory();
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
        items = p.getAllProduk();
        itemsObservable = FXCollections.observableArrayList(items);
        productTable.setItems(itemsObservable);
        search_btn.setOnAction(event -> {
            String query = search_field.getText();
            if(query.isEmpty()){
                items = p.getAllProduk();
            }else{
                items = p.searchProduk(query);
            }
            itemsObservable = FXCollections.observableArrayList(items);
            productTable.setItems(itemsObservable);
        });
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
        aksi.setCellFactory(col -> new TableCell<>() {
            private final Button updateBtn = new Button("Update");
            private final Button deleteBtn = new Button("Delete");
            private final HBox hBox = new HBox(10, deleteBtn, updateBtn); // Spacing antar tombol
            {
                updateBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                updateBtn.setOnAction(event -> {
                    Produk produk = getTableView().getItems().get(getIndex());
                    bukaHalamanEdit(produk);
                    System.out.println("Update clicked: " + produk.getKodeProduk());
                });
                deleteBtn.setOnAction(event -> {
                    Produk produk = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus produk ini?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            ProdukRepostiory repo = new ProdukRepostiory();
                            repo.deleteProduk(produk.getKodeProduk());
                            productTable.getItems().remove(produk);
                        }
                    });
                    System.out.println("Delete clicked: " + produk.getKodeProduk());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hBox);
                }
            }
        });
    }
    private void bukaHalamanEdit(Produk produk) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-page/product/edit-product-view.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 800);
            EditProductController controller = loader.getController();
            controller.setProduk(produk);
            Stage stage = (Stage) productTable.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Edit Produk");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
