import java.sql.*;
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
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
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
        if (EstaPenalizado(sesion) == false && PrestamosMaximos(sesion) == false) {
            VerLibrosDisponibles(sesion);
            System.out.println("\n\nIntroduce el id del libro que quieres coger prestado:");
            int idLibro = conexion.validarNumero();
            String selectLibro = "SELECT * from Libros where id_libro = ?";
            try (Connection conn = conexion.ConectarBD();
            PreparedStatement stmt = conn.prepareStatement(selectLibro)){
                stmt.setInt(1, idLibro);
                try (ResultSet rsLibro = stmt.executeQuery();){
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
                    if (rsLibro.next()) {
                        String insertPrest = "INSERT INTO Prestamos (id_usuario,id_libro,fecha_fin) values (?, ?, NOW() + INTERVAL 14 DAY)";
                        String updateLibros = "UPDATE Libros SET Ejemplares = Ejemplares -1 WHERE id_libro = ?";
                        PreparedStatement stmtPrest = conn.prepareStatement(insertPrest);
                        stmtPrest.setInt(1, sesion.getId());
                        stmtPrest.setInt(2, idLibro);
                        PreparedStatement stmtLibro = conn.prepareStatement(updateLibros);
                        stmtLibro.setInt(1, idLibro);
                        stmtPrest.executeUpdate();
                        stmtLibro.executeUpdate();
                        System.out.println("Se ha realizado el prestamo");
                        System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                    }else{
                        System.out.println("No tenemos ningun libro con ese id, se te devolvera al menu");
                        System.out.println("Pulse ENTER para continuar...");
                        scanner.nextLine();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Error al prestar el libro");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error en la conexion");
            }
        }else if (EstaPenalizado(sesion) == true) {
            System.out.println("Estas penalizado, no puedes realizar ningun prestamo");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }else if (PrestamosMaximos(sesion) == true) {
            System.out.println("Ya tienes prestados tres libros, devuelve alguno para poder prestar otro");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }
    }
    public static boolean EstaPenalizado(SesionActiva sesion){
        String selectPenalizado = "SELECT Penalizado FROM clientes_1 WHERE ID = ?";
        try (Connection conn = conexion.ConectarBD();
        PreparedStatement stmt = conn.prepareStatement(selectPenalizado)){
            stmt.setInt(1, sesion.getId());
            try (ResultSet rsPenal = stmt.executeQuery();){
                if (rsPenal.next()) {
                    if (rsPenal.getInt("Penalizado") == 1) {
                        return true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error en la consulta");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al comprobar la penalizacion");
        }
        return false;
    }
    public static boolean PrestamosMaximos(SesionActiva sesion){
        String selectPenalizado = "SELECT COUNT(*) AS total_prestamos FROM prestamos WHERE id_usuario = ? AND devuelto = ?";
        try (Connection conn = conexion.ConectarBD();
        PreparedStatement stmt = conn.prepareStatement(selectPenalizado)){
            stmt.setInt(1, sesion.getId());
            stmt.setInt(2, 0);
            ResultSet rsPenal = stmt.executeQuery();
            if (rsPenal.next()) {
                if (rsPenal.getInt("total_prestamos")>2){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al comprobar el numero de prestamos");
        }
        return false;
    }
    public static void VerLibrosDisponibles(SesionActiva sesion){
        try {
            Connection conn = conexion.ConectarBD();
            Statement statement = conn.createStatement();
            String str= "SELECT * FROM libros WHERE Ejemplares>0";
            ResultSet rs = statement.executeQuery(str);
            while (rs.next()) {
                int idLibro = rs.getInt("id_libro");
                String titulo = rs.getString("titulo");
                int anio = rs.getInt("anio_publicacion");
                String genero = rs.getString("genero");
                int idEditorial = rs.getInt("id_editorial");
                int ejemplares = rs.getInt("Ejemplares");
                System.out.println("ID: " + idLibro + " | Título: " + titulo + 
                " | Año: " + anio + " | Género: " + genero +
                " | Editorial: " + idEditorial +
                " | Ejemplares: " + ejemplares);
        } 
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
        }
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
            String updtPrestamo = "UPDATE prestamos SET devuelto = ?, fecha_devolucion = NOW() WHERE id_prestamo = ?";
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