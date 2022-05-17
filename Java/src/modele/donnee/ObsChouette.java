package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Class representing an owl observation
 */
public class ObsChouette extends Observation {
    /**
     * The type of the observation
     */
    private TypeObservation typeObs;

    /**
     * Constructor for an owl observation
     *
     * @param id           the id of the observation
     * @param date         the date of the observation
     * @param heure        the time of the observation
     * @param lieu         the place of the observation
     * @param observateurs the observers of the observation
     * @param type         the type of the observation
     */
    public ObsChouette(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, TypeObservation type) {
        super(id, date, heure, lieu, observateurs);
        this.typeObs = type;
        throw new UnsupportedOperationException();
    }

    /**
     * Getter for the type of the observation
     *
     * @return the type of the observation
     */
    public TypeObservation getTypeObs() {
        return typeObs;
    }

    /**
     * Setter for the type of the observation
     *
     * @param typeObs the type of the observation
     */
    public void setTypeObs(TypeObservation typeObs) {
        if (typeObs != null) this.typeObs = typeObs;
        else throw new IllegalArgumentException("Type d'observation null");
    }

    /**
     * Getter for the observed species
     *
     * @return the observed species
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.CHOUETTE;
    }
}