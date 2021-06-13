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
import datas.MultaData;
import datas.PrestamoData;
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
        Conexion conexion = null;
        try {
            conexion = new Conexion();

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error de driver");
        }
        
        //autor--------------------------------------------------
        Autor a1 = new Autor(37639823, "Federico", "Lorca", LocalDate.of(1978, 8, 8));
        AutorData ad = new AutorData(conexion);
        
        //ad.agregarAutor(a1);
        ad.actualizarAutor(a1);
        ad.buscarAutor(0);
        
      //ad.borrarAutor(1);
      
        LectorData ld = new LectorData(conexion);
//        Lector le = ld.BuscarXdni(34677931);
//        ld.registrarLector(le);

        
       // a1 = new Autor(9,37639823, "Federico", "Lorca", LocalDate.of(1978, 8, 8));
       // a1.setNombre("Lautaro");
       // ad.actualizarAutor(a1); //para actualizar sobre escribir el constructor con id de autor
       
        //ad.borrarAutor(id de autor al momento de borrar);
        
        //Libro---------------------------------------------------
        LibroData lb = new LibroData(conexion);

        Libro l1 = new Libro("Libro de la selva", "educativo", "Larreus", 1999, "Nuevo", a1);
        Libro l2 = new Libro("Libro de idioma", "educativo", "Larreus", 2001, "Nuevo", a1);

        
        //lb.agregrarLibro(l2);
//        l1 = lb.BuscarXnombre("Libro").get(1);
       //-----  lb.BuscarLibro(0);
        Ejemplar ej = new Ejemplar("Nuevo", l1);
        EjemplarData ed = new EjemplarData(conexion);
//        ed.agregarEjemplar(ej);
//        ej = ed.buscarEjemplarXLibro(l1.getIdLibro());
        ed.buscarEjemplar(0);
        
//        ed.borrarEjemplar(0);

        MultaData md = new MultaData(conexion);
//        Multa m = md.registrarMulta();
        System.out.println(md.multasDentroDelMesDeLaConsulta(LocalDate.of(2021, 6, 10)));

        
       // lb.agregrarLibro(l1); //agregar libro
       // lb.agregrarLibro(l2); 
       
       // System.out.println( lb.BuscarLibro(18));
       
        /* List<Libro>  lbb =  lb.BuscarXnombre("libro de idioma"); //mostrar libro
         for(Libro a : lbb){
             System.out.println(a);
         }*/
        
        //Ejemplar-----------------------------------------------
         
         l1 = new Libro(18,"Libro de la selva", "educativo", "Larreus", 1999, "Nuevo", a1);
         
         
        // ed.agregarEjemplar(ej);
        
        // System.out.println(ed.buscarEjemplarXLibro(18));
        
        // ed.borrarEjemplar(//id de ejemplar a borrar);
        
        
         //lector---------------------------------------------
         
         Lector le= new Lector(23456889,"Maximo","Dindon",LocalDate.of(1995,06,02),"activo");
         
        // ld.registrarLector(le);
        
        //le= new Lector(8,23456889,"Maximo","Dindon",LocalDate.of(1995,06,02),"activo");
       // le.setNombre("Leonardo");
       
        //System.out.println(ld.BuscarXdni(23456889));
        
        //Multa-------------------------------------------------
         
         //Multa m = md.registrarMulta();
         
        // System.out.println(md.multasDentroDelMesDeLaConsulta(LocalDate.of(2021, 6, 8))); //tira error
          
        //Prestamo----------------------------------------------
        PrestamoData pd = new PrestamoData(conexion);
//         pd.registrarPrestamo(p1);
//         Prestamo p1 = pd.buscarPrestamo(4);
//         p1.setEstado("activo");
//         p1.setMultas(m);
//         pd.actualizarPrestamo(p1);
//         System.out.println (pd.lectoresQueSeLesVencioElPrestamo());
//        List<Prestamo> la= pd.PrestamosxFecha(LocalDate.of(2021,06, 02));
//        
//        for (Prestamo a: la){
//            System.out.println(a);
//        }
//         System.out.println(pd.prestamosVigentesXlector(7));//del prestamo, de lector, obtengo el idLector
        le= new Lector(8,23456889,"Maximo","Dindon",LocalDate.of(1995,06,02),"activo"); //sobre escribir lector para tomar el id 
        ej=  new Ejemplar(10,"Nuevo", l1); //sobre escribir ejemplar para que tome el id de ejemplar
        Prestamo p1 = new Prestamo (LocalDate.of(2001, 1, 10),LocalDate.of(2001, 2, 10),"prestado", le, ej, null);
        
        
       // pd.registrarPrestamo(p1);
       
      /*  List<Prestamo> la= pd.PrestamosxFecha(LocalDate.of(2021,06, 8));
       
        for (Prestamo a: la){
           System.out.println(a);
       }*/
      
      
     // p1 = new Prestamo (8,LocalDate.of(2001, 1, 10),LocalDate.of(2001, 2, 10),"Usado", le, ej, null); //Sobre escribir el prestamo
     // pd.actualizarPrestamo(p1);
  
     
     /*  List<Prestamo> pe= pd.prestamosVigentesXlector(8);
       for (Prestamo a: pe){
           System.out.println(a);
     */
     /*
      List<Lector> pp =pd.lectoresQueSeLesVencioElPrestamo();
      for (Lector a : pp){
          System.out.println(a);
      }*/
    }
}

