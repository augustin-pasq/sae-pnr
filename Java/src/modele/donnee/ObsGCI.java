package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Class representing a GCI observation
 *
 * @author Groupe SAE PNR 1D1
 */
public class ObsGCI extends Observation {
    /**
     * The content of the observed nest
     */
    private ContenuNid natureObs;
    /**
     * The number of elements in the nest
     */
    private int nombre;

    /**
     * Constructor for the ObsGCI class
     *
     * @param id           The id of the observation
     * @param date         The date of the observation
     * @param heure        The time of the observation
     * @param lieu         The place of the observation
     * @param observateurs The observers of the observation
     * @param nature       The content of the observed nest
     * @param leNombre     The number of elements in the nest
     */
    public ObsGCI(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, ContenuNid nature, int leNombre) {
        super(id, date, heure, lieu, observateurs);
        this.setNatureObs(nature);
        this.setNombre(leNombre);
    }

    /**
     * Getter for the nature of the content of the observed nest
     *
     * @return The content of the observed nest
     */
    public ContenuNid getNatureObs() {
        return natureObs;
    }

    /**
     * Getter for the number of elements in the nest
     *
     * @return The number of elements in the nest
     */
    public int getNombre() {
        return nombre;
    }

    /**
     * Setter for the nature of the content of the observed nest
     *
     * @param natureObs The content of the observed nest
     * @throws IllegalArgumentException If the content of the observed nest is null
     */
    public void setNatureObs(ContenuNid natureObs) throws IllegalArgumentException {
        if (natureObs != null) this.natureObs = natureObs;
        else throw new IllegalArgumentException("Observation nature is null");
    }

    /**
     * Setter for the number of elements in the nest
     *
     * @param nombre The number of elements in the nest
     * @throws IllegalArgumentException If the number of elements is negative
     */
    public void setNombre(int nombre) throws IllegalArgumentException {
        if (nombre >= 0) this.nombre = nombre;
        else throw new IllegalArgumentException("Observation nombre is null");
    }

    /**
     * Getter for the observed species
     *
     * @return The observed species
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.GCI;
    }
}