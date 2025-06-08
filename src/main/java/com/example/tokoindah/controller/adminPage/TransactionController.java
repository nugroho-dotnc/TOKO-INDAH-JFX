package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.model.Pelanggan;
import com.example.tokoindah.model.Transaksi;
import com.example.tokoindah.repository.TransaksiRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class TransactionController {

    @FXML
    private TableView<Transaksi> transaksiTable;

    @FXML
    private TableColumn<Transaksi, Void> nomor;
    @FXML
    private TableColumn<Transaksi, String> nomor_transaksi;
    @FXML
    private TableColumn<Transaksi, Date> tanggal_transaksi;
    @FXML
    private TableColumn<Transaksi, Pelanggan> pelanggan;
    @FXML
    private TableColumn<Transaksi, Float> total;
    @FXML
    private TableColumn<Transaksi, Float> pembayaran;
    @FXML
    private TableColumn<Transaksi, Float> kembalian;
    @FXML
    private TableColumn<Transaksi, Void> aksi;

    @FXML
    private DatePicker date_picker;
    /* @FXML
    private TextField search_field; */
    @FXML
    private Button search_btn;

    private ArrayList<Transaksi> items;
    private ObservableList<Transaksi> itemsObservable;
    @FXML
    public void initialize() {
        TransaksiRepository p = new TransaksiRepository();
        nomor_transaksi.setCellValueFactory(new PropertyValueFactory<>("NomorTransaksi"));
        tanggal_transaksi.setCellValueFactory(new PropertyValueFactory<>("TanggalTransaksi"));
        pelanggan.setCellValueFactory(new PropertyValueFactory<>("NamaPelanggan"));
        total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        pembayaran.setCellValueFactory(new PropertyValueFactory<>("Pembayaran"));
        kembalian.setCellValueFactory(new PropertyValueFactory<>("Kembalian"));
        nomor.setCellFactory(col -> new TableCell<>() {
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
        items = p.getTransaksi();
        itemsObservable = FXCollections.observableArrayList(items);
        transaksiTable.setItems(itemsObservable);
        search_btn.setOnAction(event -> {
            LocalDate selectedDate = date_picker.getValue();

            if (selectedDate == null) {
                items = p.getTransaksi(); // Tampilkan semua jika tidak pilih tanggal
            } else {
                items = p.searchByTanggal(selectedDate.toString());
            }

            itemsObservable = FXCollections.observableArrayList(items);
            transaksiTable.setItems(itemsObservable);
        });

        aksi.setCellFactory(col -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");
            private final HBox hBox = new HBox(10, deleteBtn); // Spacing antar tombol
            {
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                deleteBtn.setOnAction(event -> {
                    Transaksi transaksi = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus produk ini?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            TransaksiRepository repo = new TransaksiRepository();
                            repo.deleteTransaksi(transaksi.getNomorTransaksi());
                            transaksiTable.getItems().remove(transaksi);
                        }
                    });
                    System.out.println("Delete clicked: " + transaksi.getNomorTransaksi());
                });
            }

        });
    }

    /*private void bukaHalamanEdit(Transaksi transaksi) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-page/edit-product-view.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 800);
            EditProductController controller = loader.getController();
            controller.setTransaksi(transaksi);
            Stage stage = (Stage) productTable.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Edit Produk");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/



    /*public TableColumn nomor;
    public TextField search_field;
    public Button search_btn;
    public TableView transaksiTable;
    public TableColumn nomor_transaksi;
    public TableColumn tanggal_transaksi;
    public TableColumn pelanggan;
    public TableColumn total;
    public TableColumn pembayaran;
    public TableColumn kembalian;*/

}
