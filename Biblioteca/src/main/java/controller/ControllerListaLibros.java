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
import model.Data;
import model.Libro;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerListaLibros {

    @FXML
    private MFXScrollPane contenedorLibros;
    private Data data;

    public void crearLibros(){
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        vBox.setSpacing(30);
        this.data.setBundle(ResourceBundle.getBundle("bundles.MessagesBundle",this.data.getLocale()));
        ResourceBundle bundle = this.data.getBundle();
        for(int i = 0; i<this.data.getLibros().size() ; i++){
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId(this.data.getLibros().get(i).getTitulo());
            anchorPane.setMinWidth(200);
            anchorPane.setMinHeight(300);
            anchorPane.getStyleClass().add("cadaAnchor");

            // solo crear anchorpane, cargar dentro de el otro y cargar vista, igual que el dashboard.

            ImageView imageView = new ImageView();
            imageView.setFitWidth(1);
            imageView.setFitHeight(1);
            imageView.setPreserveRatio(false);


            Label titulo = new Label(this.data.getLibros().get(i).getTitulo());


            titulo.setLayoutY(210);
            titulo.getStyleClass().add("labelCadaTitulo");
            titulo.setLayoutX(80);

            Label autor = new Label(this.data.getLibros().get(i).getAutor());

            autor.setLayoutY(230);
            autor.setLayoutX(80);
            autor.getStyleClass().add("labelCadaAutor");


            MFXButton btnVer = new MFXButton();
            btnVer.setOnMouseClicked(this::ver);
            btnVer.getStyleClass().add("ver");
            btnVer.setLayoutY(250);
            btnVer.setLayoutX(40);
            btnVer.setMinWidth(130);
            btnVer.setMinHeight(40);

            /*
            if (this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
                btnVer.setText(bundle.getString("books.see"));
            }else{
                btnVer.setText(bundle.getString("lista.ver"));
            }
            btnVer.setId(this.data.getLibros().get(i).getTitulo());

             */

            anchorPane.getChildren().add(imageView);
            anchorPane.getChildren().add(titulo);
            anchorPane.getChildren().add(autor);
            anchorPane.getChildren().add(btnVer);

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

    private void ver(MouseEvent event)  {
        MFXButton btn =(MFXButton) event.getSource();
        Optional<Libro> libro = this.data.getLibros().stream().filter(libro1 -> libro1.getTitulo().equalsIgnoreCase(btn.getId())).findAny();
        libro.ifPresent(value -> this.data.setLibroSeleccionado(value));
        this.data.setVistaAnterior(true);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_libro.fxml"));
        Parent root = null;
        try {
             root = fxmlLoader.load();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
        ControllerVistaLibro controllerVistaLibro =fxmlLoader.getController();
        controllerVistaLibro.establecerDatos(this.data);
        this.data.getControllerPanelPrincipal().cambiarContenido(root);

    }
    public void establecerDatos(Data data){
        this.data = data;
        this.crearLibros();
    }

}
