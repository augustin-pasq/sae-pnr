package modele.donnee;

import java.sql.Time;
import java.util.*;

public abstract class Observation {

	protected Lieu ieuObs;
	protected Collection<Observateur> lesObservateurs;
	protected int idObs;
	protected java.sql.Time dateObs;
	protected java.sql.Date heureObs;

	/**
	 * Constructor for the class Observation
	 * @param id Observation's id
	 * @param date Observation's date
	 * @param heure Observation's time
	 * @param lieu Observation's place
	 * @param observateurs Observation's observers
	 */
	public Observation(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs) {
		// TODO - implement Observation.Observation
		throw new UnsupportedOperationException();
	}

	/**
	 * Add an observer to the observation
	 * @param o Observateur to add
	 */
	public void ajouteObservateur(Observateur o) {
		this.lesObservateurs.add(o);
	}

	/**
	 * Remove an observer from the observation
	 * @param idObservateur Id of the observer to remove
	 */
	public void retireObservateur(int idObservateur) {
		// TODO - implement Observation.retireObservateur
		throw new UnsupportedOperationException();
	}

	public abstract EspeceObservee especeObs();
}