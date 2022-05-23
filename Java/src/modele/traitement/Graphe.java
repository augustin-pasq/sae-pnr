package modele.traitement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * Graphe
 */
public class Graphe {

    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    public Graphe(ArrayList<Sommet> sommets, double dist) {
        for (Sommet s1 : sommets) {
            ArrayList<Sommet> sommetsVoisins = new ArrayList<Sommet>();

            for (Sommet s2 : sommets) {
                if (s1.claculeDist(s2) > dist) {
                    sommetsVoisins.add(s2);
                } else {
                    sommetsVoisins = null;
                }
            }

            this.sommetsVoisins.put(s1, sommetsVoisins);
        }
    }

    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        this.sommetsVoisins = somVoisins;
    }

    public Graphe(Graphe g) {
        sommetsVoisins = g.sommetsVoisins;
    }

    public HashMap<Sommet, ArrayList<Sommet>> getSommetsVoisins() {
        return this.sommetsVoisins;
    }

    public int nbSommets() {
        return this.sommetsVoisins.size();
    }

    public int nbAretes() {
        int sum = 0;
        for (ArrayList<Sommet> s : this.sommetsVoisins.values()) {
            sum = sum + s.size();
        }
        return (int) Math.ceil(sum/2);
    }

    public int [][] matriceAdjacence(){

        // déclaration
        int [][] adj;
        Set<Sommet> listeSommets;
        int [] idSommets;

        // initialisation
        int nbSommets = this.nbSommets();

        adj = new int [nbSommets][nbSommets+1];
        listeSommets = this.sommetsVoisins.keySet();
        idSommets = new int [nbSommets];

        // tri des idSommets pour l'affichage
        int i = 0;
        for (Sommet s : listeSommets){
            idSommets[i] = s.getId();
            i++;
        }
        Arrays.sort(idSommets);

        // identifiant des sommets
        for (i = 0 ; i < nbSommets ; i++) adj[i][0] = idSommets[i];

        for (Sommet s : listeSommets){

            int id = s.getId();

        }
    }
}