package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.model.Pelanggan;
import com.example.tokoindah.model.Produk;
import com.example.tokoindah.repository.PelangganRepository;
import com.example.tokoindah.repository.ProdukRepostiory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class PelangganController {
    public TextField search_field;
    public Button search_btn;
    @FXML
    private TableColumn<Pelanggan, Void> nomor_pelanggan;
    @FXML
    private TableColumn<Pelanggan, String> kode_pelanggan;
    @FXML
    private TableColumn<Pelanggan, String> nama_pelanggan;
    @FXML
    private TableColumn<Pelanggan, String> telepon_pelanggan;
    @FXML
    private TableColumn<Pelanggan, String> alamat_pelanggan;
    @FXML
    private TableColumn<Pelanggan, Void> aksi;
    @FXML
    private TableView pelangganTable;
    @FXML
    void initialize() {
        PelangganRepository p = new PelangganRepository();
        kode_pelanggan.setCellValueFactory(new PropertyValueFactory<>("KodePelanggan"));
        nama_pelanggan.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        telepon_pelanggan.setCellValueFactory(new PropertyValueFactory<>("Notelp"));
        alamat_pelanggan.setCellValueFactory(new PropertyValueFactory<>("Alamat"));
        nomor_pelanggan.setCellFactory(col -> new TableCell<>() {
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
        ArrayList<Pelanggan> items = p.getPelangggan();
        ObservableList<Pelanggan> observableList = FXCollections.observableArrayList(items);
        pelangganTable.setItems(observableList);
        aksi.setCellFactory(col -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");
            private  HBox hBox = new HBox(10, deleteBtn);
            {
                hBox.setAlignment(Pos.CENTER);
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                deleteBtn.setOnAction(event -> {
                    Pelanggan pelanggan = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus produk ini?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            p.deletePelanggan(pelanggan.getKodePelanggan());
                            pelangganTable.getItems().remove(pelanggan);
                        }
                    });
                    System.out.println("Delete clicked: " + pelanggan.getKodePelanggan());
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
}

