package com.example.biblioteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Data {
    public Data() {
        this.libros.add(new Libro("Libro1","Autor1","9898231741798","19/02/1967"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));


        this.libros.add(new Libro("Libro2","Autor2","9898231741798","19/02/2003"));
    }

    private Usuario currentUser;
    private ObservableList<Libro> libros = FXCollections.observableArrayList();
    private ObservableList<Libro> librosFiltrados;
    private Boolean vistaAnteriorTabla;
    private Locale locale = new Locale("es");
    private ResourceBundle bundle;
    private boolean filtrar = false;
    private AnchorPane main;
    private Libro libroSeleccionado;
    private boolean oscuro = false;
    ControllerPanelPrincipal controllerPanelPrincipal;

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

    public Locale getLocale() {
        return locale;
    }

    public boolean isOscuro() {
        return oscuro;
    }

    public void setOscuro(boolean oscuro) {
        this.oscuro = oscuro;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setLibros(ObservableList<Libro> libros) {
        this.libros = libros;
    }

    public void setVistaAnterior(Boolean vistaAnterior) {
        this.vistaAnteriorTabla = vistaAnterior;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
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

    public ControllerPanelPrincipal getControllerPanelPrincipal() {
        return controllerPanelPrincipal;
    }

    public void setControllerPanelPrincipal(ControllerPanelPrincipal controllerPanelPrincipal) {
        this.controllerPanelPrincipal = controllerPanelPrincipal;
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
}
