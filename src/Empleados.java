import java.sql.*;
import java.util.Scanner;

public class Empleados {
    public static void usoEmpleados(){
        Scanner scanner = new Scanner(System.in);
        int opcionUEM = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Insertar Empleado");
            System.out.println("2. Actualizar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Ver Empleados");
            System.out.println("0. Volver");
            opcionUEM = conexion.validarNumero();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

            switch (opcionUEM) {
                case 1:
                    insertEmpleados();
                    break;
                case 2:
                    actualizarEmpleados();
                    break;
                case 3:
                    eliminarEmpleados();
                    break;
                case 4:
                    verEmpleados();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Introduzca una opcion valida");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (opcionUEM != 0);
    }
    public static void insertEmpleados(){
        Scanner scanner = new Scanner(System.in);
        try{
            Connection conn = conexion.ConectarBD();
            System.out.println("Introduce el nombre del empleado");
            String nombre = scanner.nextLine();
            System.out.println("Introduce el rol del empleado");
            String rol = scanner.nextLine();
            System.out.println("Introduce tu contraseña");
            String contraseña = scanner.nextLine();
            String str="INSERT INTO empleados(nombre,rol,contraseña)";
            str+="VALUES('"+nombre+"','"+rol+"','"+contraseña+"')";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Empleado Añadido");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al insertar el empleado");
        }
    }


        public static void actualizarEmpleados(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Introduce el ID del Empleado");
                String ID = scanner.nextLine();
            System.out.println("Introduce el nombre del Empleado");
                String nombre = scanner.nextLine();
                System.out.println("Introduce el rol del empleado");
                String rol = scanner.nextLine();
                System.out.println("Introduce la contraseña");
                String contraseña = scanner.nextLine();
            Connection conn = conexion.ConectarBD();
            String str = "UPDATE empleados SET nombre = '"+nombre+"', rol='"+rol+"' ,Contraseña='"+contraseña+"' WHERE id_empleado = '"+ ID +"'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Empleado Actualizado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al actualizar datos del empleado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
      
        }
    }

    public static void eliminarEmpleados(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Introduce el id del empleado que quieres eliminar");
            String ID = scanner.nextLine();
            Connection conn = conexion.ConectarBD();
            String str= "DELETE FROM empleados WHERE id_empleado = '" + ID +"'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Empleado Eliminado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al eliminar al empleado");
        }
    }

    public static void verEmpleados(){
        try {
            Connection conn = conexion.ConectarBD();
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
