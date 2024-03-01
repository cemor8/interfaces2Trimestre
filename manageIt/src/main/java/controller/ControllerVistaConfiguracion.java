package controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Data;

public class ControllerVistaConfiguracion {

    @FXML
    private ImageView imgEditar;

    @FXML
    private ImageView imgPersona;

    @FXML
    private MFXTextField introducirApellido;

    @FXML
    private TextArea introducirBio;

    @FXML
    private MFXTextField introducirNombre;
    private Data data;

    @FXML
    void cambiarClaro(MouseEvent event) {

    }

    @FXML
    void cambiarEspañol(MouseEvent event) {

    }

    @FXML
    void cambiarIngles(MouseEvent event) {

    }

    @FXML
    void cambiarOscuro(MouseEvent event) {

    }

    @FXML
    void editarImagen(MouseEvent event) {

    }

    @FXML
    void guardarApellido(MouseEvent event) {

    }

    @FXML
    void guardarBio(MouseEvent event) {

    }

    @FXML
    void guardarImagen(MouseEvent event) {

    }

    @FXML
    void guardarNombre(MouseEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;
        this.inicializar();
    }
    public void inicializar(){
        this.imgPersona.setImage(new Image("file:"+this.data.getCurrentUser().getRutaImagen()));
    }

}

