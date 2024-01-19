package controller;

import com.example.biblioteca.MainApplication;
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
import model.CambiarIdioma;
import model.Data;

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
    /**
     * Método que se encarga de guardar los cambios realizados al perfil
     * del usuario, si los datos no estan en un buen formato o se dejan en blanco,
     * quedará el valor anterior
     * */
    @FXML
    void guardar(MouseEvent event) throws IOException {
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
            FXMLLoader fxmlLoaderMenu = new FXMLLoader(MainApplication.class.getResource("menu.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent rootMenu = fxmlLoaderMenu.load();
            ControllerMenu controllerMenu = fxmlLoaderMenu.getController();
            controllerMenu.establecerDatos(this.data);
            controllerMenu.seleccionUsuario();
            this.data.getControllers().getControllerPanelPrincipal().cambiarMenu(rootMenu);



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

    /**
     * Método que se encarga de volver al login
     * */
    @FXML
    void salir(MouseEvent event) {
        this.data.setFiltrar(false);
        this.data.setOscuro(false);
        CambiarIdioma.getInstance().cargarIdioma("es","ES");
        this.data.setCurrentUser(null);
        this.data.setVistaAnterior(false);
        this.data.setLibroSeleccionado(null);
        this.data.getControllers().setControllerMenu(null);
        this.data.getControllers().setControllerPanelPrincipal(null);

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


}

