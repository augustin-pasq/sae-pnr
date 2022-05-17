package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Class representing an otter observation
 * 
 * @author Groupe SAE PNR 1D1
 */
public class ObsLoutre extends Observation {

/**
 * Class representing an observation of a loutre
 *
 * @author Groupe SAE PNR 1D1
 */
public class ObsLoutre extends Observation {
    /**
     * The indice of loutre observed
     */
    private IndiceLoutre indice;

    /**
     * Constructor for the ObsLoutre class
     * @param id the id of the observation
     * @param date the date of the observation
     * @param heure the time of the observation
     * @param lieu the place of the observation
     * @param observateurs the list of the observers
     * @see modele.donnee.Observation#Observation(int, java.sql.Date, java.sql.Time, java.lang.String, java.util.ArrayList)
     */
    public ObsLoutre(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, IndiceLoutre indice) {
        super(id, date, heure, lieu, observateurs);
        this.setIndice(indice);
    }

    /**
     * Getter for the indice of the observation
     * @return the indice of the observation
     */
    public IndiceLoutre getIndice() {
        return indice;
    }

    /**
     * Setter for the indice of the observation
     * @param indice the indice of the observation
     */
    public void setIndice(IndiceLoutre indice) {
        this.indice = indice;
    }

    /**
     * Return the type of species observed
     * @return the type of species observed
     * @see Observation#especeObs()
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.LOUTRE;
    }

}