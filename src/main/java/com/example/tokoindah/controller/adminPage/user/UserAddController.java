package com.example.tokoindah.controller.adminPage.user;

import com.example.tokoindah.repository.ProdukRepostiory;
import com.example.tokoindah.repository.UserRepository;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserAddController {

    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField role;
    @FXML
    private Button back_btn;
    @FXML
    private Button tambah_user_btn;
    @FXML
    private void initialize() {
        tambah_user_btn.setOnAction(event -> {
            try{
                UserRepository produkRepostiory = new UserRepository();
                produkRepostiory.createUser(name.getText(), username.getText(), password.getText(), role.getText());
                Back(event);
            }catch (Exception e){
                System.out.println(e);
            }
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
}
