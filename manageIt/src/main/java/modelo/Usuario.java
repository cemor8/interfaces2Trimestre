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
    private ArrayList<Nota> notas;

    public Usuario(String correo, String nombre, String apellidos, String contraseña, String descripcion, String puesto, String rutaImagen, ArrayList<Usuario> contactos, ArrayList<Nota> notas) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contraseña = contraseña;
        this.descripcion = descripcion;
        this.puesto = puesto;
        this.rutaImagen = rutaImagen;
        this.contactos = contactos;
        this.notas = notas;
    }

    public void setContactos(ArrayList<Usuario> contactos) {
        this.contactos = contactos;
    }

    public ArrayList<Usuario> getContactos() {
        return contactos;
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

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
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
