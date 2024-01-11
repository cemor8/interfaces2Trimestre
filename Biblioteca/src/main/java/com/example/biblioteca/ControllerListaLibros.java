package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;

public class ControllerListaLibros {

    @FXML
    private MFXScrollPane contenedorLibros;
    private Data data;
    public void establecerDatos(Data data){
        this.data = data;
    }
    public void crearLibros(){
        
    }

}
