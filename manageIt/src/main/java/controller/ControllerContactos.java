package controller;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Data;
import modelo.Usuario;

import java.util.ArrayList;

public class ControllerContactos {
    @FXML
    private MFXScrollPane scroll;
    private Data data;
    private ArrayList<Usuario> contactos;

    public void inicializar(){
        VBox vBox = new VBox();
        int i = 1;
        HBox hBox = new HBox(10);
        for (Usuario contacto : contactos){
            System.out.println("hola");

            if (i % 2 == 0 && i > 0){
                vBox.getChildren().add(hBox);
                hBox = new HBox(10);
                hBox.setPadding(new Insets(15, 12, 15, 12));
            }


            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getStyleClass().add("cartaContacto");

            Image image = new Image("file:"+contacto.getRutaImagen());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.getStyleClass().add("imagenContacto");

            Label nameLabel = new Label(contacto.getNombre()+" "+contacto.getApellidos());
            nameLabel.getStyleClass().add("nombreContacto");

            ImageView imgBorrar = new ImageView();
            imgBorrar.getStyleClass().add("delete-button");

            ImageView imgVer = new ImageView();
            imgVer.getStyleClass().add("delete-button");

            anchorPane.getChildren().addAll(imageView,nameLabel,imgVer,imgBorrar);
            hBox.getChildren().add(anchorPane);
            vBox.getChildren().add(hBox);
            i++;
        }
        this.scroll.setContent(vBox);

    }
    public void recibirData(Data data, ArrayList<Usuario> contacto){
        this.contactos = contacto;
        this.data = data;
        this.inicializar();
    }
}
