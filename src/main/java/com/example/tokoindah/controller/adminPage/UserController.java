package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.controller.adminPage.product.EditProductController;
import com.example.tokoindah.model.Produk;
import com.example.tokoindah.model.User;
import com.example.tokoindah.repository.ProdukRepostiory;
import com.example.tokoindah.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UserController {
    @FXML
    private TableColumn<User, Void> aksi;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TextField search_field;
    @FXML
    private Button search_btn;
    @FXML
    private Button tambah_user_btn;

    @FXML
    private void initialize() {
        UserRepository userRepository = new UserRepository();
        id.setCellValueFactory(new PropertyValueFactory<>(""));
        nama_produk.setCellValueFactory(new PropertyValueFactory<>("NamaProduk"));
        kategori_produk.setCellValueFactory(new PropertyValueFactory<>("KategoriProduk"));

        tambah_user_btn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-page/user/add-user.fxml"));
                Scene scene = new Scene(loader.load(), 1200, 800);
                stage.setTitle("Add User");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        aksi.setCellFactory(col -> new TableCell<>() {
            private final Button updateBtn = new Button("Update");
            private final Button deleteBtn = new Button("Delete");
            private final HBox hBox = new HBox(10, updateBtn, deleteBtn); // Spacing antar tombol
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
