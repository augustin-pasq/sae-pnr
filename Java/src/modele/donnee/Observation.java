package modele.donnee;

import java.lang.reflect.Field;
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
    public void setLieu(Lieu lieu) throws IllegalArgumentException {
        if (lieu == null) throw new IllegalArgumentException("Lieu is null");
        else this.lieuObs = lieu;
    }

    /**
     * Setter for the list of observers
     *
     * @param observateurs List of observers
     */
    public void setObservateurs(ArrayList<Observateur> observateurs) throws IllegalArgumentException {
        if (observateurs == null) throw new IllegalArgumentException("Observateurs is null");
        else {
            this.lesObservateurs = new ArrayList<Observateur>();
            this.lesObservateurs.addAll(observateurs);
        }
    }

    /**
     * Getter for the id of the observation
     *
     * @param id Id of the observation
     */
    public void setId(int id) throws IllegalArgumentException {
        if (id < 0) throw new IllegalArgumentException("Id is negative");
        else this.idObs = id;
    }

    /**
     * Setter for the date of the observation
     *
     * @param date Date of the observation
     */
    public void setDate(Date date) throws IllegalArgumentException {
        if (date == null) throw new IllegalArgumentException("Date is null");
        else this.dateObs = date;
    }

    /**
     * Getter for the time of observations
     *
     * @param heure Time of the observation
     */
    public void setHeure(Time heure) throws IllegalArgumentException {
        if (heure == null) throw new IllegalArgumentException("Heure is null");
        else this.heureObs = heure;
    }

    /**
     * Add an observer to the observation
     *
     * @param o Observateur to add
     */
    public void ajouteObservateur(Observateur o) throws IllegalArgumentException {
        if (o == null) throw new IllegalArgumentException("Observateur is null");
        else this.lesObservateurs.add(o);
    }

    /**
     * Remove an observer from the observation
     *
     * @param idObservateur Id of the observer to remove
     */
    public void retireObservateur(int idObservateur) throws IndexOutOfBoundsException {
        if (idObservateur < 0 || idObservateur >= this.lesObservateurs.size())
            throw new IndexOutOfBoundsException("Id out of bounds");
        else this.lesObservateurs.remove(idObservateur);
    }

    /**
     * Getter for observed species
     *
     * @return observed species
     */
    public abstract EspeceObservee especeObs();

    /**
     * Generic toString method
     * @return String representation of the observation
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        Class<?> c = this.getClass();

        // Start with the class name and open bracket
        s.append(c.getSimpleName())
                .append("{");

        // Dynamically get the fields of the class
        for (Field f : c.getDeclaredFields()) {
            try {
                s.append(f.getName())
                        .append(", ");
            } catch (IllegalArgumentException ignored) {}
        }

        // Dynamically get the fields of the super-class
        for (Field f: this.getClass().getSuperclass().getDeclaredFields()) {
            try {
                s.append(f.getName())
                        .append(": ")
                        .append(f.get(this))
                        .append(", ");
            } catch (IllegalArgumentException | IllegalAccessException ignored) {}
        }

        // Remove the last ", " before returning
        return s.substring(0, s.length()-2) + "}";
    }
}