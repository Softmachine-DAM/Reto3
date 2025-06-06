import java.sql.*;
import java.util.Scanner;

public class Cliente{
    public static void usoClientes(Scanner scanner, Connection conn){
        int opcionUC = 0;
        do {
            System.out.println("\033[H\033[2J");
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║       MENÚ DE GESTIÓN DE CLIENTES          ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║                                            ║");
            System.out.println("║   1  Insertar Cliente                      ║");
            System.out.println("║   2  Actualizar Cliente                    ║");
            System.out.println("║   3  Eliminar Cliente                      ║");
            System.out.println("║   4  Ver Clientes                          ║");
            System.out.println("║   0  Volver                                ║");
            System.out.println("║                                            ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Elige una opción: ");
            opcionUC = conexion.validarNumero(scanner);
            System.out.println("\033[H\033[2J");
            switch(opcionUC){
                case 1:
                    insertClientes(scanner, conn);
                    break;
                case 2:
                    actualizarClientes(scanner, conn);
                    break;
                case 3:
                    eliminarClientes(scanner, conn);
                    break;
                case 4:
                    verClientes(conn, scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Introduzca una opcion valida");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (opcionUC != 0);
    }
    public static void insertClientes(Scanner scanner, Connection conn){
        try{
            System.out.println("Introduce el nombre del Cliente");
            String nombre = scanner.nextLine();
            System.out.println("Introduce los apellidos del Cliente");
            String apellidos = scanner.nextLine();
            System.out.println("Introduce el correo del Cliente");
            String correo = scanner.nextLine();
            System.out.println("Introduce el telefono del Cliente");
            String telefono = scanner.nextLine();
            System.out.println("Introduce la contraseña");
            String contraseña = scanner.nextLine();
            String str = "INSERT INTO clientes_1(Nombre,Apellidos,Correo,Telefono,Contraseña)";
            str+= "VALUES (" +"UPPER('" + nombre + "'), " +"UPPER('" + apellidos + "'), " +"UPPER('" + correo + "'), " +"UPPER('" + telefono + "'), " +"UPPER('" + contraseña + "')" + ")";
            if(correo.contains("@")){
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("\033[H\033[2J");
            System.out.println("Registro Añadido");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
            }else{
                System.out.println("Introduce bien el correo");
                System.out.println("Pulse ENTER para continuar...");
                scanner.nextLine();
                return;
            }
        }catch(SQLException e){
            System.out.println("Error al insertar cliente");
            e.printStackTrace();
        }
        
    }

    public static void eliminarClientes(Scanner scanner, Connection conn){
        try{
            System.out.println("Introduce el id del usuario que quieres eliminar");
            String ID = scanner.nextLine();
            String str = "DELETE FROM clientes_1 WHERE ID ='" + ID +"'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Cliente Eliminado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al eliminar cliente");
        }
    }

    public static void actualizarClientes(Scanner scanner, Connection conn) {
        try{
            System.out.println("Introduce el ID del Cliente");
            String ID = scanner.nextLine();
            System.out.println("Introduce el nombre del Cliente");
            String nombre = scanner.nextLine();
            System.out.println("Introduce los apellidos del Cliente");
            String apellidos = scanner.nextLine();
            System.out.println("Introduce el correo del Cliente");
            String correo = scanner.nextLine();
            System.out.println("Introduce el telefono del Cliente");
            String telefono = scanner.nextLine();
            System.out.println("Introduce la contraseña");
            String contraseña = scanner.nextLine();
            if(correo.contains("@")){
            String str = "UPDATE clientes_1 SET " +
             "Nombre = UPPER('" + nombre + "'), " +
             "Apellidos = UPPER('" + apellidos + "'), " +
             "Correo = UPPER('" + correo + "'), " +
             "Telefono = UPPER('" + telefono + "'), " +
             "Contraseña = UPPER('" + contraseña + "') " +
             "WHERE ID = '" + ID + "'";


            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Cliente Actualizado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
            }else{
                System.out.println("Introduce bien el correo");
                 System.out.println("Pulse ENTER para continuar...");
                scanner.nextLine();
                return;
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al actualizar datos del cliente");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }
    }
    public static void verClientes(Connection conn, Scanner scanner){
        try {
            Statement statement = conn.createStatement();
            String str= "SELECT * FROM clientes_1";
            ResultSet rs = statement.executeQuery(str);
            while (rs.next()) {
                String Nombre = rs.getString("Nombre");
                String Apellidos = rs.getString("Apellidos");
                String Correo = rs.getString("Correo");
                int  Telefono = rs.getInt("telefono");
                String Contraseña = rs.getString("Contraseña");
                int ID = rs.getInt("ID");
                int Penalizado = rs.getInt("penalizado");
                System.out.println("Nombre: " + Nombre + " | Apellidos: " + Apellidos + 
                " | Correo: " + Correo + " | Telefono: " + Telefono + " | Contraseña: " + Contraseña + " | ID:" + ID + " |Penalizado:" + Penalizado);
            } 
            System.out.println("\nPulse ENTER para volver...");
            scanner.nextLine();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        }
    }
}