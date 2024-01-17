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
import javafx.scene.layout.HBox;
import model.CambiarIdioma;
import model.Data;

import java.io.IOException;

public class ControllerMenu {

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

    @FXML
    private Label labelAñadirLibro;

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
    private Data data;

    @FXML
    void meterLibro(MouseEvent event) throws IOException {
        this.data.setFiltrar(false);

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("meter_libro.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerMeterLibro controllerMeterLibro = fxmlLoader.getController();
        controllerMeterLibro.recibirData(this.data);
        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);

        this.reiniciarHboxes();

        this.labelAñadirLibro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        this.hboxMeter.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

        if (!this.data.isOscuro()) {
            this.imagenMeter.setImage(new Image(getClass().getResourceAsStream("/images/plusgris.png")));
        }

    }

    @FXML
    void verConfiguracion(MouseEvent event) throws IOException {
        this.data.setFiltrar(false);

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("configuracion.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();

        ControllerConfiguracion controllerConfiguracion = fxmlLoader.getController();
        controllerConfiguracion.recibirData(this.data);

        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);
        this.seleccionConfig();

    }

    @FXML
    void verLibros(MouseEvent event) throws IOException {

        this.data.setFiltrar(false);

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lista_libros.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerListaLibros controllerListaLibros = fxmlLoader.getController();
        controllerListaLibros.establecerDatos(this.data);

        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);

        this.reiniciarHboxes();

        this.labelLibros.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        this.hboxLibros.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

        if (!this.data.isOscuro()) {
            this.imagenLibros.setImage(new Image(getClass().getResourceAsStream("/images/librosgris.png")));
        }

    }

    @FXML
    void verTabla(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tabla_libros.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerTablaLibros controllerTablaLibros = fxmlLoader.getController();
        controllerTablaLibros.recibirData(this.data);

        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);

        this.reiniciarHboxes();

        this.labelVista.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        this.hboxDetallada.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

        if (!this.data.isOscuro()) {
            this.imagenDetallada.setImage(new Image(getClass().getResourceAsStream("/images/listagris.png")));
        }

    }

    @FXML
    void verUsuario(MouseEvent event) throws IOException {
        this.data.setFiltrar(false);

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_usuario.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();

        ControllerVistaUsuario controllerVistaUsuario = fxmlLoader.getController();
        controllerVistaUsuario.recibirData(this.data);

        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);

        this.seleccionUsuario();
    }

    @FXML
    void volverInicio(MouseEvent event) throws IOException {
        this.data.getControllers().getControllerPanelPrincipal().cargarInicio();
    }


    public void establecerDatos(Data data) {
        this.data = data;
        this.data.getControllers().setControllerMenu(this);
        this.labelMostrarNombre.setText(this.labelMostrarNombre.getText() + this.data.getCurrentUser().getNombreUsuario());
    }

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
        if(this.data.isOscuro()){
            this.imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/images/usuarioblanco.png")));
            this.imagenDetallada.setImage(new Image(getClass().getResourceAsStream("/images/listablanca.png")));
            this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsblanco.png")));
            this.imagenLibros.setImage(new Image(getClass().getResourceAsStream("/images/librosblanco.png")));
            this.imagenMeter.setImage(new Image(getClass().getResourceAsStream("/images/plusblanco.png")));
        }else {
            this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsnegro.png")));
            this.imagenDetallada.setImage(new Image(getClass().getResourceAsStream("/images/listanegra.png")));
            this.imagenLibros.setImage(new Image(getClass().getResourceAsStream("/images/librosnegro.png")));
            this.imagenMeter.setImage(new Image(getClass().getResourceAsStream("/images/plusnegro.png")));
            this.imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/images/usuarionegro.png")));
        }
    }
    public void modoOscuro(){
        this.imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/images/usuarioblanco.png")));
        this.imagenDetallada.setImage(new Image(getClass().getResourceAsStream("/images/listablanca.png")));
        this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsblanco.png")));
        this.imagenLibros.setImage(new Image(getClass().getResourceAsStream("/images/librosblanco.png")));
        this.imagenMeter.setImage(new Image(getClass().getResourceAsStream("/images/plusblanco.png")));
    }
    public void modoClaro(){
        this.reiniciarHboxes();
        this.labelConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.hboxConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        if(!this.data.isOscuro()){
            this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsgris.png")));
        }
    }
    public void seleccionUsuario(){
        this.reiniciarHboxes();

        this.labelUsuario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        this.hboxUsuario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

        if (!this.data.isOscuro()) {
            this.imagenUsuario.setImage(new Image(getClass().getResourceAsStream("/images/usuariogris.png")));
        }
    }
    public void seleccionConfig(){
        this.reiniciarHboxes();

        this.labelConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);
        this.hboxConfig.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), true);

        if (!this.data.isOscuro()) {
            this.imagenConfig.setImage(new Image(getClass().getResourceAsStream("/images/settingsgris.png")));
        }
    }
}
