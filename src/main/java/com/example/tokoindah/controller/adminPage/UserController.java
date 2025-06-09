package com.example.tokoindah.controller.adminPage;

import com.example.tokoindah.controller.adminPage.product.EditProductController;
import com.example.tokoindah.controller.adminPage.user.UserEditController;
import com.example.tokoindah.model.Produk;
import com.example.tokoindah.model.User;
import com.example.tokoindah.repository.ProdukRepostiory;
import com.example.tokoindah.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

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

    private ArrayList<User> items;
    private ObservableList<User> itemsObservable;
    @FXML
    private void initialize() {
        UserRepository userRepository = new UserRepository();
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));
        items = userRepository.getUsers();
        itemsObservable = FXCollections.observableArrayList(items);
        userTable.setItems(itemsObservable);
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
        search_btn.setOnAction(event -> {
            String query = search_field.getText();
            if(query.isEmpty()){
                items = userRepository.getUsers();
                itemsObservable = FXCollections.observableArrayList(items);
            }else {
                User items = userRepository.getUserByUsername(query);
                ArrayList<User> userList= new ArrayList<>();
                userList.add(items);
                itemsObservable = FXCollections.observableArrayList(userList);
            }
            userTable.setItems(itemsObservable);
        });
        aksi.setCellFactory(col -> new TableCell<>() {
            private final Button updateBtn = new Button("Update");
            private final Button deleteBtn = new Button("Delete");
            private final HBox hBox = new HBox(10, deleteBtn, updateBtn); // Spacing antar tombol
            {
                updateBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                hBox.setAlignment(Pos.CENTER);
                updateBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    bukaHalamanEdit(user);

                });
                deleteBtn.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Yakin ingin menghapus user ini?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            userRepository.deleteUser(user.getId());
                            userTable.getItems().remove(user);
                        }
                    });
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
    private void bukaHalamanEdit(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-page/user/edit-user.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 800);
            UserEditController controller = loader.getController();
            controller.setUser(user);
            Stage stage = (Stage) userTable.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Edit User");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
