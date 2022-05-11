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
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Lieu(double x, double y) {
		this.xCoord = x;
		this.yCoord = y;
	}
}