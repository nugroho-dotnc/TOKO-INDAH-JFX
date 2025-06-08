package com.example.tokoindah.controller.kasirPage;

import com.example.tokoindah.global.AppGlobal;
import com.example.tokoindah.model.Keranjang;
import com.example.tokoindah.model.Pelanggan;
import com.example.tokoindah.model.Produk;
import com.example.tokoindah.repository.KeranjangRepository;
import com.example.tokoindah.repository.PelangganRepository;
import com.example.tokoindah.repository.ProdukRepostiory;
import com.example.tokoindah.repository.TransaksiRepository;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.Console;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class TransactionController {
    @FXML
    private Label total_label;
    @FXML
    private TextArea catatan_transaksi;

    @FXML
    private BorderPane main_border;

    @FXML
    private VBox addition_form;

    @FXML
    private VBox transaction;

    @FXML
    private TableColumn<Keranjang, Void> no;

    @FXML
    private TableColumn<Keranjang, String> kode_produk;

    @FXML
    private TableColumn<Keranjang, String> nama_produk;

    @FXML
    private TableColumn<Keranjang, Integer> harga;

    @FXML
    private TableColumn<Keranjang, Integer> qty;

    @FXML
    private TableColumn<Keranjang, Integer> subtotal;

    @FXML
    private TableColumn<Keranjang, Void> opsi;

    @FXML
    private TextField input_kode_product;

    @FXML
    private TextField input_qty_product;

    @FXML
    private Button tambah_keranjang_btn;

    @FXML
    private TableView<Keranjang> keranjangTable;

    @FXML
    private TextField input_pembayaran;

    @FXML
    private TextField input_kembalian;

    @FXML
    private Button cetak_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button simpan_btn;

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
    ArrayList<Keranjang> stateCart = new ArrayList<>();
    ObservableList<Keranjang> keranjangList = FXCollections.observableArrayList();
    PelangganRepository pelangganRepository = new PelangganRepository();
    ProdukRepostiory produkRepostiory = new ProdukRepostiory();
    TransaksiRepository transaksiRepository = new TransaksiRepository();
    KeranjangRepository keranjangRepository = new KeranjangRepository();
    String kodeTransaksi;
    float total;
    @FXML
    public void initialize() {
        Date thisDay = Date.valueOf(LocalDate.now());
        tanggal.setText(thisDay.toString());
        kasir.setText(AppGlobal.user != null ? AppGlobal.user.getName() : "Unknown");

        kodeTransaksi = TransaksiRepository.generateKodeTransansaksi();
        no_nota.setText(kodeTransaksi);
        total = 0;

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

        opsi.setCellFactory(col -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");
            private final HBox hBox = new HBox(10, deleteBtn);

            {
                hBox.setAlignment(Pos.CENTER);
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                deleteBtn.setOnAction(event -> {
                    Keranjang keranjang = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus produk ini?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            stateCart.remove(keranjang);
                            keranjangTable.getItems().remove(keranjang);
                            total -= keranjang.getSubtotal();
                            total_label.setText(String.valueOf(total));
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hBox);
            }
        });

        keranjangList = FXCollections.observableArrayList(stateCart);
        keranjangTable.setItems(keranjangList);

        tambah_keranjang_btn.setOnAction(event -> {
            String kodeProduct = input_kode_product.getText();
            String qtyText = input_qty_product.getText();

            if (kodeProduct.isEmpty() || qtyText.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Kode produk dan kuantitas tidak boleh kosong!").show();
                return;
            }

            int qty;
            try {
                qty = Integer.parseInt(qtyText);
                if (qty <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Kuantitas harus lebih dari 0!").show();
                    return;
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Kuantitas harus berupa angka!").show();
                return;
            }

            try {
                Produk produk = produkRepostiory.getProdukByKodeProduk(kodeProduct);
                if (produk == null) {
                    new Alert(Alert.AlertType.ERROR, "Produk tidak ditemukan!").show();
                    return;
                }

                if (qty > produk.getStock()) {
                    new Alert(Alert.AlertType.ERROR, "Stok tidak mencukupi!").show();
                    return;
                }

                for (Keranjang item : stateCart) {
                    if (item.getKodeProduk().equals(kodeProduct)) {
                        item.setQty(item.getQty() + qty);
                        item.setSubtotal(item.getQty() * item.getHarga());
                        keranjangTable.refresh();
                        updateTotal();
                        input_kode_product.clear();
                        input_qty_product.clear();
                        input_kode_product.requestFocus();
                        return;
                    }
                }

                float subtotal = produk.getHargaJual() * qty;
                Keranjang newKeranjang = new Keranjang(0, kodeTransaksi, kodeProduct, produk.getNamaProduk(), produk.getHargaJual(), qty, subtotal);
                stateCart.add(newKeranjang);
                keranjangList = FXCollections.observableArrayList(stateCart);
                keranjangTable.setItems(keranjangList);
                total += subtotal;
                total_label.setText(String.valueOf(total));
                input_kode_product.clear();
                input_qty_product.clear();
                input_kode_product.requestFocus();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Gagal menambahkan produk: " + e.getMessage()).show();
            }
        });

        simpan_btn.setOnAction(event -> {
            if (stateCart.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Keranjang kosong. Tambahkan produk terlebih dahulu!").show();
                return;
            }

            if (input_pembayaran.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Masukkan nominal pembayaran!").show();
                return;
            }

            try {
                float pembayaran = Float.parseFloat(input_pembayaran.getText());
                float kembalian = pembayaran - total;

                if (pembayaran < total) {
                    new Alert(Alert.AlertType.ERROR, "Pembayaran tidak mencukupi total transaksi!").show();
                    return;
                }

                Pelanggan newPelanggan = pelangganRepository.getPelangganByTelepon(telepon_pelanggan.getText());
                if (newPelanggan == null) {
                    new Alert(Alert.AlertType.ERROR, "Pelanggan belum terdaftar!").show();
                    return;
                }

                transaksiRepository.createTransaksi();
                keranjangRepository.createKeranjang(stateCart);
                transaksiRepository.updateTransaksi(kodeTransaksi, catatan_transaksi.getText(), total, pembayaran, kembalian, newPelanggan.getNotelp());
                new Alert(Alert.AlertType.INFORMATION, "Transaksi berhasil disimpan!").show();
                resetFormSetelahTransaksi();
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Pembayaran harus berupa angka!").show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Gagal menyimpan transaksi: " + e.getMessage()).show();
            }
        });

        cancel_btn.setOnAction(event -> resetFormSetelahTransaksi());

        PauseTransition pause = new PauseTransition(Duration.millis(500));
        input_pembayaran.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> {
                try {
                    float pembayaran = Float.parseFloat(newValue);
                    float kembalian = pembayaran - total;
                    input_kembalian.setText(kembalian < 0 ? "Pembayaran tidak cukup" : String.valueOf(kembalian));
                } catch (NumberFormatException e) {
                    input_kembalian.setText("Input bukan angka");
                }
            });
            pause.playFromStart(); // Reset timer setiap kali input berubah
        });

        telepon_pelanggan.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                try {
                    String inputTelepon = telepon_pelanggan.getText();
                    pelanggan = pelangganRepository.getPelangganByTelepon(inputTelepon);
                    if (pelanggan != null) {
                        nama_pelanggan.setText(pelanggan.getNama());
                        telepon_pelanggan.setText(pelanggan.getNotelp());
                        alamat_pelanggan.setText(pelanggan.getAlamat());
                        state_pelanggan.setText("Pelanggan Ditemukan!");
                    } else {
                        state_pelanggan.setText("Pelanggan tidak ditemukan!");
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Gagal mengambil data pelanggan: " + e.getMessage()).show();
                }
            }
        });

        tambah_pelanggan_btn.setOnAction(event -> {
            if (nama_pelanggan.getText().isEmpty() || telepon_pelanggan.getText().isEmpty() || alamat_pelanggan.getText().isEmpty()) {
                state_pelanggan.setText("Lengkapi semua field pelanggan!");
                return;
            }

            try {
                Pelanggan validatingPelanggan = pelangganRepository.getPelangganByTelepon(telepon_pelanggan.getText());
                if (validatingPelanggan == null) {
                    pelangganRepository.createPelanggan(nama_pelanggan.getText(), telepon_pelanggan.getText(), alamat_pelanggan.getText());
                    state_pelanggan.setText("Pelanggan Berhasil Ditambahkan!");
                } else {
                    state_pelanggan.setText("Pelanggan Sudah Ada!");
                }
            } catch (Exception e) {
                state_pelanggan.setText("Pelanggan gagal ditambahkan!");
                new Alert(Alert.AlertType.ERROR, "Gagal menambah pelanggan: " + e.getMessage()).show();
            }
        });
    }

    private void resetFormSetelahTransaksi() {
        stateCart.clear();
        keranjangList = FXCollections.observableArrayList(stateCart);
        keranjangTable.setItems(keranjangList);

        total = 0;
        total_label.setText("0");

        input_pembayaran.clear();
        input_kembalian.clear();
        catatan_transaksi.clear();
        telepon_pelanggan.clear();
        nama_pelanggan.clear();
        alamat_pelanggan.clear();
        state_pelanggan.setText("");

        input_kode_product.clear();
        input_qty_product.clear();
        input_kode_product.requestFocus();

        // Reset kode transaksi baru
        kodeTransaksi = TransaksiRepository.generateKodeTransansaksi();
        no_nota.setText(kodeTransaksi);
    }

    // Utility tambahan (opsional) kalau kamu ingin struktur bersih
    private void updateTotal() {
        total = 0;
        for (Keranjang item : stateCart) {
            total += item.getSubtotal();
        }
        total_label.setText(String.valueOf(total));
    }

}
