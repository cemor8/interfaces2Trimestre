package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Data;
import modelo.Usuario;

public class ControllerVistaCadaContacto {

    @FXML
    private AnchorPane anchor;

    @FXML
    private MFXButton btn;

    @FXML
    private Label descripcion;

    @FXML
    private ImageView img;

    @FXML
    private Label labelCorreo;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelPuesto;
    private Data data;
    private Usuario contacto;

    @FXML
    void gestionarContacto(MouseEvent event) {

    }
    public void cargarContacto(){

    }
    public void recibirData(Data data, Usuario usuario){
        this.data = data;
        this.contacto = usuario;
        this.cargarContacto();
    }

}

