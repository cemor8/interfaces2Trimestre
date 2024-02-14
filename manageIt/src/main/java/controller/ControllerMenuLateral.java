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

import java.io.IOException;

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

    @FXML
    void cerrar(MouseEvent event) {

    }

    @FXML
    void mostrarCalendario(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxCalendario.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenCalendario.getStyleClass().add("calendarioPresionado");
    }

    @FXML
    void mostrarConfig(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxConfiguracion.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenAjustes.getStyleClass().add("configPresionado");
    }

    @FXML
    void mostrarContacto(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxContactos.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenContactos.getStyleClass().add("contactosPresionado");
    }

    @FXML
    void mostrarNotas(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxNotas.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenNotas.getStyleClass().add("notasPresionado");
    }

    @FXML
    void mostrarPanel(MouseEvent event) throws IOException {
        System.out.println("hola");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/panel.fxml"), CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerPanel controllerPanel = fxmlLoader.getController();
        controllerPanel.recibirData(this.data);
        this.data.getListaControladores().getControllerContenedor().rellenarContenido(root);

        this.reiniciarHbox();
        this.iniciarPanel();
    }


    @FXML
    void mostrarProyectos(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxProyectos.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenProyectos.getStyleClass().add("proyectosPresionado");
    }

    @FXML
    void mostrarTareas(MouseEvent event) {
        this.reiniciarHbox();
        this.hboxTareas.pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"),true);
        this.imagenTareas.getStyleClass().add("tareasPresionado");
    }

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
