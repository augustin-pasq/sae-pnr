package modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Graphe
 */
public class Graphe {

    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    public Graphe(ArrayList<Sommet> sommets, double dist) throws IllegalArgumentException {
        if (sommets == null) throw new IllegalArgumentException("sommets cannot be null");
        else {
            this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
            for (Sommet s1 : sommets) {
                ArrayList<Sommet> sv = new ArrayList<Sommet>();

                for (Sommet s2 : sommets) {
                    if (s1.calculeDist(s2) > dist) {
                        sv.add(s2);
                    } else {
                        sv = null;
                    }
                }

                this.sommetsVoisins.put(s1, sv);
            }
        }
    }

    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        Set <Sommet> listeSommets = somVoisins.keySet();
        for (Object o : listeSommets) if (o == null) throw new IllegalArgumentException("sommets cannot be null");
        this.sommetsVoisins = somVoisins;
    }

    public Graphe(Graphe g) {
        this.sommetsVoisins = g.sommetsVoisins;
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
        return (int) (sum/2);
    }

    public boolean estDansGraphe(int idSom){
        boolean dansGraphe = false;

        for (Sommet s : this.sommetsVoisins.keySet())
            if (s.getId() == idSom)
                dansGraphe = true;

        return dansGraphe;
    }

    public int calculeDegree(int idSom){
        int degre = -1;
        
        for (Sommet s : this.sommetsVoisins.keySet())
            if (s.getId() == idSom)
                degre = this.sommetsVoisins.get(s).size();

        if (degre == -1)
            throw new  IllegalArgumentException("id cannot be found");
        else
            return degre;
    }

    public HashMap<Sommet, Integer> calculeDegrees(){
        HashMap<Sommet, Integer> degres = new HashMap<Sommet, Integer>();

        for (Sommet s : this.sommetsVoisins.keySet())
            degres.put(s, calculeDegree(s.getId()));

        return degres;
    }

    public Sommet somMaxDegree(){
        HashMap<Sommet, Integer> degres = this.calculeDegrees();
        Set<Sommet> listeSommets = degres.keySet();
        int maxDeg = -1;
        Sommet sommet;

        for (Sommet s : listeSommets)
            if (degres.get(s) > maxDeg){
                maxDeg = degres.get(s);
                sommet = s;
            }
        
        return sommet;
    }

    public boolean sontVoisins(int idSom1, int idSom2){
        boolean voisin = false;

        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom1)
                for (Sommet s2 : this.sommetsVoisins.get(s1))
                    if (s2.getId() == s1.getId())
                        voisin = true;

        return voisin;
    }

    public int[][] matriceAdjacence() {

        // déclaration
        int[][] adj;

        nbSommets = this.nbSommets();
        adj = new int[nbSommets][nbSommets + 1];
        listeSommets = this.sommetsVoisins.keySet();
        idSommets = new int[nbSommets];

        for (Sommet s : this.sommetsVoisins.) {

            adj[0][i] = this.sommetsVoisins.getKey(i);
        }

        // identifiant des sommets
        for (i = 0; i < nbSommets; i++) adj[i][0] = idSommets[i];
    }

    public boolean existeChemin(int idSom1, int idSom2){
        boolean chemin = false;
        ArrayList<Sommet> file = new ArrayList<Sommet>();
        ArrayList<Sommet> sommetsTraites = new ArrayList<Sommet>();
        Sommet s = null;

        if (!estDansGraphe(idSom1) || !estDansGraphe(idSom2))
            throw new IllegalArgumentException("sommets cannot be null");

        // Ajout du premier sommet
        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom1){
                s = s1;
            }
        sommetsTraites.add(s);
        for (Sommet s1 : this.sommetsVoisins.get(s))
            file.add(s1);

        // Vérification
        while (file.size() > 0 && !chemin){
            s = file.remove(0);
            if (s.getId() == idSom2)
                chemin = true;
            else
                sommetsTraites.add(s);
                for (Sommet s1 : this.sommetsVoisins.get(s))
                    if (!file.contains(s1) && !sommetsTraites.contains(s1))
                        file.add(s1);
        }
        return chemin;
    }

    public ArrayList<Sommet> voisins(int idSom) {
        ArrayList<Sommet> listeVoisins = null;
        
        if (!estDansGraphe(idSom))
            throw new IllegalArgumentException("sommets cannot be null");

        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom)
                listeVoisins = this.sommetsVoisins.get(s1);
        return listeVoisins;
    }

    public int rayon() {
        int s1;
        int s2;
        if (this.estConnexe()) {
            return -1;
        } else {

        }

    }


    public int diametre() {
        if (this.estConnexe()) {
            return -1;
        } else {

        }

    }
}