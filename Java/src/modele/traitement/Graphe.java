package modele.traitement;

import java.util.*;

/**
 * Class representing a graph
 *
 * @author Groupe SAE PNR 1D1
 */
public class Graphe {
    /**
     * HashMap containing the nodes of the graph as keys and their neighbours as
     * values
     */
    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    /**
     * Construct a graph from a list of vertices and a minimal distance
     *
     * @param sommets List of vertices
     * @param dist    Minimal distance between two vertices to be neighbours
     * @throws IllegalArgumentException If the list of vertices is null or if the
     *                                  minimal distance is negative
     */
    public Graphe(ArrayList<Sommet> sommets, double dist) throws IllegalArgumentException {
        if (sommets == null)
            throw new IllegalArgumentException("sommets cannot be null");
        else if (dist < 0)
            throw new IllegalArgumentException("dist cannot be negative");
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
     * Construct a graph from a Hashmap containing the nodes of the graph as keys
     * and their neighbours as values
     *
     * @param somVoisins Hashmap containing the nodes of the graph as keys and their
     *                   neighbours as values
     * @throws IllegalArgumentException If the Hashmap is null or if it contains
     *                                  null keys or null values
     */
    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) throws IllegalArgumentException {

        // HashMap non nuls
        if (somVoisins == null)
            throw new IllegalArgumentException("HashMap cannot be null");

        Set<Sommet> listeSommets = somVoisins.keySet();
        ArrayList<Integer> listeId = new ArrayList<Integer>();

        for (Sommet s : listeSommets) {
            // Clés non nulles
            if (s == null)
                throw new IllegalArgumentException("Hashmap key cannot be null");

            ArrayList<Sommet> voisins = somVoisins.get(s); // Voisins de s

            // Id différents
            if (listeId.contains(s.getId()))
                throw new IllegalArgumentException("Hashmap keys id cannot be repeated");

            listeId.add(s.getId());

            // Liste des voisins non nulle
            if (voisins == null)
                throw new IllegalArgumentException("Hashmap value cannot be null");

            for (Sommet v : voisins) {
                // Sommets adjacents non nuls
                if (v == null)
                    throw new IllegalArgumentException("ArrayList value (from HashMap values) cannot be null");

                // Symétrie respectée : v voisin de s => v a des voisins
                if (somVoisins.get(v) == null)
                    throw new IllegalArgumentException(
                            "HashMap values have to be symmetrical, no neighbors for vertex " + v.getId());

                // Symétrie respectée : v voisin de s => s voisin de v
                if (!somVoisins.get(v).contains(s))
                    throw new IllegalArgumentException(
                            "HashMap values have to be symmetrical, " + s.getId() + " not neighbor of " + v.getId());
            }
        }

        this.sommetsVoisins = somVoisins;
    }

    /**
     * Copy-constructor for a graph
     *
     * @param g Graph to be copied
     * @throws IllegalArgumentException If the graph is null
     */
    public Graphe(Graphe g) {
        this(g.getSommetsVoisins());
    }

    /**
     * Get the Hashmap containing the nodes and their neighbours
     *
     * @return Hashmap containing the nodes of the graph as keys and their
     * neighbours as values
     */
    public HashMap<Sommet, ArrayList<Sommet>> getSommetsVoisins() {
        return new HashMap<>(this.sommetsVoisins);
    }

    /**
     * Get the number of vertices in the graph
     *
     * @return Number of vertices in the graph
     */
    public int nbSommets() {
        return this.sommetsVoisins.size();
    }

    /**
     * Get the number of edges in the graph
     *
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
     *
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
     *
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
     *
     * @return Hashmap containing the ID of the vertex as key and the number of
     * neighbours as value
     */
    public HashMap<Sommet, Integer> calculeDegres() {
        HashMap<Sommet, Integer> degres = new HashMap<Sommet, Integer>();

        for (Sommet s : this.sommetsVoisins.keySet())
            degres.put(s, calculeDegre(s.getId()));

        return degres;
    }

