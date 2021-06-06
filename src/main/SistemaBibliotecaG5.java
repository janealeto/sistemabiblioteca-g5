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
        
        
        Autor a1 = new Autor(37639823, "Federico", "Lorca", LocalDate.of(1978, 8, 8));
        AutorData ad = new AutorData(conexion);
        //ad.agregarAutor(a1);

//        List<Prestamo> la= pd.PrestamosxFecha(LocalDate.of(2021,06, 02));
//        
//        for (Prestamo a: la){
//            System.out.println(a);
//        }
        //ad.borrarAutor(1);
        LectorData ld = new LectorData(conexion);
//        Lector le = ld.BuscarXdni(34677931);
//        ld.registrarLector(le);

        Libro l1 = new Libro("Libro de la selva", "educativo", "Larreus", 1999, "Nuevo", a1);
        Libro l2 = new Libro("Libro de idioma", "educativo", "Larreus", 2001, "Nuevo", a1);
        LibroData lb = new LibroData(conexion);
        //lb.agregrarLibro(l2);

//        l1 = lb.BuscarXnombre("Libro").get(1);
        Ejemplar ej = new Ejemplar("Nuevo", l1);
        EjemplarData ed = new EjemplarData(conexion);
//        ed.agregarEjemplar(ej);
//        ej = ed.buscarEjemplarXLibro(l1.getIdLibro());

        MultaData md = new MultaData(conexion);
//        Multa m = md.registrarMulta();
        System.out.println(md.multasDentroDelMesDeLaConsulta(LocalDate.of(2021, 6, 10)));
        
//        Prestamo p1 = new Prestamo (LocalDate.of(2001, 1, 10),LocalDate.of(2001, 2, 10),"prestado", le, ej, null);
        PrestamoData pd = new PrestamoData(conexion);
//         pd.registrarPrestamo(p1);
//         Prestamo p1 = pd.buscarPrestamo(4);
//         p1.setEstado("activo");
//         p1.setMultas(m);
//         pd.actualizarPrestamo(p1);
//         System.out.println (pd.lectoresQueSeLesVencioElPrestamo());

//         System.out.println(pd.prestamosVigentesXlector(7));//del prestamo, de lector, obtengo el idLector
    }
}
