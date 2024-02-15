package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import modelo.CambiarIdioma;
import modelo.Data;
import modelo.Proyecto;

import java.io.IOException;
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
    void meter(MouseEvent event) throws IOException {
        /*Abrir interfaz de crear proyecto*/

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/vistaCrearProyecto.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerVistaCrearProyecto controllerVistaCrearProyecto = fxmlLoader.getController();
        controllerVistaCrearProyecto.recibirData(this.data);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    /**
     * Método que se encarga de inicializar la vista de todos los proyectos, recorre la lista de
     * proyectos y los muestra en el scroll, también indica la cantidad de proyectos en proceso, pendientes
     * o bloqueados.
     */
    public void inicializar() throws IOException {
        int enProceso = 0;
        int pendientes = 0;
        int bloqueados = 0;
        int i = 0;
        HBox hBox = new HBox();
        for (Proyecto proyecto : this.proyectosRecorrer){
            if(proyecto.getEstado().equalsIgnoreCase("En proceso")){
                enProceso+=1;
            }else if(proyecto.getEstado().equalsIgnoreCase("Pendiente")){
                pendientes+=1;
            }else if(proyecto.getEstado().equalsIgnoreCase("Bloqueado")){
                bloqueados+=1;
            }

            /* Cargar las vistas de cada proceso y meterlos de 3 en 3 en el scroll */
            if(i%3 == 0 && i > 0){
                break;
            }
            AnchorPane anchorPane = new AnchorPane();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/cadaProyecto.fxml"),CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();
            ControllerCadaProyecto controllerCadaProyecto = fxmlLoader.getController();
            controllerCadaProyecto.recibirData(this.data,proyecto);
            anchorPane.getChildren().setAll(root);
            HBox.setMargin(anchorPane,new Insets(0,0,0,20));
            hBox.getChildren().add(anchorPane);
            this.scroll.setContent(hBox);
            i++;

        }
        this.labelProyectosBloqueados.setText(String.valueOf(bloqueados));
        this.labelProyectosPendientes.setText(String.valueOf(pendientes));
        this.labelProyectosProceso.setText(String.valueOf(enProceso));
        this.btnMeter.setText("");

    }

    /**
     * Método que se encarga de recibir data y la lista de proyectos a cargar en la vista
     * @param data informacion
     * @param proyectos lista de proyectos a recorrer
     */
    public void recibirData(Data data,ArrayList<Proyecto> proyectos) throws IOException {
        this.data = data;
        this.proyectosRecorrer = proyectos;
        this.data.getListaControladores().setControllerProyectos(this);
        this.inicializar();
    }

}

