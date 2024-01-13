package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControllerTablaLibros implements Initializable {
    @FXML
    private MFXTextField barraBusqueda;

    @FXML
    private MFXButton btnBorrar;

    @FXML
    private MFXButton btnVer;

    @FXML
    private MFXCheckbox filtrar;
    @FXML
    private TableView<Libro> tablaLibros;
    @FXML
    private TableColumn<Libro, String> columnaAutor;

    @FXML
    private TableColumn<Libro, String> columnaFecha;

    @FXML
    private TableColumn<Libro, String> columnaISBN;

    @FXML
    private TableColumn<Libro, String> columnaTitulo;
    private Data data;

    @FXML
    void borrar(MouseEvent event) {
        Libro libro = this.tablaLibros.getSelectionModel().getSelectedItem();
        if(libro == null){
            return;
        }
        this.data.getLibros().remove(libro);
        if(this.data.getLibrosFiltrados()!= null && this.data.getLibrosFiltrados().contains(libro)){
            this.data.getLibrosFiltrados().remove(libro);
        }
        this.tablaLibros.refresh();
    }

    @FXML
    void filtrar(MouseEvent event) {
        System.out.println(this.filtrar.isSelected());
        if (this.filtrar.isSelected()){
            this.data.setFiltrar(true);
            List<Libro> listaFiltrada = this.data.getLibros().stream()
                    .filter(libro -> Integer.parseInt(libro.getFecha().substring(6, 10)) > 2000)
                    .collect(Collectors.toList());
            ObservableList<Libro> librosFiltrados = FXCollections.observableArrayList(listaFiltrada);
            this.tablaLibros.setItems(librosFiltrados);
            this.data.setLibrosFiltrados(librosFiltrados);
        }else {
            this.data.setFiltrar(false);
            this.tablaLibros.setItems(this.data.getLibros());
        }

    }

    @FXML
    void ver(MouseEvent event) throws IOException {
        Libro libro = this.tablaLibros.getSelectionModel().getSelectedItem();
        if(libro == null){
            return;
        }
        this.data.setLibroSeleccionado(libro);
        this.data.setVistaAnterior(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_libro.fxml"));
        Parent contenido = fxmlLoader.load();
        ControllerVistaLibro controllerVistaLibro = fxmlLoader.getController();
        controllerVistaLibro.establecerDatos(this.data);
        this.data.getControllerPanelPrincipal().cambiarContenido(contenido);

    }
    public void recibirData(Data data){
        this.data = data;
        this.traducir();
        if(this.data.isFiltrar()){
            this.filtrar.setSelected(true);
            this.tablaLibros.setItems(this.data.getLibrosFiltrados());
        }else {
            this.tablaLibros.setItems(this.data.getLibros());
        }


        this.barraBusqueda.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                this.tablaLibros.setItems(this.data.getLibros());
            } else {
                if(this.data.isFiltrar()){
                    ObservableList<Libro> filteredList = this.data.getLibrosFiltrados().filtered(item -> item.getTitulo().toLowerCase().contains(newValue.toLowerCase()));
                    this.tablaLibros.setItems(filteredList);
                }else {
                    ObservableList<Libro> filteredList = this.data.getLibros().filtered(item -> item.getTitulo().toLowerCase().contains(newValue.toLowerCase()));
                    this.tablaLibros.setItems(filteredList);
                }


            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.columnaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        this.columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.columnaISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.tablaLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    public void traducir(){
        this.columnaISBN.setText("ISBN");
        this.data.setBundle(ResourceBundle.getBundle("bundles.MessagesBundle",this.data.getLocale()));
        ResourceBundle bundle = this.data.getBundle();
        if(this.data.getLocale().getLanguage().equalsIgnoreCase("en")){
            this.btnBorrar.setText(bundle.getString("detailded.delete"));
            this.btnVer.setText(bundle.getString("detailed.see"));
            this.filtrar.setText(bundle.getString("detailed.filter"));
            this.columnaAutor.setText(bundle.getString("detailed.author"));
            this.columnaFecha.setText(bundle.getString("detailed.date"));
            this.columnaTitulo.setText(bundle.getString("detailed.title"));

        }else{
            this.btnBorrar.setText(bundle.getString("detallada.borrar"));
            this.btnVer.setText(bundle.getString("detallada.ver"));
            this.filtrar.setText(bundle.getString("detallada.filtrar"));
            this.columnaAutor.setText(bundle.getString("detallada.autor"));
            this.columnaFecha.setText(bundle.getString("detallada.fecha"));
            this.columnaTitulo.setText(bundle.getString("detallada.titulo"));
        }
    }
}

