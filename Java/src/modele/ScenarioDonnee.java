package modele;

import modele.donnee.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;



public class ScenarioDonnee {
    public static void main(String[] args) {
        System.out.println("## Scenario de données");
        ArrayList<Observateur> listeObs = testObservateur();
        testChouette(listeObs);
        testObsHippocampe(listeObs);
        testObsLoutre(listeObs);
        testLieu();


    }

    public static ArrayList<Observateur> testObservateur() {  
        System.out.println("Test de la classe Chouette");
        
         // Creation des objets
        ArrayList<Observateur> listeObs = new ArrayList<Observateur>();
        Observateur obs0 = new Observateur(0, "Doe", "John");
        Observateur obs1 = new Observateur(1, "Le Ny", "Liam");
        Observateur obs2 = new Observateur(2, "Pasquier", "Augustin");
        Observateur obs3 = new Observateur(3, "Malivet", "Ervan");
        Observateur obs4 = new Observateur(4, "Ozanne", "Colin");
        Observateur obs5 = new Observateur(5, "Tabor", "Samuel");
        try {
            Observateur obsNull = new Observateur(5, null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Observateur null : " + e.getMessage());
        }
        try {
            Observateur obsVide = new Observateur(5, "", "");
        } catch (IllegalArgumentException e) {
            System.out.println("Observateur vide : " + e.getMessage());
        }
        listeObs.add(obs1);
        listeObs.add(obs2);
        listeObs.add(obs3);
        listeObs.add(obs4);
        listeObs.add(obs5);

        obs0.setIdObservateur(10);
        obs0.setNom("Nom");
        obs0.setPrenom("Prenom");

        System.out.println("ID : " + obs0.getIdObservateur());
        System.out.println("Nom : " + obs0.getNom());
        System.out.println("Prenom : " + obs0.getPrenom());



        return listeObs;
    }


    public static void testChouette(ArrayList<Observateur> listeObs) {

        
        // Test de la classe Chouette

        Chouette chouette = new Chouette("1", Sexe.MALE, EspeceChouette.HULOTTE);
        chouette.setIdChouette("2");
        try {
            chouette.setIdChouette(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Id null : " + e.getMessage());
        }
        try {
            chouette.setLesObservations(null);
        } catch (IllegalArgumentException e) {
            System.out.println("lesObservations null : " + e.getMessage());
        }
        
        chouette.setSexe(Sexe.FEMELLE);
        Sexe nonDefini = null;
        try {
            chouette.setSexe(nonDefini);
        } catch (IllegalArgumentException e) {
            System.out.println("sexe null : " + e.getMessage());
        }
        EspeceChouette aucuneEspece = null;
        try {
            chouette.setEspece(aucuneEspece);
        } catch (IllegalArgumentException e) {
            System.out.println("Espece null : " + e.getMessage());
        }
        Date dixSeptMai = new Date(17 / 05 / 2022);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(47.6586772, -2.7599079);
        Lieu arradon = new Lieu(47.62798, -2.8229152);

        System.out.println("Changement du sexe de la chouette en femelle");
        System.out.println("Sexe : " + chouette.getSexe());
        System.out.println("Espece : " + chouette.getEspece());
        System.out.println("Id : " + chouette.getIdChouette());
        ObsChouette obsChouette = new ObsChouette(1, dixSeptMai, now, vannes, listeObs, TypeObservation.SONORE);
        ObsChouette obsChouette2 = new ObsChouette(2, dixSeptMai, now, vannes, listeObs, TypeObservation.VISUELLE);
        ObsChouette obsChouette3 = new ObsChouette(3, dixSeptMai, now, arradon, listeObs, TypeObservation.SONORE);

        // Ajouter des observations
        ArrayList<ObsChouette> listeObsChouette = new ArrayList<ObsChouette>();
        listeObsChouette.add(obsChouette);
        listeObsChouette.add(obsChouette2);
        listeObsChouette.add(obsChouette3);
        chouette.setLesObservations(listeObsChouette);

        System.out.println("Affichage des observations de la chouette : ");
        System.out.println(chouette.getLesObservations());

        ObsChouette obsChouette4 = new ObsChouette(4, dixSeptMai, now, arradon, listeObs, TypeObservation.SONORE_VISUELLE);
        chouette.ajouterUneObs(obsChouette4);


        ObsChouette obsChouette5 = new ObsChouette(5, dixSeptMai, minuit, arradon, listeObs, TypeObservation.SONORE_VISUELLE);
        ObsChouette obsChouette6 = new ObsChouette(6, dixSeptMai, minuit, arradon, listeObs, TypeObservation.SONORE_VISUELLE);
        ArrayList<ObsChouette> listeObsChouetteNuit = new ArrayList<ObsChouette>();
        listeObsChouetteNuit.add(obsChouette5);
        listeObsChouetteNuit.add(obsChouette6);

        System.out.println("Il y a au total " + chouette.nbObs() + " observations pour cet chouette");
        if (chouette.nbObs() == 6) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }

        System.out.println("L'observation numero 2 est retirée");
        chouette.retireObs(2);
        
        System.out.println("Il y a au total " + chouette.nbObs() + " observations pour cet chouette");
        if (chouette.nbObs() == 5) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }
        
        System.out.println("Toutes les observations ont été retirées");
        chouette.videObs();
        System.out.println("Il y a au total " + chouette.nbObs() + " observations pour cet chouette");
        if (chouette.nbObs() == 0) {
            System.out.println("OK");
        } else {
            System.out.println("ERREUR");
        }




        Lieu lieu = new Lieu(5, 5);
        NidGCI nd = new NidGCI(109, "Bellevue");


    }

