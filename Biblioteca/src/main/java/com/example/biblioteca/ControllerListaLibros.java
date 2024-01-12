package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import javax.swing.text.View;
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
            anchorPane.setMinWidth(250);
            anchorPane.setMinHeight(300);
            //anchorPane.getStyleClass().add("test");


            ImageView imageView = new ImageView();
            imageView.setFitWidth(290);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(false);

            /*
            Rectangle clipRect = new Rectangle(290, 150);
            clipRect.setArcWidth(20);
            clipRect.setArcHeight(20);
            imageView.setClip(clipRect);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(imageView);
            stackPane.setPrefHeight(270);
            stackPane.setPrefWidth(150);
             */

            Label titulo = new Label(this.data.getLibros().get(i).getTitulo());
            //titulo.getStyleClass().add("nombre_restaurante");
            titulo.setLayoutY(160);

            Label autor = new Label(this.data.getLibros().get(i).getAutor());
            //autor.getStyleClass().add("tiempo-entrega");
            autor.setLayoutY(190);

            MFXButton btnVer = new MFXButton();
            btnVer.setOnMouseClicked(this::ver);

            if (this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
                btnVer.setText(bundle.getString("books.see"));
            }else{
                btnVer.setText(bundle.getString("lista.ver"));
            }
            
            btnVer.setLayoutY(220);

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

    private void ver(MouseEvent event) {

    }
    public void establecerDatos(Data data){
        this.data = data;
        this.crearLibros();
    }

}
