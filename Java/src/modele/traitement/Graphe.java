package modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Graphe
 */
public class Graphe {

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