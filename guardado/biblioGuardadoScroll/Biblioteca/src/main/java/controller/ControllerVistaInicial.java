package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Data;

import java.util.ResourceBundle;

public class ControllerVistaInicial {

    @FXML
    private Label labelBienvenido;
    private Data data;

    public void establecerDatos(Data data){
        this.data = data;
    }

}

