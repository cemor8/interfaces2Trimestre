package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.CambiarIdioma;
import model.Data;
import model.Libro;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerMeterLibro {

    @FXML
    private MFXButton btnEnviar;

    @FXML
    private MFXTextField introducirAutor;

    @FXML
    private MFXTextField introducirAño;

    @FXML
    private MFXTextField introducirIsbn;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelAño;

    @FXML
    private Label labelIsbn;
    @FXML
    private Label infoAutor;
    @FXML
    private ImageView meterImagen;

    @FXML
    private Label infoAño;

    @FXML
    private Label infoISBN;

    @FXML
    private Label infoNombre;
    private String imagenSeleccionada;

    @FXML
    private Label labelNombre;
    @FXML
    private Label infoImagen;
    private Data data;
    Map<String, String> columnasExpresiones = new HashMap<String, String>() {
        {
            put("ISBN", "^(978|979)-[0-9]{2}-[0-9]{5}-[0-9]{2}-[0-9]{1}$");
            put("Nombre", "^[\\w\\d\\s,.'\":;?!-]+$");
            put("Autor", "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ' -]+$");
            put("Año", "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$");
        }

    };
    /**
     * Método que se encarga de añadir un nuevo libro, comprueba los valores con expresiones
     * regulares, si hay algun fallo, el libro no se crea e indica el contenido erroneo
     * */
    @FXML
    void enviar(MouseEvent event) {
        boolean error = false;
        this.infoImagen.setText("");
        this.infoAutor.setText("");
        this.infoISBN.setText("");
        this.infoNombre.setText("");
        this.infoAño.setText("");
        if(!validarContenido(this.columnasExpresiones.get("ISBN"),this.introducirIsbn.getText())){
            error = true;
            this.infoISBN.setText(CambiarIdioma.getInstance().getBundle().getString("aviso"));
            this.introducirIsbn.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("Año"),this.introducirAño.getText())){
            error = true;
            this.infoAño.setText(CambiarIdioma.getInstance().getBundle().getString("aviso"));
            this.introducirAño.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("Nombre"),this.introducirNombre.getText())){
            error = true;
            this.infoNombre.setText(CambiarIdioma.getInstance().getBundle().getString("aviso"));
            this.introducirNombre.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("Autor"),this.introducirAutor.getText())){
            error = true;
            this.infoAutor.setText(CambiarIdioma.getInstance().getBundle().getString("aviso"));
            this.introducirAutor.setText("");
        }
        if(this.imagenSeleccionada == null){
            error = true;
            this.infoImagen.setText(CambiarIdioma.getInstance().getBundle().getString("aviso"));
        }
        if (error){
            return;
        }
        Libro libro = new Libro(this.introducirNombre.getText(),this.introducirAutor.getText(),this.introducirIsbn.getText(),this.introducirAño.getText(),this.imagenSeleccionada);
        this.data.getLibros().add(libro);
        this.meterImagen.setImage(new Image(getClass().getResourceAsStream("/images/imagenPlaceholder/preview.png")));
        this.introducirAutor.setText("");
        this.introducirIsbn.setText("");
        this.introducirNombre.setText("");
        this.introducirAño.setText("");
    }
    /**
     * Método que se encarga de recibir el modelo para que el controlador tenga acceso a el
     * */
    public void recibirData(Data data){
        this.data = data;
    }
    /**
     * Método que devuelve true si se cumple una expresion regular en una string
     *
     * @param patron       expresion regular
     * @param texto_buscar texto donde buscar el patron
     */
    public boolean validarContenido(String patron, String texto_buscar) {
        Pattern patronValidar = Pattern.compile(patron);
        Matcher matcher = patronValidar.matcher(texto_buscar);
        return matcher.matches();
    }
    @FXML
    void meterImagen(MouseEvent event) {
        FileChooser filechooser = new FileChooser();

        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("image files", "*.png","*.jpg","*.jpeg"));
        File selectedFile = filechooser.showOpenDialog(null);


        if(selectedFile != null){
            String imagePath = selectedFile.getAbsolutePath();
            this.imagenSeleccionada = imagePath;
            System.out.println(imagenSeleccionada);
            this.meterImagen.setImage(new Image("file:"+imagenSeleccionada));
        }else {
            if(this.imagenSeleccionada != null){
                this.meterImagen.setImage(new Image("file:"+imagenSeleccionada));
            }else {
                this.meterImagen.setImage(new Image(getClass().getResourceAsStream("/images/imagenPlaceholder/preview.png")));
            }

        }
    }

}

