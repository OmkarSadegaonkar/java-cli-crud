package dbutil;

/*  dbUtil - Database connection classes, useful for jdbc connection*/
import java.sql.*;

public class DBUtil {

    // static method that creates a connection to the database and return the
    // connection object
    public static Connection getConnection() {
        Connection conn = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/ZLF4MxdoLx", "ZLF4MxdoLx",
                    "CzFgd3NfAX");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // close connection
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
