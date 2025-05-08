// import java.sql.*;
// import java.util.Scanner;

public class Menu {
    public static void menuEmpleados(){
        Scanner scanner = new Scanner(System.in);
        int opcionME = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Clientes");
            System.out.println("2. Ejemplares");
            System.out.println("3. Empleados");
            System.out.println("4. Cerrar sesion");
            opcionME = scanner.nextInt();
            scanner.nextLine();
            switch (opcionME){
                case 1:
                    Cliente.usoClientes();
                    int opcion1 = scanner.nextInt();
                    scanner.nextLine();
                    if(opcion1==1){
                        Cliente.insertClientes();
                    }else if(opcion1==2){
                    
                }else if(opcion1==3){

                }else{

                }
                break;
                case 2:
                Ejemplares.usoEjemplares();
                int opcion2 = scanner.nextInt();
                scanner.nextLine();
                if(opcion2==1){
                    Ejemplares.inserEjemplares();
                }else if(opcion2==2){

                }else if(opcion2==3){

                }else{

                }
                case 3:
                Empleados.usoEmpleados();
                int opcion3 = scanner.nextInt();
                scanner.nextLine();
                if(opcion3==1){
                    Empleados.insertEmpleados();
                }else if(opcion3==2){

                }else if(opcion3==3){

                }else{

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

