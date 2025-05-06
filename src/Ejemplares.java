import java.sql.*;
import java.util.Scanner;

public class Ejemplares {
    public static void usoEjemplares(){
        System.out.println("Elige una opción:");
        System.out.println("1. Insertar Ejemplar");
        System.out.println("2. Actualizar Ejemplar");
        System.out.println("3. Eliminar Ejemplar");
        System.out.println("4. Ver Ejemplar");
}
    public static void inserEjemplares(){
    String url="jdbc:mysql://127.0.0.1:3306/biblioteca";
    String user="Grupo3";
    String password="Reto3";
    Scanner scanner = new Scanner(System.in);
    try{
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Introduce el estado del ejemplar");
                    String estado = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Introduce el codigo del ejemplar");
                    String cod_libro = scanner.nextLine();
                    scanner.nextLine();
                    String str="INSERT INTO ejemplares";
                    str+="VALUES('"+estado+"',"+cod_libro+"')";
                    Statement stmt=conn.createStatement();
                    stmt.executeUpdate(str);
                    System.out.println("Ejemplar Añadido");
                }catch(SQLException e){

                }
    }
}
