import java.sql.*;
import java.util.Scanner;

public class Ejemplares {
    public static void usoEjemplares(){
        Scanner scanner = new Scanner(System.in);
        int opcionUEJ = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Insertar Ejemplar");
            System.out.println("2. Actualizar Ejemplar");
            System.out.println("3. Eliminar Ejemplar");
            System.out.println("4. Ver Ejemplar"); 
            System.out.println("5. Volver");
            opcionUEJ = conexion.validarNumero();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

            switch(opcionUEJ){
                case 1:
                insertEjemplares();
                break;
                case 2:
                break;
                case 3:
                // eliminarEjemplares();
                break;
                case 4:
                break;
                default:
                break;
            }
        } while (opcionUEJ != 5);
    }
    public static void insertEjemplares(){
        Scanner scanner = new Scanner(System.in);
        try{
            Connection conn = conexion.ConectarBD();
            System.out.println("Introduce el estado del ejemplar");
            String estado = scanner.nextLine();
            System.out.println("Introduce el codigo del ejemplar");
            String cod_libro = scanner.nextLine();
            String str="INSERT INTO ejemplares(estado,cod_libro)";
            str+="VALUES('"+estado+"','"+cod_libro+"')";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Ejemplar Añadido");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al insertar ejemplares");
        }
    }


    public static void actualizarEjemplares(){
        Scanner scanner = new Scanner(System.in);
        try{
            Connection conn = conexion.ConectarBD();
            String str = "UPDATE ejemplares SET";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
        }catch(SQLException e){

        }
    }


    // public static void eliminarEjemplares(){
    //     Connection conn = conexion.ConectarBD();
    //     Scanner scanner = new Scanner(System.in);
    //     try{
    //         System.out.println("Introduce el id del ejemplar que quieres eliminar");
    //         String ID = scanner.nextLine();
    //         String str = "DELETE FROM ejemplares WHERE cod_ejemplar ='" + ID + "'";
    //         Statement stmt=conn.createStatement();
    //         stmt.executeUpdate(str);
    //         System.out.println("Ejemplar Eliminado");
    //         System.out.println("Pulse ENTER para continuar...");
    //         scanner.nextLine();
    //     }catch(SQLException e){
    //         System.out.println("Error al eliminar el ejemplar");
    //     }
    // }
}
