package servidor;

import interfaces.Agenda;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            AgendaImpl obj = new AgendaImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AgendaService", obj);
            System.out.println("Servidor RMI corriendo...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
