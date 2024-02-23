package modelo;

import java.util.ArrayList;

public class Usuario {
    private String correo;
    private String nombre;
    private String apellidos;
    private String contraseña;
    private String descripcion;
    private String puesto;
    private String rutaImagen;
    private ArrayList<Usuario> contactos;
    private ArrayList<Tarea> tareas;
    private ArrayList<Nota> notas;

    public Usuario(String correo, String nombre, String apellidos, String contraseña, String descripcion, String puesto, String rutaImagen, ArrayList<Usuario> contactos, ArrayList<Tarea> tareas) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contraseña = contraseña;
        this.descripcion = descripcion;
        this.puesto = puesto;
        this.rutaImagen = rutaImagen;
        this.contactos = contactos;
        this.tareas = tareas;
    }

    public void setContactos(ArrayList<Usuario> contactos) {
        this.contactos = contactos;
    }

    public ArrayList<Usuario> getContactos() {
        return contactos;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Tarea> tareas) {
        this.tareas = tareas;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
