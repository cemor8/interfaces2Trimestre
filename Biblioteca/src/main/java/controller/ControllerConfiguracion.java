package controller;

import com.example.biblioteca.MainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.CambiarIdioma;
import model.Data;

import java.io.IOException;
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
    void guardar(MouseEvent event) throws IOException {
        this.cambiarIdioma();
        this.cambiarColor();
    }

    public void recibirData(Data data) {
        this.data = data;

        if (this.data.isOscuro()) {
            this.opcionesColor.getSelectionModel().selectFirst();
        } else {
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

    public void cambiarIdioma() throws IOException {

        if (this.opcionesIdioma.getSelectedItem() == null) {
            return;
        } else if (this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("English") || this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Ingles")) {
            CambiarIdioma.getInstance().cargarIdioma("en", "US");
        } else if (this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Spanish") || this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Español")) {
            CambiarIdioma.getInstance().cargarIdioma("es", "ES");
        }


        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("configuracion.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();

        ControllerConfiguracion controllerConfiguracion = fxmlLoader.getController();
        controllerConfiguracion.recibirData(this.data);
        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);

        FXMLLoader fxmlLoaderMenu = new FXMLLoader(MainApplication.class.getResource("menu.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent rootMenu = fxmlLoader.load();
        ControllerMenu controllerMenu = fxmlLoaderMenu.getController();
        controllerMenu.establecerDatos(this.data);
        this.data.getControllers().getControllerPanelPrincipal().cambiarMenu(rootMenu);


    }

    public void cambiarColor() {
        this.data.getMain().getStylesheets().clear();
        if (this.opcionesColor.getSelectedItem().equalsIgnoreCase("Dark Mode") || this.opcionesColor.getSelectedItem().equalsIgnoreCase("Modo Oscuro")) {
            this.data.getMain().getStylesheets().add(getClass().getResource("/styles/oscuro/principal.css").toExternalForm());
            this.data.setOscuro(true);
            this.data.getControllers().getControllerMenu().modoOscuro();
        } else {
            this.data.getMain().getStylesheets().add(getClass().getResource("/styles/claro/principal.css").toExternalForm());
            this.data.setOscuro(false);
            this.data.getControllers().getControllerMenu().modoClaro();
        }

    }
}

