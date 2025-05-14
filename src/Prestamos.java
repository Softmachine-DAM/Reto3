import java.sql.Connection;
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
                if (!rsPrest.next()) {
                    System.out.println("No tienes ningun libro pendiente por devolver");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                }else{
                    int[] ids = {0, 0, 0};
                    int[] idsLibros = {0, 0, 0};
                    int i = 0;
                    ids[i] = rsPrest.getInt("id_prestamo");
                    idsLibros[i] = rsPrest.getInt("id_libro");
                    i++; 
                    while (rsPrest.next()) {
                        ids[i] = rsPrest.getInt("id_prestamo");
                        idsLibros[i] = rsPrest.getInt("id_libro");
                        i++; 
                    }
                    PrestamosPendientes prestamos = new PrestamosPendientes(ids[0], ids[1], ids[2],idsLibros[0],idsLibros[1],idsLibros[2]);
                    int opcionDevolver = 0;
                    int cont = 1;
                    System.out.println("Tienes pendiente de devolver los siguientes libros:");
                    System.out.println("Elige el que quieras devolver o selecciona la ultima opcion para salir");
                    System.out.println(cont + ". " + ObtenerTituloLibro(prestamos.getId_libro1()));
                    if (prestamos.getId2() != 0) {
                        cont++;
                        System.out.println(cont + ". " + ObtenerTituloLibro(prestamos.getId_libro2()));
                        if (prestamos.getId3() != 0) {
                            cont++;
                            System.out.println(cont + ". " + ObtenerTituloLibro(prestamos.getId_libro3()));
                        }
                    }
                    cont++;
                    System.out.println(cont + ". Ninguno");
                    opcionDevolver = conexion.validarNumero();
                    if (opcionDevolver == 1){
                        DevolverLibro(sesion, prestamos.getId1());
                        System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                    }else if (cont == 3 && opcionDevolver == 2){
                        DevolverLibro(sesion, prestamos.getId2());
                        System.out.println("Se ha devuelto el libro correctamente");
                        System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                    }else if (cont == 4 && opcionDevolver == 3){
                        DevolverLibro(sesion, prestamos.getId3());
                        System.out.println("Se ha devuelto el libro correctamente");
                        System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                    }else if (opcionDevolver == cont){
                        System.out.println("No se devolvera ningun libro");
                        System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al consultar prestamos pendientes");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        }
    }
    public static void PrestarLibro(SesionActiva sesion){
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = conexion.ConectarBD();
            String slctPenal = "SELECT * FROM prestamos WHERE id_usuario = ? AND devuelto = 0";
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        }
    }
    public static void VerLibros(SesionActiva sesion){

    }
    public static void DevolverLibro(SesionActiva sesion, int id_prestamo) {
        try {
            Connection conn = conexion.ConectarBD();
            int id_libro = -1;
            String getLibro = "SELECT id_libro FROM prestamos WHERE id_prestamo = ?";
            try (PreparedStatement getStmt = conn.prepareStatement(getLibro)) {
                getStmt.setInt(1, id_prestamo);
                ResultSet rs = getStmt.executeQuery();
                if (rs.next()) {
                    id_libro = rs.getInt("id_libro");
                } else {
                    System.out.println("No se encontró el préstamo con ese ID.");
                    return;
                }
                rs.close();
            }
            String updtPrestamo = "UPDATE prestamos SET devuelto = ?, fecha_fin = NOW() WHERE id_prestamo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updtPrestamo)) {
                stmt.setInt(1, 1);
                stmt.setInt(2, id_prestamo);
    
                int rsPrestamo = stmt.executeUpdate();
                if (rsPrestamo == 1) {
                    String updtLibro = "UPDATE libros SET Ejemplares = Ejemplares + 1 WHERE id_libro = ?";
                    try (PreparedStatement stmt1 = conn.prepareStatement(updtLibro)) {
                        stmt1.setInt(1, id_libro);
                        stmt1.executeUpdate();
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("Se ha devuelto el libro");
                    } catch (SQLException exception) {
                        System.out.println("Error al añadir el ejemplar");
                        exception.printStackTrace();
                    }
                } else {
                    System.out.println("Error: No se actualizó el préstamo.");
                }
            } catch (SQLException e) {
                System.out.println("Error al devolver el libro");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
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