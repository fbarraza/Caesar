package Connexio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio extends ModelConnexio {

    private Connection connection_;

    public Connection ConnectionJDBC() throws SQLException, ClassNotFoundException, Exception {

        Class.forName("com.mysql.jdbc.Driver");
        connection_ = DriverManager.getConnection(getUrl(), getLogin(), getPassword());
        if (connection_ != null) {
            //	st_ = connection_ . createStatement () ;
            System.out.println(" Conexión a base de datos " + getDb() + " correcta . ");
        } else {
            System.out.println(" Conexión fallida. ");
        }
        return connection_;
    }

}