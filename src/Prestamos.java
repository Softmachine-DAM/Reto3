import java.sql.*;

public class Prestamos {
    public static void PrestamosPendientes(SesionActiva sesion){
        String url="jdbc:mysql://127.0.0.1:3306/biblioteca";
        String user="Grupo3";
        String password="Reto3";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            String slctPrest = "SELECT FROM prestamos WHERE ID = ? AND devuelto = 0";
            try (PreparedStatement stmt = conn.prepareStatement(slctPrest)){
                stmt.setInt(1, sesion.getId());
                ResultSet rsPrest = stmt.executeQuery();
                if (rsPrest.next()) {
                    System.out.println("Tienes pendiente de devolver el libro" + ObtenerTituloLibro(rsPrest.getInt("id_libro")));
                }
            } catch (SQLException e) {
                System.out.println("Error al consultar prestamos pendientes");
            }
        } catch (Exception e) {
            
        }
    }
    public static void PrestarLibro(SesionActiva sesion){

    }
    public static void VerLibros(SesionActiva sesion){

    }
    public static void DevolverLibro(SesionActiva sesion){

    }
    public static String ObtenerTituloLibro(int idLibro){
        String slctLibro = "SELECT titulo from Libros WHERE id_libro = ?";
        String url="jdbc:mysql://127.0.0.1:3306/biblioteca";
        String user="Grupo3";
        String password="Reto3";
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement(slctLibro);
            stmt.setInt(1, idLibro);
            ResultSet rsLibro = stmt.executeQuery();
            if (rsLibro.next()) {
                return rsLibro.getString("Nombre");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Libro desconocido";
    }
}