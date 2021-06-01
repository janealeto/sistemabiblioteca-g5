/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
        Autor a1= new Autor(37639812,"Mauricio","Arg",LocalDate.of(1993, 11, 07));
        AutorData ad= new AutorData(conexion);
        PrestamoData pd = new PrestamoData(conexion);
        //ad.AgregarAutor(a1);
       
        List<Prestamo> la= pd.PrestamosxFecha();
        
        for (Prestamo a: la){
            System.out.println(a);
        }
        //ad.borrarAutor(1);
    }
}
    
    

