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
public class LectorData {
    private Connection con =null;
      private Conexion conexion; 
    public LectorData(Conexion conexion){
        try {
            this.conexion= conexion;
            con= conexion.getConexion();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion en Prestamo Data: "+ex.getMessage());
        }
    }
    
    public Lector BuscarLector(int id){
        Lector lector= null;
        String sql = "SELECT * FROM lector WHERE idLector =?;";
         try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
           
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setDni(rs.getInt("dni"));
                lector.setNombre(rs.getString("nombre"));
                lector.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                lector.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
         } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en Buscar Lector: "+ex.getMessage());
    }
    return lector;
    
    
}
}



