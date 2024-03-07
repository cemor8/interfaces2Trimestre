package controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.converter.DefaultStringConverter;
import modelo.Data;
import modelo.Proyecto;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControllerVistaCrearProyecto {

    @FXML
    private ImageView imagenProyecto;

    @FXML
    private ImageView imgVideo;
    @FXML
    private MediaView media;
    @FXML
    private DatePicker datePicker;


    @FXML
    private ImageView imagenProyecto1;

    @FXML
    private TextArea introducirDescripcion;

    @FXML
    private MFXTextField introducirFecha;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private Label labelTextoCrearProyecto;

    @FXML
    private ComboBox<String> seleccionarCliente;
    private Data data;
    private String imagenSeleccionada;
    private String videoSeleccionado = "";

    /**
     * Método que se encarga de crear el proyecto
     * @param event
     */
    @FXML
    void crear(MouseEvent event) {
        if (this.imagenSeleccionada == null || this.introducirDescripcion.getText() == null || this.introducirNombre.getText() == null
        || this.seleccionarCliente.getValue() == null || this.datePicker.getValue() == null){
            return;
        }
        LocalDate today = LocalDate.now();
        Date dateHoy = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateCrear = Date.from(this.datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.data.getProyectos().add(new Proyecto(this.introducirNombre.getText(),
                this.seleccionarCliente.getValue(),this.imagenSeleccionada,"En proceso",this.introducirDescripcion.getText(),dateHoy,dateCrear,this.data.getCurrentUser(),
                new ArrayList<>(),new ArrayList<>(),new ArrayList<>(List.of(this.data.getCurrentUser())),this.videoSeleccionado));
        try {
            this.data.getListaControladores().getControllerMenuLateral().mostrarProyectos(null);
        }catch (IOException err){
            System.out.println(err.getMessage());
        }




    }

    /**
     * Método que se encarga de elegir la imagen para el proyecto
     * @param event
     */
    @FXML
    void imagen(MouseEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.png","*.jpg","*.jpeg"));
        File selectedFile = filechooser.showOpenDialog(null);

        if(selectedFile != null){
            String imagePath = selectedFile.getAbsolutePath();
            this.imagenSeleccionada = imagePath;

            this.imagenProyecto.setImage(new Image("file:"+imagenSeleccionada));

        }
    }

    /**
     * Método que selecciona el vídeo para el proyecto
     * @param event
     */
    @FXML
    void video(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a Video File", "*.mp4", "*.avi");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            this.videoSeleccionado = file.getAbsolutePath();
            Media media2 = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media2);
            this.media.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();

        }
    }

    /**
     * Método que recibe informacion
     * @param data
     */
    public void recibirData(Data data){
        this.data = data;
        this.seleccionarCliente.getItems().addAll("Netflix","Uber", "Discord", "Movistar");
        this.datePicker.setShowWeekNumbers(false);
        this.datePicker.getValue();


    }

}


