package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Data;
import modelo.Proyecto;

import java.util.Objects;

public class ControllerCadaProyecto {

    @FXML
    private Label descripcion;

    @FXML
    private Label fechaCreacion;

    @FXML
    private Label fechaEntrega;

    @FXML
    private ImageView imagen;

    @FXML
    private Label nombre;
    private Data data;
    private Proyecto proyecto;

    @FXML
    void eliminarProyecto(MouseEvent event) {

    }

    @FXML
    void verProyecto(MouseEvent event) {

    }
    public void recibirData(Data data, Proyecto proyecto){
        this.data = data;
        this.proyecto = proyecto;
        this.inicializar();
    }
    public void inicializar(){
        this.nombre.setText(this.proyecto.getNombre());
        this.descripcion.setText(this.proyecto.getDescripcion());
        this.imagen.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("file:" + this.proyecto.getRutaImagen()))));
    }

}

