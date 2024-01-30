package com.example.chat;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerLogin {

    @FXML
    private MFXTextField introducirNombre;


    /**
     * Método que comprueba el login, si es correcto, lleva a la pantalla del panel al usuario.
     */
    @FXML
    void enviarCredenciales(ActionEvent event) throws IOException {
        if (this.introducirNombre.getText().isEmpty()){
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        ControllerCliente cliente = fxmlLoader.getController();
        cliente.recibirNombre(this.introducirNombre.getText());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Chat");
        stage.show();
        MFXButton button = (MFXButton) event.getSource();
        Stage stageThis= (Stage) button.getScene().getWindow();
        stageThis.close();
    }

}
