/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import ClassandModel.Conexion;
import ClassandModel.Lector;
import ClassandModel.Prestamo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauri
 */
public class LectorData {

    private Connection con = null;
    private Conexion conexion;

    public LectorData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion en Prestamo Data: " + ex.getMessage());
        }
    }

    public void registrarLector(Lector lector) {
        String sql = "INSERT into lector(dni,nombre,apellido, fechaNac, estado) VALUES (?,?,?,?,?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lector.getDni());
            ps.setString(2, lector.getNombre());
            ps.setString(3, lector.getApellido());
            ps.setDate(4, Date.valueOf(lector.getFechaNac()));
            ps.setString(5, lector.getEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                lector.setIdLector(rs.getInt(1));
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion en registrarLector: " + ex.getMessage());
        }
    }

    public Lector BuscarLector(int id) {
        Lector lector = null;
        String sql = "SELECT * FROM lector WHERE idLector =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setDni(rs.getInt("dni"));
                lector.setNombre(rs.getString("nombre"));
                lector.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                lector.setEstado(rs.getString("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar Lector: " + ex.getMessage());
        }
        return lector;

    }

    public Lector BuscarXdni(int dni) {
        Lector lector = null;
        String sql = "SELECT * FROM lector WHERE dni =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, dni);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lector = new Lector();
                lector.setIdLector(rs.getInt("idLector"));
                lector.setDni(rs.getInt("dni"));
                lector.setNombre(rs.getString("nombre"));
                
                lector.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                lector.setEstado(rs.getString("estado"));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar Lector por dni: " + ex.getMessage());
        }
        return lector;

    }

}
