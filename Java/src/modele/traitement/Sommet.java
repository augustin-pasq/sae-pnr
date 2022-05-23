package modele.traitement;

<<<<<<< HEAD
/**
 * Sommet
 */
public class Sommet {

    
}
=======
import modele.donnee.EspeceObservee;
import modele.donnee.Lieu;
import modele.donnee.Observation;

import java.sql.Date;

public class Sommet {
    private int id;
    private Lieu coordLieu;
    private Date date;
    private EspeceObservee espece;

    public Sommet(int id, Lieu coordLieu, Date date, EspeceObservee espece) throws IllegalArgumentException {
        this.setId(id);
        this.setCoordLieu(coordLieu);
        this.setDate(date);
        this.setEspece(espece);
    }

    public Sommet(Observation obs) throws IllegalArgumentException {
        if (obs == null) throw new IllegalArgumentException("obs ne peut être null");
        else {
            this.setId(obs.getId());
            this.setCoordLieu(obs.getLieu());
            this.setDate(obs.getDate());
            this.setEspece(obs.especeObs());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws IllegalArgumentException {
        if (this.id < 0) throw new IllegalArgumentException("id doit être positif");
        else this.id = id;
    }

    public EspeceObservee getEspece() {
        return espece;
    }

    public void setEspece(EspeceObservee espece) throws IllegalArgumentException {
        if (espece == null) throw new IllegalArgumentException("espece ne peut être null");
        else this.espece = espece;
    }

    public Lieu getCoordLieu() {
        return coordLieu;
    }

    public void setCoordLieu(Lieu coordLieu) throws IllegalArgumentException {
        if (coordLieu == null) throw new IllegalArgumentException("coordLieu ne peut être null");
        else this.coordLieu = coordLieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) throws IllegalArgumentException {
        if (date == null) throw new IllegalArgumentException("date ne peut être null");
        else this.date = date;
    }

    public double claculeDist(Sommet som) {
        double ret = 0;
        if (som == null) throw new IllegalArgumentException("som ne doit pas être null");
        else {
            double dX = Math.pow(this.getCoordLieu().getXCoord() - som.getCoordLieu().getXCoord(), 2);
            double dY = Math.pow(this.getCoordLieu().getYCoord() - som.getCoordLieu().getYCoord(), 2);
            ret = Math.sqrt(dX + dY);
        }
        return ret;
    }

    public String toString() {
        return "Sommet{" + this.getId() + ", " + this.getDate() + ", " + this.getCoordLieu() + ", " + this.getEspece() + '}';
    }

}
>>>>>>> bd67d75ab9cf808d5ce118daad23337083d4f2d0
