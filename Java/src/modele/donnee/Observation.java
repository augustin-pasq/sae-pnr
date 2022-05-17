package modele.donnee;

import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Class representing an observation
 *
 * @author Groupe SAE PNR 1D1
 */
public abstract class Observation {
    /**
     * The place of observation
     */
    protected Lieu lieuObs;
    /**
     * The list of observers of the observation
     */
    protected ArrayList<Observateur> lesObservateurs;
    /**
     * The id of the observation
     */
    protected int idObs;
    /**
     * The date of the observation
     */
    protected Date dateObs;
    /**
     * The time of the observation
     */
    protected Time heureObs;

    /**
     * Constructor for the class Observation
     *
     * @param id           Observation's id
     * @param date         Observation's date
     * @param heure        Observation's time
     * @param lieu         Observation's place
     * @param observateurs Observation's observers
     */
    public Observation(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs) {
        this.setId(id);
        this.setDate(date);
        this.setHeure(heure);
        this.setLieu(lieu);
        this.setObservateurs(observateurs);
    }

    /**
     * Getter for the place of observation
     *
     * @return Place of observation
     */
    public Lieu getLieu() {
        return this.lieuObs;
    }

    /**
     * Getter for the list of observers
     *
     * @return List of observers
     */
    public ArrayList<Observateur> getObservateurs() {
        return this.lesObservateurs;
    }

    /**
     * Getter for the id of the observation
     *
     * @return Id of the observation
     */
    public int getId() {
        return this.idObs;
    }

    /**
     * Getter for the date of the observation
     *
     * @return Date of the observation
     */
    public Date getDate() {
        return this.dateObs;
    }

    /**
     * Getter for the time of the observation
     *
     * @return Time of the observation
     */
    public Time getHeure() {
        return this.heureObs;
    }

    /**
     * Setter for the place of observation
     *
     * @param lieu Place of observation
     */
    public void setLieu(Lieu lieu) {
        this.lieuObs = lieu;
    }

    /**
     * Setter for the list of observers
     *
     * @param observateurs List of observers
     */
    public void setObservateurs(ArrayList<Observateur> observateurs) {
        this.lesObservateurs = new ArrayList<Observateur>();
        this.lesObservateurs.addAll(observateurs);
    }

    /**
     * Getter for the id of the observation
     *
     * @param id Id of the observation
     */
    public void setId(int id) {
        this.idObs = id;
    }

    /**
     * Setter for the date of the observation
     *
     * @param date Date of the observation
     */
    public void setDate(Date date) {
        this.dateObs = date;
    }

    /**
     * Getter for the time of observations
     *
     * @param heure Time of the observation
     */
    public void setHeure(Time heure) {
        this.heureObs = heure;
    }

    /**
     * Add an observer to the observation
     *
     * @param o Observateur to add
     */
    public void ajouteObservateur(Observateur o) {
        this.lesObservateurs.add(o);
    }

    /**
     * Remove an observer from the observation
     *
     * @param idObservateur Id of the observer to remove
     */
    public void retireObservateur(int idObservateur) {
        this.lesObservateurs.remove(idObservateur);
    }

    /**
     * Getter for observed species
     *
     * @return observed species
     */
    public abstract EspeceObservee especeObs();
}