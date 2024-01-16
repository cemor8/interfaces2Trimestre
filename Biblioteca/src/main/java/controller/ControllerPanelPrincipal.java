package controller;

import com.example.biblioteca.MainApplication;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.CambiarIdioma;
import model.Data;

import java.io.IOException;
import java.util.ResourceBundle;

public class ControllerPanelPrincipal {

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane menuLateral;

    @FXML
    private AnchorPane rellenarContenido;
    private Data data;



    public void cargarInicio() throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_inicial.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerVistaInicial controllerVistaInicial = fxmlLoader.getController();
        controllerVistaInicial.establecerDatos(this.data);
        this.cambiarContenido(root);
    }
    public void cambiarContenido(Parent root){
        this.rellenarContenido.getChildren().setAll(root);
    }
    public void cambiarMenu(Parent root){
        this.menuLateral.getChildren().setAll(root);
    }



    public void ponerNombre(){

    }
    public void establecerDatos(Data data) throws IOException {
        this.data = data;
        this.cargarInicio();
        this.cargarMenu();
        this.data.getControllers().setControllerPanelPrincipal(this);

    }
    public void cargarMenu() throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu.fxml"));
        Parent root = fxmlLoader.load();
        ControllerMenu controllerMenu = fxmlLoader.getController();
        controllerMenu.establecerDatos(this.data);
        this.menuLateral.getChildren().setAll(root);
        this.data.getControllers().setControllerMenu(controllerMenu);
    }

}

