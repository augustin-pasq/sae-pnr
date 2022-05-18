package modele;

import modele.donnee.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import javax.xml.validation.Validator;


public class ScenarioDonnee {
    public static void main(String[] args) {
        p("## Scenario de données");
        ArrayList<Observateur> listeObs = testObservateur();
        testChouette(listeObs);
        testObsChouette(listeObs);
        testObsHippocampe(listeObs);
        testObsLoutre(listeObs);
        testGCI(listeObs);
        testLieu();
    }

    public static ArrayList<Observateur> testObservateur() {
        p("Test de la classe Chouette");

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
        } catch (NullPointerException e) {
            p("Observateur null : " + e.getMessage());
        }
        try {
            Observateur obsVide = new Observateur(5, "", "");
        } catch (IllegalArgumentException e) {
            p("Observateur vide : " + e.getMessage());
        }
        listeObs.add(obs1);
        listeObs.add(obs2);
        listeObs.add(obs3);
        listeObs.add(obs4);
        listeObs.add(obs5);

        obs0.setIdObservateur(10);
        obs0.setNom("Nom");
        obs0.setPrenom("Prenom");

        p("ID : " + obs0.getIdObservateur());
        p("Nom : " + obs0.getNom());
        p("Prenom : " + obs0.getPrenom());


