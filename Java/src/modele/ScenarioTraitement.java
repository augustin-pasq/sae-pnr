package modele;

import modele.donnee.*;
import modele.traitement.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class ScenarioTraitement {
    public static void main(String[] args) {
        p("## Scenario de traitement");
        ArrayList<Sommet> sommets = testSommet();
        testGraphe(sommets);
    }

    private static ArrayList<Sommet> testSommet() {
        p("--- Test de la classe Sommet ---");

        Date date = new Date(162523025);
        Time heure = new Time(80000000);
        Lieu lieu = new Lieu(268045.333, 6744460.457);
        ObsBatracien obs = new ObsBatracien(12, date, heure, lieu, new ArrayList<>(), new int[]{1, 3, 2, 5}, EspeceBatracien.CALAMITE);
        Sommet s1 = new Sommet(obs);
        Sommet s2 = new Sommet(78, lieu, date, EspeceObservee.BATRACIEN);
        Sommet s3 = new Sommet(98, lieu, date, EspeceObservee.BATRACIEN);
        Sommet s4 = new Sommet(35, lieu, date, EspeceObservee.BATRACIEN);
        Sommet s5 = null;

        try {
            s5 = new Sommet(-5, lieu, date, EspeceObservee.BATRACIEN);
        } catch (Exception e) {
            p("Exception : " + e.getMessage() + " ; OK");
        }

        p("Sommet s1 : " + s1);
        p("Sommet s2 : " + s2);
        p("Sommet s3 : " + s3);
        p("Sommet s4 : " + s4);
        p("Sommet s5 : " + s5);

        p("");

        s1.setId(0);
        p("s1.setId(0) = " + s1.getId());
        s1.setDate(new Date(162523025));
        p("s1.setDate(new Date(1)) = " + s1.getDate());
        s1.setCoordLieu(new Lieu(500, 6000000));
        p("s1.setCoordLieu(new Lieu(1, 6506512)) = " + s1.getCoordLieu());
        s1.setEspece(EspeceObservee.LOUTRE);
        p("s1.setEspece(EspeceObservee.LOUTRE) = " + s1.getEspece());

        p("");

        s2.setId(1);
        p("s2.setId(1) = " + s2.getId());
        s2.setDate(new Date(2));
        p("s2.setDate(new Date(2)) = " + s2.getDate());
        s2.setCoordLieu(new Lieu(500, 6000000));
        p("s2.setCoordLieu(new Lieu(2, 6985410)) = " + s2.getCoordLieu());
        s2.setEspece(EspeceObservee.BATRACIEN);
        p("s2.setEspece(EspeceObservee.BATRACIEN) = " + s2.getEspece());

        p("");

        s3.setId(2);
        p("s3.setId(2) = " + s3.getId());
        s3.setDate(new Date(3));
        p("s3.setDate(new Date(3)) = " + s3.getDate());
        s3.setCoordLieu(new Lieu(3, 6995410));
        p("s3.setCoordLieu(new Lieu(3, 6995410)) = " + s3.getCoordLieu());
        s3.setEspece(EspeceObservee.BATRACIEN);
        p("s3.setEspece(EspeceObservee.BATRACIEN) = " + s3.getEspece());

        p("");

        s4.setId(3);
        p("s4.setId(3) = " + s4.getId());
        s4.setDate(new Date(4));
        p("s4.setDate(new Date(4)) = " + s4.getDate());
        s4.setCoordLieu(new Lieu(4, 6995410));
        p("s4.setCoordLieu(new Lieu(4, 6995410)) = " + s4.getCoordLieu());
        s4.setEspece(EspeceObservee.BATRACIEN);
        p("s4.setEspece(EspeceObservee.BATRACIEN) = " + s4.getEspece());

        p("");

        p("s1.calculeDist(s2) = " + s1.calculeDist(s2));
        p("s2.calculeDist(s1) = " + s2.calculeDist(s1));
        p("s1.calculeDist(s3) = " + s1.calculeDist(s3));
        p("s3.calculeDist(s1) = " + s3.calculeDist(s1));
        p("s1.calculeDist(s4) = " + s1.calculeDist(s4));
        p("s4.calculeDist(s1) = " + s4.calculeDist(s1));

        p("\n");

        ArrayList<Sommet> ret = new ArrayList<Sommet>();
        ret.add(s1); // id & index 0
        ret.add(s2); // id & index 1
        ret.add(s3); // id & index 2
        ret.add(s4); // id & index 3
        // ret.add(s5); Sommet nul
        return ret;
    }

    private static void testGraphe(ArrayList<Sommet> sommets) {
        p("--- Test de la classe Graphe ---");

        HashMap<Sommet, ArrayList<Sommet>> hashMap = new HashMap<>();

        // sommets : [0, 1, 2, 3]

        // voisins de 0
        ArrayList<Sommet> sommets1 = new ArrayList<>(); 
        sommets1.add(sommets.get(0));
        sommets1.add(sommets.get(1));
        sommets1.add(sommets.get(2));

        // voisins de 1
        ArrayList<Sommet> sommets2 = new ArrayList<>();
        sommets2.add(sommets.get(0));
        sommets2.add(sommets.get(2));

        // voisins de 2
        ArrayList<Sommet> sommets3 = new ArrayList<>();
        sommets3.add(sommets.get(0));
        sommets3.add(sommets.get(1));
        sommets3.add(sommets.get(2));
        sommets3.add(sommets.get(3));
        // sommets3.add(sommets.get(4));

        // voisins de 3
        ArrayList<Sommet> sommets4 = new ArrayList<>();
        sommets4.add(sommets.get(2));
        // sommets4.add(sommets.get(4));

        hashMap.put(new Sommet(0, new Lieu(0, 6000001), new Date(0), EspeceObservee.BATRACIEN), sommets1); // {0 : 0, 1, 2}
        hashMap.put(new Sommet(1, new Lieu(1, 6000002), new Date(1), EspeceObservee.BATRACIEN), sommets2); // {1 : 0, 2}
        hashMap.put(new Sommet(2, new Lieu(2, 6000003), new Date(2), EspeceObservee.BATRACIEN), sommets3); // {2 : 0, 1, 2, 3}
        hashMap.put(new Sommet(3, new Lieu(3, 6000004), new Date(3), EspeceObservee.BATRACIEN), sommets4); // {3 : 2}

        Graphe g1 = new Graphe(hashMap);
        Graphe g2 = new Graphe(g1);

        p("");

        p("--- getSommetsVoisins");
        p("g1.getSommetsVoisins() >>> " + g1.getSommetsVoisins() + "\n");
        p("g2.getSommetsVoisins() >>> " + g2.getSommetsVoisins());

        p("");

        p("--- nbSommets()");
        p("g1.nbSommets() = 4 >>> " + g1.nbSommets());
        p("g2.nbSommets() = 4 >>> " + g2.nbSommets());

        p("");

        p("--- nbAretes()");
        p("g1.nbAretes() = 5 >>> " + g1.nbAretes());
        p("g2.nbAretes() = 5 >>> " + g2.nbAretes());

        p("");

        p("--- estDansGraphe()");
        p("g1.estDansGraphe(2) = true >>> " + g1.estDansGraphe(2));
        p("g2.estDansGraphe(2) = true >>> " + g2.estDansGraphe(2));
        p("g1.estDansGraphe(6) = false >>> " + g1.estDansGraphe(6));
        p("g1.estDansGraphe(9) = false >>> " + g1.estDansGraphe(9));

        p("");

        p("--- calculeDegre()");
        p("g1.calculeDegre(0) = 2  >>> " + g1.calculeDegre(0));
        p("g1.calculeDegre(1) = 2 >>> " + g1.calculeDegre(1));
        p("g2.calculeDegre(2) = 2 >>> " + g2.calculeDegre(2));
        p("g2.calculeDegre(3) = 2 >>> " + g2.calculeDegre(3));

        p("");  

        p(g1);
    }

    private static void p(Object o) {
        System.out.println(o);
    }
}
