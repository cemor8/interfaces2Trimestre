package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modelo.CambiarIdioma;
import modelo.Data;
import modelo.Proyecto;
import modelo.Usuario;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerCadaProyecto {

    @FXML
    private Label descripcion;

    @FXML
    private Label fechaCreacion;

    @FXML
    private Label fechaEntrega;

    @FXML
    private ImageView fotoCreacion;

    @FXML
    private ImageView fotoEntrega;

    @FXML
    private ImageView imagen;

    @FXML
    private ImageView imagenBasura;

    @FXML
    private ImageView imagenVer;
    @FXML
    private ImageView imagenCreador;

    @FXML
    private Label nombre;
    private Data data;
    private Proyecto proyecto;

    /**
     * Método que se encarga de eliminar un proyecto de la lista, luego vuelve a cargar la vista para que se
     * actualice el proyecto borrado
     * @param event
     * @throws IOException
     */
    @FXML
    void eliminarProyecto(MouseEvent event) throws IOException {
        this.data.getProyectos().remove(this.proyecto);
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        for (Proyecto proyecto : this.data.getProyectos()) {
            for (Usuario usuario : proyecto.getPersonasAsignadas()) {
                if (usuario.getCorreo().equals(this.data.getCurrentUser().getCorreo())) {
                    proyectos.add(proyecto);
                    break;
                }
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/proyectos.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerProyectos controllerProyectos = fxmlLoader.getController();
        controllerProyectos.recibirData(this.data,proyectos);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    @FXML
    void verProyecto(MouseEvent event) {

    }

    /**
     * Método que se encarga de recibir la información
     * @param data información
     * @param proyecto  proyecto a crear
     */
    public void recibirData(Data data, Proyecto proyecto){
        this.data = data;
        this.proyecto = proyecto;
        this.inicializar();
    }

    /**
     * Método que se encarga de cargar todos los datos del proyecto en la vista
     */
    public void inicializar(){

        this.nombre.setText(this.proyecto.getNombre());
        this.descripcion.setText(this.proyecto.getDescripcion());
        SimpleDateFormat fechaCreacion = new SimpleDateFormat("dd-MM-yyyy");
        String mostrarCreacion = fechaCreacion.format(this.proyecto.getFechaCreacion());
        String mostrarEntrega = fechaCreacion.format(this.proyecto.getFechaEntrega());
        this.fechaCreacion.setText(mostrarCreacion);
        this.fechaEntrega.setText(mostrarEntrega);
        this.imagen.setImage(new Image("file:"+this.proyecto.getRutaImagen()));
        Rectangle clip = new Rectangle(
                this.imagen.getFitWidth(), this.imagen.getFitHeight()
        );
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        this.imagen.setClip(clip);

        this.imagenBasura.setFitWidth(30);
        this.imagenBasura.setFitHeight(30);

        this.imagenVer.setFitWidth(30);
        this.imagenVer.setFitHeight(30);

        this.fotoEntrega.setFitWidth(20);
        this.fotoEntrega.setFitHeight(20);

        this.fotoCreacion.setFitWidth(20);
        this.fotoCreacion.setFitHeight(20);

        this.imagenCreador.setImage(new Image("file:"+this.proyecto.getJefeProyecto().getRutaImagen()));
        this.imagenCreador.setFitWidth(70);
        this.imagenCreador.setFitHeight(70);
        this.imagenCreador.setPreserveRatio(true);

        Circle circulo = new Circle(this.imagenCreador.getFitWidth() / 2, this.imagenCreador.getFitHeight() / 2, this.imagenCreador.getFitWidth() / 2);
        this.imagenCreador.setClip(circulo);


    }

}

