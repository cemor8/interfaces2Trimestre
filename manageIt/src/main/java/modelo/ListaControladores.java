package modelo;

import controller.ControllerContenedor;
import controller.ControllerMenuLateral;
import controller.ControllerMenuSuperior;

public class ListaControladores {
    private ControllerContenedor controllerContenedor;
    private ControllerMenuLateral controllerMenuLateral;
    private ControllerMenuSuperior controllerMenuSuperior;

    public ControllerContenedor getControllerContenedor() {
        return controllerContenedor;
    }

    public ControllerMenuLateral getControllerMenuLateral() {
        return controllerMenuLateral;
    }

    public void setControllerContenedor(ControllerContenedor controllerContenedor) {
        this.controllerContenedor = controllerContenedor;
    }

    public void setControllerMenuLateral(ControllerMenuLateral controllerMenuLateral) {
        this.controllerMenuLateral = controllerMenuLateral;
    }

    public void setControllerMenuSuperior(ControllerMenuSuperior controllerMenuSuperior) {
        this.controllerMenuSuperior = controllerMenuSuperior;
    }

    public ControllerMenuSuperior getControllerMenuSuperior() {
        return controllerMenuSuperior;
    }
}
