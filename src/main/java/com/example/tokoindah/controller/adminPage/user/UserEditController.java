package com.example.tokoindah.controller.adminPage.user;

import com.example.tokoindah.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditController {
    public TextField name;
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public TextField role;
    @FXML
    public Button back_btn;
    @FXML
    private Button edit_user_btn;
    @FXML
    private User user;
    @FXML
    private void initialize() {
        edit_user_btn.setOnAction(event -> {
            try{
                com.example.tokoindah.repository.UserRepository produkRepostiory = new com.example.tokoindah.repository.UserRepository();
                produkRepostiory.updateUser(user.getId(),name.getText(), username.getText(), password.getText(), role.getText());
                Back(event);
            }catch (Exception e){
                System.out.println(e);
            }
        });
        back_btn.setOnAction(this::Back);
    }
    public void setUser(User user) {
        this.user = user;
        isiDataKeForm();
    }

    private void isiDataKeForm() {
        if (user != null) {
            name.setText(user.getName());
            username.setText(user.getUsername());
            password.setText(user.getPassword());
            role.setText(user.getRole());
        }
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
