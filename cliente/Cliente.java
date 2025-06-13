package cliente;

import interfaces.Agenda;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            String host = "192.168.100.10";
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            Agenda stub = (Agenda) registry.lookup("AgendaService");
            Scanner scanner = new Scanner(System.in);
            int opcion = 0;

            do {
                System.out.println("\n=== Menú Agenda Distribuida ===");
                System.out.println("1. Añadir contacto");
                System.out.println("2. Mostrar contactos");
                System.out.println("3. Eliminar contacto");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");

                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese el teléfono: ");
                        String telefono = scanner.nextLine();
                        String respuesta = stub.agregarContacto(nombre, telefono);
                        System.out.println(respuesta);
                        break;
                    case 2:
                        List<String> contactos = stub.obtenerContactos();
                        if (contactos.isEmpty()) {
                            System.out.println("No hay contactos registrados.");
                        } else {
                            System.out.println("Contactos:");
                            for (String c : contactos) {
                                System.out.println("- " + c);
                            }
                        }
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre del contacto a eliminar: ");
                        String nombreEliminar = scanner.nextLine();
                        String resultado = stub.eliminarContacto(nombreEliminar);
                        System.out.println(resultado);
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }

            } while (opcion != 4);

            scanner.close();

        } catch (Exception e) {
            System.err.println("Excepción en Cliente:");
            e.printStackTrace();
        }
    }
}
