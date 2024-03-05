module com.example.manageit {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires atlantafx.base;


    opens mainApp to javafx.fxml;
    exports mainApp;
    exports controller;
    exports modelo;
    opens controller to javafx.fxml;
}