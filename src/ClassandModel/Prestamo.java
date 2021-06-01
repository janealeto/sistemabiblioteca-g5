/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassandModel;

import java.time.LocalDate;

/**
 *
 * @author Mauri
 */
public class Prestamo {
    private int idPrestamo;
    private LocalDate fechaCre;
    private LocalDate fechaExp;
    private String estado;
    private Lector lector; // p id lector
    private Libros libros; // para idEjemplar

    public Prestamo() {
    }

    public Prestamo(int idPrestamo, LocalDate fechaCre, LocalDate fechaExp, String estado, Lector lector, Libros libros) {
        this.idPrestamo = idPrestamo;
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.estado = estado;
        this.lector = lector;
        this.libros = libros;
    }

    public Prestamo(LocalDate fechaCre, LocalDate fechaExp, String estado, Lector lector, Libros libros) {
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.estado = estado;
        this.lector = lector;
        this.libros = libros;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public LocalDate getFechaCre() {
        return fechaCre;
    }

    public void setFechaCre(LocalDate fechaCre) {
        this.fechaCre = fechaCre;
    }

    public LocalDate getFechaExp() {
        return fechaExp;
    }

    public void setFechaExp(LocalDate fechaExp) {
        this.fechaExp = fechaExp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Prestamo " + "idPrestamo=" + idPrestamo + ", fechaCre=" + fechaCre + ", fechaExp=" + fechaExp + ", estado=" + estado + ", lector=" + lector.getNombre() + ", libros=" + libros ;
    }
    
    
}
