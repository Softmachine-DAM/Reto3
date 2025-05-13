import java.sql.*;
import java.util.Scanner;

public class conexion{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        try{
            Connection conn = ConectarBD();
            if (conn != null) {
                System.out.println("Conexion exitosa a la base de datos");
                int opcion = 0;
                int id = 0;
                String contraseña = "";
                do {
                    System.out.println("\nBienvenido al servicio de la Biblioteca de Muskiz");
                    System.out.println("*************************************************");
                    System.out.println("Elige tu tipo de Usuario:");
                    System.out.println("1. Clientes");
                    System.out.println("2. Empleados");
                    System.out.println("3. Salir");
                    opcion = validarNumero();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    if (opcion == 1 || opcion == 2) {
                        System.out.print("Introduce tu numero ID: ");
                        id = validarNumero();
                        if (id != 0) {
                            System.out.print("Introduce tu Contraseña: ");
                            contraseña = scanner.nextLine();     
                        }
                    System.out.println("\n\n\n\n\n\n\n\n\n\n");
                    }
                    SesionActiva sesion = new SesionActiva(id, contraseña);
                    switch (opcion) {
                        case 1:
                            if (id == 0){
                                System.out.println("No has introducido un numero id valido");
                                System.out.println("Pulse ENTER para continuar...");
                                scanner.nextLine();
                            }else if (loginUsuario(conn, "clientes_1", scanner, sesion, opcion)) {
                                System.out.println("Inicio de sesión correcto. ¡Bienvenido " + obtenerNombreUsuario(conn, sesion, "clientes_1", opcion) + " al portal de clientes!");
                                System.out.println("Pulse ENTER para continuar...");
                                scanner.nextLine();
                                Menus.menuClientes(sesion);
                            } else {
                                System.out.println("Usuario o contraseña incorrectos.");
                                System.out.println("Se le enviara al menu principal.");
                                System.out.println("Pulse ENTER para continuar...");
                                scanner.nextLine();
                            }
                            break;
                        case 2:
                            if (id == 0){
                                System.out.println("No has introducido un numero id valido");
                                System.out.println("Pulse ENTER para continuar...");
                                scanner.nextLine();
                            }else if (loginUsuario(conn, "empleados", scanner, sesion, opcion)) {
                                System.out.println("Inicio de sesión correcto. ¡Bienvenido " + obtenerNombreUsuario(conn, sesion, "empleados", opcion) + " al portal de empleados!");
                                System.out.println("Pulse ENTER para continuar...");
                                scanner.nextLine();
                                Menus.menuEmpleados(sesion);
                            } else {
                                System.out.println("Usuario o contraseña incorrectos.");
                                System.out.println("Se le enviara al menu principal.");
                                System.out.println("Pulse ENTER para continuar...");
                                scanner.nextLine();
                            }
                            break;
                        case 3:
                            System.out.println("Gracias por utilizar el programa");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Introduzca una opcion valida");
                            System.out.println("Pulse ENTER para continuar...");
                            scanner.nextLine();
                            break;
                    }
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
                } while (opcion != 3);
            }
        }catch(Exception e){
            //e.printStackTrace();
            System.out.print("Error en la conexión ");
        }
    }
    public static boolean loginUsuario(Connection conn, String tabla, Scanner scanner, SesionActiva sesion, int opcion) {
        String str = "";
        if (opcion == 1) {
            str = "SELECT * FROM " + tabla + " WHERE ID = ? AND Contraseña = ?";
        }else{
            str = "SELECT * FROM " + tabla + " WHERE id_empleado = ? AND contraseña = ?";
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        try (PreparedStatement stmt = conn.prepareStatement(str)) {
            stmt.setInt(1, sesion.getId());
            stmt.setString(2, sesion.getContraseña());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
    public static String obtenerNombreUsuario(Connection conn, SesionActiva sesion, String tabla, int opcion) {
        String select;
        if (opcion == 1) {
            select = "SELECT Nombre FROM " + tabla + " WHERE id = ?";
        }else{
            select = "SELECT nombre FROM " + tabla + " WHERE id_empleado = ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(select)) {
            stmt.setInt(1, sesion.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Usuario desconocido";
    }
    public static int validarNumero(){
        Scanner scanner = new Scanner(System.in);
        int numero;
        if (scanner.hasNextInt()) {
            numero = scanner.nextInt();
        } else {
            numero = 0;
        }
        return numero;
    }
    public static Connection ConectarBD(){
        String url="jdbc:mysql://127.0.0.1:3306/biblioteca3";
        String user="123";
        String password="123";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Error en la conexion");
        }
        return conn;
    }
}