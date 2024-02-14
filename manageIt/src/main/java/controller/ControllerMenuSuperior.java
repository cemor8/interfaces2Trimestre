package controller;

import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import modelo.Data;

public class ControllerMenuSuperior {

    @FXML
    private MFXTextField barraBusqueda;

    @FXML
    private AnchorPane menuSuperior;
    private Data data;
    public void recibirData(Data data){
        this.data = data;
    }

}

