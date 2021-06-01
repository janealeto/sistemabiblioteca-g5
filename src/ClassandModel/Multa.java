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
public class Multa {
    private LocalDate fechaCre;
    private LocalDate fechaExp;
    private Lector lector; // para sacar id lector
    private Prestamo prestamo; // para sacar id prestamo
    private int idMulta;

    public Multa() {
    }

    public Multa(LocalDate fechaCre, LocalDate fechaExp, Lector lector, Prestamo prestamo, int idMulta) {
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.lector = lector;
        this.prestamo = prestamo;
        this.idMulta = idMulta;
    }

    public Multa(LocalDate fechaCre, LocalDate fechaExp, Lector lector, Prestamo prestamo) {
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.lector = lector;
        this.prestamo = prestamo;
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

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }
    
    
}
