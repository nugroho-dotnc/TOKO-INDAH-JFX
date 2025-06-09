package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.model.Transaksi;
import com.example.tokoindah.repository.DashboardRepository;
import com.example.tokoindah.repository.TransaksiRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class DashboardController {
    public Label keranjang;
    public Label barang_terjual;
    public Label revenue;
    public Label pelanggan;
    public TableView transactionTable;
    public TableColumn nomor_transaksi;
    public TableColumn tanggal_transaksi;
    public TableColumn nama_pelanggan;
    public TableColumn total;
    public TableColumn pembayaran;
    public TableColumn kembalian;

    private ArrayList<Transaksi> items;
    private ObservableList<Transaksi> itemsObservable;
    @FXML
    public void initialize() {
        TransaksiRepository p = new TransaksiRepository();
        DashboardRepository d = new DashboardRepository();
        nomor_transaksi.setCellValueFactory(new PropertyValueFactory<>("NomorTransaksi"));
        tanggal_transaksi.setCellValueFactory(new PropertyValueFactory<>("TanggalTransaksi"));
        nama_pelanggan.setCellValueFactory(new PropertyValueFactory<>("NamaPelanggan"));
        total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        pembayaran.setCellValueFactory(new PropertyValueFactory<>("Pembayaran"));
        kembalian.setCellValueFactory(new PropertyValueFactory<>("Kembalian"));
        items = p.getTransaksi();
        itemsObservable = FXCollections.observableArrayList(items);
        transactionTable.setItems(itemsObservable);
        keranjang.setText(String.valueOf(d.countTransaksi()));
        barang_terjual.setText(String.valueOf(d.countBarangTerjual()));
        revenue.setText(String.valueOf(d.countRevenue()));
        pelanggan.setText(String.valueOf(d.countPelanggan()));
    }
}
