package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerVistaLibro {

    @FXML
    private MFXButton btnVolver;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelTitulo;
    private Data data;

    @FXML
    void volver(MouseEvent event) {

    }
    public void establecerDatos(Data data){
        this.data = data;
    }

}

