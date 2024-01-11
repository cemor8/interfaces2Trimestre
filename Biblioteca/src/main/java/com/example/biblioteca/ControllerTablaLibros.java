package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ControllerTablaLibros {

    @FXML
    private MFXButton btnBorrar;

    @FXML
    private MFXButton btnVer;

    @FXML
    private MFXCheckbox filtrar;
    @FXML
    private TableColumn<Libro, String> columnaAutor;

    @FXML
    private TableColumn<Libro, String> columnaFecha;

    @FXML
    private TableColumn<Libro, String> columnaISBN;

    @FXML
    private TableColumn<Libro, String> columnaTitulo;
    @FXML
    private TableView<Libro> tabla;
    private Data data;

    @FXML
    void borrar(MouseEvent event) {

    }

    @FXML
    void filtrar(MouseEvent event) {

    }

    @FXML
    void ver(MouseEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;

    }

}

