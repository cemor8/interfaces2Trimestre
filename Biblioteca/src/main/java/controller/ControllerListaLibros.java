package controller;

import com.example.biblioteca.MainApplication;
import controller.ControllerVistaLibro;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.CambiarIdioma;
import model.Data;
import model.Libro;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerListaLibros {

    @FXML
    private Button btnAnterior;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Label pagina;

    @FXML
    private GridPane rellenar;
    private int numeroPagina;
    private int paginasTotales;
    private int librosXpagina = 8;
    private Data data;

    /**
     * Método que se encarga de crear los libros rellenando la vista de "cada_libro" con
     * el contenido de un libro, recorre toda la lista de los libros haciendo esto.
     */
    public void crearLibros() throws IOException {
        int y = 0;
        this.rellenar.setHgap(10);
        this.rellenar.setVgap(10);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(20);
        columnConstraints.setHalignment(HPos.CENTER);
        this.rellenar.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints, columnConstraints);
        for (int i = librosXpagina * numeroPagina; i < librosXpagina * (numeroPagina + 1) && i < this.data.getLibros().size(); i++) {


            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId(this.data.getLibros().get(i).getTitulo());

            anchorPane.getStyleClass().add("cadaAnchor");

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cada_libro.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();

            ControllerCadaLibro controllerCadaLibro = fxmlLoader.getController();
            controllerCadaLibro.recibirData(this.data, this.data.getLibros().get(i));
            anchorPane.getChildren().setAll(root);



            int fila = y / 4;
            int columna = y % 4;
            Insets margin = new Insets(10, 10, 10, 10);
            GridPane.setMargin(anchorPane, margin);
            GridPane.setFillHeight(anchorPane, false);
            GridPane.setFillWidth(anchorPane, false);
            this.rellenar.setPrefSize(800, 600);
            GridPane.setConstraints(anchorPane, columna, fila);
            this.rellenar.getChildren().add(anchorPane);
            y++;
        }
        this.rellenar.setLayoutX(105);
        this.rellenar.setLayoutY(70);

    }

    @FXML
    void anterior(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lista_libros.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerListaLibros controllerListaLibros = fxmlLoader.getController();
        controllerListaLibros.establecerDatos(this.data, this.numeroPagina - 1);
        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);
    }

    @FXML
    void siguiente(MouseEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("lista_libros.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerListaLibros controllerListaLibros = fxmlLoader.getController();
        controllerListaLibros.establecerDatos(this.data, this.numeroPagina + 1);
        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(root);
    }

    /**
     * Método que se encarga de recibir el modelo para que el controlador tenga acceso a el
     */
    public void establecerDatos(Data data, int pagina) throws IOException {
        this.data = data;
        this.numeroPagina = pagina;
        this.data.setCurrentPage(pagina);
        this.paginasTotales = this.data.getLibros().size() / this.librosXpagina;

        if (this.data.getLibros().size() % this.librosXpagina != 0) {
            this.paginasTotales++;
        }
        this.pagina.setText((this.numeroPagina + 1) + " / " + this.paginasTotales);

        if ((double) this.numeroPagina <= 0) {
            this.btnAnterior.setDisable(true);
        }
        if ((double) this.numeroPagina >= this.paginasTotales - 1) {
            this.btnSiguiente.setDisable(true);
        }
        this.crearLibros();

    }

}
