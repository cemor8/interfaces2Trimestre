package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Data;
import model.Libro;

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
    private Label infoAño;

    @FXML
    private Label infoISBN;

    @FXML
    private Label infoNombre;

    @FXML
    private Label labelNombre;
    private Data data;
    Map<String, String> columnasExpresiones = new HashMap<String, String>() {
        {
            put("ISBN", "^(978|979)-[0-9]{2}-[0-9]{5}-[0-9]{2}-[0-9]{1}$");
            put("Nombre", "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+ [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)?$");
            put("Autor", "^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ' -]+$");
            put("Año", "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$");
        }

    };

    @FXML
    void enviar(MouseEvent event) {
        boolean error = false;
        if(!validarContenido(this.columnasExpresiones.get("ISBN"),this.introducirIsbn.getText())){
            error = true;
            this.infoISBN.setText("Contenido inválido");
            this.introducirIsbn.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("Año"),this.introducirAño.getText())){
            error = true;
            this.infoAño.setText("Contenido inválido");
            this.introducirAño.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("Nombre"),this.introducirNombre.getText())){
            error = true;
            this.infoNombre.setText("Contenido inválido");
            this.introducirNombre.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("Autor"),this.introducirAutor.getText())){
            error = true;
            this.infoAutor.setText("Contenido inválido");
            this.introducirAutor.setText("");
        }
        if (error){
            return;
        }
        Libro libro = new Libro(this.introducirNombre.getText(),this.introducirAutor.getText(),this.introducirIsbn.getText(),this.introducirAño.getText());
        this.data.getLibros().add(libro);
        this.introducirAutor.setText("");
        this.introducirIsbn.setText("");
        this.introducirNombre.setText("");
        this.introducirAño.setText("");
    }
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

}

