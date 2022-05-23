package modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;

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

        int nbSommets;
        int [][] adj;

        nbSommets = this.nbSommets();
        adj = new int [nbSommets][nbSommets+1];

        for (Sommet s : this.sommetsVoisins.){

            adj[0][i] = this.sommetsVoisins.getKey(i);
        }
        
    }
}