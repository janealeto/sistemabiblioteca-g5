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

    private Connection con = null; //para buscar la info en la base de datos, como un puente usando los datos de conexion
    private Conexion conexion; //para saber a que base datos conectarme

    public PrestamoData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion en Prestamo Data: " + ex.getMessage());
        }
    }

    public void registrarPrestamo(Prestamo prestamo) {
        String sql = "INSERT into prestamo(estado,fechaCre,fechaExp, idEjemplar, idLector, idMulta) VALUES (?,?,?,?,?,?);";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prestamo.getEstado());
            ps.setDate(2, Date.valueOf(LocalDate.now()));//agrego en prestamo la fecha de creacion del momento en que se genera
            ps.setDate(3, Date.valueOf(LocalDate.now().plusDays(30)));//le asigno la fecha de creacion actual + 30 dias
            ps.setInt(4, prestamo.getEjemplar().getIdEjemplar());
            ps.setInt(5, prestamo.getLector().getIdLector());

            if (prestamo.getMultas() != null) {
                ps.setInt(6, prestamo.getMultas().getIdMulta());

            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                prestamo.setIdPrestamo(rs.getInt(1));
            }

            ps.close();
            JOptionPane.showMessageDialog(null, "Prestamo Registrado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion en registrarPrestamo: " + ex.getMessage());
        }
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        String sql = "UPDATE prestamo SET estado=?, idMulta=? WHERE idPrestamo=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prestamo.getEstado());

            if (prestamo.getMultas() != null) {//Pregunta si el objt multa es != de null sabe que es un obj y 
                //puede intentar obtener el id de multa y si no setea un integer null

                ps.setInt(2, prestamo.getMultas().getIdMulta());

            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            ps.setInt(3, prestamo.getIdPrestamo());
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(null, "Prestamo Actualizado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Prestamo" + ex.getMessage());
        }
    }

    public void anularPrestamo(int id) {
        String sql = "DELETE FROM prestamo WHERE idPrestamo=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Prestamo borrado");
        } catch (SQLException ex) {
            System.out.println("Error Al anular prestamo" + ex.getMessage());
        }
    }

    public Prestamo buscarPrestamo(int id) {
        Prestamo prestamo = null;
        String sql = "SELECT * FROM prestamo WHERE idPrestamo =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                prestamo.setFechaExp(rs.getDate("fechaExp").toLocalDate());
                prestamo.setEstado(rs.getString("estado"));

                LectorData ld = new LectorData(conexion);//creo el ld para conectarme a la bd para obtener la info del lector
                prestamo.setLector(ld.BuscarLector(rs.getInt("idLector")));
                EjemplarData ed = new EjemplarData(conexion);//entidades externas
                prestamo.setEjemplar(ed.buscarEjemplar(rs.getInt("idEjemplar")));
                Integer idMulta = rs.getInt("idMulta");
                MultaData md = new MultaData(conexion);
                prestamo.setMultas(idMulta == null ? null : md.buscarMulta(idMulta));//null solo lo permiten los objetos "Integer" no los primitivos "int"
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar prestamo " + ex.getMessage());
        }
        return prestamo;
    }

    public List<Prestamo> PrestamosxFecha(LocalDate fechaCre) {
        List<Prestamo> prestamos = new ArrayList<>();
        Prestamo prestamo;

        String sql = "SELECT * FROM `prestamo` WHERE fechaCre=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(fechaCre));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                prestamo.setFechaExp(rs.getDate("fechaExp").toLocalDate());
                prestamo.setEstado(rs.getString("estado"));
                LectorData ld = new LectorData(conexion);
                Lector lector = ld.BuscarLector(rs.getInt("idLector"));
                prestamo.setLector(lector);
                EjemplarData ed = new EjemplarData(conexion);
                Ejemplar ejemplar = ed.buscarEjemplar(rs.getInt("idEjemplar"));
                prestamo.setEjemplar(ejemplar);
                Integer idMulta = rs.getInt("idMulta");
                MultaData md = new MultaData(conexion);
                prestamo.setMultas(idMulta == null ? null : md.buscarMulta(idMulta));

                prestamos.add(prestamo);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en obtener lista prestamos por fecha " + ex.getMessage());
        }
        return prestamos;
    }

    public List<Prestamo> prestamosVigentesXlector(int id) {
        List<Prestamo> prestamos = new ArrayList<Prestamo>();

        String sql = "SELECT * FROM prestamo WHERE idLector=? AND estado='activo';";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                prestamo.setFechaExp(rs.getDate("fechaExp").toLocalDate());
                prestamo.setEstado(rs.getString("estado"));
                LectorData ld = new LectorData(conexion);//creo el ld para conectarme a la bd para obtener la info del lector
                prestamo.setLector(ld.BuscarLector(rs.getInt("idLector")));
                EjemplarData ed = new EjemplarData(conexion);//entidades externas
                prestamo.setEjemplar(ed.buscarEjemplar(rs.getInt("idEjemplar")));
                Integer idMulta = rs.getInt("idMulta");
                MultaData md = new MultaData(conexion);
                prestamo.setMultas(idMulta == null ? null : md.buscarMulta(idMulta));

                prestamos.add(prestamo);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en obtener los prestamos vigentes por lector " + ex.getMessage());
        }
        return prestamos;
    }

    public List<Lector> lectoresQueSeLesVencioElPrestamo() {
        List<Lector> lectores = new ArrayList<Lector>();

        String sql = "SELECT * FROM prestamo WHERE fechaExp < NOW();";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LectorData ld = new LectorData(conexion);//creo el ld para conectarme a la bd para obtener la info del lector
                Lector lector = ld.BuscarLector(rs.getInt("idLector"));

                lectores.add(lector);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en obtener lectores que se les vencio el prestamo " + ex.getMessage());
        }
        return lectores;
    }

    public Prestamo buscarPrestamoXidMulta(int idMulta) {
        Prestamo prestamo = null;
        String sql = "SELECT * FROM prestamo WHERE idMulta =?;";
        try {

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idMulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setFechaCre(rs.getDate("fechaCre").toLocalDate());
                prestamo.setFechaExp(rs.getDate("fechaExp").toLocalDate());
                prestamo.setEstado(rs.getString("estado"));

                LectorData ld = new LectorData(conexion);//creo el ld para conectarme a la bd para obtener la info del lector
                prestamo.setLector(ld.BuscarLector(rs.getInt("idLector")));
                EjemplarData ed = new EjemplarData(conexion);//entidades externas
                prestamo.setEjemplar(ed.buscarEjemplar(rs.getInt("idEjemplar")));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Buscar Prestamo por id de multa " + ex.getMessage());
        }
        return prestamo;
    }

}
