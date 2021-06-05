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
    private Ejemplar ejemplar; // para idLibro
    private Multa multas;
    public Prestamo() {               
    }

    public Prestamo(int idPrestamo, LocalDate fechaCre, LocalDate fechaExp, String estado, Lector lector, Ejemplar ejemplar,Multa multas) {
        this.idPrestamo = idPrestamo;
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.estado = estado;
        this.lector = lector;
        this.ejemplar = ejemplar;
        this.multas= multas;
    }

    public Prestamo(LocalDate fechaCre, LocalDate fechaExp, String estado, Lector lector, Ejemplar ejemplar,Multa multas) {
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.estado = estado;
        this.lector = lector;
        this.ejemplar = ejemplar;
        this.multas= multas;
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

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    

    public Multa getMultas() {
        return multas;
    }

    public void setMultas(Multa multas) {
        this.multas = multas;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", fechaCre=" + fechaCre + ", fechaExp=" + fechaExp + ", estado=" + estado + ", lector=" + lector.getNombre() + ", ejemplar=" + ejemplar.getLibro().getNombre() + ", multas=" + multas + '}';
    }

   
   

    
    
    
}
