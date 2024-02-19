package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.CambiarIdioma;
import modelo.Data;
import modelo.Proyecto;
import modelo.Tarea;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerTareas {

    @FXML
    private MFXButton btnMeter;

    @FXML
    private MFXScrollPane scroll;
    private Data data;
    private ArrayList<Tarea> tareas;

    @FXML
    void meter(MouseEvent event) {

    }

    public void inicializar() throws IOException {
        int i = 0;
        HBox hBox = new HBox();
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        for (Tarea tarea : this.tareas) {
            if (i % 3 == 0 && i > 0) {
                if (hBox != null) {
                    vbox.getChildren().add(hBox);
                }
                hBox = new HBox(10);
            }
            AnchorPane anchorPane = new AnchorPane();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/cadaTarea.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();
            ControllerCadaTarea controllerCadaTarea = fxmlLoader.getController();
            controllerCadaTarea.recibirData(this.data, tarea, this.tareas);
            anchorPane.getChildren().setAll(root);

            HBox.setMargin(anchorPane, new Insets(10, 0, 0, 10));

            hBox.getChildren().add(anchorPane);
            i++;


            if (i == this.tareas.size()) {
                vbox.getChildren().add(hBox);
            }

        }
        this.scroll.setContent(vbox);
        this.btnMeter.setText("");

    }

    public void recibirData(Data data, ArrayList<Tarea> tareas) throws IOException {
        this.data = data;
        this.tareas = tareas;
        this.inicializar();
    }

}
