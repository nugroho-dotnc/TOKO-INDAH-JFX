package com.example.tokoindah.controller.adminPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductController {
    @FXML
    private TextField search_field;
    @FXML
    public Button search_btn;
    @FXML
    public Button tambah_btn;
    @FXML
    public void initialize() {
        tambah_btn.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/tokoindah/admin-page/product/add-product-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                stage.setTitle("Add Product");
                stage.setScene(scene);
                stage.show();
            }catch (Exception e){
                System.err.println(e);
            }
        });
    }
}
