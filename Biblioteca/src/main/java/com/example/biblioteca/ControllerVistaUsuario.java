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
            put("Nombre", "^[A-Z][a-z]{3,20}$");
            put("Edad", "^((1[01][0-9]|12[0]|[1-9][0-9]|[1-9]))$");
            put("Email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        }

    };

    @FXML
    void guardar(MouseEvent event) {
        if(!validarContenido(this.columnasExpresiones.get("Apellido"),this.introducirApellido.getText())){
            this.data.getCurrentUser().setApellidos(this.introducirApellido.getText());
        }
        if(!validarContenido(this.columnasExpresiones.get("Nombre"),this.introducirNombre.getText())){
            this.data.getCurrentUser().setNombreUsuario(this.introducirNombre.getText());
            this.data.getControllerPanelPrincipal().ponerNombre();
        }
        if(!validarContenido(this.columnasExpresiones.get("Edad"),this.introducirEdad.getText())){
            this.data.getCurrentUser().setEdad(this.introducirEdad.getText());
        }
        if(!validarContenido(this.columnasExpresiones.get("Email"),this.introducirEmail.getText())){
            this.data.getCurrentUser().setCorreo(this.introducirEmail.getText());
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
        if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
            this.btnGuardar.setText(bundle.getString("user.save"));
            this.btnSalir.setText(bundle.getString("user.logout"));
            this.labelApellido.setText(bundle.getString("user.surname"));
            this.labelEdad.setText(bundle.getString("user.age"));
            this.labelEmail.setText(bundle.getString("user.email"));
            this.labelNombre.setText(bundle.getString("user.new"));

            this.introducirEmail.setPromptText(bundle.getString("prompt.text"));
            this.introducirEdad.setPromptText(bundle.getString("prompt.text"));
            this.introducirApellido.setPromptText(bundle.getString("prompt.text"));

        }else{
            this.btnGuardar.setText(bundle.getString("usuario.guardar"));
            this.btnSalir.setText(bundle.getString("usuario.salir"));
            this.labelApellido.setText(bundle.getString("usuario.apellido"));
            this.labelEdad.setText(bundle.getString("usuario.edad"));
            this.labelEmail.setText(bundle.getString("usuario.email"));
            this.labelNombre.setText(bundle.getString("usuario.nombre"));

            this.introducirEmail.setPromptText(bundle.getString("placeholder.texto"));
            this.introducirEdad.setPromptText(bundle.getString("placeholder.texto"));
            this.introducirApellido.setPromptText(bundle.getString("placeholder.texto"));

        }
    }

}

