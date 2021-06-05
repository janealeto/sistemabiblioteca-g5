/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import ClassandModel.Conexion;
import ClassandModel.Multa;
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
public class MultaData {
     private Connection con =null;
    private Conexion conexion; 
    
    public MultaData(Conexion conexion){
         try {
            this.conexion= conexion;
            con= conexion.getConexion();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion en Multa Data: "+ex.getMessage());
        }
    }
    public Multa buscarMulta(int id){
         Multa multa= null;
        String sql = "SELECT * FROM multa WHERE idMulta =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                multa = new Multa();
                multa.setIdMulta(rs.getInt("idMulta"));
                multa.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                multa.setFechaExp(rs.getDate("fechaExp").toLocalDate());
            }
             }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en Buscar multa "+ex.getMessage());       
    }
        return multa;
}
}
