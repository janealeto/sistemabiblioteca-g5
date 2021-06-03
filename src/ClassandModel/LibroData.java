/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassandModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauri
 */
public class LibroData {
     private Connection con =null;
      private Conexion conexion; 
    public LibroData(Conexion conexion){
        try {
            this.conexion= conexion;
            con= conexion.getConexion();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion en Prestamo Data: "+ex.getMessage());
        }
    }
    
    public void agregrarLibro(Libros libro){
        String sql="INSERT INTO `libros`(`nombre`, `tipo`, `editorial`, `anio`, `ejeEstado`, `idAutor`) VALUES(?,?,?,?,?,?)";
        try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);   
            ps.setString(1,libro.getNombre());
            ps.setString(2, libro.getTipo());
            ps.setString(3, libro.getEditorial());
            ps.setInt(4, libro.getAnio());
            ps.setString(5, libro.getEjeEstado());
            ps.setInt(6, libro.getAutor().getIdAutor());
             ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
             if(rs.next()){
            libro.setIdLibro(rs.getInt(1));
            }
        ps.close();
        JOptionPane.showMessageDialog(null,"Libro Registrado");        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en agregar libro"+ex.getMessage());
    }
    }
    

    
    
        public Libros BuscarLibro(int id){
        Libros libros= null;
        String sql = "SELECT * FROM libros WHERE idLibro =?;";
         try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
           
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                libros = new Libros();
                libros.setIdLibro(rs.getInt("idLibro"));
                libros.setNombre(rs.getString("nombre"));
                libros.setTipo(rs.getString("tipo"));
                libros.setEditorial(rs.getString("editorial"));
                libros.setAnio(rs.getInt("anio"));
                libros.setEjeEstado(rs.getString("ejeEstado"));
                AutorData ad= new AutorData(conexion);
                Autor a1= ad.buscarAutor(rs.getInt("idAutor"));
                libros.setAutor(a1);
                
            }
            ps.close();
         } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en Buscar Libro: "+ex.getMessage());
    }
    return libros;
}
}
