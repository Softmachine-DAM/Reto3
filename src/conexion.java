import java.sql.*;

public class conexion{
    public static void main(String[]args){
        String url="jdbc:mysql://127.0.0.1:3306/bdmarkel1";
        String user="alumno1";
        String password="alumno1";
        try{
            //CONEXION A LA BASE DE DATOS
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("Conexion exitosa a la base de datos");
            String swid;
            String str="INSERT INTO TUSUARIOS";
            str+= "(USUCOD,USUNOM,USUAPEL,USUDNI) ";
            str+= "VALUES ('"+(int)(Math.random()*1000)+"','','','')";
            System.out.println(str);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Registro Añadido");
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}