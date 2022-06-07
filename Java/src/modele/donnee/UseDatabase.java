package modele.donnee;

import java.sql.*;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        selectQuery("SELECT lObservateur, COUNT(lObservation) nbObs FROM AObserve GROUP BY lObservateur HAVING nbObs > (SELECT AVG(nbObs) moy FROM (SELECT lObservateur, COUNT(lObservation) nbObs FROM AObserve GROUP BY lObservateur) B);");
    }

    /**
     * Makes the connection to the database
     *
     * @return the connection to the database
     */
    public static Connection MySQLConnection() {

        Connection connection = null;

        // The URL of the database is generated with the text typed in the fields "address" and "port"
        String jdbcURL = "jdbc:mysql://" + address + ":" + port + "/" + databaseName;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);

            // This display a message if the connection is successfully established
            System.out.println("Connection established.");

        } catch (SQLException se) {
            // This display a message if the connection failed + the reason of the error
            System.out.println("Connection failed: " + se.getMessage());
        }

        return connection;
    }

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

            System.out.println(output.toString());

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing query:" + e.getMessage());
        }

        return output;
    }

}