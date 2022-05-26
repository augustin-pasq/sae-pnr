package modele.traitement;

import modele.donnee.EspeceObservee;
import modele.donnee.Lieu;
import modele.donnee.Observation;

import java.sql.Date;

/**
 * Class representing a vertex in the graph.
 *
 * @author Groupe SAE PNR 1D1
 */
public class Sommet {
    /**
     * Identifier of the vertex
     */
    private int id;
    /**
     * The coordinates of the vertex
     */
    private Lieu coordLieu;
    /**
     * Date of the vertex creation
     */
    private Date date;
    /**
     * The species observed at the vertex
     */
    private EspeceObservee espece;

    /**
     * Constructor for the vertex
     *
     * @param id        the identifier of the vertex
     * @param coordLieu the coordinates of the vertex
     * @param date      the date of the vertex creation
     * @param espece    the species observed at the vertex
     * @throws IllegalArgumentException if the identifier is negative or if the coordinates, the date or species are null
     */
    public Sommet(int id, Lieu coordLieu, Date date, EspeceObservee espece) throws IllegalArgumentException {
        this.setId(id);
        this.setCoordLieu(coordLieu);
        this.setDate(date);
        this.setEspece(espece);
    }

    /**
     * Secondary constructor for the vertex
     *
     * @param obs the observation of the vertex
     * @throws IllegalArgumentException if the observation is null
     */
    public Sommet(Observation obs) throws IllegalArgumentException {
        if (obs == null) throw new IllegalArgumentException("obs ne peut être null");
        else {
            this.setId(obs.getId());
            this.setCoordLieu(obs.getLieu());
            this.setDate(obs.getDate());
            this.setEspece(obs.especeObs());
        }
    }

    /**
     * Copy-constructor for the vertex
     *
     * @param sommet the vertex to copy
     */
    public Sommet(Sommet sommet) {
        this.setId(sommet.getId());
        this.setCoordLieu(sommet.getCoordLieu());
        this.setDate(sommet.getDate());
        this.setEspece(sommet.getEspece());
    }

    /**
     * Getter for the identifier of the vertex
     *
     * @return the identifier of the vertex
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the identifier of the vertex
     *
     * @param id the identifier of the vertex
     * @throws IllegalArgumentException if the identifier is negative
     */
    public void setId(int id) throws IllegalArgumentException {
        if (id < 0) throw new IllegalArgumentException("id cannot be negative");
        else this.id = id;
    }

    /**
     * Getter for the species observed at the vertex
     *
     * @return the species observed at the vertex
     */
    public EspeceObservee getEspece() {
        return espece;
    }

    /**
     * Setter for the species observed at the vertex
     *
     * @param espece the species observed at the vertex
     * @throws IllegalArgumentException if the species is null
     */
    public void setEspece(EspeceObservee espece) throws IllegalArgumentException {
        if (espece == null) throw new IllegalArgumentException("espece cannot be null");
        else this.espece = espece;
    }

    /**
     * Getter for the coordinates of the vertex
     *
     * @return the coordinates of the vertex
     */
    public Lieu getCoordLieu() {
        return coordLieu;
    }

    /**
     * Setter for the coordinates of the vertex
     *
     * @param coordLieu the coordinates of the vertex
     * @throws IllegalArgumentException if the coordinates are null
     */
    public void setCoordLieu(Lieu coordLieu) throws IllegalArgumentException {
        if (coordLieu == null) throw new IllegalArgumentException("coordLieu cannot be null");
        else this.coordLieu = coordLieu;
    }

    /**
     * Getter for the date of the vertex creation
     *
     * @return the date of the vertex creation
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setter for the date of the vertex creation
     *
     * @param date the date of the vertex creation
     * @throws IllegalArgumentException if the date is null
     */
    public void setDate(Date date) throws IllegalArgumentException {
        if (date == null) throw new IllegalArgumentException("date cannot be null");
        else this.date = date;
    }

    /**
     * Calculate the distance between two vertices
     *
     * @param som the vertex to calculate the distance to
     * @return the distance between the two vertices
     */
    public double calculeDist(Sommet som) throws IllegalArgumentException {
        double ret;
        if (som == null) throw new IllegalArgumentException("som cannot be null");
        else {
            double dX = Math.pow(this.getCoordLieu().getXCoord() - som.getCoordLieu().getXCoord(), 2);
            double dY = Math.pow(this.getCoordLieu().getYCoord() - som.getCoordLieu().getYCoord(), 2);
            ret = Math.sqrt(dX + dY);
        }
        return ret;
    }

    /**
     * Format the vertex to a string
     *
     * @return the vertex formatted as a string
     */
    public String toString() {
        return "Sommet{" + this.getId() + ", " + this.getDate() + ", " + this.getCoordLieu() + ", " + this.getEspece() + '}';
    }

}
