module com.example.tokoindah {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tokoindah to javafx.fxml;
    exports com.example.tokoindah;
    exports com.example.tokoindah.controller;
    opens com.example.tokoindah.controller to javafx.fxml;
    opens com.example.tokoindah.controller.adminPage to javafx.fxml;
    exports com.example.tokoindah.controller.adminPage;

}