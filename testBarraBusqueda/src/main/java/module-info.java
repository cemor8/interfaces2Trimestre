module com.example.testbarrabusqueda {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testbarrabusqueda to javafx.fxml;
    exports com.example.testbarrabusqueda;
}