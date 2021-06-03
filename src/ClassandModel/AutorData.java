/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassandModel;

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
public class AutorData {
     private Connection con =null;
      private Conexion conexion; 
      
      public AutorData(Conexion conexion){
        try {
            this.conexion= conexion;
            con= conexion.getConexion();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error de conexion en Autor Data: "+ex.getMessage());
        }
    }
      
    public void agregarAutor(Autor autor){
        String sql="INSERT INTO autor (dni,nombre,nacio,fechaNac) VALUES(?,?,?,?)";
        try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);          
            ps.setInt(1, autor.getDni());
            ps.setString(2, autor.getNombre());
            ps.setString(3, autor.getNacio());
            ps.setDate(4, Date.valueOf(autor.getFechaNac()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
            autor.setIdAutor(rs.getInt(1));
        }
        ps.close();
        JOptionPane.showMessageDialog(null,"Autor Registrado");        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Dni duplicado, ingrese otro"+ex.getMessage());
    }  
}
    
    public List<Autor> obtenerAutores(){
        Autor autor;
        List<Autor> autores= new ArrayList<>();
        String sql="SELECT * FROM autor";
        
        try {
            
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                autor = new Autor();
                autor.setIdAutor(rs.getInt("idAutor"));
                autor.setDni(rs.getInt("dni"));
                autor.setNombre(rs.getString("nombre"));
                autor.setNacio(rs.getString("nacio"));
                autor.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                autores.add(autor);
            }
            ps.close();
    }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en obtener lista"+ex.getMessage());
    }
        return autores;
    }
    public void borrarAutor(int id){
        String sql="DELETE FROM autor WHERE idAutor=?";
        try{
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        JOptionPane.showMessageDialog(null, "Borrado con exito");
        }catch (SQLException ex) {
            System.out.println("Error Al Borrar Autor" + ex.getMessage());
        }
    }
    public void actualizarAutor(Autor autor){
        String sql="UPDATE autor SET dni=? , nombre=?, nacio=? ,fechaNac=? WHERE idAutor=?";
        try{     PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
             ps.setInt(1, autor.getDni());
            ps.setString(2, autor.getNombre());
            ps.setString(3, autor.getNacio());
            ps.setDate(4, Date.valueOf(autor.getFechaNac()));
            ps.setInt(5, autor.getIdAutor());
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(null, "Actualizado con exito");
        }catch (SQLException ex) {
            System.out.println("Error Al Actualizar Autor" + ex.getMessage());
    }
}
    public Autor buscarAutor(int id){
        Autor autor=null;
        String sql = "SELECT * FROM autor WHERE idAutor =?;";
         try {
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
           
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                autor = new Autor();
                autor.setIdAutor(rs.getInt("idAutor"));
                autor.setDni(rs.getInt("dni"));
                autor.setNombre(rs.getString("nombre"));
                autor.setNacio(rs.getString("nacio"));
                autor.setFechaNac(rs.getDate("fechaNac").toLocalDate());
            }
         } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en Buscar Autor: "+ex.getMessage());
        }
    return autor;
    }
}
