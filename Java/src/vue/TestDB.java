package vue;

import java.sql.*;

public class TestDB {

    public static void main(String[] args) {
        try {

            // Lien vers la base
            String ip = "localhost";
            String port = "3306";
            String nomBase = "bd_iut";

            // Chaîne de connexion
            String conString = "jdbc:mysql://" + ip + ":" + port + "/" + nomBase;

            // Identifiants de connexion et mot de passe
            String nomConnexion = "root";
            String motDePasse = "mysql29";

            // Connexion
            Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse);

            // Envoi d’un requête générique
            String sql = "select * from Enseignant";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("nomEns"));
            }
            
        } catch (Exception e) {
            // gestion des exceptions
        }
    }
}