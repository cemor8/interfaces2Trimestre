package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Tarea {
    private String nombre;
    private String estado;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaEntrega;
    private String rutaimagen;
    private ArrayList<Nota> notas;
    private ArrayList<Usuario> personasAsignadas;

    public Tarea(String nombre, String estado, String descripcion, Date fechaCreacion, Date fechaEntrega, String rutaimagen, ArrayList<Nota> notas, ArrayList<Usuario> personasAsignadas) {
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.rutaimagen = rutaimagen;
        this.notas = notas;
        this.personasAsignadas = personasAsignadas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public String getRutaimagen() {
        return rutaimagen;
    }

    public ArrayList<Nota> getNotas() {
        return notas;
    }

    public ArrayList<Usuario> getPersonasAsignadas() {
        return personasAsignadas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setRutaimagen(String rutaimagen) {
        this.rutaimagen = rutaimagen;
    }

    public void setNotas(ArrayList<Nota> notas) {
        this.notas = notas;
    }

    public void setPersonasAsignadas(ArrayList<Usuario> personasAsignadas) {
        this.personasAsignadas = personasAsignadas;
    }
}