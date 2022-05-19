package modele.donnee;

import java.util.ArrayList;

/**
 * Set of classes to be implemented on an observation
 *
 * @author Groupe PNR 1D1
 */
public interface IObs<T> {

    /**
     * Add an observation to the list
     *
     * @param obs the observation to add
     */
    void ajouterUneObs(T obs);

    /**
     * Add several observations to the list
     *
     * @param obs the observations to add
     */
    void ajouterPlsObs(ArrayList<T> obs);

    /**
     * Remove all observations from the list
     */
    void videObs();

    /**
     * Remove an observation from the list
     *
     * @param idObs the id of the observation to remove
     * @return true if the observation has been removed, false otherwise
     */
    boolean retireObs(int idObs);

    /**
     * Get the number of observations
     *
     * @return the number of observations
     */
    int nbObs();

}