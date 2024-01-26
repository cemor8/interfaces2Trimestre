package model;

public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private String fecha;
    private String imagen;

    public Libro(String titulo, String autor, String isbn2, String fecha) {
        this.titulo = titulo;
        this.autor = autor;
        isbn = isbn2;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getFecha() {
        return fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn2) {
        isbn = isbn2;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
