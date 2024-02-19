package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modelo.CambiarIdioma;
import modelo.Data;
import modelo.Tarea;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerVistaCadaTarea {

    @FXML
    private MFXButton btnAtras;

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private ImageView imagenJefe;

    @FXML
    private ImageView imagenProyecto;

    @FXML
    private Label labelDescripcion;

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

    }

    @FXML
    void verAsignados(MouseEvent event) {

    }

    @FXML
    void verNotas(MouseEvent event) {

    }

    @FXML
    void volver(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/tareas.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerTareas controllerTareas = fxmlLoader.getController();
        controllerTareas.recibirData(this.data,this.tareas);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    public void recibirData(Data data,Tarea tarea, ArrayList<Tarea> tareas){
        this.tarea = tarea;
        this.data = data;
        this.tareas = tareas;
        this.inicializar();
    }
    public void inicializar(){
        this.btnAtras.setText("");
        this.labelDescripcion.setText(this.tarea.getDescripcion());
        this.labelNombreJefe.setText(this.tarea.getCreador().getNombre()+" "+this.tarea.getCreador().getApellidos());
        this.labelNombreTarea.setText(this.tarea.getNombre());
        this.imagenJefe.setImage(new Image("file:"+this.tarea.getCreador().getRutaImagen()));
        this.imagenProyecto.setImage(new Image("file:"+this.tarea.getCreador()));

        this.imagenJefe.setFitWidth(40);
        this.imagenJefe.setFitHeight(40);
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
        }
    }

}

