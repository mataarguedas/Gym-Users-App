package fit_zone.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        var dataBase =  "fit_zone_db";
        var url =  "jdbc:mysql://localhost:3306/" + dataBase;
        var user = "root";
        var password = "Reactjs69!";
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conexion = DriverManager.getConnection(url, user, password);

                }
                catch(Exception e){
                    System.out.println("Error while connecting the DB" + e.getMessage());
                }
                return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if(conexion != null){
            System.out.println("Succesful conexion " + conexion);
        }
        else{
            System.out.println("Error while connecting");

        }
    }
}
