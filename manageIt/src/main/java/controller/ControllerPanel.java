package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modelo.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerPanel {

    @FXML
    private MFXButton btnVerTodosProyectos;
    @FXML
    private VBox contenedorCadaProyecto;

    @FXML
    private AnchorPane contenedorProyectos;
    @FXML
    private Label labelTareasTerminados;

    @FXML
    private Label labelTareasTotales;

    @FXML
    private Label labelMostrarNombre;
    @FXML
    private VBox contenedorCadaTarea;
    @FXML
    private AnchorPane contenedorTareas;

    @FXML
    private Label labelProyectos;

    @FXML
    private Label labelProyectosTerminados;

    @FXML
    private Label labelProyectosTotales;
    private ArrayList<Proyecto> proyectosAsignados = new ArrayList<>();
    private ArrayList<Tarea> tareasAsignadas = new ArrayList<>();
    private Data data;

    /**
     * Método que se encarga de cargar la vista que muestra todos los proyectos para verlos
     * @param event
     */
    @FXML
    void verProyectos(MouseEvent event) throws IOException {
        this.data.getListaControladores().getControllerMenuLateral().mostrarProyectos(null);
    }

    @FXML
    void verTareas(MouseEvent event) throws IOException {
        this.data.getListaControladores().getControllerMenuLateral().mostrarTareas(null);
    }

    /**
     * Método que se encarga de cargar los 3 primeros proyectos que tengan relación con el
     * usuario en la interfaz
     */
    public void cargarProyectos(){
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

    /**
     * Método que se encarga de cargar la vista detallada de un proyecto
     * @param event
     */
    public void cargarProyecto(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            Integer posicion = Integer.parseInt(btn.getId());
            Proyecto proyecto = this.proyectosAsignados.get(posicion);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/vistaCadaProyecto.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();
            ControllerVistaCadaProyecto controllerVistaCadaProyecto = fxmlLoader.getController();
            controllerVistaCadaProyecto.recibirData(this.data,proyecto,this.proyectosAsignados);
            this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
            this.data.getListaControladores().getControllerMenuLateral().reiniciarHbox();
            this.data.getListaControladores().getControllerMenuLateral().hboxProyectos.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
            this.data.getListaControladores().getControllerMenuLateral().imagenProyectos.getStyleClass().add("proyectosPresionado");

        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }
    /**
     * Método que se encarga de cargar la vista detallada de un proyecto
     * @param event
     */
    public void cargarTarea(MouseEvent event){
        try {
            Button btn = (Button) event.getSource();
            Integer posicion = Integer.parseInt(btn.getId());
            Tarea tarea = this.tareasAsignadas.get(posicion);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/vistaCadaTarea.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();
            ControllerVistaCadaTarea controllerVistaCadaTarea = fxmlLoader.getController();
            controllerVistaCadaTarea.recibirData(this.data,tarea,this.tareasAsignadas);
            this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
            this.data.getListaControladores().getControllerMenuLateral().reiniciarHbox();
            this.data.getListaControladores().getControllerMenuLateral().hboxTareas.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
            this.data.getListaControladores().getControllerMenuLateral().imagenTareas.getStyleClass().add("tareasPresionado");


        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }

    /**
     * Método que se encarga de cargar las tareas
     */
    public void cargarTareas(){

        if (this.tareasAsignadas.isEmpty()){
            return;
        }
        for (int i = 0 ; i < this.tareasAsignadas.size() && i<3;i++){

            HBox hBox = new HBox();
            hBox.getStyleClass().add("hboxTarjetaProyecto");

            VBox.setMargin(hBox, new Insets(0, 10, 0, 10));

            Label titulo = new Label(tareasAsignadas.get(i).getNombre());
            titulo.getStyleClass().add("nombreTarjetaProyecto");
            titulo.setMinWidth(120);
            titulo.setPrefWidth(120);
            titulo.setMinWidth(120);
            titulo.setPrefHeight(40);
            titulo.setMinHeight(40);
            titulo.setMaxHeight(40);


            Label estado = new Label(tareasAsignadas.get(i).getEstado());
            estado.getStyleClass().add("estadoTarjetaProyecto");
            estado.setMinWidth(100);
            estado.setPrefWidth(100);
            estado.setMinWidth(100);
            estado.setPrefHeight(40);
            estado.setMinHeight(40);
            estado.setMaxHeight(40);


            MFXButton btnver = new MFXButton();
            btnver.getStyleClass().add("btnTarjetaProyecto");
            btnver.setOnMouseClicked(this::cargarTarea);
            btnver.setId(String.valueOf(i));

            HBox.setMargin(btnver,new Insets(0,0,0,40));
            hBox.getChildren().addAll(titulo,estado,btnver);

            this.contenedorCadaTarea.getChildren().add(hBox);

        }
    }

    /**
     * Método que se encarga de recibir la informacion y mostrar el nombre del usuario
     * @param data
     */
    public void recibirData(Data data,ArrayList<Proyecto> proyectos,ArrayList<Tarea> tareasAsignadas){
        this.data = data;
        this.proyectosAsignados = proyectos;
        this.tareasAsignadas = tareasAsignadas;
        this.cargarProyectos();
        this.cargarTareas();
        this.labelMostrarNombre.setText(this.labelMostrarNombre.getText()+this.data.getCurrentUser().getNombre());

        this.labelProyectosTotales.setText(String.valueOf(this.proyectosAsignados.size()));
        this.labelTareasTotales.setText(String.valueOf(this.tareasAsignadas.size()));




    }

}

