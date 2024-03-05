package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.PointLight;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modelo.CambiarIdioma;
import modelo.Data;
import modelo.Proyecto;
import modelo.Tarea;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

public class ControllerVistaCadaTarea {

    @FXML
    private MFXButton btnAtras;
    @FXML
    private Label cliente;

    @FXML
    private MFXButton btnGuardar;
    @FXML
    private ImageView imgGuardarDesc;
    @FXML
    private Label creacion;

    @FXML
    private Label entrega;

    @FXML
    private ImageView imagenJefe;

    @FXML
    private ImageView imagenProyecto;

    @FXML
    private TextArea labelDescripcion;

    @FXML
    private Label labelNombreJefe;

    @FXML
    private Label labelNombreTarea;

    @FXML
    private ComboBox<String> opcionesEstado;
    private Data data;
    private Tarea tarea;
    private ArrayList<Tarea> tareas;

    @FXML
    void btnGuardarEstado(MouseEvent event) {
        this.tarea.setEstado(this.opcionesEstado.getSelectionModel().getSelectedItem());
        if (this.tarea.getEstado().equalsIgnoreCase("Completado")){
            this.btnGuardar.setDisable(true);
            this.imgGuardarDesc.setDisable(true);
            this.labelDescripcion.setEditable(false);
        }
    }

    @FXML
    void verAsignados(MouseEvent event) throws IOException {
        Proyecto proyectoEncontrado = null;
        for (Proyecto proyecto : this.data.getProyectos()){
           if (proyecto.getTareas().contains(this.tarea)){
               proyectoEncontrado = proyecto;
               break;
           }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/contactos.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerContactos controllerContactos = fxmlLoader.getController();
        assert proyectoEncontrado != null;
        controllerContactos.recibirData(this.data,this.tarea.getPersonasAsignadas(),false,proyectoEncontrado.getPersonasAsignadas(),true);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    @FXML
    void verNotas(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/notas.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerNotas controllerNotas = fxmlLoader.getController();
        controllerNotas.recibirData(this.data,this.tarea.getNotas());
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    /**
     * Método que se encarga de volver a la vista anterior
     * @param event
     * @throws IOException
     */
    @FXML
    void volver(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/tareas.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerTareas controllerTareas = fxmlLoader.getController();
        controllerTareas.recibirData(this.data,this.tareas);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    /**
     * Método que se encarga de recibir la informacion
     * @param data  clase con informacion
     * @param tarea tarea seleccionada
     * @param tareas    lista de tareas
     */
    public void recibirData(Data data,Tarea tarea, ArrayList<Tarea> tareas){
        this.tarea = tarea;
        this.data = data;
        this.tareas = tareas;
        this.inicializar();
    }

    /**
     * Método que se encarga de inicializar la tarea
     */
    public void inicializar(){
        this.btnAtras.setText("");
        this.labelDescripcion.setText(this.tarea.getDescripcion());
        this.labelNombreJefe.setText(this.tarea.getCreador().getNombre()+" "+this.tarea.getCreador().getApellidos());
        this.labelNombreTarea.setText(this.tarea.getNombre());
        this.imagenJefe.setImage(new Image("file:"+this.tarea.getCreador().getRutaImagen()));
        this.imagenProyecto.setImage(new Image("file:"+this.tarea.getRutaimagen()));

        this.imagenJefe.setFitWidth(55);
        this.imagenJefe.setFitHeight(55);
        this.imagenJefe.setPreserveRatio(true);
        Circle clip = new Circle(this.imagenJefe.getFitWidth() / 2, this.imagenJefe.getFitHeight() / 2, this.imagenJefe.getFitWidth() / 2);
        this.imagenJefe.setClip(clip);

        Rectangle rect = new Rectangle(
                this.imagenProyecto.getFitWidth(), this.imagenProyecto.getFitHeight()
        );
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        this.imagenProyecto.setClip(rect);

        this.opcionesEstado.getItems().addAll("En Proceso","Completado","Bloqueado");
        this.opcionesEstado.setValue(this.tarea.getEstado());
        if(this.tarea.getEstado().equalsIgnoreCase("Completado") || !this.data.getCurrentUser().getCorreo().equalsIgnoreCase(this.tarea.getCreador().getCorreo())){
            this.btnGuardar.setDisable(true);
            this.imgGuardarDesc.setDisable(true);
            this.labelDescripcion.setEditable(false);
        }

        this.cliente.setText(this.tarea.getCampo());
        if (this.tarea.getCampo().equalsIgnoreCase("BBDD")){
            this.cliente.getStyleClass().clear();
            this.cliente.getStyleClass().add("pendiente");
        }else{
            this.cliente.getStyleClass().clear();
            this.cliente.getStyleClass().add("pendiente");
        }

        SimpleDateFormat fechaCreacion = new SimpleDateFormat("dd-MM-yyyy");
        String mostrarCreacion = fechaCreacion.format(this.tarea.getFechaCreacion());
        this.creacion.setText(mostrarCreacion);

        SimpleDateFormat fechaEntrega = new SimpleDateFormat("dd-MM-yyyy");
        String mostrarEntrega = fechaEntrega.format(this.tarea.getFechaEntrega());
        this.entrega.setText(mostrarEntrega);


        if (!this.tarea.getCreador().getCorreo().equalsIgnoreCase(this.data.getCurrentUser().getCorreo())){
            this.btnGuardar.setDisable(true);
            this.imgGuardarDesc.setDisable(true);
            this.labelDescripcion.setEditable(false);
        }


    }

}

