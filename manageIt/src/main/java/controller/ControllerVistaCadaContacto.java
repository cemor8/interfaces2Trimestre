package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import modelo.Data;
import modelo.Usuario;

import java.util.ArrayList;

public class ControllerVistaCadaContacto {

    @FXML
    private AnchorPane anchor;

    @FXML
    private MFXButton btn;

    @FXML
    private Label descripcion;

    @FXML
    private ImageView img;

    @FXML
    private Label labelCorreo;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelPuesto;
    private Data data;
    private Usuario contacto;
    private boolean añadir;
    private ArrayList<Usuario> contactos;
    private ArrayList<Usuario> contactosAñadir;

    @FXML
    void gestionarContacto(MouseEvent event) {

    }
    public void cargarContacto(){
        this.labelCorreo.setText(this.contacto.getCorreo());
        this.labelNombre.setText(this.contacto.getNombre()+" "+this.contacto.getApellidos());
        this.labelPuesto.setText(this.contacto.getPuesto());
        this.descripcion.setText(this.contacto.getDescripcion());

        this.img.setImage(new Image("file:"+this.contacto.getRutaImagen()));
        img.setFitHeight(60);
        img.setFitWidth(60);

    }
    public void recibirData(Data data, Usuario usuario,boolean añadir, ArrayList<Usuario> contactos,ArrayList<Usuario> contactosAñadir){
        this.data = data;
        this.contacto = usuario;
        this.añadir = añadir;
        this.contactos = contactos;
        this.contactosAñadir = contactosAñadir;
        this.cargarContacto();
    }

}