    /**
     * Calculate the vertex with the most neighbours
     *
     * @return vertex with the most neighbours
     */
    public Sommet somMaxDegree() {
        if (this.sommetsVoisins.isEmpty())
            throw new IllegalArgumentException("Graph is empty");
        HashMap<Sommet, Integer> degres = this.calculeDegres();
        Set<Sommet> listeSommets = degres.keySet();
        int maxDeg = -1;
        Sommet sommet = null;

        for (Sommet s : listeSommets)
            if (degres.get(s) > maxDeg) {
                maxDeg = degres.get(s);
                sommet = s;
            }

        return sommet;
    }

    /**
     * Check if a vertex is a neighbour of another
     *
     * @param idSom1 ID of the first vertex
     * @param idSom2 ID of the second vertex
     * @return True if the second vertex is a neighbour of the first, false
     * otherwise
     */
    public boolean sontVoisins(int idSom1, int idSom2) {
        boolean voisin = false;

        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom1)
                for (Sommet s2 : this.sommetsVoisins.get(s1))
                    if (s2.getId() == idSom2)
                        voisin = true;

        return voisin;
    }

    /**
     * Check if a path exists between two vertices
     *
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
     *
     * @param idSom ID of the vertex
     * @return ArrayList containing the neighbours of the vertex
     */
    public ArrayList<Sommet> voisins(int idSom) throws IllegalArgumentException {
        ArrayList<Sommet> listeVoisins = null;

        for (Sommet s1 : this.sommetsVoisins.keySet())
            if (s1.getId() == idSom)
                listeVoisins = this.sommetsVoisins.get(s1);

        if (listeVoisins == null)
            throw new IllegalArgumentException("vertex is not in the graph");

        return listeVoisins;
    }

    /**
     * Add an edge to the graph
     *
     * @param idSom1 ID of the first vertex
     * @param idSom2 ID of the second vertex
     * @return True if the edge has been added, false otherwise
     */
    public boolean ajouteArete(int idSom1, int idSom2) {
        boolean arete = false;
        boolean voisin = false;
        Sommet s1 = null;
        Sommet s2 = null;

        if (estDansGraphe(idSom1) && estDansGraphe(idSom2)) {

            // récupération des id.
            for (Sommet s : this.sommetsVoisins.keySet()) {
                if (s.getId() == idSom1)
                    s1 = s;
                if (s.getId() == idSom2)
                    s2 = s;
            }

            // empêche les arcs multiples.
            if (this.sommetsVoisins.get(s1).contains(s2))
                voisin = true;

            // ajoute une arete.
            if (!voisin) {
                arete = true;
                this.sommetsVoisins.get(s1).add(s2);
                if (s1 != s2)
                    this.sommetsVoisins.get(s2).add(s1);
            }
        }
        return arete;
    }

    /**
     * Remove an edge from the graph
     *
     * @param idSom1 ID of the first vertex
     * @param idSom2 ID of the second vertex
     * @return True if the edge has been removed, false otherwise
     */
    public boolean retireArete(int idSom1, int idSom2) {
        boolean arete = false;
        Sommet s1 = null;
        Sommet s2 = null;

        if (estDansGraphe(idSom1) && estDansGraphe(idSom2)) {

            // récupération des id.
            for (Sommet s : this.sommetsVoisins.keySet()) {
                if (s.getId() == idSom1)
                    s1 = s;
                if (s.getId() == idSom2)
                    s2 = s;
            }

            // retire une arete.
            if (this.sommetsVoisins.get(s1).contains(s2)) {
                arete = true;
                this.sommetsVoisins.get(s1).remove(s2);
                if (s1 != s2)
                    this.sommetsVoisins.get(s2).remove(s1);
            }
        }
        return arete;
    }

    /**
     * Generate the adjacency matrix of the graph
     *
     * @return Adjacency matrix of the graph
     */
    public int[][] matriceAdjacence() {
        int nbSommets = this.nbSommets();
        int[][] adj = new int[nbSommets][nbSommets + 1];
        Sommet[] sommets = this.triParId();
        Sommet s;
        ArrayList<Sommet> v;

        for (int i = 0; i < nbSommets; i++) {
            adj[i][0] = sommets[i].getId();
            s = sommets[i];
            v = this.sommetsVoisins.get(s);
            for (int j = 1; j < nbSommets + 1; j++) {
                if (v.contains(sommets[j - 1])) {
                    adj[i][j] = 1;
                }
            }
        }
        return adj;
    }

    /**
     * Generate an array which contains the vertices sorted by their id
     *
     * @return an id-sorted array of vertices
     */
    private Sommet[] triParId() {

        int nbSommets = this.nbSommets();
        Sommet[] sommets = new Sommet[nbSommets];

        int i = 0;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            sommets[i] = s;
            i++;
        }

        // Tri par id
        int id, mini, tmp;
        Sommet refTmp;

        for (i = 0; i < nbSommets; i++) {
            mini = sommets[i].getId();
            tmp = i;
            for (int j = i + 1; j < nbSommets; j++) {

                id = sommets[j].getId();
                if (id < mini) {
                    mini = id;
                    tmp = j;
                }
            }
            refTmp = sommets[i];
            sommets[i] = sommets[tmp];
            sommets[tmp] = refTmp;
        }

        return sommets;
    }

    /**
     * Check if the graph is connected
     *
     * @return True if the graph is connected, false otherwise
     */
    public boolean estConnexe() {
        boolean connexe = true;
        ArrayList<Sommet> sommets = new ArrayList<>(this.sommetsVoisins.keySet());
        ArrayList<Sommet> dejaVu = new ArrayList<>();
        ArrayList<Sommet> file = new ArrayList<>();

        Sommet som = sommets.get(0);
        dejaVu.add(som);
        for (Sommet s : this.sommetsVoisins.get(som))
            file.add(s);

        while (!file.isEmpty()) {
            som = file.remove(0);
            if (!dejaVu.contains(som))
                dejaVu.add(som);
            for (Sommet s : this.sommetsVoisins.get(som)) {
                if (!file.contains(s) && !dejaVu.contains(s))
                    file.add(s);
            }
        }

        if (dejaVu.size() != sommets.size())
            connexe = false;

        return connexe;
    }

    /**
     * Generate an ArrayList of Graph containing all the sub-graphs of the graph
     *
     * @return an ArrayList of Graph containing all the sub-graphs of the graph
     */
    public ArrayList<Graphe> composanteConnexe() {
        ArrayList<Graphe> composantes = new ArrayList<>();
        HashMap<Sommet, ArrayList<Sommet>> hashmap = new HashMap<>();

        ArrayList<Sommet> sommets = new ArrayList<>(this.sommetsVoisins.keySet());
        ArrayList<Sommet> dejaVu = new ArrayList<>();
        ArrayList<Sommet> file = new ArrayList<>();
        Sommet som;

        while (!sommets.isEmpty()) {
            som = sommets.remove(0);
            if (!dejaVu.contains(som))
                dejaVu.add(som);

            for (Sommet s : this.sommetsVoisins.get(som)) {
                if (!file.contains(s) && !dejaVu.contains(s))
                    file.add(s);
            }

            while (!file.isEmpty()) {
                som = file.remove(0);
                sommets.remove(som);
                if (!dejaVu.contains(som))
                    dejaVu.add(som);
                for (Sommet s : this.sommetsVoisins.get(som)) {
                    if (!file.contains(s) && !dejaVu.contains(s))
                        file.add(s);
                }
            }

            while (!dejaVu.isEmpty()) {
                som = dejaVu.remove(0);
                hashmap.put(som, this.sommetsVoisins.get(som));
            }
            composantes.add(new Graphe(hashmap));
            hashmap = new HashMap<>();
        }
        return composantes;
    }

    /**
     * Gets the minimum distance between two vertices
     *
     * @param idSom1 the id of the first vertex
     * @param idSom2 the id of the second vertex
     * @return the distance separating the vertices
     */
    public int distArete(int idSom1, int idSom2) {
        Sommet s1 = null;
        Sommet s2 = null;
        int dist;

        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom1)
                s1 = s;
            if (s.getId() == idSom2)
                s2 = s;
        }

        if (s1 == null || s2 == null)
            throw new IllegalArgumentException("id cannot be found");

        dist = this.minDistance(idSom1).get(s2);
        return dist;
    }

    /**
     * Generate an HashMap containing the vertices associated with their distance
     * from the given vertex
     *
     * @param id the id of the vertex we calculate the distances
     * @return an ArrayList containing the distancesA2QZ3+
     */
    private HashMap<Sommet, Integer> minDistance(int id) {

        Sommet som = null;
        HashMap<Sommet, Integer> hashmap = new HashMap<>();
        HashMap<Sommet, Integer> dejaVu = new HashMap<>();
        ArrayList<HashMap<Sommet, Integer>> file = new ArrayList<>();
        int dist = 0;

        if (!this.estConnexe()) {
            for (Sommet s : this.sommetsVoisins.keySet())
                dejaVu.put(s, -1);
        } else {

            for (Sommet s : this.sommetsVoisins.keySet()) {
                if (s.getId() == id)
                    som = s;
            }

            if (som == null)
                throw new IllegalArgumentException("id cannot be found");

            dejaVu.put(som, dist);
            dist++;
            for (Sommet s : this.sommetsVoisins.get(som)) {
                hashmap.put(s, dist);
                file.add(hashmap);
                hashmap = new HashMap<>();
            }

            while (!file.isEmpty()) {
                hashmap = file.remove(0);
                for (Sommet s : hashmap.keySet())
                    som = s;
                dist = hashmap.get(som);
                if (!dejaVu.keySet().contains(som))
                    dejaVu.put(som, dist);

                else if (dejaVu.get(som) > dist)
                    dejaVu.put(som, dist);

                for (Sommet s : this.sommetsVoisins.get(som)) {
                    if (!dejaVu.keySet().contains(s)) {
                        hashmap = new HashMap<>();
                        hashmap.put(s, dist + 1);
                        file.add(hashmap);
                    }
                }
            }
        }
        return dejaVu;
    }

    /**
     * Calculates the maximum number of edges of the path between the parameter
     * vertex and the other vertices of the graph. Works only with related graphs,
     * otherwise returns -1.
     *
     * @param idSom Vertex identifier
     * @return The eccentricity of the vertex or -1 if the function is used in an
     * unconnected graph.
     */
    public int excentricite(int idSom) {
        int mini = -1;
        HashMap<Sommet, Integer> distances = minDistance(idSom);
        for (Sommet s : distances.keySet())
            if (mini < distances.get(s))
                mini = distances.get(s);
        return mini;
    }

    /**
     * Calculate the smallest exentricity of the graph
     *
     * @return Radius of the graph
     */
    public int rayon() {
        int rayon = Integer.MAX_VALUE;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            int idSom = s.getId();
            int excentriciteActuel = excentricite(idSom);
            if (excentriciteActuel < rayon) {
                rayon = excentriciteActuel;
            }

        }
        return rayon;
    }

    /**
     * Calculate the largest exentricity of the graph
     *
     * @return Diameter of the graph
     */
    public int diametre() {
        int diametre = -1;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            int idSom = s.getId();
            int excentriciteActuel = excentricite(idSom);
            if (excentriciteActuel > diametre) {
                diametre = excentriciteActuel;
            }
        }
        return diametre;
    }

    /**
     * Format the graph to a string
     *
     * @return String representation of the graph
     */
    public String toString() {
        String printFormat = "Graphe :\n";
        ArrayList<Sommet> debug;
        Integer[] idSommets;
        Sommet som;

        for (Sommet s : this.sommetsVoisins.keySet()) {
            debug = this.sommetsVoisins.get(s);
            if (debug == null)
                idSommets = new Integer[0];
            else {
                idSommets = new Integer[debug.size()];
                for (int i = 0; i < idSommets.length; i++) {
                    som = debug.get(i);
                    if (som != null)
                        idSommets[i] = som.getId();
                    else
                        idSommets[i] = null;
                }
            }
            printFormat = printFormat + "Id : " + s.getId() + " " + Arrays.toString(idSommets) + "\n";
        }
        return printFormat;
    }
}