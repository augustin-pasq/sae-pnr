package modele.donnee;

import controleur.NoInternetException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class to interact with the database
 *
 * @author Groupe SAE PNR 1D1
 */
public class UseDatabase {

    /**
     * The address of the database
     */
    public static String address = "mysql.ozna.me";

    /**
     * The username to connect to the database
     */
    public static String username = "pnr1";

    /**
     * The password to connect to the database
     */
    public static String password = "groupesaepnr1";

    /**
     * The port to connect to the database
     */
    public static String port = "3306";

    /**
     * The name of the database to connect to
     */
    public static String databaseName = "PNR";

    /**
     * Makes the connection to the database
     *
     * @return the connection to the database
     */
    public static Connection MySQLConnection() {

        Connection connection = null;

        // The URL of the database is generated with the text typed in the fields
        // "address" and "port"
        String jdbcURL = "jdbc:mysql://" + address + ":" + port + "/" + databaseName;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException se) {
            // This display a message if the connection failed + the reason of the error
            System.out.println("Connection failed: " + se.getMessage());
        }

        return connection;
    }

    /**
     * Allows to make an update query (INSERT, UPDATE, DELETE) in the database
     *
     * @param query the query to make
     * @return a boolean indicating whether the update is successful
     */
    public static boolean updateQuery(String query) {
        boolean success = false;

        try {
            Statement stmt = MySQLConnection().createStatement();
            stmt.executeUpdate(query);
            success = true;
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing query:" + e.getMessage());
        }

        return success;
    }

    /**
     * Allows to make a SELECT query in the database
     *
     * @param query the query to make
     * @return an ArrayList of the results of the query
     */
    public static ArrayList<ArrayList<String>> selectQuery(String query) {
        ArrayList<ArrayList<String>> output = null;

        try {
            Statement stmt = MySQLConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> columnsNames = new ArrayList<String>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                columnsNames.add(rs.getMetaData().getColumnName(i));
            }

            output = new ArrayList<ArrayList<String>>();
            output.add(columnsNames);

            while (rs.next()) {
                ArrayList<String> line = new ArrayList<String>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    line.add(rs.getString(i));
                }
                output.add(line);
            }

            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error executing query:" + e.getMessage());
        }

        return output;
    }

    /**
     * Authenticate a user
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return a boolean indicating whether the user is authenticated
     */
    public static boolean authenticateUser(String username, String password) throws NoInternetException {
        boolean success = false;

        try {
            String query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
            ArrayList<ArrayList<String>> results = selectQuery(query);
            if (results.size() > 1) {
                success = true;
            }
        } catch (NullPointerException e) {
            throw new NoInternetException("Not connected to internet");
        }

        return success;
    }
}