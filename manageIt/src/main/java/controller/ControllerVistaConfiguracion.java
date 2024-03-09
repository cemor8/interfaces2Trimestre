package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import mainApp.HelloApplication;
import modelo.CambiarIdioma;
import modelo.Data;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ControllerVistaConfiguracion {

    @FXML
    public ImageView imgEditar;

    @FXML
    public ImageView imgPersona;
    @FXML
    public MFXButton btnClaro;

    @FXML
    public MFXButton btnEspañol;

    @FXML
    public MFXButton btnIngles;
    @FXML
    private Label errorApellidos;

    @FXML
    private Label errorBio;

    @FXML
    private Label errorImagen;

    @FXML
    private Label errorNombre;

    @FXML
    public MFXButton btnOscuro;

    @FXML
    public MFXTextField introducirApellido;

    @FXML
    public TextArea introducirBio;

    @FXML
    public MFXTextField introducirNombre;
    public String rutaImagen;
    private Data data;

    @FXML
    void cambiarClaro(MouseEvent event) {
        if (!this.data.isOscuro()){
            return;
        }
        this.btnOscuro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.btnClaro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.data.setOscuro(false);
    }

    /**
     * Método que cambia el idioma a español
     * @param event
     * @throws IOException
     */
    @FXML
    void cambiarEspañol(MouseEvent event) throws IOException {
        if (this.data.isEspañol()){
            return;
        }
        this.btnEspañol.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.btnIngles.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.data.setEspañol(true);

        CambiarIdioma.getInstance().cargarIdioma("es", "ES");
        Locale.setDefault(new Locale("es"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/vistaConfiguracion.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();

        ControllerVistaConfiguracion controllerVistaConfiguracion = fxmlLoader.getController();
        controllerVistaConfiguracion.recibirData(this.data);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);

        if (this.rutaImagen == null || this.rutaImagen.equalsIgnoreCase("")){
            controllerVistaConfiguracion.imgPersona.setImage(new Image("file:"+this.data.getCurrentUser().getRutaImagen()));
        }else {
            controllerVistaConfiguracion.imgPersona.setImage(new Image("file:"+rutaImagen));
        }
        if (this.introducirBio.getText().isEmpty()){
            controllerVistaConfiguracion.introducirBio.setText(this.data.getCurrentUser().getDescripcion());
        }else{
            controllerVistaConfiguracion.introducirBio.setText(this.introducirBio.getText());
        }
        if (this.introducirNombre.getText().isEmpty()){
            controllerVistaConfiguracion.introducirNombre.setPromptText(this.data.getCurrentUser().getNombre());
        }else{
            controllerVistaConfiguracion.introducirNombre.setText(this.introducirNombre.getText());
        }
        if (this.introducirApellido.getText().isEmpty()){
            controllerVistaConfiguracion.introducirApellido.setPromptText(this.data.getCurrentUser().getApellidos());
        }else{
            controllerVistaConfiguracion.introducirApellido.setText(this.introducirApellido.getText());
        }
        this.data.getListaControladores().getControllerContenedor().cargarLateral();


    }

    /**
     * Método que cambia el idioma de la app a inglés
     * @param event
     * @throws IOException
     */
    @FXML
    void cambiarIngles(MouseEvent event) throws IOException {
        if (!this.data.isEspañol()){
            return;
        }
        this.btnEspañol.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.btnIngles.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.data.setEspañol(false);
        CambiarIdioma.getInstance().cargarIdioma("en", "US");
        Locale.setDefault(new Locale("en"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/vistaConfiguracion.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();

        ControllerVistaConfiguracion controllerVistaConfiguracion = fxmlLoader.getController();
        controllerVistaConfiguracion.recibirData(this.data);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);

        if (this.rutaImagen == null || this.rutaImagen.equalsIgnoreCase("")){
            controllerVistaConfiguracion.imgPersona.setImage(new Image("file:"+this.data.getCurrentUser().getRutaImagen()));
        }else {
            controllerVistaConfiguracion.imgPersona.setImage(new Image("file:"+rutaImagen));
        }
        if (this.introducirBio.getText().isEmpty()){
            controllerVistaConfiguracion.introducirBio.setText(this.data.getCurrentUser().getDescripcion());
        }else{
            controllerVistaConfiguracion.introducirBio.setText(this.introducirBio.getText());
        }
        if (this.introducirNombre.getText().isEmpty()){
            controllerVistaConfiguracion.introducirNombre.setPromptText(this.data.getCurrentUser().getNombre());
        }else{
            controllerVistaConfiguracion.introducirNombre.setText(this.introducirNombre.getText());
        }
        if (this.introducirApellido.getText().isEmpty()){
            controllerVistaConfiguracion.introducirApellido.setPromptText(this.data.getCurrentUser().getApellidos());
        }else{
            controllerVistaConfiguracion.introducirApellido.setText(this.introducirApellido.getText());
        }

        this.data.getListaControladores().getControllerContenedor().cargarLateral();

    }

    @FXML
    void cambiarOscuro(MouseEvent event) {
        if (this.data.isOscuro()){
            return;
        }
        this.btnOscuro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.btnClaro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.data.setOscuro(true);
    }

    @FXML
    void editarImagen(MouseEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.png","*.jpg","*.jpeg"));
        File selectedFile = filechooser.showOpenDialog(null);

        if(selectedFile != null){
            String imagePath = selectedFile.getAbsolutePath();
            this.rutaImagen = imagePath;

            this.imgPersona.setImage(new Image("file:"+rutaImagen));

        }
    }

    @FXML
    void guardarApellido(MouseEvent event) {

    }

    @FXML
    void guardarBio(MouseEvent event) {

    }

    @FXML
    void guardarImagen(MouseEvent event) {
        if (this.rutaImagen!= null && !this.rutaImagen.equalsIgnoreCase("")){
            this.data.getCurrentUser().setRutaImagen(this.rutaImagen);
        }
    }

    @FXML
    void guardarNombre(MouseEvent event) {
        if (this.introducirNombre.getText().isEmpty()){
            return;
        }

    }
    public void recibirData(Data data){
        this.data = data;
        this.inicializar();
    }

    /**
     * Método que se encarga de inicializar todos los datos en la configuracion
     */
    public void inicializar(){
        this.imgPersona.setImage(new Image("file:"+this.data.getCurrentUser().getRutaImagen()));
        this.introducirBio.setText(this.data.getCurrentUser().getDescripcion());
        this.introducirNombre.setPromptText(this.data.getCurrentUser().getNombre());
        this.introducirApellido.setPromptText(this.data.getCurrentUser().getApellidos());

        if (this.data.isEspañol()){
            this.btnEspañol.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
            this.btnIngles.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        }else {
            this.btnEspañol.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
            this.btnIngles.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        }
        if (this.data.isOscuro()){
            this.btnOscuro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
            this.btnClaro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        }else{
            this.btnOscuro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
            this.btnClaro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        }
    }

}

