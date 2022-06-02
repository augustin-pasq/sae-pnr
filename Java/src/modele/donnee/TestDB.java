package modele.donnee;

import java.sql.*;

public class TestDB {

    public static Connection conn_ = null;

    /** The address of the database */
    public static String address = "mysql.ozna.me";

    /** The username to connect to the database */
    public static String username = "pnr1";

    /** The password to connect to the database */
    public static String password = "groupesaepnr1";

    /** The port to connect to the database */
    public static String port = "3306";

    /** The name of the database to connect to */
    public static String databaseName = "PNR";

    public static void main(String[] args) {
        MySQLConnection();
    }

    public static void MySQLConnection() {
        
        // The URL of the database is generated with the text typed in the fields
        // "address" and "port"
        String jdbcURL = "jdbc:mysql://" + address + ":" + port + "/" + databaseName;

        try {
            conn_ = DriverManager.getConnection(jdbcURL, username, password);

            // This display a message if the connection is successfully established
            System.out.println("Connection established.");
        } catch (SQLException se) {
            // This display a message if the connection failed + the reason of the error
            System.out.println("Connection failed: " + se.getMessage());
        }
    }
}