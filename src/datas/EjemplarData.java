/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import ClassandModel.Conexion;
import ClassandModel.Ejemplar;
import ClassandModel.Libro;
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

    private Connection con = null;
    private Conexion conexion;

    public EjemplarData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion en Prestamo Data: " + ex.getMessage());
        }
    }

    public void agregarEjemplar(Ejemplar ejemplar) {
        String sql = "INSERT INTO ejemplar( estado, idLibro) VALUES (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ejemplar.getEstado());
            ps.setInt(2, ejemplar.getLibro().getIdLibro());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                ejemplar.setIdEjemplar(rs.getInt(1));
            }
            ps.close();
            JOptionPane.showMessageDialog(null, "Ejemplar Agregado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en ejemplar, ingrese otro" + ex.getMessage());
        }

    }

    public void borrarEjemplar(int id) {
        String sql = "DELETE FROM ejemplar WHERE idEjemplar=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Borrado con exito");
        } catch (SQLException ex) {
            System.out.println("Error Al Borrar Ejemplar" + ex.getMessage());
        }
    }

    public void actualizarEjemplar(Ejemplar ejemplar) {
        String sql = "UPDATE ejemplar SET estado=?,idLibro=? WHERE idEjemplar=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ejemplar.getEstado());
            ps.setInt(2, ejemplar.getLibro().getIdLibro());
            ps.setInt(3, ejemplar.getIdEjemplar());
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Ejemplar Actualizado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro en ejemplar, ingrese otro" + ex.getMessage());
        }
    }

    public Ejemplar buscarEjemplarXLibro(int idLibro) {
        Ejemplar ejemplar = null;
        String sql = "SELECT * FROM ejemplar WHERE idLibro =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ejemplar = new Ejemplar();
                ejemplar.setIdEjemplar(rs.getInt("idEjemplar"));
                ejemplar.setEstado(rs.getString("estado"));
                LibroData lb = new LibroData(conexion);
                Libro libro = lb.buscarLibro(rs.getInt("idLibro"));
                ejemplar.setLibro(libro);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar ejemplar " + ex.getMessage());

        }

        return ejemplar;
    }

    public Ejemplar buscarEjemplar(int id) {
        Ejemplar ejemplar = null;
        String sql = "SELECT * FROM ejemplar WHERE idEjemplar =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ejemplar = new Ejemplar();
                ejemplar.setIdEjemplar(rs.getInt("idEjemplar"));
                ejemplar.setEstado(rs.getString("estado"));
                LibroData lb = new LibroData(conexion);
                Libro libro = lb.buscarLibro(rs.getInt("idLibro"));
                ejemplar.setLibro(libro);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar ejemplar " + ex.getMessage());

        }
        return ejemplar;
    }
}
