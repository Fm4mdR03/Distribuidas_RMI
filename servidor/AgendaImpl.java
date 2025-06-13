package servidor;

import interfaces.IAgenda;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaImpl extends UnicastRemoteObject implements IAgenda {

    public AgendaImpl() throws RemoteException {
        super();
    }

    public String agregarContacto(String nombre, String telefono) throws RemoteException {
        try (Connection conn = DBUtil.obtenerConexion()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO contactos (nombre, telefono) VALUES (?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, telefono);
            ps.executeUpdate();
            return "Contacto agregado.";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }

    public List<String> obtenerContactos() throws RemoteException {
        List<String> contactos = new ArrayList<>();
        try (Connection conn = DBUtil.obtenerConexion()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contactos");
            while (rs.next()) {
                contactos.add(rs.getString("nombre") + " - " + rs.getString("telefono"));
            }
        } catch (SQLException e) {
            contactos.add("Error: " + e.getMessage());
        }
        return contactos;
    }

    public String eliminarContacto(String nombre) throws RemoteException {
        try (Connection conn = DBUtil.obtenerConexion()) {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM contactos WHERE nombre = ?");
            ps.setString(1, nombre);
            int filas = ps.executeUpdate();
            return filas > 0 ? "Contacto eliminado." : "Contacto no encontrado.";
        } catch (SQLException e) {
            return "Error: " + e.getMessage();
        }
    }
}
