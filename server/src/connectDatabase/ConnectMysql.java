package connectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class ConnectMysql{
    private static String DB_URL = "jdbc:mysql://localhost:3306/quantrimang";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}