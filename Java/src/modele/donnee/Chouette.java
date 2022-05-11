package modele.donnee;

import java.util.ArrayList;

/**
 * Classe représentant un chouette
 *
 * @author Groupe PNR 1D1
 */
public class Chouette implements modele.donnee.lObs {

    private Sexe sexe;
    private EspeceChouette espece;
    private String idChouette;
    private ArrayList<ObsChouette> lesObservations;

    /**
     * Constructor for the class Chouette
     *
     * @param id      chouette identifier
     * @param leSexe  chouette sex
     * @param lEspece chouette species
     */
    public Chouette(String id, Sexe leSexe, EspeceChouette lEspece) {
        // TODO - create constructor
    }

    /**
     * @see modele.donnee.lObs#ajouterUneObs(T)
     */
    public void ajouterUneObs(T obs) {

    }

    /**
     * @see modele.donnee.lObs#ajouterPlsObs(ArrayList)
     */
    public void ajouterPlsObs(ArrayList<T> obs) {
        for (T o : obs) {
            this.ajouterUneObs(o);
        }
    }

    /**
     * @see modele.donnee.lObs#videObs()
     */
    public void videObs() {
        this.lesObservations.clear();
    }

    /**
     * @see modele.donnee.lObs#retireObs(int)
     */
    public boolean retireObs(int idObs) {
        boolean ret = true;
        if (idObs < 0 || idObs >= this.nbObs()) {
            return false;
        } else {
            this.lesObservations.remove(idObs);
        }
        return ret;
    }

    /**
     * @see modele.donnee.lObs#nbObs()
     */
    public int nbObs() {
        return this.lesObservations.size();
    }
}