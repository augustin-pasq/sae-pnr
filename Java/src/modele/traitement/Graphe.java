package modele.traitement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * Graphe
 */
public class Graphe {

    public int [][] matriceAdjacence(){

        // déclaration
        HashMap <Integer, ArrayList<Sommet>> sommets; 
        int [][] adj;
        Set<Sommet> listeSommets;
        int [] idSommets;

        // initialisation
        int nbSommets = this.nbSommets();

        adj = new int [nbSommets][nbSommets+1];
        listeSommets = this.sommetsVoisins.keySet();
        idSommets = new int [nbSommets];
       
        for (Sommet s : listeSommets){
            sommets.put(s.getId(), this.sommetsVoisins.get(s));
        }

        // tri des idSommets pour l'affichage
        int i = 0;
        for (Sommet s : listeSommets){
            idSommets[i] = s.getId();
            i++;
        }
        Arrays.sort(idSommets);

        // identifiant des sommets
        for (i = 0 ; i < nbSommets ; i++) adj[i][0] = idSommets[i];
        
    }
}