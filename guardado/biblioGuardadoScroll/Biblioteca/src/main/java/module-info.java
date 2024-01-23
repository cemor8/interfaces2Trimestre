module com.example.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;


    opens com.example.biblioteca to javafx.fxml;
    exports com.example.biblioteca;
    exports model;
    opens model to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}