package com.example.biblioteca;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ControllerPanelPrincipal {

    @FXML
    private Label labelMostrarNombre;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane menuLateral;

    @FXML
    private AnchorPane rellenarContenido;
    private Data data;

    @FXML
    void meterLibro(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("meter_libro.fxml"));
        Parent root = fxmlLoader.load();
        ControllerMeterLibro controllerMeterLibro = fxmlLoader.getController();
        controllerMeterLibro.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);

    }

    @FXML
    void verConfiguracion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("configuracion.fxml"));
        Parent root = fxmlLoader.load();
        ControllerConfiguracion controllerConfiguracion = fxmlLoader.getController();
        controllerConfiguracion.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);
    }

    @FXML
    void verLibros(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lista_libros.fxml"));
        Parent root = fxmlLoader.load();
        ControllerListaLibros controllerListaLibros = fxmlLoader.getController();
        this.data.setControllerPanelPrincipal(this);
        controllerListaLibros.establecerDatos(this.data);
        this.rellenarContenido.getChildren().setAll(root);
    }

    @FXML
    void verTabla(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tabla_libros.fxml"));
        Parent root = fxmlLoader.load();
        ControllerTablaLibros controllerTablaLibros = fxmlLoader.getController();
        this.data.setControllerPanelPrincipal(this);
        controllerTablaLibros.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);
    }

    @FXML
    void verUsuario(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_usuario.fxml"));
        Parent root = fxmlLoader.load();
        ControllerVistaUsuario controllerVistaUsuario = fxmlLoader.getController();
        controllerVistaUsuario.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);
    }
    public void cambiarContenido(Parent root){
        this.rellenarContenido.getChildren().setAll(root);
    }
    public void establecerDatos(Data data){
        this.data = data;
        this.labelMostrarNombre.setText("Hola, "+this.data.getCurrentUser().getNombreUsuario());
    }

}

