package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.model.Keranjang;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class TransactionController {
    public TextField search_field;
    public Button search_btn;
    public TableView transaksiTable;
    public TableColumn nomor_transaksi;
    public TableColumn tanggal_transaksi;
    public TableColumn nama_pelanggan;
    public TableColumn total_transaksi;
    public TableColumn pembayaran_transaksi;
    public TableColumn kembalian_transaksi;

}