    public static void testObsHippocampe(ArrayList<Observateur> listeObs) {

        Date dixHuitMai = new Date(18 / 05 / 2022);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(47.6586772, -2.7599079);
        Lieu arradon = new Lieu(47.62798, -2.8229152);

        ObsHippocampe hippocampe1 = new ObsHippocampe(01, dixHuitMai, now, vannes, listeObs, 9.5, Peche.CASIER_CREVETTES, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, Sexe.MALE);

        try {
            ObsHippocampe hippocampeTailleNegatif = new ObsHippocampe(01, dixHuitMai, now, vannes, listeObs, -1, Peche.CASIER_CREVETTES, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, Sexe.MALE);
        } catch (IllegalArgumentException e) {
            System.out.println("Taille négative : " + e.getMessage());
        }


        System.out.println("Modificiation du type de peche par PETIT_FILET");
        hippocampe1.setTypePeche(Peche.PETIT_FILET);


        try {
            hippocampe1.setTypePeche(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Type peche null : " + e.getMessage());
        }

        System.out.println("TypePeche : " + hippocampe1.getTypePeche());


        System.out.println("Modificiation de l'espece par HIPPOCAMPUS_HIPPOCAMPUS");
        hippocampe1.setEspece(EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS);



        try {
            hippocampe1.setEspece(null);
        } catch (IllegalArgumentException e) {
            System.out.println("EspeceHippocampe null : " + e.getMessage());
        }

        System.out.println("TypeEspece: " + hippocampe1.getEspece());

        System.out.println("Modificiation du sexe par FEMELLE");
        hippocampe1.setSexe(Sexe.FEMELLE);

        try {
            hippocampe1.setSexe(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Sexe null : " + e.getMessage());
        }

        System.out.println("Sexe : " + hippocampe1.getSexe());

        System.out.println("Modificiation de la taille par 10.3");
        hippocampe1.setTaille(10.3);

        try {
            hippocampe1.setTaille(-2.3);
        } catch (IllegalArgumentException e) {
            System.out.println("Taille négative : " + e.getMessage());
        }

        System.out.println("Taille : " + hippocampe1.getTaille());

        try {
            hippocampe1.setEstGestant(true);
        } catch (IllegalArgumentException e) {
            System.out.println("Femelle ne peut pas être gestante : " + e.getMessage());
        }

        System.out.println("Modificiation du sexe par FEMELLE");
        hippocampe1.setSexe(Sexe.MALE);
        System.out.println("Animal gestant");
        hippocampe1.setEstGestant(true);

        System.out.println("Gestant ? " + hippocampe1.getEstGestant());


        System.out.println("Espece : " + hippocampe1.especeObs());

    }

    public static void testObsLoutre(ArrayList<Observateur> listeObs) {

        Date dixHuitMai = new Date(18 / 05 / 2022);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(47.6586772, -2.7599079);
        Lieu arradon = new Lieu(47.62798, -2.8229152);

        ObsLoutre loutre1 = new ObsLoutre(1, dixHuitMai, now, arradon, listeObs, IndiceLoutre.POSITIF);

        System.out.println("Modificiation de l'indice par NEGATIF");
        loutre1.setIndice(IndiceLoutre.NEGATIF);
        System.out.println("Indice : " + loutre1.getIndice());

        System.out.println("Espece : " + loutre1.especeObs());
    }


    public static void testLieu () {
        Lieu vannes = new Lieu(47.6586772, -2.7599079);

        System.out.println("Modificiation de coordX par 23.5");
        vannes.setXCoord(23.5);
        System.out.println("Modificiation de coordX par -45.3");
        vannes.setYCoord(-45.3);
        System.out.println("Coord x : " + vannes.getXCoord());
        System.out.println("Coord y : " + vannes.getYCoord());
    }


}