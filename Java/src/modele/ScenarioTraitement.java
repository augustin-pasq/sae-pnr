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
        ObsBatracien obs = new ObsBatracien(0, date, heure, lieu, new ArrayList<>(), new int[]{1, 3, 2, 5}, EspeceBatracien.CALAMITE);
        Sommet s1 = new Sommet(obs);
        Sommet s2 = new Sommet(1, lieu, date, EspeceObservee.BATRACIEN);

        p("Sommet s1 : " + s1);
        p("Sommet s2 : " + s2);

        s1.setId(3);
        p("this.setId(3) = " + s1.getId());
        s1.setDate(new Date(1));
        p("this.setDate(new Date(1)) = " + s1.getDate());
        s1.setCoordLieu(new Lieu(1, 1));
        p("this.setCoordLieu(new Lieu(1, 1)) = " + s1.getCoordLieu());
        s1.setEspece(EspeceObservee.LOUTRE);
        p("this.setEspece(EspeceObservee.LOUTRE) = " + s1.getEspece());

        s2.setId(4);
        p("this.setId(4) = " + s2.getId());
        s2.setDate(new Date(2));
        p("this.setDate(new Date(2)) = " + s2.getDate());
        s2.setCoordLieu(new Lieu(2, 2));
        p("this.setCoordLieu(new Lieu(2, 2)) = " + s2.getCoordLieu());
        s2.setEspece(EspeceObservee.BATRACIEN);
        p("this.setEspece(EspeceObservee.BATRACIEN) = " + s2.getEspece());
    }

    private static void p(String str) {
        System.out.println(str);
    }
}