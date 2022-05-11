package modele.donnee;

/**
 * Class to represent an observer
 *
 * @author Groupe PNR 1D1
 */
public class Observateur {

    private int idObservateur;
    private String nom;
    private String prenom;

    /**
     * Constructor of the class
     *
     * @param id       the id of the observer
     * @param leNom    the name of the observer
     * @param lePrenom the first name of the observer
     */
    public Observateur(int id, String leNom, String lePrenom) {
        // TODO - implement Observateur.Observateur
        this.idObservateur = id;
        this.nom = leNom;
        this.prenom = lePrenom;
    }
}