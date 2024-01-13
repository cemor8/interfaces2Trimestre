package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerConfiguracion {

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private Label labelColor;

    @FXML
    private Label labelIdioma;

    @FXML
    private MFXComboBox<String> opcionesColor;

    @FXML
    private MFXComboBox<String> opcionesIdioma;
    private Data data;
    @FXML
    void guardar(MouseEvent event) {
        this.cambiarIdioma();
        this.comprobarColor();
    }
    public void recibirData(Data data){
        this.data = data;
        this.traducir();
    }
    public void traducir(){
        this.data.setBundle(ResourceBundle.getBundle("bundles.MessagesBundle",this.data.getLocale()));
        ResourceBundle bundle = this.data.getBundle();
        if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){

            this.btnGuardar.setText(bundle.getString("settings.save"));

            this.labelColor.setText(bundle.getString("settings.color"));

            this.opcionesColor.getItems().setAll(
                    bundle.getString("settings.color1"),
                    bundle.getString("settings.color2"));

            this.labelIdioma.setText(bundle.getString("settings.language"));
            this.opcionesIdioma.getItems().setAll(
                    bundle.getString("settings.language1"),
                    bundle.getString("settings.language2"));
            this.opcionesIdioma.setValue(bundle.getString("settings.language2"));

        }else{

            this.btnGuardar.setText(bundle.getString("configuracion.guardar"));

            this.labelColor.setText(bundle.getString("configuracion.color"));

            this.opcionesColor.getItems().setAll(
                    bundle.getString("configuracion.color1"),
                    bundle.getString("configuracion.color2"));

            this.labelIdioma.setText(bundle.getString("configuracion.idioma"));
            this.opcionesIdioma.getItems().setAll(
                    bundle.getString("configuracion.idioma1"),
                    bundle.getString("configuracion.idioma2"));

            this.opcionesIdioma.setValue(bundle.getString("configuracion.idioma1"));

        }
        this.data.getControllerPanelPrincipal().traducir();
    }
    public void cambiarIdioma(){
        if (this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Spanish") || this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Español")){
            this.data.setLocale(new Locale("es"));
        }else {
            this.data.setLocale(new Locale("en"));
        }
        this.traducir();
    }
    public void comprobarColor(){
        Scene scene = this.labelColor.getScene();
        if(this.data.isOscuro()){
            scene.getStylesheets().add(getClass().getResource("/styles/oscuro/principal.css").toExternalForm());
        }else{
            scene.getStylesheets().add(getClass().getResource("/styles/claro/principal.css").toExternalForm());
        }
    }
}

