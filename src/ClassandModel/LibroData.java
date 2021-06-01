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
        public Libros BuscarLector(int id){
        Libros libros= null;
        String sql = "SELECT * FROM libros WHERE idEjemplar =?;";
         try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
           
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                libros = new Libros();
                libros.setIdEjemplar(rs.getInt("idEjemplar"));
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
            JOptionPane.showMessageDialog(null, "Error en Buscar Lector: "+ex.getMessage());
    }
    return libros;
}
}
