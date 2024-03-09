package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.css.PseudoClass;
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
    private MFXButton btnClaro;

    @FXML
    private MFXButton btnEspañol;

    @FXML
    private MFXButton btnIngles;

    @FXML
    private MFXButton btnOscuro;

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
        this.introducirBio.setText(this.data.getCurrentUser().getDescripcion());
        this.introducirNombre.setPromptText(this.data.getCurrentUser().getNombre());
        this.introducirApellido.setPromptText(this.data.getCurrentUser().getApellidos());

        if (this.data.isEspañol()){
            this.btnEspañol.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
            this.btnIngles.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        }else {
            this.btnEspañol.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
            this.btnIngles.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        }
        if (this.data.isOscuro()){
            this.btnOscuro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
            this.btnClaro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        }else{
            this.btnOscuro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
            this.btnClaro.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        }
    }

}

