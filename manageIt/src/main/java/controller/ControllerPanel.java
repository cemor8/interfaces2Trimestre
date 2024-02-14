package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modelo.Data;
import modelo.Proyecto;
import modelo.Tarea;
import modelo.Usuario;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ControllerPanel {

    @FXML
    private MFXButton btnVerTodosProyectos;
    @FXML
    private VBox contenedorCadaProyecto;

    @FXML
    private AnchorPane contenedorProyectos;

    @FXML
    private Label labelMostrarNombre;

    @FXML
    private Label labelProyectos;

    @FXML
    private Label labelProyectosTerminados;

    @FXML
    private Label labelProyectosTotales;
    private ArrayList<Proyecto> proyectosAsignados = new ArrayList<>();
    private ArrayList<Tarea> tareasAsignadas = new ArrayList<>();
    private Data data;

    @FXML
    void verProyectos(MouseEvent event) {

    }

    public void cargarProyectos(){
        this.proyectosAsignados = new ArrayList<>();
        for (Proyecto proyecto : this.data.getProyectos()) {
            for (Usuario usuario : proyecto.getPersonasAsignadas()) {
                if (usuario.getCorreo().equals(this.data.getCurrentUser().getCorreo())) {
                    proyectosAsignados.add(proyecto);
                    break;
                }
            }
        }
        System.out.println(proyectosAsignados);
        if (this.proyectosAsignados.isEmpty()){
            return;
        }
        for (int i = 0 ; i < this.proyectosAsignados.size() && i<3;i++){

            HBox hBox = new HBox();
            hBox.getStyleClass().add("hboxTarjetaProyecto");

            VBox.setMargin(hBox, new Insets(0, 10, 0, 10));

            Label titulo = new Label(proyectosAsignados.get(i).getNombre());
            titulo.getStyleClass().add("nombreTarjetaProyecto");
            titulo.setMinWidth(120);
            titulo.setPrefWidth(120);
            titulo.setMinWidth(120);
            titulo.setPrefHeight(40);
            titulo.setMinHeight(40);
            titulo.setMaxHeight(40);


            Label estado = new Label(proyectosAsignados.get(i).getEstado());
            estado.getStyleClass().add("estadoTarjetaProyecto");
            estado.setMinWidth(100);
            estado.setPrefWidth(100);
            estado.setMinWidth(100);
            estado.setPrefHeight(40);
            estado.setMinHeight(40);
            estado.setMaxHeight(40);


            MFXButton btnver = new MFXButton();
            btnver.getStyleClass().add("btnTarjetaProyecto");
            btnver.setOnMouseClicked(this::cargarProyecto);
            btnver.setId(String.valueOf(i));

            HBox.setMargin(btnver,new Insets(0,0,0,40));
            hBox.getChildren().addAll(titulo,estado,btnver);

            this.contenedorCadaProyecto.getChildren().add(hBox);


        }
    }
    public void cargarProyecto(MouseEvent event){

    }
    public void cargarTareas(){
        this.tareasAsignadas = new ArrayList<>();
        for (Tarea tarea : this.data.getTareas()) {
            for (Usuario usuario : tarea.getPersonasAsignadas()) {
                if (usuario.getCorreo().equals(this.data.getCurrentUser().getCorreo())) {
                    tareasAsignadas.add(tarea);
                    break;
                }
            }
        }
    }
    public void recibirData(Data data){
        this.data = data;
        this.cargarProyectos();
        this.labelMostrarNombre.setText(this.labelMostrarNombre.getText()+this.data.getCurrentUser().getNombre());
    }

}

