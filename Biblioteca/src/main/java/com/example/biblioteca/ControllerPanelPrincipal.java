package com.example.biblioteca;

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
    private HBox hboxConfig;

    @FXML
    private HBox hboxDetallada;

    @FXML
    private HBox hboxLibros;

    @FXML
    private HBox hboxMeter;

    @FXML
    private HBox hboxUsuario;
    @FXML
    private ImageView imagenConfig;

    @FXML
    private ImageView imagenDetallada;

    @FXML
    private ImageView imagenLibros;

    @FXML
    private ImageView imagenMeter;

    @FXML
    private ImageView imagenUsuario;
    public void reiniciarHboxes(){
        this.labelAñadirLibro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.labelLibros.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.labelUsuario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.labelConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.labelVista.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);


        this.hboxConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxDetallada.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxLibros.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxMeter.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxUsuario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsnegro.png")));
        this.imagenDetallada.setImage(new Image(getClass().getResourceAsStream("/images/listanegra.png")));
        this.imagenLibros.setImage(new Image(getClass().getResourceAsStream("/images/librosnegro.png")));
        this.imagenMeter.setImage(new Image(getClass().getResourceAsStream("/images/plusnegro.png")));
        this.imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/images/usuarionegro.png")));

    }

    @FXML
    void meterLibro(MouseEvent event) throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("meter_libro.fxml"));
        Parent root = fxmlLoader.load();
        ControllerMeterLibro controllerMeterLibro = fxmlLoader.getController();
        controllerMeterLibro.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);
        this.reiniciarHboxes();
        this.labelAñadirLibro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.hboxMeter.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        if(this.data.isOscuro()){
            this.imagenMeter.setImage(new Image(getClass().getResourceAsStream("/images/plusblanco.png")));
        }else {
            this.imagenMeter.setImage(new Image(getClass().getResourceAsStream("/images/plusgris.png")));
        }



    }

    @FXML
    void verConfiguracion(MouseEvent event) throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("configuracion.fxml"));
        Parent root = fxmlLoader.load();
        ControllerConfiguracion controllerConfiguracion = fxmlLoader.getController();
        this.data.setControllerPanelPrincipal(this);
        controllerConfiguracion.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);
        this.reiniciarHboxes();
        this.labelConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.hboxConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        if(this.data.isOscuro()){
            this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsblanco.png")));
        }else {
            this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsgris.png")));
        }
    }

    @FXML
    void verLibros(MouseEvent event) throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lista_libros.fxml"));
        Parent root = fxmlLoader.load();
        ControllerListaLibros controllerListaLibros = fxmlLoader.getController();
        this.data.setControllerPanelPrincipal(this);
        controllerListaLibros.establecerDatos(this.data);
        this.rellenarContenido.getChildren().setAll(root);
        this.reiniciarHboxes();
        this.labelLibros.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.hboxLibros.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        if(this.data.isOscuro()){
            this.imagenLibros.setImage(new Image(getClass().getResourceAsStream("/images/librosblanco.png")));
        }else {
            this.imagenLibros.setImage(new Image(getClass().getResourceAsStream("/images/librosgris.png")));
        }
    }

    @FXML
    void verTabla(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tabla_libros.fxml"));
        Parent root = fxmlLoader.load();
        ControllerTablaLibros controllerTablaLibros = fxmlLoader.getController();
        this.data.setControllerPanelPrincipal(this);
        controllerTablaLibros.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);
        this.reiniciarHboxes();
        this.labelVista.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.hboxDetallada.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        if(this.data.isOscuro()){
            this.imagenDetallada.setImage(new Image(getClass().getResourceAsStream("/images/listablanca.png")));
        }else {
            this.imagenDetallada.setImage(new Image(getClass().getResourceAsStream("/images/listagris.png")));
        }
    }

    @FXML
    void verUsuario(MouseEvent event) throws IOException {
        this.data.setFiltrar(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_usuario.fxml"));
        Parent root = fxmlLoader.load();
        ControllerVistaUsuario controllerVistaUsuario = fxmlLoader.getController();
        controllerVistaUsuario.recibirData(this.data);
        this.rellenarContenido.getChildren().setAll(root);
        this.reiniciarHboxes();
        this.labelUsuario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.hboxUsuario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        if(this.data.isOscuro()){
            this.imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/images/usuarioblanco.png")));
        }else {
            this.imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/images/usuariogris.png")));
        }
    }
    public void cambiarContenido(Parent root){
        this.rellenarContenido.getChildren().setAll(root);
    }
    public void establecerDatos(Data data){
        this.data = data;
        this.data.setMain(this.main);
        this.traducir();
        this.labelMostrarNombre.setText("Hola, "+this.data.getCurrentUser().getNombreUsuario());
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

