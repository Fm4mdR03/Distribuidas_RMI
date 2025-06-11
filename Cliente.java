import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            String host = "IP_DEL_SERVIDOR"; // Cambiar por IP real
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            IHello stub = (IHello) registry.lookup("Hola");
            String respuesta = stub.decirHola("Usuario desde Cliente");
            System.out.println("Respuesta del servidor: " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
