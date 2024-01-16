package controller;

import com.example.biblioteca.MainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.CambiarIdioma;
import model.Data;

import java.io.IOException;
import java.util.ResourceBundle;

public class ControllerVistaLibro {

    @FXML
    private MFXButton btnVolver;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelTitulo;
    private Data data;

    @FXML
    void volver(MouseEvent event) throws IOException {

        if(!this.data.getVistaAnterior()){
            this.data.setLibroSeleccionado(null);
            this.data.setVistaAnterior(null);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("tabla_libros.fxml"),CambiarIdioma.getInstance().getBundle());
            Parent contenido = fxmlLoader.load();
            ControllerTablaLibros controllerTablaLibros = fxmlLoader.getController();
            controllerTablaLibros.recibirData(this.data);
            this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(contenido);
        }else {
            this.data.setLibroSeleccionado(null);
            this.data.setVistaAnterior(null);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lista_libros.fxml"),CambiarIdioma.getInstance().getBundle());
            Parent contenido = fxmlLoader.load();
            ControllerListaLibros controllerListaLibros = fxmlLoader.getController();
            controllerListaLibros.establecerDatos(this.data);
            this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(contenido);

        }
    }
    public void establecerDatos(Data data){
        this.data = data;
        this.labelAutor.setText(this.data.getLibroSeleccionado().getAutor());
        this.labelTitulo.setText(this.data.getLibroSeleccionado().getTitulo());
    }


}

