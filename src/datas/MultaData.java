/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import ClassandModel.Conexion;
import ClassandModel.Lector;
import ClassandModel.Multa;
import ClassandModel.Prestamo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauri
 */
public class MultaData {

    private Connection con = null;
    private Conexion conexion;

    public MultaData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion en Multa Data: " + ex.getMessage());
        }
    }

    public Multa registrarMulta() {
        Multa multa = null;
        String sql = "INSERT into multa (fechaCre,fechaExp,estado) VALUES (?,?,?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setDate(1, Date.valueOf(LocalDate.now()));//agrego en multa la fecha de creacion del momento en que se genera
            ps.setDate(2, Date.valueOf(LocalDate.now().plusDays(2)));//le asigno la fecha de creacion actual + 2 dias
            ps.setString(3, "estado");
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                multa = new Multa();//creo el objeto
                multa.setIdMulta(rs.getInt("idMulta"));
            }

            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion en registrarMulta: " + ex.getMessage());
        }

        return multa;
    }

    public void actualizarMulta(Multa multa) {
        String sql = "UPDATE multa SET fechaExp=?,estado=? WHERE idMulta=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(multa.getFechaExp()));
            ps.setString(2, multa.getEstado());
            ps.setInt(3, multa.getIdMulta());
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Multa Actualizado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Multa" + ex.getMessage());
        }
    }

    public void anularMulta(int id) {
        String sql = "DELETE FROM multa WHERE idMulta=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Multa borrada");
        } catch (SQLException ex) {
            System.out.println("Error Al Borrar Multa" + ex.getMessage());
        }
    }

    public Multa buscarMulta(int id) {
        Multa multa = null;
        String sql = "SELECT * FROM multa WHERE idMulta =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                multa = new Multa();
                multa.setIdMulta(rs.getInt("idMulta"));
                multa.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                multa.setFechaExp(rs.getDate("fechaExp").toLocalDate());
                multa.setEstado(rs.getString("estado"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar multa " + ex.getMessage());
        }
        return multa;
    }

    public List<Lector> multasDentroDelMesDeLaConsulta(LocalDate fechaConsulta) {// le doy la opcion al usuario para que me de la fecha del mes que tuvo multa
        List<Lector> lectores = new ArrayList<>();

        String sql = "SELECT * FROM multa WHERE fechaCre BETWEEN ? AND ?;";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(fechaConsulta.withDayOfMonth(1)));//Seteo el primer dia del mes
            ps.setDate(2, Date.valueOf(fechaConsulta.withDayOfMonth(fechaConsulta.lengthOfMonth())));//1."whit..."que dia del mes quiero setear, 
            //2."lenght..." obtengo el tamaño del mes de la fecha que quiero obtener
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PrestamoData pd = new PrestamoData(conexion);

                Prestamo prestamo = pd.buscarPrestamoXidMulta(rs.getInt("idMulta"));//para obtener el id de multa

                LectorData ld = new LectorData(conexion);

                Lector lector = ld.BuscarLector(prestamo.getLector().getIdLector());//lo obtengo y lo guardo

                lectores.add(lector);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar multa dentro del mes de la consulta " + ex.getMessage());
        }
        return lectores;
    }
}
