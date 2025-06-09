package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.model.Keranjang;
import com.example.tokoindah.model.Pelanggan;
import com.example.tokoindah.model.Transaksi;
import com.example.tokoindah.repository.KeranjangRepository;
import com.example.tokoindah.repository.ProdukRepostiory;
import com.example.tokoindah.repository.TransaksiRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;


public class DetailTransaksiController {

    public Label kode_transaksi;
    public Label tanggal_transaksi;
    public Label pelanggan_transaksi;
    public TableView<Keranjang> keranjangTable;
    public TableColumn<Keranjang, Void> no;
    public TableColumn<Keranjang, String> kode_produk;
    public TableColumn<Keranjang, String> nama_produk;
    public TableColumn<Keranjang, Integer> harga;
    public TableColumn<Keranjang, Integer> qty;
    public TableColumn<Keranjang, Integer> subtotal;
    public Label total_transaksi;
    public Label pembayaran_transaksi;
    public Label kembalian_transaksi;
    public Button back_btn;
    public Button cetak_btn;

    KeranjangRepository keranjangRepository = new KeranjangRepository();
    ArrayList<Keranjang> stateCart;

    Transaksi transaksi;
    ObservableList<Keranjang> keranjangList = FXCollections.observableArrayList();

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
        stateCart = keranjangRepository.getKeranjangByNomorTransaksi(transaksi.getNomorTransaksi());
        keranjangList.setAll(stateCart); // update list setelah dapat data
        setupAll();
    }

    @FXML
    public void initialize() {
        kode_produk.setCellValueFactory(new PropertyValueFactory<>("KodeProduk"));
        nama_produk.setCellValueFactory(new PropertyValueFactory<>("NamaProduk"));
        harga.setCellValueFactory(new PropertyValueFactory<>("Harga"));
        qty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        subtotal.setCellValueFactory(new PropertyValueFactory<>("Subtotal"));
        no.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(getIndex() + 1));
            }
        });

        keranjangTable.setItems(keranjangList);

        cetak_btn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Detail Transaksi Berhasil Di Cetak");
            alert.show();
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
    public void setupAll() {
        kode_transaksi.setText(transaksi.getNomorTransaksi());
        tanggal_transaksi.setText(transaksi.getTanggalTransaksi().toString());
        pelanggan_transaksi.setText(transaksi.getPelanggan().getNama());
        total_transaksi.setText(String.valueOf(transaksi.getTotal()));
        pembayaran_transaksi.setText(String.valueOf(transaksi.getPembayaran()));
        kembalian_transaksi.setText(String.valueOf(transaksi.getKembalian()));
    }
}