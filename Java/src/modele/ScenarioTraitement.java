package modele;

import modele.donnee.*;
import modele.traitement.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ScenarioTraitement {
    public static void main(String[] args) {
        p("## Scenario de traitement");
        testGraphe();
        testSommet();
    }

    private static void testGraphe() {
        p("--- Test de la classe Graphe ---");

        Graphe g = new Graphe();
        //TODO : tester Graphe
    }

    private static void testSommet() {
        p("--- Test de la classe Sommet ---");

        Date date = new Date(0);
        Time heure = new Time(80000000);
        Lieu lieu = new Lieu(268045.333, 6744460.457);
        ArrayList<Observateur> tags = new ArrayList<>();
        ObsBatracien obs = new ObsBatracien(0, date, heure, lieu, listeObs, new int[]{1, 3, 2, 5}, EspeceBatracien.CALAMITE);
        Sommet s1 = new Sommet(obs);
        Sommet s2 = new Sommet(1, lieu, date, EspeceObservee.BATRACIEN);
    }

    private static void p(String str) {
        System.out.println(str);
    }
}