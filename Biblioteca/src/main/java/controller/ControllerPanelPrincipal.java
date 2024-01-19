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


    /**
     * Método que se encarga de cargar el inicio de la app
     * */
    public void cargarInicio() throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_inicial.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerVistaInicial controllerVistaInicial = fxmlLoader.getController();
        controllerVistaInicial.establecerDatos(this.data);
        this.cambiarContenido(root);
    }
    /**
     * Método que se encarga de cambiar el contenido del anchor que
     * mostrara las diferentes vistas de la app
     * */
    public void cambiarContenido(Parent root){
        this.rellenarContenido.getChildren().setAll(root);
    }
    /**
     * Método que se encarga de cambiar el contenido del menu
     * */
    public void cambiarMenu(Parent root){
        this.menuLateral.getChildren().setAll(root);
    }

    /**
     * Método que se encarga de recibir el modelo para que el controlador tenga acceso a el
     * */
    public void establecerDatos(Data data) throws IOException {
        this.data = data;
        this.cargarInicio();
        this.cargarMenu();
        this.data.getControllers().setControllerPanelPrincipal(this);

    }
    public void cargarMenu() throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("menu.fxml"),CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerMenu controllerMenu = fxmlLoader.getController();
        controllerMenu.establecerDatos(this.data);
        this.menuLateral.getChildren().setAll(root);
    }
    /**
     * Método que se encarga de eliminar los estilos
     * */
    public void eliminarEstilos(){
        this.main.getStylesheets().clear();
    }
    /**
     * Método que se encarga de añadir un estilo
     * */
    public void meterEstilo(String ruta){
        this.main.getStylesheets().add(getClass().getResource(ruta).toExternalForm());
    }

}

