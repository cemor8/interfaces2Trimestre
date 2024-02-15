package modelo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Data {
    private ArrayList<Nota> notas;
    private ArrayList<Proyecto> proyectos;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Tarea> tareas;
    private Usuario currentUser;
    private ListaControladores listaControladores;
    private boolean oscuro = true;

    public Data() {
        this.notas = new ArrayList<>();
        this.proyectos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.tareas = new ArrayList<>();
        this.listaControladores = new ListaControladores();
        LocalDate hoy = LocalDate.now();
        LocalDate manana = hoy.plusDays(1);
        Usuario usuario = new Usuario("carlosmoraisblanco@gmail.com","Carlos","Morais","12q12q12",
                "Hola me llamo Carlos Morais","Informático","src/main/resources/images/usuarios/persona.png");

        Tarea tarea = new Tarea("Tarea de prueba","Prueba","Descripcion de tarea de prueba",
                Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant()),Date.from(manana.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "src/main/resources/images/proyectos/vistaCadaProyecto/fondoProyectoPrueba.png",new ArrayList<>(),new ArrayList<>(List.of(usuario)));
        Nota nota = new Nota("Nota de prueba","Descripcion de nota de prueba", Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                usuario);
        Proyecto proyecto = new Proyecto("Proyecto de prueba","Cliente de prueba","src/main/resources/images/proyectos/vistaCadaProyecto/fondoProyectoPrueba.png",
                "Estado de prueba","Descripcion de proyecto de prueba",Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(manana.atStartOfDay(ZoneId.systemDefault()).toInstant()),usuario,new ArrayList<>(),new ArrayList<>(),new ArrayList<>(List.of(usuario)));

        this.proyectos.add(proyecto);
        this.notas.add(nota);
        this.tareas.add(tarea);
        this.usuarios.add(usuario);


    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
    }

    public void setProyectos(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public ListaControladores getListaControladores() {
        return listaControladores;
    }

    public void setListaControladores(ListaControladores listaControladores) {
        this.listaControladores = listaControladores;
    }

    public boolean isOscuro() {
        return oscuro;
    }

    public void setOscuro(boolean oscuro) {
        this.oscuro = oscuro;
    }
}