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
            String str="INSERT INTO empleados";
            str+= "(id_empleado,nombre,rol)";
            str+= "VALUES ('"+(int)(Math.random()*1000)+"','Jose','Barrendero')";
            System.out.println(str);
            Statement stmt=conn.createStatement();
            stmt.executeUpdate(str);
            System.out.println("Registro Añadido");
            String sql="DELETE FROM empleados WHERE nombre = 'Jose'";
            stmt.executeUpdate(sql);
            System.out.println("Registro Eliminado");
            String upd="UPDATE empleados SET rol = 'Bibliotecario' WHERE nombre = 'Alberto'";
            stmt.executeUpdate(upd);
            System.out.println("Registro Actualizado");
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleados");

            while (rs.next()) {
            int id_empleado = rs.getInt("id_empleado");
            String nombre = rs.getString("nombre");
            String rol = rs.getString("rol");

            System.out.println("ID: " + id_empleado + ", Nombre: " + nombre + ", Rol: " + rol);
}

            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}