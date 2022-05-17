package modele;

import modele.donnee.*;

public class ScenarioDonnee {
    public static void main(String[] args) {
        System.out.println("## Scenario de données");

        // Creation des objets
        Chouette chouette = new Chouette("chouette", Sexe.MALE, EspeceChouette.HULOTTE);
        Lieu lieu = new Lieu(5, 5);
    }
}