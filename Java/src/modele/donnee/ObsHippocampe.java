package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Class representing an observation of a seahorse
 *
 * @author Groupe SAE PNR 1D1
 */
public class ObsHippocampe extends Observation {
    /**
     * The type of fishing
     */
    private Peche typePeche;
    /**
     * The species of the seahorse
     */
    private EspeceHippocampe espece;
    /**
     * The sex of the seahorse
     */
    private Sexe sexe;
    /**
     * The size of the seahorse
     */
    private double taille;
    /**
     * The gestation status of the seahorse
     */
    private boolean estGestant;

    /**
     * Constructor for the ObsHippocampe class
     *
     * @param id           the id of the observation
     * @param date         the date of the observation
     * @param heure        the time of the observation
     * @param lieu         the place of the observation
     * @param observateurs the observers of the observation
     * @param laTaille     the size of the seahorse
     * @param leTypePeche  the type of fishing
     * @param lEspece      the species of the seahorse
     * @param leSexe       the sex of the seahorse
     */
    public ObsHippocampe(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, double laTaille, Peche leTypePeche, EspeceHippocampe lEspece, Sexe leSexe) {
        super(id, date, heure, lieu, observateurs);
        this.setTaille(laTaille);
        this.setTypePeche(leTypePeche);
        this.setEspece(lEspece);
        this.setSexe(leSexe);
    }

    /**
     * Getter for the type of fishing
     *
     * @return the type of fishing
     */
    public Peche getTypePeche() {
        return this.typePeche;
    }

    /**
     * Getter for the species of seahorse
     *
     * @return the species of seahorse
     */
    public EspeceHippocampe getEspece() {
        return this.espece;
    }

    /**
     * Getter for the sex of the seahorse
     *
     * @return the sex of the seahorse
     */
    public Sexe getSexe() {
        return this.sexe;
    }

    /**
     * Getter for the size of the seahorse
     *
     * @return the size of the seahorse
     */
    public double getTaille() {
        return this.taille;
    }

    /**
     * Getter for the gestation status of the seahorse
     *
     * @return the gestation status of the seahorse
     */
    public boolean getEstGestant() {
        return this.estGestant;
    }

    /**
     * Setter for the type of fishing
     *
     * @param typePeche the type of fishing
     */
    public void setTypePeche(Peche typePeche) {
        if (typePeche != null) this.typePeche = typePeche;
        else throw new IllegalArgumentException("The fishing type is null");
    }

    /**
     * Setter for the species of seahorse
     *
     * @param espece the species of seahorse
     */
    public void setEspece(EspeceHippocampe espece) {
        if (espece != null) this.espece = espece;
        else throw new IllegalArgumentException("The species is null");
    }

    /**
     * Setter for the sex of the seahorse
     *
     * @param sexe the sex of the seahorse
     */
    public void setSexe(Sexe sexe) {
        if (sexe != null) this.sexe = sexe;
        else throw new IllegalArgumentException("The sex is null");
    }

    /**
     * Setter for the size of the seahorse
     *
     * @param taille the new size of the seahorse
     */
    public void setTaille(double taille) {
        if (taille > 0) this.taille = taille;
        else throw new IllegalArgumentException("The size is negative");
    }

    /**
     * Setter for the gestation status of the seahorse
     *
     * @param estGestant the new gestation status of the seahorse
     */
    public void setEstGestant(boolean estGestant) {
        switch (this.sexe) {
            case FEMELLE -> {
                if (estGestant) throw new IllegalArgumentException("A female cannot be gestating");
                else this.estGestant = false;
            }
            case MALE -> this.estGestant = estGestant;
            case INCONNU -> {
                if (estGestant) {
                    this.setSexe(Sexe.MALE);
                }
                this.estGestant = estGestant;
            }
        }
    }

    /**
     * Getter for the observed species
     *
     * @return The observed species
     */
    public EspeceObservee especeObs() {
        return EspeceObservee.HIPPOCAMPE;
    }
}