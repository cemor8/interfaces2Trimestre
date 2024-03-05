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
import modelo.Proyecto;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ControllerVistaCadaProyecto {

    @FXML
    private MFXButton btnAtras;

    @FXML
    private Label labelDescripcion;

    @FXML
    private Label labelNombreJefe;

    @FXML
    private Label labelNombreProyecto;
    @FXML
    private Label cliente;
    @FXML
    private ImageView imagenJefe;

    @FXML
    private ImageView imagenProyecto;
    @FXML
    private Label creacion;

    @FXML
    private Label entrega;

    @FXML
    private ComboBox<String> opcionesEstado;
    @FXML
    private MFXButton btnGuardar;
    private Data data;
    private Proyecto proyecto;
    private ArrayList<Proyecto> listaDeProyecto;

    @FXML
    void btnGuardarEstado(MouseEvent event) {
        /* Cambiar el estado del proyecto */
    }

    @FXML
    void verAsignados(MouseEvent event) {
        /* Enviar vista de contactos con lista del proyecto */
    }

    @FXML
    void verNotas(MouseEvent event) {
        /* Enviar vista notas con lista del proyecto */
    }

    @FXML
    void verTareas(MouseEvent event) {
        /* Enviar vista de tareas con la lista del proyecto */
    }

    @FXML
    void volver(MouseEvent event) throws IOException {
        /* Volver a vista de todos los proyectos */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/proyectos.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerProyectos controllerProyectos = fxmlLoader.getController();
        controllerProyectos.recibirData(this.data,this.listaDeProyecto);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }
    public void inicializar(){
        this.btnAtras.setText("");
        this.labelDescripcion.setText(this.proyecto.getDescripcion());
        this.labelNombreJefe.setText(this.proyecto.getJefeProyecto().getNombre()+" "+this.proyecto.getJefeProyecto().getApellidos());
        this.labelNombreProyecto.setText(this.proyecto.getNombre());
        this.imagenJefe.setImage(new Image("file:"+this.proyecto.getJefeProyecto().getRutaImagen()));
        this.imagenProyecto.setImage(new Image("file:"+this.proyecto.getRutaImagen()));

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
        this.opcionesEstado.setValue(this.proyecto.getEstado());
        if(this.proyecto.getEstado().equalsIgnoreCase("Completado") || !this.data.getCurrentUser().getCorreo().equalsIgnoreCase(this.proyecto.getJefeProyecto().getCorreo())){
            this.btnGuardar.setDisable(true);
        }
        this.cliente.setText(this.proyecto.getCliente());
        if (this.proyecto.getCliente().equalsIgnoreCase("netflix")){
            this.cliente.getStyleClass().clear();
            this.cliente.getStyleClass().add("pendiente");
        }else{
            this.cliente.getStyleClass().clear();
            this.cliente.getStyleClass().add("pendiente");
        }
        SimpleDateFormat fechaCreacion = new SimpleDateFormat("dd-MM-yyyy");
        String mostrarCreacion = fechaCreacion.format(this.proyecto.getFechaCreacion());
        this.creacion.setText(mostrarCreacion);

        SimpleDateFormat fechaEntrega = new SimpleDateFormat("dd-MM-yyyy");
        String mostrarEntrega = fechaEntrega.format(this.proyecto.getFechaEntrega());
        this.entrega.setText(mostrarEntrega);


        if (!this.proyecto.getJefeProyecto().getCorreo().equalsIgnoreCase(this.data.getCurrentUser().getCorreo())){
            this.btnGuardar.setDisable(true);
        }
    }
    public void recibirData(Data data,Proyecto proyecto, ArrayList<Proyecto> listaDeProyecto){
        this.data = data;
        this.proyecto = proyecto;
        this.listaDeProyecto = listaDeProyecto;
        this.inicializar();
    }

}

