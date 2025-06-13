package cliente;

import interfaces.IAgenda;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        try {
            String host = "IP_DEL_SERVIDOR"; // reemplazar
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            IAgenda stub = (IAgenda) registry.lookup("AgendaService");

            System.out.println(stub.agregarContacto("Ana", "123456789"));
            System.out.println("Contactos actuales:");
            List<String> contactos = stub.obtenerContactos();
            for (String c : contactos) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
