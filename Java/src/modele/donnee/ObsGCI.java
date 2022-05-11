package modele.donnee;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsGCI extends Observation {

	private ContenuNid natureObs;
	private int nombre;

	/**
	 * 
	 * @param id
	 * @param date
	 * @param heure
	 * @param lieu
	 * @param observateurs
	 * @param nature
	 * @param leNombre
	 */
	public ObsGCI(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, ContenuNid nature, int leNombre) {
		// TODO - implement ObsGCI.ObsGCI
		super(id, date, heure, lieu, observateurs);
		this.natureObs = nature;
		this.nombre = leNombre;
	}

	/**
	 *
	 * @return
	 */
	public EspeceObservee especeObs() {
		// TODO - implement Observation.especeObs
		return null;
	}
}