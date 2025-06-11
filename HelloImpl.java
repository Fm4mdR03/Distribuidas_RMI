import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class HelloImpl extends UnicastRemoteObject implements IHello {

    protected HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String decirHola(String nombre) throws RemoteException {
        return "Hola, " + nombre + "! Servidor RMI funcionando correctamente.";
    }
}