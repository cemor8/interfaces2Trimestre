package controller;

import atlantafx.base.controls.Calendar;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import modelo.Data;
import modelo.Proyecto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ControllerCalendario {

    @FXML
    private Calendar calendario;
    private Data data;
    private VBox vBox;

    @FXML
    private MFXScrollPane scroll;
    public void recibirData(Data data){
        this.data = data;
    }
    @FXML
    void mostrarContenidoDia(MouseEvent event) {
        LocalDate localDate = calendario.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = localDate.format(formatter);
        this.vBox = new VBox();
        for (Proyecto proyecto : this.data.getProyectos()){
            LocalDate fechaCreacion = proyecto.getFechaCreacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaEntrega = proyecto.getFechaEntrega().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            String fechaCreacionString = fechaCreacion.format(formatter);
            String fechaEntregaString = fechaEntrega.format(formatter);

            if (fechaEntregaString.equalsIgnoreCase(fechaFormateada) ||fechaCreacionString.equalsIgnoreCase(fechaFormateada)){

            }
        }
    }

}

