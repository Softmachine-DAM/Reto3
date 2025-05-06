import java.sql.*;
import java.util.Scanner;

public class conexion{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        String url="jdbc:mysql://127.0.0.1:3306/biblioteca";
        String user="Grupo3";
        String password="Reto3";
        try{
            //CONEXION A LA BASE DE DATOS
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion exitosa a la base de datos");
            //LOGIN
            int opcion = 0;
            do {
                System.out.println("Bienvenido");
                System.out.println("Elige tu tipo de Usuario:");
                System.out.println("1. Clientes");
                System.out.println("2. Empleados");
                System.out.println("3. Salir");
                opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1:
                        if (loginUsuario(conn, "clientes_1", scanner)) {
                            System.out.println("Inicio de sesión correcto. ¡Bienvenido al portal de clientes!");
                        } else {
                            System.out.println("Usuario o contraseña incorrectos.");
                        }
                        break;
                    case 2:
                        if (loginUsuario(conn, "empleados_1", scanner)) {
                            System.out.println("Inicio de sesión correcto. ¡Bienvenido al portal de empleados!");
                        } else {
                            System.out.println("Usuario o contraseña incorrectos.");
                        }
                        break;
                    case 3:
                        System.out.println("Gracias por utilizar el programa");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Introduzca una opcion valida");
                        break;
                }
            } while (opcion != 3);
            //MENU
            System.out.println("Elige una opción:");
            System.out.println("1. Clientes");
            System.out.println("2. Ejemplares");
            System.out.println("3. Empleados");
            int tabla = scanner.nextInt();
            scanner.nextLine();
            switch (tabla){
                case 1:
                Cliente.usoClientes();
                int opcion1 = scanner.nextInt();
                scanner.nextLine();
                if(opcion1==1){
                    Cliente.insertClientes();
                }else if(opcion1==2){
                    
                }else if(opcion1==3){

                }else{

                }
                break;
                case 2:
                Ejemplares.usoEjemplares();
                int opcion2 = scanner.nextInt();
                scanner.nextLine();
                if(opcion2==1){
                    Ejemplares.inserEjemplares();
                }else if(opcion2==2){

                }else if(opcion2==3){

                }else{

                }
                case 3:
                Empleados.usoEmpleados();
                int opcion3 = scanner.nextInt();
                scanner.nextLine();
                if(opcion3==1){
                    Empleados.insertEmpleados();
                }else if(opcion3==2){

                }else if(opcion3==3){

                }else{

                }
                break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }catch(SQLException e){
            //e.printStackTrace();
            System.out.print("Error en la conexión ");

        }
    }
    public static boolean loginUsuario(Connection conn, String tabla, Scanner scanner) {
        System.out.print("Introduce tu Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Introduce tu Contraseña: ");
        String contraseña = scanner.nextLine();

        String query = "SELECT * FROM " + tabla + " WHERE Nombre = ? AND Contraseña = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}