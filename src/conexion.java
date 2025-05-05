import java.sql.*;
import java.util.Scanner;

public class conexion{
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        String url="jdbc:mysql://127.0.0.1:3306/biblioteca";
        String user="Grupo3";
        String password="Reto3";
        try{
            //CONEXION A LA BASE DE DATOS
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion exitosa a la base de datos");
            System.out.println("Elige una opción:");
            System.out.println("1. Autores");
            System.out.println("2. Clientes");
            System.out.println("3. Editoriales");
            System.out.println("4. Ejemplares");
            System.out.println("5. Empleados");
            System.out.println("6. Libros");
            System.out.println("7. Prestamos");
            System.out.println("8. Quejas");
            int tabla = scanner.nextInt();
            scanner.nextLine();
            switch (tabla){
                case 1:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Autor");
                System.out.println("2. Actualizar Autor");
                System.out.println("3. Eliminar Autor");
                System.out.println("4. Ver Autor");
                int opcion1 = scanner.nextInt();
                scanner.nextLine();
                switch (opcion1){
                    case 1:
                    System.out.println("Introduce el nombre del autor");
                    String nombre = scanner.next();
                    scanner.nextLine();
                    System.out.println("Introduce los apellidos del autor");
                    String apellidos = scanner.next();
                    scanner.nextLine();
                    String str = "INSERT INTO autores(nombre,apellidos)";
                    str+= "VALUES ('"+ nombre +"','"+ apellidos +"')";
                    Statement stmt=conn.createStatement();
                    stmt.executeUpdate(str);
                    System.out.println("Registro Añadido");
                    break;
                }
                break;
                case 2:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Cliente");
                System.out.println("2. Actualizar Cliente");
                System.out.println("3. Eliminar Cliente");
                System.out.println("4. Ver Cliente");
                int opcion2 = scanner.nextInt();
                scanner.nextLine();
                break;
                case 3:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Editorial");
                System.out.println("2. Actualizar Editorial");
                System.out.println("3. Eliminar Editorial");
                System.out.println("4. Ver Editorial");
                int opcion3 = scanner.nextInt();
                scanner.nextLine();
                break;
                case 4:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Ejemplares");
                System.out.println("2. Actualizar Ejemplares");
                System.out.println("3. Eliminar Ejemplares");
                System.out.println("4. Ver Ejemplares");
                int opcion4 = scanner.nextInt();
                scanner.nextLine();
                break;
                case 5:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Empleados");
                System.out.println("2. Actualizar Empleados");
                System.out.println("3. Eliminar Empleados");
                System.out.println("4. Ver Empleados");
                int opcion5 = scanner.nextInt();
                scanner.nextLine();
                break;
                case 6:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Libro");
                System.out.println("2. Actualizar Libro");
                System.out.println("3. Eliminar Libro");
                System.out.println("4. Ver Libros");
                break;
                case 7:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Prestamo");
                System.out.println("2. Actualizar Prestamo");
                System.out.println("3. Eliminar Prestamo");
                System.out.println("4. Ver Prestamos");
                break;
                case 8:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Queja");
                System.out.println("2. Actualizar Queja");
                System.out.println("3. Eliminar Queja");
                System.out.println("4. Ver Quejas");
                break;

                default:
                    System.out.println("Opción no válida.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}