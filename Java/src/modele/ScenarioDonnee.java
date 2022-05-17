package modele;

import modele.donnee.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthStyle;

public class ScenarioDonnee {
    public static void main(String[] args) {
        System.out.println("## Scenario de données");

        // Creation des objets
        ArrayList<Observateur> listeObs = new ArrayList<Observateur>(); 
        Observateur obs1 = new Observateur(1, "Le Ny", "Liam");
        Observateur obs2 = new Observateur(2, "Pasquier", "Augustin");
        Observateur obs3 = new Observateur(3, "Malivet", "Ervan");
        Observateur obs4 = new Observateur(4, "Ozanne", "Colin");
        Observateur obs5 = new Observateur(5, "Tabor", "Samuel");
        listeObs.add(obs1);
        listeObs.add(obs2);
        listeObs.add(obs3);
        listeObs.add(obs4);
        listeObs.add(obs5);




        Date today = new Date(17/05/2022);
        Time now = new Time(50000);
        Lieu vannes = new Lieu(48.862725, 2.287592);
        

        Chouette chouette = new Chouette("1", Sexe.MALE, EspeceChouette.HULOTTE);
        chouette.setIdChouette("2");
        chouette.setIdChouette(null);
        chouette.setLesObservations(null);
        chouette.setSexe(Sexe.FEMELLE);
        Sexe nonDefini = null;
        chouette.setSexe(nonDefini);
        EspeceChouette aucuneEspece = null;
        chouette.setEspece(aucuneEspece);


        System.out.println("Changement du sexe de la chouette en femelle");
        System.out.println(chouette.getSexe());
        ObsChouette obsChouette = new ObsChouette(1, today, now, vannes, listeObs, TypeObservation.SONORE);


        Lieu lieu = new Lieu(5, 5);
        NidGCI nd = new NidGCI(109, "Bellevue");



    }
}