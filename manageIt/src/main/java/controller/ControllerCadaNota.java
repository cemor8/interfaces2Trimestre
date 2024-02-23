package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import modelo.Data;
import modelo.Nota;

import java.util.ArrayList;

public class ControllerCadaNota {

    @FXML
    private ImageView imagen;

    @FXML
    private ImageView imagenOjo;

    @FXML
    private ImageView imagenPapelera;

    @FXML
    private Label labelDescripcion;

    @FXML
    private Label labelFecha;

    @FXML
    private Label labelTitulo;
    private ArrayList<Nota> notas;


    @FXML
    void borrarNota(MouseEvent event) {
        MFXButton btn = (MFXButton) event.getSource();
        String id = btn.getId();
        this.notas.remove(this.notas.get(Integer.parseInt(id)));
    }

    @FXML
    void ver(MouseEvent event) {
        /* Cambiar el contenido del anterior controlador */
    }
    public void recibirData(Data data,ArrayList<Nota> notas){
        this.notas = notas;
    }

}

