import java.sql.*;
import java.util.Scanner;

public class Empleados {
    public static void usoEmpleados(Scanner scanner, Connection conn){
        int opcionUEM = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Insertar Empleado");
            System.out.println("2. Actualizar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Ver Empleados");
            System.out.println("0. Volver");
            opcionUEM = conexion.validarNumero(scanner);
            System.out.println("\033[H\033[2J");

            switch (opcionUEM) {
                case 1:
                    insertEmpleados(scanner, conn);
                    break;
                case 2:
                    actualizarEmpleados(scanner, conn);
                    break;
                case 3:
                    eliminarEmpleados(scanner, conn);
                    break;
                case 4:
                    verEmpleados(conn);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Introduzca una opcion valida");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    System.out.println("\033[H\033[2J");
                    break;
            }
        } while (opcionUEM != 0);
    }
    public static void insertEmpleados(Scanner scanner, Connection conn){
        try{
            System.out.println("Introduce el nombre del empleado");
            String nombre = scanner.nextLine();
            System.out.println("Introduce el rol del empleado");
            String rol = scanner.nextLine();
            System.out.println("Introduce tu contraseña");
            String contraseña = scanner.nextLine();
            System.out.println("\033[H\033[2J");
            if (nombre != "" && rol != "") {
                String str="INSERT INTO empleados(nombre,rol,contraseña)";
                str+="VALUES('"+nombre+"','"+rol+"','"+contraseña+"')";
                Statement stmt=conn.createStatement();
                stmt.executeUpdate(str);
                System.out.println("Empleado Añadido");
            }else{
                System.out.println("Nombre y rol no pueden estar vacios");
            }
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al insertar el empleado");
        }
    }


        public static void actualizarEmpleados(Scanner scanner, Connection conn){
        try{
            System.out.println("Introduce el ID del Empleado");
            String ID = scanner.nextLine();
            System.out.println("Introduce el nombre del Empleado");
            String nombre = scanner.nextLine();
            System.out.println("Introduce el rol del empleado");
            String rol = scanner.nextLine();
            System.out.println("Introduce la contraseña");
            String contraseña = scanner.nextLine();
            String str = "UPDATE empleados SET nombre = '"+nombre+"', rol='"+rol+"' ,Contraseña='"+contraseña+"' WHERE id_empleado = '"+ ID +"'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("\033[H\033[2J");
            System.out.println("Empleado Actualizado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al actualizar datos del empleado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
      
        }
    }

    public static void eliminarEmpleados(Scanner scanner, Connection conn){
        try{
            System.out.println("Introduce el id del empleado que quieres eliminar");
            String ID = scanner.nextLine();
            String str= "DELETE FROM empleados WHERE id_empleado = '" + ID +"'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("\033[H\033[2J");
            System.out.println("Empleado Eliminado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al eliminar al empleado");
        }
    }

    public static void verEmpleados(Connection conn){
        try {
            Statement statement = conn.createStatement();
            String str= "SELECT * FROM empleados";
            ResultSet rs = statement.executeQuery(str);
            while (rs.next()) {
                int idempleado = rs.getInt("id_empleado");
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");
                System.out.println("ID: " + idempleado + " | Nombre: " + nombre + 
                " | Rol: " + rol + " ");
        } 
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        }
    }
}
