package controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import modelo.CambiarIdioma;
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

    /**
     * Método que elimina un contacto si lo tiene agregado el usuario y si no lo tiene agregado lo añade
     * @param event
     */
    @FXML
    void gestionarContacto(MouseEvent event) {
        if (this.data.getCurrentUser().getContactos().contains(this.contacto)){
            this.data.getCurrentUser().getContactos().remove(contacto);
        }else {
            this.data.getCurrentUser().getContactos().add(contacto);
        }
    }

    /**
     * Método que carga el contenido de cada contacto
     */
    public void cargarContacto(){
        this.labelCorreo.setText(this.contacto.getCorreo());
        this.labelNombre.setText(this.contacto.getNombre()+" "+this.contacto.getApellidos());
        this.labelPuesto.setText(this.contacto.getPuesto());
        this.descripcion.setText(this.contacto.getDescripcion());
        this.img.setImage(new Image("file:"+this.contacto.getRutaImagen()));
        img.setFitHeight(100);
        img.setFitWidth(100);
        Rectangle clip = new Rectangle(
                this.img.getFitWidth(), this.img.getFitHeight()
        );
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        this.img.setClip(clip);

    }
    public void recibirData(Data data, Usuario usuario,boolean añadir, ArrayList<Usuario> contactos,ArrayList<Usuario> contactosAñadir){
        this.data = data;
        this.contacto = usuario;
        this.añadir = añadir;
        this.contactos = contactos;
        this.contactosAñadir = contactosAñadir;
        if (this.data.getCurrentUser().getContactos().contains(this.contacto)){
            this.btn.setText(CambiarIdioma.getInstance().getBundle().getString("contactoVista.eliminar"));
        }else {
            this.btn.setText(CambiarIdioma.getInstance().getBundle().getString("contactoVista.meter"));
        }
        this.cargarContacto();
    }

}

