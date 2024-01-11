package com.example.testbarrabusqueda;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        TextField searchField = new TextField();
        ListView<String> listView = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList(
                "Elemento 1", "Elemento 2", "Elemento 3" // ... más elementos
        );
        listView.setItems(items);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                listView.setItems(items); // Si el campo está vacío, muestra todos los elementos
            } else {
                ObservableList<String> filteredList = items.filtered(item -> item.toLowerCase().contains(newValue.toLowerCase()));
                listView.setItems(filteredList);
            }
        });

        root.getChildren().addAll(searchField, listView);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}