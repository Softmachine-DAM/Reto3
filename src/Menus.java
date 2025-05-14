// import java.sql.*;
import java.util.Scanner;

public class Menus {
    public static void menuEmpleados(SesionActiva sesion){
        Scanner scanner = new Scanner(System.in);
        int opcionME = 0;
        do {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nPortal de Empleados");
            System.out.println("******************");
            System.out.println("Elige una opción:");
            System.out.println("1. Clientes");
            System.out.println("2. Libros");
            System.out.println("3. Empleados");
            System.out.println("0. Cerrar sesion");
            opcionME = conexion.validarNumero();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
            switch (opcionME){
                case 1:
                    Cliente.usoClientes();
                    break;
                case 2:
                    Libros.usoLibros();
                    break;
                case 3:
                    if (sesion.getId() == 250) {
                        Empleados.usoEmpleados();
                    } else {
                        System.out.println("No tienes permisos para modificar datos de los empleados");
                        System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                    }
                    break;
                case 0:
                    sesion.setId(0);
                    sesion.setContraseña(null);
                    System.out.println("Se cerró la sesion correctamente");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcionME != 0);
    }
    public static void menuClientes(SesionActiva sesion){
        Scanner scanner = new Scanner(System.in);
        int opcionMC = 0;
        do {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nPortal de Clientes");
            System.out.println("*******************");
            System.out.println("Elige una opción:");
            System.out.println("1. Prestamos pendientes");
            System.out.println("2. Prestar libro");
            System.out.println("0. Cerrar sesion");
            opcionMC = conexion.validarNumero();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
            switch (opcionMC){
                case 1:
                    Prestamos.PrestamosPendientes(sesion);
                    break;
                case 2:
                    Prestamos.PrestarLibro(sesion);
                    break;
                case 0:
                    sesion.setId(0);
                    sesion.setContraseña(null);
                    System.out.println("Se cerró la sesion correctamente");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcionMC != 0);
    }
}

