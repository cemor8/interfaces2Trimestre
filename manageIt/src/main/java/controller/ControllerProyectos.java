package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modelo.Data;
import modelo.Proyecto;

import java.util.ArrayList;

public class ControllerProyectos {

    @FXML
    private MFXButton btnMeter;

    @FXML
    private Label labelProyectosBloqueados;

    @FXML
    private Label labelProyectosPendientes;

    @FXML
    private Label labelProyectosProceso;

    @FXML
    private MFXScrollPane scroll;
    private Data data;
    private ArrayList<Proyecto> proyectosRecorrer;

    /**
     * Método que se encarga de abrir la ventana de creacion de un nuevo proyecto
     * @param event
     */
    @FXML
    void meter(MouseEvent event) {
        /*Abrir interfaz de crear proyecto*/
    }

    /**
     * Método que se encarga de inicializar la vista de todos los proyectos, recorre la lista de
     * proyectos y los muestra en el scroll, también indica la cantidad de proyectos en proceso, pendientes
     * o bloqueados.
     */
    public void inicializar(){
        int enProceso = 0;
        int pendientes = 0;
        int bloqueados = 0;

        for (Proyecto proyecto : this.proyectosRecorrer){
            if(proyecto.getEstado().equalsIgnoreCase("En proceso")){
                enProceso+=1;
            }else if(proyecto.getEstado().equalsIgnoreCase("Pendiente")){
                pendientes+=1;
            }else if(proyecto.getEstado().equalsIgnoreCase("Bloqueado")){
                bloqueados+=1;
            }

            /* Cargar las vistas de cada proceso y meterlos de 3 en 3 en el scroll */

        }
        this.labelProyectosBloqueados.setText(String.valueOf(bloqueados));
        this.labelProyectosPendientes.setText(String.valueOf(pendientes));
        this.labelProyectosProceso.setText(String.valueOf(enProceso));


    }

    /**
     * Método que se encarga de recibir data y la lista de proyectos a cargar en la vista
     * @param data informacion
     * @param proyectos lista de proyectos a recorrer
     */
    public void recibirData(Data data,ArrayList<Proyecto> proyectos){
        this.data = data;
        this.proyectosRecorrer = proyectos;
        this.inicializar();
    }

}

