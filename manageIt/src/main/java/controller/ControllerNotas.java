package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modelo.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ControllerNotas {

    @FXML
    private MFXButton btnGuardarCambios;

    @FXML
    private MFXButton btnMeter;

    @FXML
    private AnchorPane contenedorNota;

    @FXML
    private Label creador;

    @FXML
    private TextArea descripcion;

    @FXML
    private Label fecha;
    @FXML
    private ImageView img;

    @FXML
    private ImageView imgCreador;

    @FXML
    private MFXScrollPane scroll;

    @FXML
    private Label tituloNota;
    private Data data;
    @FXML
    private Label creadorTexto;
    private ArrayList<Nota> notasRecorrer;
    private Nota notaSeleccionada;
    @FXML
    private ImageView calendario;

    @FXML
    void meter(MouseEvent event) {
        /* Crear nota vacia y ponerla al inicio de la lista, si globales es true meterla en notas del usuario, si no en las del proyecto o la tarea */
    }
    @FXML
    void guardarNota(MouseEvent event) {
        LocalDate hoy = LocalDate.now();
        this.notasRecorrer.add(new Nota("","","",Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant())
        ));
    }
    public void inicializar() throws IOException {
        /* Recorrer notas y meter en scrolleable */
        this.btnMeter.setText("");
        this.creadorTexto.setVisible(false);
        this.descripcion.setVisible(false);
        this.btnGuardarCambios.setVisible(false);
        this.calendario.setVisible(false);
        VBox vBox = new VBox();
        for (Nota nota : notasRecorrer){
            AnchorPane anchorPane = new AnchorPane();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/cadaNota.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();
            ControllerCadaNota controllerCadaNota = fxmlLoader.getController();
            controllerCadaNota.recibirData(this.data,nota,this.notasRecorrer);
            anchorPane.getChildren().setAll(root);
            vBox.getChildren().add(anchorPane);
            VBox.setMargin(anchorPane, new Insets(10, 0, 20, 10));
        }
        this.scroll.setContent(vBox);
        if (!this.notasRecorrer.isEmpty()){
            this.cargarNota(this.notasRecorrer.get(0));
        }


    }

    public void recibirData(Data data, ArrayList<Nota> notas) throws IOException {
        this.notasRecorrer = notas;
        this.data = data;
        this.data.getListaControladores().setControllerNotas(this);
        this.inicializar();
    }
    public void cargarNota(Nota nota){
        this.notaSeleccionada = nota;
        this.creadorTexto.setVisible(true);
        this.descripcion.setVisible(true);
        this.btnGuardarCambios.setVisible(true);
        this.calendario.setVisible(true);
        this.tituloNota.setText(this.notaSeleccionada.getTitulo());

        this.descripcion.setText(this.notaSeleccionada.getDescripcion());

        this.imgCreador.setImage(new Image("file:"+this.notaSeleccionada.getUsuario().getRutaImagen()));
        this.imgCreador.setFitWidth(60);
        this.imgCreador.setFitHeight(60);
        this.imgCreador.setPreserveRatio(true);

        Circle circulo = new Circle(this.imgCreador.getFitWidth() / 2, this.imgCreador.getFitHeight() / 2, this.imgCreador.getFitWidth() / 2);
        this.imgCreador.setClip(circulo);
        Rectangle clip = new Rectangle(
                this.img.getFitWidth(), this.img.getFitHeight()
        );
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        if(!this.notaSeleccionada.getRutaImagen().isEmpty()){
            this.img.setImage(new Image("file:"+this.notaSeleccionada.getRutaImagen()));
        }

        this.img.setClip(clip);

        SimpleDateFormat fechaCreacion = new SimpleDateFormat("dd-MM-yyyy");
        String mostrarCreacion = fechaCreacion.format(this.notaSeleccionada.getFechaCreacion());
        this.fecha.setText(mostrarCreacion);
        this.creador.setText(this.notaSeleccionada.getUsuario().getNombre()+" "+this.notaSeleccionada.getUsuario().getApellidos());



    }


}

