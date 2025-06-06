module com.example.tokoindah {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.xml.crypto;

    opens com.example.tokoindah to javafx.fxml;
    exports com.example.tokoindah;
    exports com.example.tokoindah.controller;
    opens com.example.tokoindah.controller to javafx.fxml;
    opens com.example.tokoindah.controller.adminPage to javafx.fxml;
    exports com.example.tokoindah.controller.adminPage;
    opens com.example.tokoindah.controller.adminPage.product to javafx.fxml;
    exports com.example.tokoindah.controller.adminPage.product;
    opens com.example.tokoindah.model to javafx.fxml;
    exports com.example.tokoindah.model;
}