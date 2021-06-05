/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import datas.AutorData;
import datas.LibroData;
import datas.LectorData;
import datas.EjemplarData;
import ClassandModel.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauri
 */
public class SistemaBibliotecaG5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion conexion= null;
       try{
           conexion = new Conexion();
           
           
       }catch(ClassNotFoundException ex){
           JOptionPane.showMessageDialog(null, "Error de driver");
     }
        Autor a1 = new Autor(37639823,"Federico","Lorca",LocalDate.of(1978, 8, 8));
        AutorData ad = new AutorData(conexion);
        //ad.agregarAutor(a1);

        LectorData ld = new LectorData(conexion);
        Lector le = ld.BuscarXdni(37639812);
        
//        ld.registrarLector(le);
        
        Libro l1 = new Libro("Libro de la selva","educativo","Larreus",1999,"Nuevo",a1);
        Libro l2 = new Libro("Libro del sexo","educativo","Larreus",2001,"Nuevo",a1);
        LibroData lb = new LibroData(conexion);
        //lb.agregrarLibro(l2);
          
          System.out.println(lb.BuscarXnombre("Libro"));
        
        Ejemplar ej = new Ejemplar("Nuevo",l1);
        EjemplarData ed = new EjemplarData(conexion);
//        ed.agregarEjemplar(ej);
        
//        Prestamo p1 = new Prestamo (LocalDate.of(2001, 1, 10),LocalDate.of(2001, 2, 10),"prestado",le,ej, null);
//        PrestamoData pd = new PrestamoData(conexion);
//        pd.registrarPrestamo(p1);
        
        
        //AUTOR
        //ad.agregarAutor(a1);
       
//        List<Prestamo> la= pd.PrestamosxFecha(LocalDate.of(2021,06, 02));
//        
//        for (Prestamo a: la){
//            System.out.println(a);
//        }
        //ad.borrarAutor(1);
        
       //LIBRO
       // 
       
       //LECTOR
       
       
       //PRESTAMO
       //registrarPrestamo
       
       
       
       
    }
}
    
    

