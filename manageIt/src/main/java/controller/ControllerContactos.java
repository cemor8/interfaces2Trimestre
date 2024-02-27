package controller;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.CambiarIdioma;
import modelo.Data;
import modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerContactos {
    @FXML
    private MFXScrollPane scroll;
    private Data data;
    private ArrayList<Usuario> contactos;
    private HBox hBoxMeter = new HBox();
    private boolean añadir;
    private ArrayList<Usuario> contactosAsignados;

    /**
     * Método que se encarga de inicializar la lista de contactos cuando accedes a
     * tu lista personal de contactos
     */
    public void inicializar(){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("vboxContactos");
        VBox vBox2 = new VBox();
        vBox2.getStyleClass().add("vboxContactos");
        int i = 1;
        HBox hBox = new HBox(10);
        for (Usuario contacto : contactos){
            hBox.getStyleClass().add("cartaContacto");

            Image image = new Image("file:"+contacto.getRutaImagen());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.getStyleClass().add("imagenContacto");

            Label nameLabel = new Label(contacto.getNombre()+" "+contacto.getApellidos());
            nameLabel.getStyleClass().add("nombreContacto");

            ImageView imgBorrar = new ImageView();
            imgBorrar.getStyleClass().add("papeleraVistaCadaProyecto");
            imgBorrar.setOnMouseClicked(this::eliminarContacto);
            imgBorrar.setFitHeight(30);
            imgBorrar.setFitWidth(30);
            imgBorrar.setId(String.valueOf(this.contactos.indexOf(contacto)));


            ImageView imgVer = new ImageView();
            imgVer.getStyleClass().add("ojoVistaCadaProyecto");
            imgVer.setFitHeight(30);
            imgVer.setFitWidth(30);
            imgVer.setOnMouseClicked(this::verContacto);
            imgVer.setId(String.valueOf(this.contactos.indexOf(contacto)));
            HBox.setMargin(nameLabel,new Insets(0,50,0,65));
            HBox.setMargin(imgVer,new Insets(0,10,0,0));
            hBox.getChildren().addAll(imageView,nameLabel,imgVer,imgBorrar);
            i++;
            if (i % 2 == 0){
                vBox.getChildren().add(hBox);

            }else {
                vBox2.getChildren().add(hBox);
            }
            hBox = new HBox(10);
            hBox.setPadding(new Insets(15, 12, 15, 12));
        }
        hBoxMeter.getChildren().addAll(vBox,vBox2);
        this.scroll.setContent(hBoxMeter);

    }

    /**
     * Método que se encarga de inicializar el apartado de contactos cuando se quiere
     * añadir un contacto a un proyecto o tarea
     */
    public void inicializarAñadir(){
        VBox vBox = new VBox();
        vBox.getStyleClass().add("vboxContactos");
        VBox vBox2 = new VBox();
        vBox2.getStyleClass().add("vboxContactos");
        int i = 1;
        HBox hBox = new HBox(10);
        for (Usuario contacto : contactos){
            hBox.getStyleClass().add("cartaContacto");

            Image image = new Image("file:"+contacto.getRutaImagen());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.getStyleClass().add("imagenContacto");

            Label nameLabel = new Label(contacto.getNombre()+" "+contacto.getApellidos());
            nameLabel.getStyleClass().add("nombreContacto");

            ImageView imgBorrar = new ImageView();
            imgBorrar.getStyleClass().add("papeleraVistaCadaProyecto");
            imgBorrar.setOnMouseClicked(this::quitarContacto);
            imgBorrar.setFitHeight(30);
            imgBorrar.setFitWidth(30);
            imgBorrar.setId(String.valueOf(this.contactos.indexOf(contacto)));


            ImageView imgVer = new ImageView();
            imgVer.getStyleClass().add("ojoVistaCadaProyecto");
            imgVer.setFitHeight(30);
            imgVer.setFitWidth(30);
            imgVer.setOnMouseClicked(this::verContacto);
            imgVer.setId(String.valueOf(this.contactos.indexOf(contacto)));
            HBox.setMargin(nameLabel,new Insets(0,50,0,65));
            HBox.setMargin(imgVer,new Insets(0,10,0,0));


            ImageView imgAñadir = new ImageView();
            imgAñadir.getStyleClass().add("añadirCadaProyecto");
            imgAñadir.setFitHeight(30);
            imgAñadir.setFitWidth(30);
            imgAñadir.setOnMouseClicked(this::añadirContacto);
            imgAñadir.setId(String.valueOf(this.contactos.indexOf(contacto)));
            HBox.setMargin(nameLabel,new Insets(0,50,0,65));
            HBox.setMargin(imgAñadir,new Insets(0,10,0,0));
            hBox.getChildren().addAll(imageView,nameLabel,imgVer,imgAñadir,imgBorrar);

            i++;
            if (i % 2 == 0){
                vBox.getChildren().add(hBox);

            }else {
                vBox2.getChildren().add(hBox);
            }
            hBox = new HBox(10);
            hBox.setPadding(new Insets(15, 12, 15, 12));
        }
        hBoxMeter.getChildren().addAll(vBox,vBox2);
        this.scroll.setContent(hBoxMeter);
    }

    /**
     * Método que se encarga de eliminar un contacto de un proyecto o tarea
     * @param mouseEvent
     */
    private void quitarContacto(MouseEvent mouseEvent) {
        ImageView img = (ImageView) mouseEvent.getSource();
        int posicion = Integer.parseInt(img.getId());
        this.contactosAsignados.remove(this.contactos.get(posicion));
        img.setDisable(false);
    }

    /**
     * Método que se encarga de añadir un contacto a la lista de contactos
     * @param mouseEvent
     */
    private void añadirContacto(MouseEvent mouseEvent) {
        ImageView img = (ImageView) mouseEvent.getSource();
        int posicion = Integer.parseInt(img.getId());
        this.contactosAsignados.add(this.contactos.get(posicion));
        img.setDisable(true);
    }

    /**
     * Método que se encarga de cargar la vista detallada de un contacto
     * @param event
     */
    private void verContacto(MouseEvent event) {
        ImageView img = (ImageView) event.getSource();

    }

    /**
     * Método que se encarga de eliminar un contacto de la lista de contactos personales
     * @param event
     */
    private void eliminarContacto(MouseEvent event) {
        ImageView img = (ImageView) event.getSource();
        int posicion = Integer.parseInt(img.getId());
        this.contactos.remove(posicion);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/contactos.fxml"), CambiarIdioma.getInstance().getBundle());
            Parent root = fxmlLoader.load();
            ControllerContactos controllerContactos = fxmlLoader.getController();
            controllerContactos.recibirData(this.data,this.contactos,this.añadir,this.contactosAsignados);
            this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);

        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }

    /**
     * Método que se encarga de recibir informacion
     * @param data  clase con informacion
     * @param contactos  lista de contactos
     * @param añadir    si es para añadir a un proyecto o tarea
     * @param contactosAsignados    lista de contactos del proyecto o tarea
     */
    public void recibirData(Data data, ArrayList<Usuario> contactos, boolean añadir,ArrayList<Usuario> contactosAsignados){
        this.contactos = contactos;
        this.contactosAsignados = contactosAsignados;
        this.data = data;
        this.añadir = añadir;
        if (this.añadir){
            this.inicializarAñadir();
        }else {
            this.inicializar();
        }

    }
}
