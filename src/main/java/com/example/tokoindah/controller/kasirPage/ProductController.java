package com.example.tokoindah.controller.kasirPage;

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
    }
}
