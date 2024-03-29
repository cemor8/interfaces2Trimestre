package controller;

import com.example.biblioteca.MainApplication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.CambiarIdioma;
import model.Data;
import model.Libro;

import java.io.IOException;
import java.net.URL;
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

    /**
     * Método que se encarga de borrar un elemento de la tabla y
     * de la lista de libros
     * */
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
    /**
     * Método que se encarga de filtrar los libros.
     * */
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
    /**
     * Método que se encarga de mostrar la vista de un libro seleccionado en
     * la tabla
     * */
    @FXML
    void ver(MouseEvent event) throws IOException {
        Libro libro = this.tablaLibros.getSelectionModel().getSelectedItem();
        if(libro == null){
            return;
        }
        this.data.setLibroSeleccionado(libro);
        this.data.setVistaAnterior(false);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("vista_libro.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent contenido = fxmlLoader.load();
        ControllerVistaLibro controllerVistaLibro = fxmlLoader.getController();
        controllerVistaLibro.establecerDatos(this.data);
        this.data.getControllers().getControllerPanelPrincipal().cambiarContenido(contenido);

    }
    /**
     * Método que se encarga de recibir el modelo para que el controlador tenga acceso a el, tambien
     * comprueba si hay que filtrar la tabla
     * */
    public void recibirData(Data data){
        this.data = data;
        if(this.data.isFiltrar()){
            this.filtrar.setSelected(true);
            this.tablaLibros.setItems(this.data.getLibrosFiltrados());
        }else {
            this.tablaLibros.setItems(this.data.getLibros());
        }


        this.barraBusqueda.textProperty().addListener((observable, textoPrevio, textoIntroducido) -> {
            if (textoIntroducido.isEmpty()) {
                this.tablaLibros.setItems(this.data.getLibros());
            } else {
                if(this.data.isFiltrar()){
                    ObservableList<Libro> filteredList = this.data.getLibrosFiltrados().filtered(item -> item.getTitulo().toLowerCase().contains(textoIntroducido.toLowerCase()));
                    this.tablaLibros.setItems(filteredList);
                }else {
                    ObservableList<Libro> filteredList = this.data.getLibros().filtered(item -> item.getTitulo().toLowerCase().contains(textoIntroducido.toLowerCase()));
                    this.tablaLibros.setItems(filteredList);
                }


            }
        });

    }
    /**
     * Método que se encarga de inicializar la tabla
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.columnaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        this.columnaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.columnaISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.tablaLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}

