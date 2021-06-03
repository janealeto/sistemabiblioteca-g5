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
public class Lector {
    private int idLector;
    private int dni;
    private String nombre;
    private String apelido;
    private LocalDate fechaNac;
    private boolean estado;

    public Lector() {
    }

    public Lector(int idLector, int dni, String nombre, String apelido, LocalDate fechaNac, boolean estado) {
        this.idLector = idLector;
        this.dni = dni;
        this.nombre = nombre;
        this.apelido = apelido;
        this.fechaNac = fechaNac;
        this.estado = estado;
    }

    public Lector(int dni, String nombre, String apelido, LocalDate fechaNac, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apelido = apelido;
        this.fechaNac = fechaNac;
        this.estado = estado;
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

   
    
}
