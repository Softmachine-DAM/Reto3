// import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;

public class Menus {
    public static void menuEmpleados(Scanner scanner, SesionActiva sesion, Connection conn){
        int opcionME = 0;
        do {
            System.out.println("\033[H\033[2J");
            System.out.println("Portal de Empleados");
            System.out.println("******************");
            System.out.println("Elige una opción:");
            System.out.println("1. Clientes");
            System.out.println("2. Libros");
            System.out.println("3. Empleados");
            System.out.println("0. Cerrar sesion");
            opcionME = conexion.validarNumero(scanner);
            System.out.println("\033[H\033[2J");
            switch (opcionME){
                case 1:
                    Cliente.usoClientes(scanner, conn);
                    break;
                case 2:
                    Libros.usoLibros(scanner, conn);
                    break;
                case 3:
                    if (sesion.getId() == 250) {
                        Empleados.usoEmpleados(scanner, conn);
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
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (opcionME != 0);
    }
    public static void menuClientes(Scanner scanner, SesionActiva sesion, Connection conn){
        int opcionMC = 0;
        do {
            System.out.println("\033[H\033[2J");
            System.out.println("Portal de Clientes");
            System.out.println("*******************");
            System.out.println("Elige una opción:");
            System.out.println("1. Prestamos pendientes");
            System.out.println("2. Prestar libro");
            System.out.println("0. Cerrar sesion");
            opcionMC = conexion.validarNumero(scanner);
            System.out.println("\033[H\033[2J");
            switch (opcionMC){
                case 1:
                    Prestamos.PrestamosPendientes(scanner, sesion, conn);
                    break;
                case 2:
                    Prestamos.PrestarLibro(scanner, sesion, conn);
                    break;
                case 0:
                    sesion.setId(0);
                    sesion.setContraseña(null);
                    System.out.println("Se cerró la sesion correctamente");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (opcionMC != 0);
    }
}

