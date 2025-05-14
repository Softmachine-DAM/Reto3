import java.sql.*;
import java.util.Scanner;

public class Libros {
    public static void usoLibros(){
        Scanner scanner = new Scanner(System.in);
        int opcionUEJ = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Insertar Libro");
            System.out.println("2. Actualizar Libro");
            System.out.println("3. Eliminar Libro");
            System.out.println("4. Ver Libros"); 
            System.out.println("5. Volver");
            opcionUEJ = conexion.validarNumero();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

            switch(opcionUEJ){
                case 1:
                    insertLibros();
                    break;
                case 2:
                    actualizarLibros();
                    break;
                case 3:
                    eliminarLibros();
                    break;
                case 4:
                    VerLibros();
                    break;
                default:
                    System.out.println("Introduzca una opcion valida");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (opcionUEJ != 5);
    }
    public static void insertLibros(){
        Scanner scanner = new Scanner(System.in);
        try{
            Connection conn = conexion.ConectarBD();
            System.out.println("Introduce el estado del Libro");
            String estado = scanner.nextLine();
            System.out.println("Introduce el codigo del Libro");
            String cod_libro = scanner.nextLine();
            String str="INSERT INTO libros(estado,cod_libro)";
            str+="VALUES('"+estado+"','"+cod_libro+"')";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Libro Añadido");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al insertar Libro");
        }
    }


        public static void actualizarLibros() {
        Scanner scanner = new Scanner(System.in);
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
            Connection conn = conexion.ConectarBD();
            String str = "UPDATE libros SET Nombre = '"+nombre+"', Apellidos='"+apellidos+"' ,Correo='"+correo+"',Telefono='"+telefono+"',Contraseña='"+contraseña+"' WHERE ID = '"+ ID +"'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Cliente Actualizado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al actualizar datos del cliente");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }
    }



    public static void eliminarLibros(){
        Connection conn = conexion.ConectarBD();
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Introduce el id del ejemplar que quieres eliminar");
            String ID = scanner.nextLine();
            String str = "DELETE FROM libros WHERE cod_ejemplar ='" + ID + "'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Ejemplar Eliminado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al eliminar el ejemplar");
            System.out.println("Pulse ENTER para volver...");
            scanner.nextLine();
        }
    }
    public static void VerLibros(){
        Scanner scanner = new Scanner(System.in);
        try {
            Connection conn = conexion.ConectarBD();
            Statement statement = conn.createStatement();
            String str= "SELECT * FROM libros";
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
            System.out.println("Pulse ENTER para volver...");
            scanner.nextLine(); 
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la conexion");
            System.out.println("Pulse ENTER para volver...");
            scanner.nextLine();
        }
    }
}
