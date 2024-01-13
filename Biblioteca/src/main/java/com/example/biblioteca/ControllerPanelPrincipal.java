package com.example.biblioteca;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ResourceBundle;

public class ControllerPanelPrincipal {

    @FXML
    private Label labelAñadirLibro;

    @FXML
    private Label labelBienvenido;

    @FXML
    private Label labelConfig;

    @FXML
    private Label labelLibros;

    @FXML
    private Label labelMostrarNombre;

    @FXML
    private Label labelUsuario;

    @FXML
    private Label labelVista;

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
        this.data.setControllerPanelPrincipal(this);
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
        this.comprobarColor();
        this.traducir();
        this.labelMostrarNombre.setText("Hola, "+this.data.getCurrentUser().getNombreUsuario());
    }
    public void comprobarColor(){
        if(this.data.isOscuro()){
            this.main.getStylesheets().add(getClass().getResource("/styles/oscuro/principal.css").toExternalForm());
        }else{
            this.main.getStylesheets().add(getClass().getResource("/styles/claro/principal.css").toExternalForm());
        }
    }
    public void traducir(){
        this.data.setBundle(ResourceBundle.getBundle("bundles.MessagesBundle",this.data.getLocale()));
        ResourceBundle bundle = this.data.getBundle();
        if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){

            this.labelBienvenido.setText(bundle.getString("panel.wellcome"));
            this.labelAñadirLibro.setText(bundle.getString("menu.add"));
            this.labelConfig.setText(bundle.getString("menu.settings"));
            this.labelLibros.setText(bundle.getString("menu.books"));
            this.labelVista.setText(bundle.getString("menu.detailed"));
            this.labelUsuario.setText(bundle.getString("menu.user"));
            this.labelMostrarNombre.setText(bundle.getString("menu.username") + this.data.getCurrentUser().getNombreUsuario());
        }else{

            this.labelBienvenido.setText(bundle.getString("panel.hola"));
            this.labelAñadirLibro.setText(bundle.getString("panel.añadir"));
            this.labelConfig.setText(bundle.getString("panel.configuracion"));
            this.labelLibros.setText(bundle.getString("panel.libros"));
            this.labelVista.setText(bundle.getString("panel.vista"));
            this.labelUsuario.setText(bundle.getString("panel.usuario"));
            this.labelMostrarNombre.setText(bundle.getString("panel.nombre") + this.data.getCurrentUser().getNombreUsuario());
        }
    }

}

