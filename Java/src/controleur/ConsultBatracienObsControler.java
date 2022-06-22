package controleur;

import java.util.ArrayList;
import modele.donnee.UseDatabase;

public class ConsultBatracienObsControler {

    private static ArrayList<String> observation;

    public static void setObs(int numObs) {
        observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromLoutre WHERE ObsL = " + numObs + ";").get(1);
    }
}
