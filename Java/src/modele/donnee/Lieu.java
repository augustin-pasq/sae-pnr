package modele.donnee;

/**
 * Class representing a place
 *
 * @author Groupe SAE PNR 1D1
 */
public class Lieu {

    /**
     * The x lambert coordinate of the observation
     */
    private double xCoord;

    /**
     * The y lambert coordinate of the observation
     */
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
        if (x < 0 || x > 1300000) throw new IndexOutOfBoundsException("Coordinate is out of range");
        else this.xCoord = x;
    }

    /**
     * Setter for the y coordinate
     *
     * @param y the y coordinate
     */
    public void setYCoord(double y) {
        if (y < 6000000 || y > 7200000) throw new IndexOutOfBoundsException("Coordinate is out of range");
        this.yCoord = y;
    }
}