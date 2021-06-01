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
public class Autor {
    private int idAutor;
    private int dni;
    private String nombre;
    private String nacio;
    private LocalDate fechaNac;

    public Autor() {
    }

    public Autor(int dni, String nombre, String nacio, LocalDate fechaNac) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacio = nacio;
        this.fechaNac = fechaNac;
    }

    public Autor(int idAutor, int dni, String nombre, String nacio, LocalDate fechaNac) {
        this.idAutor = idAutor;
        this.dni = dni;
        this.nombre = nombre;
        this.nacio = nacio;
        this.fechaNac = fechaNac;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacio() {
        return nacio;
    }

    public void setNacio(String nacio) {
        this.nacio = nacio;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return  " Autor " + "Id: "+ idAutor+ "dni: " + dni + ", nombre: " + nombre + " nacionalidad: " + nacio;
    }
    
    
    
    
}
