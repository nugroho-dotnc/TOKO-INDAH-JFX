package com.example.tokoindah.controller.kasirPage;

import com.example.tokoindah.global.AppGlobal;
import com.example.tokoindah.model.Keranjang;
import com.example.tokoindah.model.Pelanggan;
import com.example.tokoindah.repository.PelangganRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class TransactionController {
    public BorderPane main_border;
    public VBox addition_form;
    public VBox transaction;
    @FXML
    private Button tambah_pelanggan_btn;
    @FXML
    private Label state_pelanggan;
    @FXML
    private TextField no_nota;
    @FXML
    private TextField tanggal;
    @FXML
    private TextField kasir;
    @FXML
    private TextField nama_pelanggan;
    @FXML
    private TextField telepon_pelanggan;
    @FXML
    private TextArea alamat_pelanggan;
    Pelanggan pelanggan;
    @FXML
    public void initialize() {
        PelangganRepository pelangganRepository = new PelangganRepository();
        Date thisDay = Date.valueOf(LocalDate.now());
        tanggal.setText(thisDay.toString());
        kasir.setText(AppGlobal.user.getName());


        telepon_pelanggan.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // saat fokus hilang
                String inputTelepon = telepon_pelanggan.getText();
                pelanggan = pelangganRepository.getPelangganByTelepon(inputTelepon);
                if(pelanggan != null) {
                    nama_pelanggan.setText(pelanggan.getNama());
                    telepon_pelanggan.setText(pelanggan.getNotelp());
                    alamat_pelanggan.setText(pelanggan.getAlamat());
                    state_pelanggan.setText("Pelanggan Ditemukan!");
                }else{
                    state_pelanggan.setText("Pelanggan tidak ditemukan!");
                }
            }
        });

        tambah_pelanggan_btn.setOnAction(event -> {
            Pelanggan validatingPelanggan = pelangganRepository.getPelangganByTelepon(telepon_pelanggan.getText());
            if(validatingPelanggan == null) {
                try{
                    pelangganRepository.createPelanggan(nama_pelanggan.getText(), telepon_pelanggan.getText(), alamat_pelanggan.getText());
                    state_pelanggan.setText("Pelanggan Berhasil Ditambahkan!");
                } catch (Exception e) {
                    state_pelanggan.setText("Pelanggan gagal ditambahkan!");
                    throw new RuntimeException(e);
                }
            }else {
                state_pelanggan.setText("Pelanggan Sudah Ada!");
            }
        });
    }
}
