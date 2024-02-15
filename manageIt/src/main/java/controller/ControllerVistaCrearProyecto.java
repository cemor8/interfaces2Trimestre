package controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Data;

public class ControllerVistaCrearProyecto {

    @FXML
    private ImageView imagenProyecto;

    @FXML
    private MFXTextField introducirDescripcion;

    @FXML
    private MFXTextField introducirFecha;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private Label labelTextoCrearProyecto;

    @FXML
    private ComboBox<String> seleccionarCliente;
    private Data data;
    @FXML
    void crear(MouseEvent event) {
        /* Hacer comprobaciones */
    }
    @FXML
    void elegirImagen(MouseEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;
    }

}

