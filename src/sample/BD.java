package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "db_suvorov";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";

     private Connection dbConnection = null;
    private Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connStr, LOGIN, PASSWORD);

        return dbConnection;
    }

     public void isConnected () throws SQLException, ClassNotFoundException {
        dbConnection = getDBConnection();
         System.out.println(dbConnection.isValid(1000));
     }

}
