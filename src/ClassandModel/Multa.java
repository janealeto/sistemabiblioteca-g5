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
    private String estado;
    // para sacar id lector

    private int idMulta;

    public Multa() {
    }

    public Multa(LocalDate fechaCre, LocalDate fechaExp, String estado, int idMulta) {
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.estado = estado;

        this.idMulta = idMulta;
    }

    public Multa(LocalDate fechaCre, LocalDate fechaExp, String estado) {
        this.fechaCre = fechaCre;
        this.fechaExp = fechaExp;
        this.estado = estado;

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

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    @Override
    public String toString() {
        return " Tiene Multa desde la fecha " + fechaCre + " hasta la fecha " + fechaExp;
    }

}
