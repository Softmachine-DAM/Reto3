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
            System.out.println("5. Volver");
            opcionUEM = conexion.validarNumero();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

            switch (opcionUEM) {
                case 1:
                    insertEmpleados();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (opcionUEM != 5);
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
            Connection conn = conexion.ConectarBD();
            String str = "UPDATE empleados SET";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
        }catch(SQLException e){

        }
    }

    public static void eliminarEmpleados(){
        Scanner scanner = new Scanner(System.in);
        try{
            Connection conn = conexion.ConectarBD();
            String str= "DELETE FROM empleados WHERE";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
        }catch(SQLException e){

        }
    }
}