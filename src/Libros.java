import java.sql.*;
import java.util.Scanner;

public class Libros {
    public static void usoLibros(Scanner scanner, Connection conn){
        int opcionUEJ = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Insertar Libro");
            System.out.println("2. Actualizar Libro");
            System.out.println("3. Eliminar Libro");
            System.out.println("4. Ver Libros"); 
            System.out.println("0. Volver");
            opcionUEJ = conexion.validarNumero(scanner);
            System.out.println("\033[H\033[2J");

            switch(opcionUEJ){
                case 1:
                    insertLibros(scanner, conn);
                    break;
                case 2:
                    actualizarLibros(scanner, conn);
                    break;
                case 3:
                    eliminarLibros(scanner, conn);
                    break;
                case 4:
                    VerLibros(scanner, conn);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Introduzca una opcion valida");
                    System.out.println("Pulse ENTER para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (opcionUEJ != 0);
    }
    public static void insertLibros(Scanner scanner, Connection conn){
        try{
            System.out.println("Introduce el id del Libro");
            String id_libro = scanner.nextLine();
            System.out.println("Introduce el titulo del Libro");
            String titulo = scanner.nextLine();
            System.out.println("Introduce el año de publicacion del Libro");
            String anio_publicacion = scanner.nextLine();
            System.out.println("Introduce el genero del Libro");
            String genero = scanner.nextLine();
            System.out.println("Introduce la id de la editorial del Libro");
            String id_editorial = scanner.nextLine();
            System.out.println("Introduce el id de la categoria del Libro");
            String id_categoria = scanner.nextLine();
            System.out.println("Introduce el nº de ejemplares del Libro");
            String ejemplares = scanner.nextLine();
            String str="INSERT INTO libros(id_libro,titulo,anio_publicacion,genero,id_editorial,id_categoria,Ejemplares)";
            str+="VALUES('"+id_libro+"','"+titulo+"','"+anio_publicacion+"','"+genero+"','"+id_editorial+"','"+id_categoria+"','"+ejemplares+"')";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            conn.close();
            System.out.println("Libro Añadido");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al insertar Libro, comprueba si el codigo introducido no se esta usando ya");
        }
    }


    public static void actualizarLibros(Scanner scanner, Connection conn) {
        System.out.println("Introduce el id del Libro");
        String ID = scanner.nextLine();
        System.out.println("Introduce el titulo del Libro");
        String titulo = scanner.nextLine();
        System.out.println("Introduce el año de publicacion del Libro");
        String anio_publicacion = scanner.nextLine();
        System.out.println("Introduce el genero del Libro");
        String genero = scanner.nextLine();
        System.out.println("Introduce la id de la editorial del Libro");
        String id_editorial = scanner.nextLine();
        System.out.println("Introduce el id de la categoria del Libro");
        String id_categoria = scanner.nextLine();
        System.out.println("Introduce el nº de ejemplares del Libro");
        String ejemplares = scanner.nextLine();
        String str = "UPDATE libros SET titulo = '"+titulo+"', anio_publicacion ='"+anio_publicacion+"' ,genero='"+genero+"',id_editorial='"+id_editorial+"',id_categoria='"+id_categoria+"',Ejemplares='"+ejemplares+"' WHERE id_libro = '"+ ID +"'";
        try{
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            conn.close();
            System.out.println("Libro Actualizado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al actualizar los datos del libro");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }
    }



    public static void eliminarLibros(Scanner scanner, Connection conn){
        try{
            System.out.println("Introduce el id del ejemplar que quieres eliminar");
            String ID = scanner.nextLine();
            String str = "DELETE FROM libros WHERE id_libro ='" + ID + "'";
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            conn.close();
            System.out.println("Ejemplar Eliminado");
            System.out.println("Pulse ENTER para continuar...");
            scanner.nextLine();
        }catch(SQLException e){
            System.out.println("Error al eliminar el ejemplar");
            System.out.println("Pulse ENTER para volver...");
            scanner.nextLine();
        }
    }
    public static void VerLibros(Scanner scanner, Connection conn){
        try {
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
