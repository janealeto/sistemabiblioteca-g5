/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassandModel;

/**
 *
 * @author Mauri
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauri
 */
public class PrestamoData {
     private Connection con =null;
      private Conexion conexion; 
      
      public PrestamoData(Conexion conexion){
        try {
            this.conexion= conexion;
            con= conexion.getConexion();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion en Prestamo Data: "+ex.getMessage());
        }
    }
      
      public List<Prestamo> PrestamosxFecha(){
          List<Prestamo> prestamos= new ArrayList<>();
          Prestamo prestamo;
         
          String sql="SELECT * FROM `prestamo` WHERE `fechaCre`";
          
           try {
            
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           // ps.setDate(1,Date.valueOf(fechaCre));
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()){
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                prestamo.setFechaExp(rs.getDate("fechaExp").toLocalDate());
                prestamo.setEstado(rs.getString("estado"));
                LectorData ld= new LectorData(conexion);
                Lector lector = ld.BuscarLector(rs.getInt("idLector"));
                prestamo.setLector(lector);
                LibroData lb = new LibroData(conexion);
                Libros libro = lb.BuscarLector(rs.getInt("idEjemplar"));
                prestamo.setLibros(libro);
                
                
                
                
                prestamos.add(prestamo);
            }
            ps.close();
    }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en obtener lista"+ex.getMessage());
    }
           return prestamos;
      }
}
      