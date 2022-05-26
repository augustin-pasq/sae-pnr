package modele.traitement;

import java.util.*;

/**
 * Class representing a graph
 *
 * @author Groupe SAE PNR 1D1
 */
public class Graphe {
    /**
     * HashMap containing the nodes of the graph as keys and their neighbours as values
     */
    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    /**
     * Construct a graph from a list of vertices and a minimal distance
     * @param sommets List of vertices
     * @param dist Minimal distance between two vertices to be neighbours
     * @throws IllegalArgumentException If the list of vertices is null or if the minimal distance is negative
     */
    public Graphe(ArrayList<Sommet> sommets, double dist) throws IllegalArgumentException {
        if (sommets == null) throw new IllegalArgumentException("sommets cannot be null");
        else if (dist < 0) throw new IllegalArgumentException("dist cannot be negative");
        else {
            this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
            for (Sommet s1 : sommets) {
                ArrayList<Sommet> voisins = new ArrayList<Sommet>();

                for (Sommet s2 : sommets)
                    if (s1.calculeDist(s2) > dist)
                        voisins.add(s2);

                this.sommetsVoisins.put(s1, voisins);
            }
        }
    }

    /**
     * Construct a graph from a Hashmap containing the nodes of the graph as keys and their neighbours as values
     * @param somVoisins Hashmap containing the nodes of the graph as keys and their neighbours as values
     * @throws IllegalArgumentException If the Hashmap is null or if it contains null keys or null values
     */
    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) throws IllegalArgumentException {

        // HashMap non nuls
        if (somVoisins == null)
            throw new IllegalArgumentException("HashMap cannot be null");

        Set<Sommet> listeSommets = somVoisins.keySet();

        for (Sommet s : listeSommets) {
            // Clés non nulles
            if (s == null) 
                throw new IllegalArgumentException("Hashmap key cannot be null");

            ArrayList<Sommet> voisins1 = somVoisins.get(s); // Voisins de s

            // Liste des voisins non nulle
            if (voisins1 == null)
                throw new IllegalArgumentException("Hashmap value cannot be null");

            
            for (Sommet v : voisins1){
                // Sommets adjacents non nuls
                if (v == null)
                    throw new IllegalArgumentException("ArrayList value (from HashMap values) cannot be null");

                // Symétrie respectée : v voisin de s => v a des voisins
                if (somVoisins.get(v) == null)
                    throw new IllegalArgumentException("HashMap values have to be symmetrical, no neighbors here");

                // Symétrie respectée : v voisin de s => s voisin de v
                if (!somVoisins.get(v).contains(s))
                    throw new IllegalArgumentException("HashMap values have to be symmetrical");
            }
        }
        this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>(somVoisins);
    }

    /**
     * Copy-constructor for a graph
     * @param g Graph to be copied
     * @throws IllegalArgumentException If the graph is null
     */
    public Graphe(Graphe g) {
        this(g.getSommetsVoisins());
    }

    /**
     * Get the Hashmap containing the nodes and their neighbours
     * @return Hashmap containing the nodes of the graph as keys and their neighbours as values
     */
    public HashMap<Sommet, ArrayList<Sommet>> getSommetsVoisins() {
        return new HashMap<>(this.sommetsVoisins);
    }

    /**
     * Get the number of vertices in the graph
     * @return Number of vertices in the graph
     */
    public int nbSommets() {
        return this.sommetsVoisins.size();
    }

    /**
     * Get the number of edges in the graph
     * @return Number of edges in the graph
     */
    public int nbAretes() {
        int sum = 0;
        for (ArrayList<Sommet> s : this.sommetsVoisins.values()) {
            sum += s.size();
        }
        return sum / 2;
    }

    /**
     * Check if a vertex is in the graph
     * @param idSom ID of the vertex to be checked
     * @return True if the vertex is in the graph, false otherwise
     */
    public boolean estDansGraphe(int idSom) {
        boolean dansGraphe = false;

        Iterator<Sommet> it = this.sommetsVoisins.keySet().iterator();

        while (!dansGraphe && it.hasNext()) {
            Sommet s = it.next();
            if (s.getId() == idSom)
                dansGraphe = true;
        }

        return dansGraphe;
    }

    /**
     * Calculate the number of neighbours of a vertex
     * @param idSom ID of the vertex
     * @return Number of neighbours
     */
    public int calculeDegre(int idSom) {
        int degre = -1;

        for (Sommet s : this.sommetsVoisins.keySet())
            if (s.getId() == idSom)
                degre = this.sommetsVoisins.get(s).size();

        if (degre == -1)
            throw new IllegalArgumentException("id cannot be found");
        else
            return degre;
    }

    /**
     * Calculate the number of neighbours of all vertices in the graph
     * @return Hashmap containing the ID of the vertex as key and the number of neighbours as value
     */
    public HashMap<Sommet, Integer> calculeDegres() {
        HashMap<Sommet, Integer> degres = new HashMap<Sommet, Integer>();

        for (Sommet s : this.sommetsVoisins.keySet())
            degres.put(s, calculeDegre(s.getId()));

        return degres;
    }

    /**
     * Calculate the vertex with the most neighbours
     * @return vertex with the most neighbours
     */
    public Sommet somMaxDegree() {
        if (this.sommetsVoisins.isEmpty()) throw new IllegalArgumentException("Graph is empty");
        HashMap<Sommet, Integer> degres = this.calculeDegres();
        Set<Sommet> listeSommets = degres.keySet();
        int maxDeg = -1;
        Sommet sommet = null;

        for (Sommet s : listeSommets)
            if (degres.get(s) > maxDeg){
                maxDeg = degres.get(s);
                sommet = s;
            }
        
        return sommet;
    }

    /**
     * Check if a vertex is a neighbour of another
     * @param idSom1 ID of the first vertex
     * @param idSom2 ID of the second vertex
     * @return True if the second vertex is a neighbour of the first, false otherwise
     */
    public boolean sontVoisins(int idSom1, int idSom2) {
        boolean voisin = false;

        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom1)
                for (Sommet s2 : this.sommetsVoisins.get(s1))
                    if (s2.getId() == s1.getId())
                        voisin = true;

        return voisin;
    }

    /**
     * Generate the adjacency matrix of the graph
     * @return Adjacency matrix of the graph
     */
    public int[][] matriceAdjacence() {
        // TODO : get the adjacency matrix
        return new int[][]{new int[]{0}};
    }

    /**
     * Check if a path exists between two vertices
     * @param idSom1 ID of the first vertex
     * @param idSom2 ID of the second vertex
     * @return True if a path exists, false otherwise
     */
    public boolean existeChemin(int idSom1, int idSom2) {
        boolean chemin = false;
        ArrayList<Sommet> file = new ArrayList<Sommet>();
        ArrayList<Sommet> sommetsTraites = new ArrayList<Sommet>();
        Sommet s = null;

        if (!estDansGraphe(idSom1) || !estDansGraphe(idSom2))
            throw new IllegalArgumentException("sommets cannot be null");

        // Ajout du premier sommet
        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom1) {
                s = s1;
            }
        sommetsTraites.add(s);
        file.addAll(this.sommetsVoisins.get(s));

        // Vérification
        while (file.size() > 0 && !chemin) {
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

    /**
     * Get the neighbours of a vertex
     * @param idSom ID of the vertex
     * @return ArrayList containing the neighbours of the vertex
     */
    public ArrayList<Sommet> voisins(int idSom) throws IllegalArgumentException {
        ArrayList<Sommet> listeVoisins = null;

        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom)
                listeVoisins = this.sommetsVoisins.get(s1);

        if (listeVoisins == null) throw new IllegalArgumentException("vertex is not in the graph");

        return listeVoisins;
    }

    /**
     * Add an edge to the graph
     * @param idSom1 ID of the first vertex
     * @param idSom2 ID of the second vertex
     * @return True if the edge has been added, false otherwise
     */
    public boolean ajouteArete(int idSom1, int idSom2){
        boolean arete = false;
        boolean voisin = false;
        Sommet s1 = null;
        Sommet s2 = null;
        ArrayList<Sommet> voisinS1 = null;
        ArrayList<Sommet> voisinS2 = null;

        if (estDansGraphe(idSom1) && estDansGraphe(idSom2)){
            for (Sommet s : this.sommetsVoisins.keySet())
                if (s.getId() == idSom1)
                    s1 = s;
                else if (s.getId() == idSom2)
                    s2 = s;

            for (Sommet s : this.sommetsVoisins.get(s1))
                if (s.equals(s2))
                    voisin = true;

            if (!voisin){
                arete = true;
                voisinS1 = this.sommetsVoisins.get(s1);
                voisinS1.add(s2);
                voisinS2 = this.sommetsVoisins.get(s2);
                voisinS2.add(s1);
                this.sommetsVoisins.put(s1, voisinS1);
                this.sommetsVoisins.put(s2, voisinS2);
            }
        }

        return arete;
    }

    /**
     * Remove an edge from the graph
     * @param idSom1 ID of the first vertex
     * @param idSom2 ID of the second vertex
     * @return True if the edge has been removed, false otherwise
     */
    public boolean retireArete(int idSom1, int idSom2){
        boolean arete = false;
        boolean voisin = false;
        Sommet s1 = null;
        Sommet s2 = null;

        if (estDansGraphe(idSom1) && estDansGraphe(idSom2)){
            for (Sommet s : this.sommetsVoisins.keySet())
                if (s.getId() == idSom1)
                    s1 = s;
                else if (s.getId() == idSom2)
                    s2 = s;

            for (Sommet s : this.sommetsVoisins.get(s1))
                if (s.equals(s2))
                    voisin = true;

            if (!voisin){
                arete = true;
                this.sommetsVoisins.get(s1).add(s2);
                this.sommetsVoisins.get(s2).add(s1);
            }
        }

        return arete;
    }

    /**
     * Calculate the smallest eccentricity of the graph
     * @return Radius of the graph
     */
    public int rayon() {
        // TODO : determine the radius of the graph
        return 0;
    }

    /**
     * Calculate the largest eccentricity of the graph
     * @return Diameter of the graph
     */
    public int diametre() {
        // TODO : determine the diameter of the graph
        return 0;
    }

    /**
     * Check if the graph is connected
     * @return True if the graph is connected, false otherwise
     */
    public boolean estConnexe() {
        boolean connexe = true;
        Set<Sommet> sommets = this.sommetsVoisins.keySet();
        Iterator<Sommet> it = sommets.iterator();
        Sommet s1;
        Sommet s2;

        while (it.hasNext() && connexe) {
            s1 = it.next();

            Iterator<Sommet> it2 = sommets.iterator();
            while (it2.hasNext() && connexe) {
                s2 = it2.next();
                if (!this.existeChemin(s1.getId(), s2.getId()))
                    connexe = false;
            }
        }

        return connexe;
    }

    /**
     * Format the graph to a string
     * @return String representation of the graph
     */
    public String toString(){
        String printFormat = "Graphe :\n";
        ArrayList<Sommet> debug;
        Integer [] idSommets;
        Sommet som;

        for (Sommet s : this.sommetsVoisins.keySet()){
            debug = this.sommetsVoisins.get(s);
            if (debug == null) idSommets = new Integer [0];
            else {
                idSommets = new Integer [debug.size()];
                for (int i = 0 ; i < idSommets.length ; i++){
                    som = debug.get(i);
                    if (som != null)
                        idSommets[i] = som.getId();
                    else
                        idSommets[i] = null;
                }
            }
            printFormat = printFormat + "Id : " + s.getId() + "\n";
            printFormat = printFormat + "\tNeighbors : " + Arrays.toString(idSommets) + "\n";
        }
        return printFormat;
    }
}