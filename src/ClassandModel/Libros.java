/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassandModel;

/**
 *
 * @author Mauri
 */
public class Libros {
    private int idLibro;
    private String nombre;
    private String tipo;
    private String editorial;
    private int anio;
    private String ejeEstado;
    private Autor autor; //para sacar el id del autor

    public Libros() {
    }

    public Libros(int idLibro, String nombre, String tipo, String editorial, int anio, String ejeEstado, Autor autor) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.tipo = tipo;
        this.editorial = editorial;
        this.anio = anio;
        this.ejeEstado = ejeEstado;
        this.autor = autor;
    }

    public Libros(String nombre, String tipo, String editorial, int anio, String ejeEstado, Autor autor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.editorial = editorial;
        this.anio = anio;
        this.ejeEstado = ejeEstado;
        this.autor = autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getEjeEstado() {
        return ejeEstado;
    }

    public void setEjeEstado(String ejeEstado) {
        this.ejeEstado = ejeEstado;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libros" + "idLibro=" + idLibro + ", nombre=" + nombre + ", tipo=" + tipo + ", editorial=" + editorial + ", anio=" + anio + ", ejeEstado=" + ejeEstado + ", autor= " + autor.getNombre();
    }
    
    
}
