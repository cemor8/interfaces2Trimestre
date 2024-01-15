package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerVistaUsuario {

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private MFXButton btnSalir;

    @FXML
    private MFXTextField introducirApellido;

    @FXML
    private MFXTextField introducirEdad;

    @FXML
    private MFXTextField introducirEmail;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelEdad;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNombre;
    private Data data;
    Map<String, String> columnasExpresiones = new HashMap<String, String>() {
        {
            put("Apellido", "^[A-Z][a-z]+(\\s[A-Z][a-z]+)?$");
            put("Nombre", "^[a-zA-Z][a-zA-Z0-9_.]{4,10}$");
            put("Edad", "^((1[01][0-9]|12[0]|[1-9][0-9]|[1-9]))$");
            put("Email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        }

    };
    @FXML
    private Label infoApellido;

    @FXML
    private Label infoEdad;

    @FXML
    private Label infoEmail;

    @FXML
    private Label infoNombre;

    @FXML
    void guardar(MouseEvent event) {
        if(validarContenido(this.columnasExpresiones.get("Apellido"),this.introducirApellido.getText())){
            this.data.getCurrentUser().setApellidos(this.introducirApellido.getText());
            this.infoApellido.setText("");
        }else if(!this.introducirApellido.getText().isEmpty()){
            this.infoApellido.setText("Contenido inválido");
            this.introducirApellido.setText("");
        }else {
            this.infoApellido.setText("");
        }

        if(validarContenido(this.columnasExpresiones.get("Nombre"),this.introducirNombre.getText())){
            this.data.getCurrentUser().setNombreUsuario(this.introducirNombre.getText());
            this.data.getControllerPanelPrincipal().ponerNombre();
            this.infoNombre.setText("");
        }else if(!this.introducirNombre.getText().isEmpty()){
            this.infoNombre.setText("Contenido inválido");
            this.introducirNombre.setText("");
        }else {
            this.infoNombre.setText("");
        }
        if(validarContenido(this.columnasExpresiones.get("Edad"),this.introducirEdad.getText())){
            this.data.getCurrentUser().setEdad(this.introducirEdad.getText());
            this.infoEdad.setText("");
        }else if(!this.introducirEdad.getText().isEmpty()){
            this.infoEdad.setText("Contenido inválido");
            this.introducirEdad.setText("");
        }else {
            this.infoEdad.setText("");
        }

        if(validarContenido(this.columnasExpresiones.get("Email"),this.introducirEmail.getText())){
            this.data.getCurrentUser().setCorreo(this.introducirEmail.getText());
            this.infoEmail.setText("");
        }else if(!this.introducirEmail.getText().isEmpty()){
            this.infoEmail.setText("Contenido inválido");
            this.introducirEmail.setText("");
        }else {
            this.infoEmail.setText("");
        }
    }

    @FXML
    void salir(MouseEvent event) {
        this.data.setFiltrar(false);
        this.data.setOscuro(false);
        this.data.setLocale(new Locale("es"));
        this.data.setCurrentUser(null);
        this.data.setVistaAnterior(false);
        this.data.setLibroSeleccionado(null);
        Button btn = (Button) event.getSource();
        Stage stageUsuario= (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        Parent root =null;
        try {
            root = fxmlLoader.load();
        }catch (IOException err ){
            System.out.println(err.getMessage());
        }
        ControllerLogin controllerLogin = fxmlLoader.getController();
        controllerLogin.establecerDatos(this.data);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
        stageUsuario.close();
    }
    public void recibirData(Data data){
        this.data = data;
        this.traducir();
    }
    /**
     * Método que devuelve true si se cumple una expresion regular en una string
     *
     * @param patron       expresion regular
     * @param texto_buscar texto donde buscar el patron
     */
    public boolean validarContenido(String patron, String texto_buscar) {
        Pattern patronValidar = Pattern.compile(patron);
        Matcher matcher = patronValidar.matcher(texto_buscar);
        return matcher.matches();
    }
    public void traducir(){
        this.data.setBundle(ResourceBundle.getBundle("bundles.MessagesBundle",this.data.getLocale()));
        ResourceBundle bundle = this.data.getBundle();

        this.introducirNombre.setPromptText(this.data.getCurrentUser().getNombreUsuario());

        if(this.data.getCurrentUser().getApellidos() != null && !this.data.getCurrentUser().getApellidos().isEmpty()){
            this.introducirApellido.setPromptText(this.data.getCurrentUser().getApellidos());
        }else {
            if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
                this.introducirApellido.setPromptText(bundle.getString("prompt.text"));

            }else{
                this.introducirApellido.setPromptText(bundle.getString("placeholder.texto"));
            }
        }

        if(this.data.getCurrentUser().getCorreo() != null && !this.data.getCurrentUser().getCorreo().isEmpty()){
            this.introducirEmail.setPromptText(this.data.getCurrentUser().getCorreo());
        }else {
            if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
                this.introducirEmail.setPromptText(bundle.getString("prompt.text"));
            }else{
                this.introducirEmail.setPromptText(bundle.getString("placeholder.texto"));
            }

        }
        if(this.data.getCurrentUser().getEdad() != null && !this.data.getCurrentUser().getEdad().isEmpty()){
            this.introducirEdad.setPromptText(this.data.getCurrentUser().getEdad());
        }else {
            if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
                this.introducirEdad.setPromptText(bundle.getString("prompt.text"));
            }else{
                this.introducirEdad.setPromptText(bundle.getString("placeholder.texto"));
            }

        }

        if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
            this.btnGuardar.setText(bundle.getString("user.save"));
            this.btnSalir.setText(bundle.getString("user.logout"));
            this.labelApellido.setText(bundle.getString("user.surname"));
            this.labelEdad.setText(bundle.getString("user.age"));
            this.labelEmail.setText(bundle.getString("user.email"));
            this.labelNombre.setText(bundle.getString("user.new"));

        }else{
            this.btnGuardar.setText(bundle.getString("usuario.guardar"));
            this.btnSalir.setText(bundle.getString("usuario.salir"));
            this.labelApellido.setText(bundle.getString("usuario.apellido"));
            this.labelEdad.setText(bundle.getString("usuario.edad"));
            this.labelEmail.setText(bundle.getString("usuario.email"));
            this.labelNombre.setText(bundle.getString("usuario.nombre"));

        }
    }

}

