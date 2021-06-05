/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

/**
 *
 * @author Mauri
 */
import ClassandModel.Conexion;
import ClassandModel.Ejemplar;
import ClassandModel.Lector;
import ClassandModel.Multa;
import ClassandModel.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
      
      public void registrarPrestamo (Prestamo prestamo){
          String sql = "INSERT into prestamo(estado,fechaCre,fechaExp, idEjemplar, idLector, idMulta) VALUES (?,?,?,?,?,?);";
          
              try {
                  PreparedStatement ps = con.prepareStatement (sql,Statement.RETURN_GENERATED_KEYS);
                  ps.setString(1,prestamo.getEstado());
                  ps.setDate(2, Date.valueOf(prestamo.getFechaCre()));
                  ps.setDate(3, Date.valueOf(prestamo.getFechaExp()));
                  ps.setInt(4,prestamo.getEjemplar().getIdEjemplar());
                  ps.setInt(5,prestamo.getLector().getIdLector());
                 
                  if(prestamo.getMultas()!=null){
                      ps.setInt(6,prestamo.getMultas().getIdMulta());
                      
                  }else{
                      ps.setNull(6, java.sql.Types.INTEGER);
                  }
                 ps.executeUpdate();
                  
                  
                  ResultSet rs = ps.getGeneratedKeys();
                  
                  if(rs.next()){
                  prestamo.setIdPrestamo(rs.getInt(1));
                  }
                  
                  ps.close();
                          
              } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, "Error de conexion en registrarPrestamo: "+ex.getMessage());
              }
          }
      
      public Prestamo buscarPrestamo(int id){
         Prestamo prestamo= null;
        String sql = "SELECT * FROM prestamo WHERE idPrestamo =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                prestamo.setFechaExp(rs.getDate("fechaExp").toLocalDate());
                
            }
             }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en Buscar multa "+ex.getMessage());       
    }
        return prestamo;
}
      
      
      
      
      public List<Prestamo> PrestamosxFecha(LocalDate fechaCre){
          List<Prestamo> prestamos= new ArrayList<>();
          Prestamo prestamo;
         
          String sql="SELECT * FROM `prestamo` WHERE fechaCre=?";
          
           try {
            
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1,Date.valueOf(fechaCre));
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
                EjemplarData ed = new EjemplarData(conexion);
                Ejemplar ejemplar = ed.buscarEjemplar(rs.getInt("idEjemplar"));
                prestamo.setEjemplar(ejemplar);
                MultaData md = new MultaData(conexion);
                Multa multa= md.buscarMulta(rs.getInt("idMulta"));
                prestamo.setMultas(multa);
                
                
                prestamos.add(prestamo);
            }
            ps.close();
    }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en obtener lista prestamos por fecha "+ex.getMessage());
    }
           return prestamos;
      }
}
      