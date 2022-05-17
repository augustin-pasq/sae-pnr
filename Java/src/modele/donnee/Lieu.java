package modele.donnee;

/**
 * Class representing a place.
 *
 * @author Groupe SAE PNR 1D1
 */
public class Lieu {

    private double xCoord;
    private double yCoord;

    /**
     * Constructor for the class Lieu
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Lieu(double x, double y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * Getter for the x coordinate
     *
     * @return the x coordinate
     */
    public double getXCoord() {
        return xCoord;
    }

    /**
     * Getter for the y coordinate
     *
     * @return the y coordinate
     */
    public double getYCoord() {
        return yCoord;
    }

    /**
     * Setter for the x coordinate
     *
     * @param x the x coordinate
     */
    public void setXCoord(double x) {
        this.xCoord = x;
    }

    /**
     * Setter for the y coordinate
     *
     * @param y the y coordinate
     */
    public void setYCoord(double y) {
        this.yCoord = y;
    }
}