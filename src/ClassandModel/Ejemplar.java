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
public class Ejemplar {
    private int idEjemplar;
    private String estado;
    private Libro libro;

    public Ejemplar() {
    }

    public Ejemplar(int idEjemplar, String estado, Libro libro) {
        this.idEjemplar = idEjemplar;
        this.estado = estado;
        this.libro = libro;
    }

    public Ejemplar(String estado, Libro libro) {
        this.estado = estado;
        this.libro = libro;
    }

    public int getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    
    
}
