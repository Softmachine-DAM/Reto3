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
            //MENU
            System.out.println("Elige una opción:");
            System.out.println("1. Clientes");
            System.out.println("2. Ejemplares");
            System.out.println("3. Empleados");
            int tabla = scanner.nextInt();
            scanner.nextLine();
            switch (tabla){
                case 1:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Cliente");
                System.out.println("2. Actualizar Cliente");
                System.out.println("3. Eliminar Cliente");
                System.out.println("4. Ver Clientes");
                int opcion1 = scanner.nextInt();
                scanner.nextLine();
                switch (opcion1){
                    case 1:
                    System.out.println("Introduce el nombre del Cliente");
                    String nombre = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Introduce los apellidos del Cliente");
                    String apellidos = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Introduce el correo del Cliente");
                    String correo = scanner.next();
                    scanner.nextLine();
                    System.out.println("Introduce el telefono del Cliente");
                    String telefono = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Introduce la contraseña");
                    String contraseña = scanner.nextLine();
                    scanner.nextLine();
                    String str = "INSERT INTO clientes_1(Nombre,Apellidos,Correo,Telefono,Contraseña)";
                    str+= "VALUES ('"+ nombre +"','"+ apellidos +"','"+ correo +"','"+ telefono +"','"+ contraseña +"')";
                    Statement stmt=conn.createStatement();
                    stmt.executeUpdate(str);
                    System.out.println("Registro Añadido");
                    break;
                    case 2:

                    break;
                    case 3:

                    break;
                    case 4:

                    break;
                }
                break;
                case 2:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Ejemplares");
                System.out.println("2. Actualizar Ejemplares");
                System.out.println("3. Eliminar Ejemplares");
                System.out.println("4. Ver Ejemplares");
                int opcion2 = scanner.nextInt();
                scanner.nextLine();
                switch(opcion2){
                    case 1:
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
                    break;
                    case 2:

                    break;
                    case 3:

                    break;
                    case 4:

                    break;
                }
                break;
                case 3:
                System.out.println("Elige una opción:");
                System.out.println("1. Insertar Empleados");
                System.out.println("2. Actualizar Empleados");
                System.out.println("3. Eliminar Empleados");
                System.out.println("4. Ver Empleados");
                int opcion3 = scanner.nextInt();
                scanner.nextLine();
                switch(opcion3){
                    case 1:

                    break;
                    case 2:

                    break;
                    case 3:

                    break;
                    case 4:

                    break;
                }
                break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }catch(SQLException e){
            //e.printStackTrace();
            System.out.print("Error en la conexión ");

        }
    }
}