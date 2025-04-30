import java.sql.*;

public class conexion{
    public static void main(String[]args){
        String url="jdbc:mysql://127.0.0.1:3306/biblioteca";
        String user="Grupo3";
        String password="Reto3";
        try{
            //CONEXION A LA BASE DE DATOS
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion exitosa a la base de datos");
            String swid;
            Statement stmt=conn.createStatement();
            System.out.println("Registro Añadido");
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}