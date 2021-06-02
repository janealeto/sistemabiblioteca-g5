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
public class EjemplarData {
    private Connection con =null;
    private Conexion conexion; 
    
    public EjemplarData(Conexion conexion){
         try {
            this.conexion= conexion;
            con= conexion.getConexion();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion en Prestamo Data: "+ex.getMessage());
        }
    }
    public Ejemplar buscarEjemplar(int id){
        Ejemplar ejemplar= null;
        String sql = "SELECT * FROM ejemplar WHERE idEjemplar =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                ejemplar = new Ejemplar();
                ejemplar.setIdEjemplar(rs.getInt("idEjemplar"));
                ejemplar.setEstado(rs.getString("estado"));
                LibroData lb = new LibroData(conexion);
                Libros libro = lb.BuscarLibro(rs.getInt("idLibro"));
                ejemplar.setLibro(libro);
            } 
            ps.close();

        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en Buscar ejemplar "+ex.getMessage());
       
    } return ejemplar;
}
}

