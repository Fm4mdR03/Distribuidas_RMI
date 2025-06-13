package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Agenda extends Remote {
    String agregarContacto(String nombre, String telefono) throws RemoteException;
    List<String> obtenerContactos() throws RemoteException;
    String eliminarContacto(String nombre) throws RemoteException;
}
