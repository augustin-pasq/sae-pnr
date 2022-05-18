package modele;

import modele.donnee.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;


public class ScenarioDonnee {
    public static void main(String[] args) {
        System.out.println("## Scenario de données");
        ArrayList<Observateur> listeObs = testObservateur();
        testChouette(listeObs);
        testObsHippocampe(listeObs);
        testObsLoutre(listeObs);
        testGCI(listeObs);
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
        Date dixSeptMai = new Date(0);
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

        Date dixHuitMai = new Date(0);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(47.6586772, -2.7599079);
        Lieu arradon = new Lieu(47.62798, -2.8229152);

        ObsHippocampe hippocampe1 = new ObsHippocampe(1, dixHuitMai, now, vannes, listeObs, 9.5, Peche.CASIER_CREVETTES, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, Sexe.MALE);

        try {
            ObsHippocampe hippocampeTailleNegatif = new ObsHippocampe(1, dixHuitMai, now, vannes, listeObs, -1, Peche.CASIER_CREVETTES, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, Sexe.MALE);
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

        Date dixHuitMai = new Date(0);
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

    public static void testGCI(ArrayList<Observateur> listeObs) {

        // Initialisation

        //
        // 1
        //

        Date date1 = new Date(0);
        Time heure1 = new Time(36000000);
        Lieu lieu1 = new Lieu(16, 6);

        ArrayList<Observateur> observateurs1 = new ArrayList<Observateur>();
        observateurs1.add(listeObs.get(0));
        observateurs1.add(listeObs.get(2));

        ContenuNid nature1 = ContenuNid.OEUF;
        int leNombre1 = 1;

        ObsGCI obs1 = new ObsGCI(1, date1, heure1, lieu1, observateurs1, nature1, leNombre1);

        //
        // 2
        //

        // Date date1 = new Date();
        Time heure2 = new Time(72000000);
        Lieu lieu2 = new Lieu(20, 10);

        ArrayList<Observateur> observateurs2 = new ArrayList<Observateur>();
        observateurs2.add(listeObs.get(0));
        observateurs2.add(listeObs.get(1));

        ContenuNid nature2 = ContenuNid.POUSSIN;
        int leNombre2 = 2;

        ObsGCI obs2 = new ObsGCI(2, date1, heure2, lieu2, observateurs2, nature2, leNombre2);

        //
        // 3
        //

        // Date date1 = new Date();
        // Time heure1 = new Time(36000000);
        Lieu lieu3 = new Lieu(65, 99);

        ArrayList<Observateur> observateurs3 = new ArrayList<Observateur>();
        observateurs3.add(listeObs.get(4));

        // ContenuNid nature1 = ContenuNid.OEUF;
        // int leNombre1 = 1;

        ObsGCI obs3_1 = new ObsGCI(3, date1, heure1, lieu3, observateurs3, nature1, leNombre1);

        //
        // 4
        //

        // Date date1 = new Date();
        Time heure3 = new Time(80000000);
        // Lieu lieu3 = new Lieu(65, 99);

        // ArrayList<Observateur> observateurs3 = new ArrayList<Observateur>();
        // observateurs3.add(listeObs.get(4));

        // ContenuNid nature1 = ContenuNid.OEUF;
        // int leNombre1 = 1;

        ObsGCI obs3_2 = new ObsGCI(4, date1, heure3, lieu3, observateurs3, nature1, leNombre1);

        // NidGCI
        NidGCI nid1 = new NidGCI(1, "Plage1");
        NidGCI nid2 = new NidGCI(2, "Plage2");
        NidGCI nid3 = new NidGCI(3, "Plage3");

        nid1.ajouterUneObs(obs1);
        nid2.ajouterUneObs(obs2);

        ArrayList<ObsGCI> lesObs = new ArrayList<ObsGCI>();
        lesObs.add(obs3_1);
        lesObs.add(obs3_2);

        nid3.ajouterPlsObs(lesObs);

        //nbObs
        System.out.println("nbObs nid1 : " + nid1.nbObs());
        System.out.println("nbObs nid2 : " + nid2.nbObs());
        System.out.println("nbObs nid3 : " + nid3.nbObs());
        System.out.println("\nexpected :\n");
        System.out.println("1");
        System.out.println("1");
        System.out.println("2");
        System.out.println("\n");

        //videObs,retureObs
        nid1.videObs();
        nid3.retireObs(3);
        System.out.println("nbObs nid1 : " + nid1.nbObs());
        System.out.println("nbObs nid3 : " + nid1.nbObs());
        System.out.println("\nexpected :\n");
        System.out.println("0");
        System.out.println("1");
    }


    public static void testLieu() {
        Lieu vannes = new Lieu(47.6586772, -2.7599079);

        System.out.println("Modificiation de coordX par 23.5");
        vannes.setXCoord(23.5);
        System.out.println("Modificiation de coordX par -45.3");
        vannes.setYCoord(-45.3);
        System.out.println("Coord x : " + vannes.getXCoord());
        System.out.println("Coord y : " + vannes.getYCoord());
    }


}