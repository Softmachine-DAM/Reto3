import java.util.Scanner;

public class Empleados {
    public static void usoEmpleados(){
        Scanner scanner = new Scanner(System.in);
        int opcionUEM = 0;
        do {
            System.out.println("Elige una opción:");
            System.out.println("1. Insertar Empleado");
            System.out.println("2. Actualizar Empleado");
            System.out.println("3. Eliminar Empleado");
            System.out.println("4. Ver Empleados");
            System.out.println("5. Volver");
            opcionUEM = conexion.validarNumero();
            scanner.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        } while (opcionUEM != 5);
    }
    public static void insertEmpleados(){
        
    }
}
