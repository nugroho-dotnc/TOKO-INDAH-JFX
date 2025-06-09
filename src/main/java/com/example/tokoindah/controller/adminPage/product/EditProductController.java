package com.example.tokoindah.controller.adminPage.product;

import com.example.tokoindah.model.Produk;
import com.example.tokoindah.repository.ProdukRepostiory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProductController {
    @FXML
    private TextField kode_produk;
    @FXML
    private TextField nama_produk;
    @FXML
    private TextField kategori;
    @FXML
    private TextField stock;
    @FXML
    private TextField harga_modal;
    @FXML
    private TextField harga_jual;
    @FXML
    private Button back_btn;
    @FXML
    private Button edit_btn;
    @FXML
    private Produk produk;

    public void setProduk(Produk produk) {
        this.produk = produk;
        isiDataKeForm();
    }

    private void isiDataKeForm() {
        if (produk != null) {
            kode_produk.setText(produk.getKodeProduk());
            nama_produk.setText(produk.getNamaProduk());
            kategori.setText(produk.getKategoriProduk());
            stock.setText(String.valueOf(produk.getStock()));
            harga_modal.setText(String.valueOf(produk.getHargaModal()));
            harga_jual.setText(String.valueOf(produk.getHargaJual()));
        }
    }

    @FXML
    private void initialize() {
        edit_btn.setOnAction(event -> {
            try {
                // Validasi form kosong
                if (kode_produk.getText().isEmpty() ||
                        nama_produk.getText().isEmpty() ||
                        kategori.getText().isEmpty() ||
                        stock.getText().isEmpty() ||
                        harga_modal.getText().isEmpty() ||
                        harga_jual.getText().isEmpty()) {

                    showAlert(Alert.AlertType.WARNING, "Validasi Gagal", "Semua field harus diisi!");
                    return;
                }

                // Validasi angka
                int stokValue;
                float hargaModalValue, hargaJualValue;

                try {
                    stokValue = Integer.parseInt(stock.getText());
                    hargaModalValue = Float.parseFloat(harga_modal.getText());
                    hargaJualValue = Float.parseFloat(harga_jual.getText());
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.ERROR, "Format Salah", "Stock harus berupa angka bulat dan harga harus berupa angka desimal.");
                    return;
                }

                // Update data
                ProdukRepostiory produkRepostiory = new ProdukRepostiory();
                produkRepostiory.updateProduk(
                        kode_produk.getText(),
                        nama_produk.getText(),
                        kategori.getText(),
                        stokValue,
                        hargaModalValue,
                        hargaJualValue
                );

                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Produk berhasil diperbarui!");
                Back(event);

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Terjadi kesalahan: " + e.getMessage());
                e.printStackTrace();
            }
        });

        back_btn.setOnAction(this::Back);
    }

    private void Back(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
            stage.setTitle("PRODUCT");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigasi Gagal", "Tidak bisa kembali ke halaman admin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
