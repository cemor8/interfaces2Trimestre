package com.example.biblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class ControllerVistaInicial {

    @FXML
    private Label labelBienvenido;
    private Data data;

    public void establecerDatos(Data data){
        this.data = data;
        this.traducir();
    }
    public void traducir(){
        this.data.setBundle(ResourceBundle.getBundle("bundles.MessagesBundle",this.data.getLocale()));
        ResourceBundle bundle = this.data.getBundle();
        if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
            this.labelBienvenido.setText(bundle.getString("panel.welcome"));
        }else{
            this.labelBienvenido.setText(bundle.getString("panel.hola"));
        }
    }

}

