package modele.donnee;

public class ObsHippocampe extends Observation {

	private Peche typePeche;
	private EspeceHippocampe espece;
	private Sexe sexe;
	private double taille;
	private boolean estGestant;

	/**
	 * 
	 * @param id
	 * @param date
	 * @param heure
	 * @param lieu
	 * @param observateurs
	 * @param laTaille
	 * @param leTypePeche
	 * @param lEspece
	 * @param leSexe
	 */
	public ObsHippocampe(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, double laTaille, Peche leTypePeche, EspeceHippocampe lEspece, Sexe leSexe) {
		// TODO - implement ObsHippocampe.ObsHippocampe
		throw new UnsupportedOperationException();
	}

}