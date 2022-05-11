package modele.donnee;

public interface lObs {

	/**
	 * 
	 * @param obs
	 */
	void ajouterUneObs(T obs);

	/**
	 * 
	 * @param obs
	 */
	void ajouterPlsObs(ArrayList<T> obs);

	void videObs();

	/**
	 * 
	 * @param idObs
	 */
	boolean retireObs(int idObs);

	int nbObs();

}