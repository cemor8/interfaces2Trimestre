package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Data;
import modelo.Nota;
import modelo.Proyecto;
import modelo.Tarea;

import java.util.ArrayList;

public class ControllerNotas {

    @FXML
    private MFXButton btnMeter;

    @FXML
    private AnchorPane contenedorNota;

    @FXML
    private MFXScrollPane scroll;
    private Data data;
    private ArrayList<Nota> notasRecorrer;
    private Proyecto proyecto;
    private Tarea tarea;

    @FXML
    void meter(MouseEvent event) {
        /* Crear nota vacia y ponerla al inicio de la lista, si globales es true meterla en notas del usuario, si no en las del proyecto o la tarea */
    }
    public void inicializar(){
        /* Recorrer notas y meter en scrolleable */


    }
    public void recibirData(Data data, ArrayList<Nota> notas, Proyecto proyecto, Tarea tarea){
        this.notasRecorrer = notas;
        this.data = data;
        this.proyecto = proyecto;
        this.tarea = tarea;
        this.inicializar();
    }

}

