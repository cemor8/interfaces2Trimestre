package controller;

import com.example.biblioteca.MainApplication;
import controller.ControllerVistaLibro;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.CambiarIdioma;
import model.Data;
import model.Libro;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerListaLibros {

    @FXML
    private MFXScrollPane contenedorLibros;
    private Data data;

    public void crearLibros() throws IOException {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        vBox.setSpacing(30);
        for(int i = 0; i<this.data.getLibros().size() ; i++){
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId(this.data.getLibros().get(i).getTitulo());
            anchorPane.setMinWidth(200);
            anchorPane.setMinHeight(300);
            anchorPane.getStyleClass().add("cadaAnchor");

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cada_libro.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();

            ControllerCadaLibro controllerCadaLibro = fxmlLoader.getController();
            controllerCadaLibro.recibirData(this.data,this.data.getLibros().get(i));
            anchorPane.getChildren().setAll(root);


            Insets insets = new Insets(0, 0, 0, 25);
            HBox.setMargin(anchorPane, insets);

            if (i % 4 == 0) {
                if (i > 0) {
                    vBox.getChildren().add(hBox);
                }
                hBox = new HBox();
            }
            hBox.getChildren().add(anchorPane);

            if (i == this.data.getLibros().size() - 1) {
                vBox.getChildren().add(hBox);
            }
        }
        this.contenedorLibros.setContent(vBox);
    }

    public void establecerDatos(Data data) throws IOException {
        this.data = data;
        this.crearLibros();
    }

}
