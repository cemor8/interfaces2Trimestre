package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Data;

import java.util.Locale;
import java.util.ResourceBundle;

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
        this.cambiarColor();
    }
    public void recibirData(Data data){
        this.data = data;

        if (this.data.isOscuro()){
            this.opcionesColor.getSelectionModel().selectFirst();
        }else {
            this.opcionesColor.getSelectionModel().selectLast();
        }
        /*
        Comparar el idioma para seleccionar una opcion u otra
        if(){

            this.opcionesIdioma.getSelectionModel().selectLast();
        }else {
            this.opcionesIdioma.getSelectionModel().selectFirst();
        }

         */
    }

    public void cambiarIdioma(){
        //aqui cargar vistas de nuevo


    }
    public void cambiarColor(){
        this.data.getMain().getStylesheets().clear();
        if(this.opcionesColor.getSelectedItem().equalsIgnoreCase("Dark Mode") || this.opcionesColor.getSelectedItem().equalsIgnoreCase("Modo Oscuro")){
            this.data.getMain().getStylesheets().add(getClass().getResource("/styles/oscuro/principal.css").toExternalForm());
            this.data.setOscuro(true);
            this.data.getControllerPanelPrincipal().modoOscuro();
        }else {
            this.data.getMain().getStylesheets().add(getClass().getResource("/styles/claro/principal.css").toExternalForm());
            this.data.setOscuro(false);
            this.data.getControllerPanelPrincipal().modoClaro();
        }

    }
}

