package modele.donnee;

import java.util.ArrayList;

/**
 * Classe représentant un chouette
 *
 * @author Groupe PNR 1D1
 */
public class Chouette implements lObs<ObsChouette> {

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
        this.setIdChouette(id);
        this.setSexe(leSexe);
        this.setEspece(lEspece);
        this.lesObservations = new ArrayList<ObsChouette>();
    }

    public Sexe getSexe() {
        return sexe;
    }

    public EspeceChouette getEspece() {
        return espece;
    }

    public String getIdChouette() {
        return idChouette;
    }

    public ArrayList<ObsChouette> getLesObservations() {
        return lesObservations;
    }

    public void setSexe(Sexe sexe) throws NullPointerException {
        if (sexe == null) throw new NullPointerException("Sexe can't be null");
        else this.sexe = sexe;
    }

    public void setEspece(EspeceChouette espece) throws NullPointerException {
        if (espece == null) throw new NullPointerException("Espece can't be null");
        else this.espece = espece;
    }

    public void setIdChouette(String idChouette) throws NullPointerException {
        if (idChouette == null) throw new NullPointerException("Id can't be null");
        else this.idChouette = idChouette;
    }

    public void setLesObservations(ArrayList<ObsChouette> lesObservations) throws NullPointerException {
        if (lesObservations == null) throw new NullPointerException("Observations can't be null");
        else this.lesObservations = lesObservations;
    }

    /**
     * @see modele.donnee.lObs#ajouterUneObs(ObsChouette)
     */
    public void ajouterUneObs(ObsChouette obs) {
        this.lesObservations.add(obs);
    }

    /**
     * @see modele.donnee.lObs#ajouterPlsObs(ArrayList)
     */
    public void ajouterPlsObs(ArrayList<ObsChouette> obs) {
        // obs.forEach(this::ajouterUneObs); pas borne-proof
        this.lesObservations.addAll(obs);
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