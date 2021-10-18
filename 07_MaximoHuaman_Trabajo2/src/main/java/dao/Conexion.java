package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    public static Connection cnx = null;
    
    public static Connection conectar() throws  Exception{
        
        try {
            
            String user = "sa";
            String pwd = "1234";
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=dbFarmavic";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);            
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión");
            System.out.println("error de conexion " + e.getMessage());
        }
        return cnx;
    }
    
    public void cerrar() throws Exception{
        if(cnx !=null){
            cnx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        if(cnx!=null){
            System.out.println("Conectado");
        }else{
            System.out.println("fijate el driver, conexión cerrada, etc....");
        }
    }
    
    
    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }
    
}
