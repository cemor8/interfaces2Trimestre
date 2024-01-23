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
        this.cambiarColor();
        this.cambiarIdioma();
    }
    /**
     * Método que se encarga de recibir el modelo para que el controlador tenga acceso a el, también
     * inicializa los comboboxes de idioma y color
     * */
    public void recibirData(Data data) {
        this.data = data;
        this.opcionesIdioma.getItems().setAll(CambiarIdioma.getInstance().getBundle().getString("configuracion.idioma1"),
                CambiarIdioma.getInstance().getBundle().getString("configuracion.idioma2"));

        this.opcionesColor.getItems().setAll(CambiarIdioma.getInstance().getBundle().getString("configuracion.color1"),
                CambiarIdioma.getInstance().getBundle().getString("configuracion.color2"));

        if (this.data.isOscuro()) {
            this.opcionesColor.getSelectionModel().selectFirst();
        } else {
            this.opcionesColor.getSelectionModel().selectLast();
        }

        if(this.data.isEspañol()){
            this.opcionesIdioma.getSelectionModel().selectFirst();
        }else {
            this.opcionesIdioma.getSelectionModel().selectLast();
        }


    }
    /**
     * Método que se encarga de cambiar el idioma al seleccionado, vuelve a cargar la pantalla de la
     * configuracion y el menu con el nuevo idioma
     * */
    public void cambiarIdioma() throws IOException {

        if (this.opcionesIdioma.getSelectedItem() == null) {
            return;
        } else if (this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("English") || this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Inglés")) {
            CambiarIdioma.getInstance().cargarIdioma("en", "US");
            this.data.setEspañol(false);
        } else if (this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Spanish") || this.opcionesIdioma.getSelectedItem().equalsIgnoreCase("Español")) {
            CambiarIdioma.getInstance().cargarIdioma("es", "ES");
            this.data.setEspañol(true);
        }


        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("configuracion.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();

        ControllerConfiguracion controllerConfiguracion = fxmlLoader.getController();
        controllerConfiguracion.recibirData(this.data);

        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);

        FXMLLoader fxmlLoaderMenu = new FXMLLoader(MainApplication.class.getResource("menu.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent rootMenu = fxmlLoaderMenu.load();
        ControllerMenu controllerMenu = fxmlLoaderMenu.getController();
        controllerMenu.establecerDatos(this.data);
        this.data.getControllers().getControllerPanelPrincipal().cambiarMenu(rootMenu);
        this.data.getControllers().getControllerMenu().modoClaro();

    }
    /**
     * Método que se encarga de cambiar el color de fondo de la app, establece un css u otro
     * dependiendo de la opcion seleccionada
     * */
    public void cambiarColor() {
        this.data.getControllers().getControllerPanelPrincipal().eliminarEstilos();
        if (this.opcionesColor.getSelectedItem().equalsIgnoreCase("Dark Mode") || this.opcionesColor.getSelectedItem().equalsIgnoreCase("Modo Oscuro")) {
            this.data.getControllers().getControllerPanelPrincipal().meterEstilo("/styles/oscuro/principal.css");
            this.data.setOscuro(true);
            this.data.getControllers().getControllerMenu().modoOscuro();
        } else {
            this.data.getControllers().getControllerPanelPrincipal().meterEstilo("/styles/claro/principal.css");
            this.data.setOscuro(false);
            this.data.getControllers().getControllerMenu().modoClaro();
        }

    }
}

