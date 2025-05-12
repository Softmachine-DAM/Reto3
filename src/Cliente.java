import java.util.Scanner;
import java.sql.*;

public class Cliente{
    public static void usoClientes(){
        Scanner scanner = new Scanner(System.in);
        int opcionUC = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Insertar Cliente");
            System.out.println("2. Actualizar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Ver Clientes");
            System.out.println("5.Volver");
            opcionUC = conexion.validarNumero();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");


            switch(opcionUC){
                case 1:
                insertClientes();
                break;
            }
        } while (opcionUC != 5);
    }
    public static void insertClientes(){
        String url="jdbc:mysql://127.0.0.1:3306/biblioteca";
        String user="Grupo3";
        String password="Reto3";
        Scanner scanner = new Scanner(System.in);
        try{
                Connection conn = DriverManager.getConnection(url,user,password);
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
                String str = "INSERT INTO clientes_1(Nombre,Apellidos,Correo,Telefono,Contraseña)";
                str+= "VALUES ('"+ nombre +"','"+ apellidos +"','"+ correo +"','"+ telefono +"','"+ contraseña +"')";
                Statement stmt=conn.createStatement();
                stmt.executeUpdate(str);
                System.out.println("Registro Añadido");
                System.out.println("Pulse ENTER para continuar...");
                scanner.nextLine();
            }catch(SQLException e){
                System.out.println("Error al insertar cliente");
        }
    }
}