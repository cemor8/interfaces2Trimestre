package model;

import controller.ControllerPanelPrincipal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.Locale;
import java.util.ResourceBundle;

public class Data {
    public Data() {

        this.libros.add(new Libro("Libro1","Autor1","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro3","Autor3","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro4","Autor4","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro5","Autor5","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro6","Autor6","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro7","Autor7","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro8","Autor8","9898231741798","19/02/1967",""));
        this.libros.add(new Libro("Libro9","Autor9","9898231741798","19/02/1967",""));
    }


    private Usuario currentUser;
    private ObservableList<Libro> libros = FXCollections.observableArrayList();
    private ObservableList<Libro> librosFiltrados;
    private Boolean vistaAnteriorTabla;
    private boolean filtrar = false;
    private AnchorPane main;
    private Controllers controllers = new Controllers();
    private Libro libroSeleccionado;
    private boolean oscuro = false;
    private boolean español = true;
    private int currentPage = 0;

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public ObservableList<Libro> getLibros() {
        return libros;
    }

    public Boolean getVistaAnterior() {
        return vistaAnteriorTabla;
    }


    public boolean isOscuro() {
        return oscuro;
    }

    public void setOscuro(boolean oscuro) {
        this.oscuro = oscuro;
    }

    public void setLibros(ObservableList<Libro> libros) {
        this.libros = libros;
    }

    public void setVistaAnterior(Boolean vistaAnterior) {
        this.vistaAnteriorTabla = vistaAnterior;
    }


    public boolean isFiltrar() {
        return filtrar;
    }

    public void setFiltrar(boolean filtrar) {
        this.filtrar = filtrar;
    }

    public Libro getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(Libro libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    public ObservableList<Libro> getLibrosFiltrados() {
        return librosFiltrados;
    }

    public void setLibrosFiltrados(ObservableList<Libro> librosFiltrados) {
        this.librosFiltrados = librosFiltrados;
    }

    public Boolean getVistaAnteriorTabla() {
        return vistaAnteriorTabla;
    }
    public void setMain(AnchorPane anchorPane){
        main = anchorPane;
    }

    public AnchorPane getMain() {
        return main;
    }

    public Controllers getControllers() {
        return controllers;
    }

    public void setVistaAnteriorTabla(Boolean vistaAnteriorTabla) {
        this.vistaAnteriorTabla = vistaAnteriorTabla;
    }

    public void setControllers(Controllers controllers) {
        this.controllers = controllers;
    }

    public boolean isEspañol() {
        return español;
    }

    public void setEspañol(boolean español) {
        this.español = español;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
