package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ResourceBundle;

public class ControllerVistaLibro {

    @FXML
    private MFXButton btnVolver;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelTitulo;
    private Data data;

    @FXML
    void volver(MouseEvent event) throws IOException {

        if(!this.data.getVistaAnterior()){
            this.data.setLibroSeleccionado(null);
            this.data.setVistaAnterior(null);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tabla_libros.fxml"));
            Parent contenido = fxmlLoader.load();
            ControllerTablaLibros controllerTablaLibros = fxmlLoader.getController();
            controllerTablaLibros.recibirData(this.data);
            this.data.getControllerPanelPrincipal().cambiarContenido(contenido);
        }else {
            this.data.setLibroSeleccionado(null);
            this.data.setVistaAnterior(null);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lista_libros.fxml"));
            Parent contenido = fxmlLoader.load();
            ControllerListaLibros controllerListaLibros = fxmlLoader.getController();
            controllerListaLibros.establecerDatos(this.data);
            this.data.getControllerPanelPrincipal().cambiarContenido(contenido);

        }
    }
    public void establecerDatos(Data data){
        this.data = data;
        this.traducir();
        this.labelAutor.setText(this.data.getLibroSeleccionado().getAutor());
        this.labelTitulo.setText(this.data.getLibroSeleccionado().getTitulo());
    }
    public void traducir(){
        this.data.setBundle(ResourceBundle.getBundle("bundles.MessagesBundle",this.data.getLocale()));
        ResourceBundle bundle = this.data.getBundle();
        if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
            this.btnVolver.setText(bundle.getString("see.back"));
        }else{
            this.btnVolver.setText(bundle.getString("ver.volver"));
        }
    }

}

