package controller;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import modelo.CambiarIdioma;
import modelo.Data;
import modelo.Proyecto;
import modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerMenuLateral {

    @FXML
    private HBox hboxCalendario;

    @FXML
    private HBox hboxCerrar;

    @FXML
    private HBox hboxConfiguracion;

    @FXML
    private HBox hboxContactos;

    @FXML
    private HBox hboxNotas;

    @FXML
    private HBox hboxPanel;

    @FXML
    private HBox hboxProyectos;

    @FXML
    private HBox hboxTareas;
    @FXML
    private ImageView imagenContactos;
    @FXML
    private ImageView imagenAjustes;

    @FXML
    private ImageView imagenCalendario;

    @FXML
    private ImageView imagenNotas;

    @FXML
    private ImageView imagenPanel;

    @FXML
    private ImageView imagenProyectos;

    @FXML
    private ImageView imagenSalir;

    @FXML
    private ImageView imagenTareas;
    private Data data;

    /**
     * Método que se encarga de cerrar sesión
     * @param event
     */
    @FXML
    void cerrar(MouseEvent event) {

    }

    /**
     * Método que se encarga de mostrar la vista del calendario
     * @param event
     */
    @FXML
    void mostrarCalendario(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxCalendario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenCalendario.getStyleClass().add("calendarioPresionado");
    }

    /**
     * Método que se encarga de mostrar la vista de la configuracion del usuario
     * @param event
     */
    @FXML
    void mostrarConfig(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxConfiguracion.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenAjustes.getStyleClass().add("configPresionado");
    }

    /**
     * Método que se encarga de mostrar la vista de contactos
     * @param event
     */
    @FXML
    void mostrarContacto(MouseEvent event) throws IOException {
        this.reiniciarHbox();
        this.hboxContactos.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenContactos.getStyleClass().add("contactosPresionado");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/contactos.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerContactos controllerContactos = fxmlLoader.getController();
        controllerContactos.recibirData(this.data,this.data.getCurrentUser().getContactos());
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    /**
     * Método que se encarga de mostrar la vista de notas
     * @param event
     */
    @FXML
    void mostrarNotas(MouseEvent event) throws IOException {
        this.reiniciarHbox();
        this.hboxNotas.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenNotas.getStyleClass().add("notasPresionado");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/notas.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerNotas controllerNotas = fxmlLoader.getController();
        controllerNotas.recibirData(this.data,this.data.getCurrentUser().getNotas());
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    /**
     * Método que se encarga de mostrar el panel principal
     * @param event
     * @throws IOException
     */
    @FXML
    void mostrarPanel(MouseEvent event) throws IOException {
        System.out.println("hola");
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        for (Proyecto proyecto : this.data.getProyectos()) {
            for (Usuario usuario : proyecto.getPersonasAsignadas()) {
                if (usuario.getCorreo().equals(this.data.getCurrentUser().getCorreo())) {
                    proyectos.add(proyecto);
                    break;
                }
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/panel.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerPanel controllerPanel = fxmlLoader.getController();
        controllerPanel.recibirData(this.data,proyectos);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);

        this.reiniciarHbox();
        this.iniciarPanel();
    }

    /**
     * Método que se encarga de mostrar la vista de los proyectos
     * @param event
     */
    @FXML
    void mostrarProyectos(MouseEvent event) throws IOException {
        this.reiniciarHbox();
        this.hboxProyectos.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenProyectos.getStyleClass().add("proyectosPresionado");

        ArrayList<Proyecto> proyectos = new ArrayList<>();
        for (Proyecto proyecto : this.data.getProyectos()) {
            for (Usuario usuario : proyecto.getPersonasAsignadas()) {
                if (usuario.getCorreo().equals(this.data.getCurrentUser().getCorreo())) {
                    proyectos.add(proyecto);
                    break;
                }
            }
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/proyectos.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerProyectos controllerProyectos = fxmlLoader.getController();
        controllerProyectos.recibirData(this.data,proyectos);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    /**
     * Método que se encarga de mostrar tareas
     * @param event
     */
    @FXML
    void mostrarTareas(MouseEvent event) throws IOException {
        this.reiniciarHbox();
        this.hboxTareas.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenTareas.getStyleClass().add("tareasPresionado");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/tareas.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerTareas controllerTareas = fxmlLoader.getController();
        controllerTareas.recibirData(this.data,this.data.getCurrentUser().getTareas());
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);
    }

    /**
     * Método que se encarga de recibir la información
     * @param data
     */
    public void recibirData(Data data){
        this.data = data;
        this.data.getListaControladores().setControllerMenuLateral(this);
    }

    /**
     * Método que se encarga de reiniciar el color de los hboxes
     * */
    public void reiniciarHbox(){
        this.hboxCalendario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxCerrar.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxConfiguracion.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxContactos.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxNotas.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxPanel.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxProyectos.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);
        this.hboxTareas.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),false);


        this.limpiarClasesImagenes();

        this.imagenPanel.getStyleClass().add("panel");
        this.imagenCalendario.getStyleClass().add("calendario");
        this.imagenSalir.getStyleClass().add("cerrar");
        this.imagenContactos.getStyleClass().add("contactos");
        this.imagenProyectos.getStyleClass().add("proyectos");
        this.imagenTareas.getStyleClass().add("tareas");
        this.imagenNotas.getStyleClass().add("notas");
        this.imagenAjustes.getStyleClass().add("config");
    }
    public void iniciarPanel(){
        this.hboxPanel.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenPanel.getStyleClass().add("panelPresionado");

    }
    public void limpiarClasesImagenes(){
        this.imagenPanel.getStyleClass().clear();
        this.imagenCalendario.getStyleClass().clear();
        this.imagenSalir.getStyleClass().clear();
        this.imagenContactos.getStyleClass().clear();
        this.imagenProyectos.getStyleClass().clear();
        this.imagenTareas.getStyleClass().clear();
        this.imagenNotas.getStyleClass().clear();
        this.imagenAjustes.getStyleClass().clear();
    }

}
