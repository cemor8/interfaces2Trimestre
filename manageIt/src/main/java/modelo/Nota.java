package modelo;

import java.util.Date;

public class Nota {
    private String titulo;
    private String descripcion;
    private String rutaImagen;
    private Date fechaCreacion;
    private Usuario usuario;

    public Nota(String titulo, String descripcion, String rutaImagen, Date fechaCreacion, Usuario usuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", rutaImagen='" + rutaImagen + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", usuario=" + usuario +
                '}';
    }
}
