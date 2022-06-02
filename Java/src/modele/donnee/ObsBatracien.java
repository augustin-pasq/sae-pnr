package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Class representing batracien observation
 *
 * @author Groupe SAE PNR 1D1
 */
public class ObsBatracien extends Observation {
    /**
     * The batracien's species
     */
    private EspeceBatracien espece;
    /**
     * The number of adult batraciens
     */
    private int nombreAdultes;
    /**
     * The number af amplexus
     */
    private int nombreAmplexus;
    /**
     * The number of tagpoles
     */
    private int nombreTetard;
    /**
     * The number of egg-lays
     */
    private int nombrePonte;

    /**
     * Constructor for the ObsBatracien class
     *
     * @param id           the id of the observation
     * @param date         the date of the observation
     * @param heure        the time of observation
     * @param lieu         the place of observation
     * @param observateurs the observers
     * @param resObs       data about the observation
     * @param lEspece      the species of the egg-lay
     * @throws IllegalArgumentException if the resObs parameter is null
     */
    public ObsBatracien(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, int[] resObs, EspeceBatracien lEspece) throws IllegalArgumentException, NullPointerException {
        super(id, date, heure, lieu, observateurs);
        if (resObs == null) throw new NullPointerException("resObs is null");
        if (resObs.length != 4) throw new IllegalArgumentException("Expected 4 results, got " + resObs.length);
        this.setEspece(lEspece);
        this.setNombreAdultes(resObs[0]);
        this.setNombreAmplexus(resObs[1]);
        this.setNombreTetard(resObs[2]);
        this.setNombrePonte(resObs[3]);
    }

    /**
     * Getter for the batracien's species
     *
     * @return the batracien's species
     */
    public EspeceBatracien getEspece() {
        return espece;
    }

    /**
     * Getter for the number of adult batraciens
     *
     * @return the number of adult batraciens
     */
    public int getNombreAdultes() {
        return nombreAdultes;
    }

    /**
     * Getter for the number of amplexus
     *
     * @return the number of amplexus
     */
    public int getNombreAmplexus() {
        return nombreAmplexus;
    }

    /**
     * Getter for the number of tagpoles
     *
     * @return the number of tagpoles
     */
    public int getNombreTetard() {
        return nombreTetard;
    }

    /**
     * Getter for the number of egg-lays
     *
     * @return the number of egg-lays
     */
    public int getNombrePonte() {
        return nombrePonte;
    }

    /**
     * Setter for the batracien's species
     *
     * @param espece the batracien's species
     * @throws IllegalArgumentException if the species is null
     */
    public void setEspece(EspeceBatracien espece) throws IllegalArgumentException {
        if (espece != null) this.espece = espece;
        else throw new NullPointerException("Espece is null");
    }

    /**
     * Setter for the number of adult batraciens
     *
     * @param nombreAdultes the number of adult batraciens
     * @throws IllegalArgumentException if the number of adult batraciens is negative
     */
    public void setNombreAdultes(int nombreAdultes) throws IllegalArgumentException {
        if (nombreAdultes >= 0) this.nombreAdultes = nombreAdultes;
        else throw new IllegalArgumentException("Number of adults is negative");
    }

    /**
     * Setter for the number of amplexus
     *
     * @param nombreAmplexus the number of amplexus
     * @throws IllegalArgumentException if the number of amplexus is negative
     */
    public void setNombreAmplexus(int nombreAmplexus) throws IllegalArgumentException {
        if (nombreAmplexus >= 0) this.nombreAmplexus = nombreAmplexus;
        else throw new IllegalArgumentException("Number of amplexus is negative");
    }

    /**
     * Setter for the number of tagpoles
     *
     * @param nombreTetard the number of tagpoles
     * @throws IllegalArgumentException if the number of tagpoles is negative
     */
    public void setNombreTetard(int nombreTetard) throws IllegalArgumentException {
        if (nombreTetard >= 0) this.nombreTetard = nombreTetard;
        else throw new IllegalArgumentException("Number of tagpoles is negative");
    }

    /**
     * Setter for the number of egg-lays
     *
     * @param nombrePonte the number of egg-lays
     * @throws IllegalArgumentException if the number of egg-lays is negative
     */
    public void setNombrePonte(int nombrePonte) throws IllegalArgumentException {
        if (nombrePonte >= 0) this.nombrePonte = nombrePonte;
        else throw new IllegalArgumentException("Number of egg-lays is negative");
    }

    /**
     * Getter for the batracien species
     *
     * @return the batracien species
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.BATRACIEN;
    }
}