package controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Data;

public class ControllerVistaCrearTarea {

    @FXML
    private ImageView imagenProyecto;

    @FXML
    private ImageView imagenTarea;

    @FXML
    private TextArea introducirDescripcion;

    @FXML
    private MFXTextField introducirFecha;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private Label labelTextoCrearTarea;

    @FXML
    private ComboBox<String> seleccionarCliente;
    private Data data;

    @FXML
    void crear(MouseEvent event) {

    }

    @FXML
    void elegirImagen(MouseEvent event) {

    }
    private void recibirData(Data data){
        this.data = data;
    }

}

