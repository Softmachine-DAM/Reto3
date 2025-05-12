import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Prestamos {
    public static void PrestamosPendientes(SesionActiva sesion){
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = conexion.ConectarBD();
            String slctPrest = "SELECT * FROM prestamos WHERE id_usuario = ? AND devuelto = 0";
            try (PreparedStatement stmt = conn.prepareStatement(slctPrest)){
                stmt.setInt(1, sesion.getId());
                ResultSet rsPrest = stmt.executeQuery();
                if (rsPrest.next()) {
                    System.out.println("Tienes pendiente de devolver el libro " + ObtenerTituloLibro(rsPrest.getInt("id_libro")));
                    int opcionDevolver = 0;
                    System.out.println("¿Quieres devolverlo?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    opcionDevolver = conexion.validarNumero();
                    switch (opcionDevolver) {
                        case 1:
                            
                            break;
                        case 2:
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
                            System.out.println("Volviendo al portal de Clientes");
                            System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                            break;
                        default:
                            System.out.println("Introduzca una opcion valida");
                            System.out.println("Pulse ENTER para continuar...");
                            scanner.nextLine();
                            break;
                    }
                }else{
                    System.out.println("No tienes ningun libro pendiente por devolver");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al consultar prestamos pendientes");
            }
        } catch (Exception e) {
            System.out.println("Error en la conexion");
        }
    }
    public static void PrestarLibro(SesionActiva sesion){

    }
    public static void VerLibros(SesionActiva sesion){

    }
    public static void DevolverLibro(SesionActiva sesion){
        try {
            Connection conn = conexion.ConectarBD();
            String updtPrestamo = "UPDATE INTO prestamos (devuelto) values (?)";
            try (PreparedStatement stmt = conn.prepareStatement(updtPrestamo)) {
                stmt.setInt(1, 1);
                ResultSet rsLibro = stmt.executeQuery();
                if (rsLibro.next()) {
                    System.out.println("Se ha devuelto el libro");
                }
            }catch(SQLException e){
                e.printStackTrace();
                System.out.println("Error al devolver el libro");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        }
    }
    public static String ObtenerTituloLibro(int idLibro){
        try {
            Connection conn = conexion.ConectarBD();
            String slctLibro = "SELECT titulo from Libros WHERE id_libro = ?";
            try (PreparedStatement stmt = conn.prepareStatement(slctLibro)) {
                stmt.setInt(1, idLibro);
                ResultSet rsLibro = stmt.executeQuery();
                if (rsLibro.next()) {
                    return rsLibro.getString("Titulo");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        }
        return "Libro desconocido";
    }
}