        return listeObs;
    }


    public static void testChouette(ArrayList<Observateur> listeObs) {

        Chouette chouette = new Chouette("1", Sexe.MALE, EspeceChouette.HULOTTE);

        p("Changement de l'id de la chouette par 2");
        chouette.setIdChouette("2");
        p("Id : " + chouette.getIdChouette());
        try {
            chouette.setIdChouette(null);
        } catch (NullPointerException e) {
            p("Id null : " + e.getMessage());
        }
        try {
            chouette.setLesObservations(null);
        } catch (NullPointerException e) {
            p("lesObservations null : " + e.getMessage());
        }

        p("Changement du sexe de la chouette en femelle");
        chouette.setSexe(Sexe.FEMELLE);
        p("Sexe : " + chouette.getSexe());
        Sexe nonDefini = null;
        try {
            chouette.setSexe(nonDefini);
        } catch (NullPointerException e) {
            p("sexe null : " + e.getMessage());
        }

        p("Changement de l'espece de la chouette en hulotte");
        chouette.setEspece(EspeceChouette.HULOTTE);
        p("Espece : " + chouette.getEspece());
        EspeceChouette aucuneEspece = null;
        try {
            chouette.setEspece(aucuneEspece);
        } catch (NullPointerException e) {
            p("Espece null : " + e.getMessage());
        }


        Date dixSeptMai = new Date(0);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(268045.333, 6744460.457);
        Lieu arradon = new Lieu(263077.46, 6741405.844);

    

        ObsChouette obsChouette = new ObsChouette(1, dixSeptMai, now, vannes, listeObs, TypeObservation.SONORE);
        ObsChouette obsChouette2 = new ObsChouette(2, dixSeptMai, now, vannes, listeObs, TypeObservation.VISUELLE);
        ObsChouette obsChouette3 = new ObsChouette(3, dixSeptMai, now, arradon, listeObs, TypeObservation.SONORE);

        // Ajouter des observations
        ArrayList<ObsChouette> listeObsChouette = new ArrayList<ObsChouette>();
        listeObsChouette.add(obsChouette);
        listeObsChouette.add(obsChouette2);
        listeObsChouette.add(obsChouette3);
        chouette.setLesObservations(listeObsChouette);

        p("Affichage des observations de la chouette : ");
        p(chouette.getLesObservations().toString());

        ObsChouette obsChouette4 = new ObsChouette(4, dixSeptMai, now, arradon, listeObs, TypeObservation.SONORE_VISUELLE);
        chouette.ajouterUneObs(obsChouette4);


        ObsChouette obsChouette5 = new ObsChouette(5, dixSeptMai, minuit, arradon, listeObs, TypeObservation.SONORE_VISUELLE);
        ObsChouette obsChouette6 = new ObsChouette(6, dixSeptMai, minuit, arradon, listeObs, TypeObservation.SONORE_VISUELLE);
        ArrayList<ObsChouette> listeObsChouetteNuit = new ArrayList<ObsChouette>();
        listeObsChouetteNuit.add(obsChouette5);
        listeObsChouetteNuit.add(obsChouette6);

        p("Il y a au total " + chouette.nbObs() + " observations pour cet chouette");
        if (chouette.nbObs() == 6) {
            p("OK");
        } else {
            p("ERREUR");
        }

        p("L'observation numero 2 est retirée");
        chouette.retireObs(2);

        p("Il y a au total " + chouette.nbObs() + " observations pour cet chouette");
        if (chouette.nbObs() == 5) {
            p("OK");
        } else {
            p("ERREUR");
        }

        p("Toutes les observations ont été retirées");
        chouette.videObs();
        p("Il y a au total " + chouette.nbObs() + " observations pour cet chouette");
        if (chouette.nbObs() == 0) {
            p("OK");
        } else {
            p("ERREUR");
        }


        p("");
        p(obsChouette.toString());
        p("");
    }
    public static void testObsChouette(ArrayList<Observateur> listeObs) {
        Date dixHuitMai = new Date(0);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(268045.333, 6744460.457);
        Lieu arradon = new Lieu(263077.46, 6741405.844);

        ObsChouette obsChouette = new ObsChouette(1, dixHuitMai, now, vannes, listeObs, TypeObservation.SONORE);

        p("Changement du type d'observation en VISUELLE");
        obsChouette.setTypeObs(TypeObservation.VISUELLE);
        p("Type d'observation : " + obsChouette.getTypeObs());


        p("Espece de la chouette : " + obsChouette.especeObs());


    }

    public static void testObsHippocampe(ArrayList<Observateur> listeObs) {

        Date dixHuitMai = new Date(0);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(268045.333, 6744460.457);
        Lieu arradon = new Lieu(263077.46, 6741405.844);

        ObsHippocampe hippocampe1 = new ObsHippocampe(1, dixHuitMai, now, vannes, listeObs, 9.5, Peche.CASIER_CREVETTES, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, Sexe.MALE);

        try {
            ObsHippocampe hippocampeTailleNegatif = new ObsHippocampe(1, dixHuitMai, now, vannes, listeObs, -1, Peche.CASIER_CREVETTES, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, Sexe.MALE);
        } catch (IllegalArgumentException e) {
            p("Taille négative : " + e.getMessage());
        }


        p("Modificiation du type de peche par PETIT_FILET");
        hippocampe1.setTypePeche(Peche.PETIT_FILET);


        try {
            hippocampe1.setTypePeche(null);
        } catch (IllegalArgumentException e) {
            p("Type peche null : " + e.getMessage());
        }

        p("TypePeche : " + hippocampe1.getTypePeche());


        p("Modificiation de l'espece par HIPPOCAMPUS_HIPPOCAMPUS");
        hippocampe1.setEspece(EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS);


        try {
            hippocampe1.setEspece(null);
        } catch (IllegalArgumentException e) {
            p("EspeceHippocampe null : " + e.getMessage());
        }

        p("TypeEspece: " + hippocampe1.getEspece());

        p("Modificiation du sexe par FEMELLE");
        hippocampe1.setSexe(Sexe.FEMELLE);

        try {
            hippocampe1.setSexe(null);
        } catch (IllegalArgumentException e) {
            p("Sexe null : " + e.getMessage());
        }

        p("Sexe : " + hippocampe1.getSexe());

        p("Modificiation de la taille par 10.3");
        hippocampe1.setTaille(10.3);

        try {
            hippocampe1.setTaille(-2.3);
        } catch (IllegalArgumentException e) {
            p("Taille négative : " + e.getMessage());
        }

        p("Taille : " + hippocampe1.getTaille());

        try {
            hippocampe1.setEstGestant(true);
        } catch (IllegalArgumentException e) {
            p("Femelle ne peut pas être gestante : " + e.getMessage());
        }

        p("Modificiation du sexe par FEMELLE");
        hippocampe1.setSexe(Sexe.MALE);
        p("Animal gestant");
        hippocampe1.setEstGestant(true);

        p("Gestant ? " + hippocampe1.getEstGestant());


        p("Espece : " + hippocampe1.especeObs());

    }

    public static void testObsLoutre(ArrayList<Observateur> listeObs) {

        Date dixHuitMai = new Date(464314741);
        Time now = new Time(50000);
        Time minuit = new Time(0);
        Lieu vannes = new Lieu(268045.333, 6744460.457);
        Lieu arradon = new Lieu(263077.46, 6741405.844);

        ObsLoutre loutre1 = new ObsLoutre(1, dixHuitMai, now, arradon, listeObs, IndiceLoutre.POSITIF);

        p("Modificiation de l'indice par NEGATIF");
        loutre1.setIndice(IndiceLoutre.NEGATIF);
        p("Indice : " + loutre1.getIndice());

        p("Espece : " + loutre1.especeObs());
    }

    public static void testGCI(ArrayList<Observateur> listeObs) {
        Lieu vannes = new Lieu(268045.333, 6744460.457);
        Lieu arradon = new Lieu(263077.46, 6741405.844);

        // Initialisation

        //
        // 1
        //

        Date date1 = new Date(0);
        Time heure1 = new Time(36000000);

        ArrayList<Observateur> observateurs1 = new ArrayList<Observateur>();
        observateurs1.add(listeObs.get(0));
        observateurs1.add(listeObs.get(2));

        ContenuNid nature1 = ContenuNid.OEUF;
        int leNombre1 = 1;

        ObsGCI obs1 = new ObsGCI(1, date1, heure1, vannes, observateurs1, nature1, leNombre1);

        //
        // 2
        //

        // Date date1 = new Date();
        Time heure2 = new Time(72000000);

        ArrayList<Observateur> observateurs2 = new ArrayList<Observateur>();
        observateurs2.add(listeObs.get(0));
        observateurs2.add(listeObs.get(1));

        ContenuNid nature2 = ContenuNid.POUSSIN;
        int leNombre2 = 2;

        ObsGCI obs2 = new ObsGCI(2, date1, heure2, vannes, observateurs2, nature2, leNombre2);

        //
        // 3
        //

        // Date date1 = new Date();
        // Time heure1 = new Time(36000000);


        ArrayList<Observateur> observateurs3 = new ArrayList<Observateur>();
        observateurs3.add(listeObs.get(4));

        // ContenuNid nature1 = ContenuNid.OEUF;
        // int leNombre1 = 1;

        ObsGCI obs3_1 = new ObsGCI(3, date1, heure1, arradon, observateurs3, nature1, leNombre1);

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

        ObsGCI obs3_2 = new ObsGCI(4, date1, heure3, arradon, observateurs3, nature1, leNombre1);

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
        p("nbObs nid1 : " + nid1.nbObs());
        p("nbObs nid2 : " + nid2.nbObs());
        p("nbObs nid3 : " + nid3.nbObs());
        p("\nexpected :\n");
        p("1");
        p("1");
        p("2");
        p("\n");

        //videObs,retureObs
        nid1.videObs();
        nid3.retireObs(3);
        p("nbObs nid1 : " + nid1.nbObs());
        p("nbObs nid3 : " + nid1.nbObs());
        p("\nexpected :\n");
        p("0");
        p("1");
    }


    public static void testLieu() {
        Lieu vannes = new Lieu(268045.333, 6744460.457);

        p("Modificiation de coordX par 300000");
        vannes.setXCoord(300000);
        p("Modificiation de coordX par 7000000");
        vannes.setYCoord(7000000);
        p("Coord x : " + vannes.getXCoord());
        p("Coord y : " + vannes.getYCoord());

        
        try {
            Lieu impossible = new Lieu(-5, 12);
        } catch (IllegalArgumentException e) {
            p("Coordonnée Lambert x négatif et coordonnée y < 6000000 : " + e.getMessage());
        }

        try {
            vannes.setXCoord(-2);
        } catch (IllegalArgumentException e) {
            p("Coordonnée Lambert x négatif : " + e.getMessage());
        }


        try {
            vannes.setYCoord(300);
        } catch (IllegalArgumentException e) {
            p("Coordonnée Lambert y < 6000000 : " + e.getMessage());
        }

    }
    
    /**
     *  Test method for the obsBatracien class
     * @param listeObs  a list of observers of type Observateur
     */
    public static void testObsBatracien(ArrayList<Observateur> listeObs) {
        int id = 1;
        Date date = new Date(2000/10/10);
        Time heure = new Time(0);
        Lieu lieu = new Lieu(5, 5);
        int[] resObs = {1, 3, 2, 5};
        EspeceBatracien lEspece = EspeceBatracien.CALAMITE;
        ObsBatracien obsBatracien = new ObsBatracien(id, date, heure, lieu, listeObs, resObs, lEspece);


        //Tests sur l'attribut nombreAdultes:
        try {
            p("\nModificiation du nombre de batracien adulte par un nombre négatif: ");
            obsBatracien.setNombreAdultes(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Amount of adult batrachian < 0: " + e.getMessage());
        }
        p("Modificiation du nombre de batracien adulte par un nombre positif: ");
        obsBatracien.setNombreAdultes(10);
        p("Nombre d'adultes apres modification: " + obsBatracien.getNombreAdultes());


        //Tests sur l'attribut nombreAmplexus
        try {
            p("\nModificiation du nombre d'amplexus par un nombre négatif: ");
            obsBatracien.setNombreAmplexus(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Amount of amplexus < 0 : " + e.getMessage());
        }
        p("Modificiation du nombre d'amplexus par un nombre positif: ");
        obsBatracien.setNombreAmplexus(5);
        p("Nombre d'amplexus apres modification: " + obsBatracien.getNombreAmplexus());


        //Tests sur l'attribut nombreTetard
        try {
            p("\nModificiation du nombre de tetards par un nombre négatif: ");
            obsBatracien.setNombreTetard(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Amount of tadpoles < 0 : " + e.getMessage());
        }
        p("Modificiation du nombre de tetards par un nombre positif: ");
        obsBatracien.setNombreTetard(5);
        p("Nombre de tetards apres modification: " + obsBatracien.getNombreTetard());


        //Tests sur l'attribut nombrePonte
        try {
            p("\nModificiation du nombre de pontes par un nombre négatif: ");
            obsBatracien.setNombrePonte(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("Amount of clutches < 0 : " + e.getMessage());
        }
        p("Modificiation du nombre de pontes par un nombre positif: ");
        obsBatracien.setNombrePonte(5);
        p("Nombre de pontes apres modification: " + obsBatracien.getNombrePonte());


        //Tests sur l'attribut
        try {
            p("\nModificiation de l'espece par une valeur nulle: ");
            obsBatracien.setEspece(null);
        } catch (NullPointerException e) {
            System.out.println("Espece is null : " + e.getMessage());
        }
        p("Modificiation de l'espece: ");
        obsBatracien.setEspece(EspeceBatracien.PELODYTE);
        p("Espece apres modification: " + obsBatracien.getEspece());
    }
    
    private static void p(String str) {
        System.out.println(str);
    }
}
