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
    public TextField username;
    public TextField password;
    public TextField role;
    @FXML
    public Button back_btn;
    @FXML
    private Button edit_user_btn;

    private User user;

    @FXML
    private void initialize() {

        back_btn.setOnAction(this::Back);
    }
    public void setUser(User user) {
        this.user = user;
        isiDataKeForm();
    }

    private void isiDataKeForm() {
        if (user != null) {
            name.setText(user.getName());

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
