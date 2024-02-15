package controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modelo.Data;

public class ControllerMenuSuperior {

    @FXML
    private MFXTextField barraBusqueda;
    @FXML
    private Label nombre;

    @FXML
    private AnchorPane menuSuperior;
    private Data data;
    @FXML
    private ImageView perfil;

    /**
     * Método que se encarga de recibir la información
     * @param data información
     */
    public void recibirData(Data data){
        this.data = data;
        this.ponerImagen();
        Platform.runLater(() -> {
            this.menuSuperior.requestFocus();
        });
        this.nombre.setText(this.data.getCurrentUser().getNombre()+" "+this.data.getCurrentUser().getApellidos());

    }

    /**
     * Método que se encarga  d
     */
    public void ponerImagen(){
        this.perfil.setImage(new Image("file:"+this.data.getCurrentUser().getRutaImagen()));
        this.perfil.setFitWidth(60);
        this.perfil.setFitHeight(60);
        this.perfil.setPreserveRatio(true);
        Circle clip = new Circle(this.perfil.getFitWidth() / 2, this.perfil.getFitHeight() / 2, this.perfil.getFitWidth() / 2);
        this.perfil.setClip(clip);
    }
    @FXML
    void verCalendario(MouseEvent event) {

    }

    @FXML
    void verPerfil(MouseEvent event) {

    }

    @FXML
    void verProyectos(MouseEvent event) {

    }

}

