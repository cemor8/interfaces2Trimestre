package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerLogin {

    @FXML
    private MFXButton btnContnuar;

    @FXML
    private Label infoContraseña;

    @FXML
    private Label infoUsuario;

    @FXML
    private MFXPasswordField introducirContraseña;

    @FXML
    private MFXTextField introducirUsuario;
    private Data data;
    Map<String, String> columnasExpresiones = new HashMap<String, String>() {
        {
            put("Contraseña", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
            put("usuario", "^[a-zA-Z][a-zA-Z0-9_.]{4,10}$");
        }

    };

    @FXML
    void continuar(MouseEvent event) throws IOException {

        boolean error = false;
        if (!validarContenido(this.columnasExpresiones.get("usuario"), this.introducirUsuario.getText())) {
            this.infoUsuario.setText("Usuario inválido");
            this.introducirUsuario.setText("");
            error = true;
        }
        if (!validarContenido(this.columnasExpresiones.get("Contraseña"), this.introducirContraseña.getText())) {
            this.infoContraseña.setText("Contraseña inválida");
            this.introducirContraseña.setText("");
            error = true;
        }
        if (error) {
            return;
        }





        this.data.setCurrentUser(new Usuario(this.introducirUsuario.getText()));
        Button btn = (Button) event.getSource();
        Stage stageLogin= (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("panel_principal.fxml"));
        Parent root =null;
        try {
             root = fxmlLoader.load();
        }catch (IOException err ){
            System.out.println(err.getMessage());
        }
        ControllerPanelPrincipal controllerPanel = fxmlLoader.getController();
        controllerPanel.establecerDatos(this.data);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/claro/principal.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Panel");
        stage.show();
        stageLogin.close();
    }
    public void establecerDatos(Data data){
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
