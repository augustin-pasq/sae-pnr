package controleur;

/**
 * Class to pass data between scenes
 *
 * @author Groupe SAE PNR 1D1
 */
public class Data {
    /**
     * The data to pass
     */
    private String[] data;

    /**
     * Constructor
     * @param data the data to pass
     */
    public Data(String... data) {
        this.data = data;
    }

    /**
     * Get the data at specified index
     * @param i the index
     * @return the data at index i
     */
    public String get(int i) {
        return data[i];
    }

    /**
     * Get all the data
     * @return all the data
     */
    public String[] getAll() {
        return data;
    }

    /**
     * Get the size of the data
     * @return the size of the data
     */
    public int size() {
        return data.length;
    }

    /**
     * Get the data as a string
     * @return the data as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : data) {
            sb.append(s);
            sb.append(", ");
        }
        return sb.toString();
    }
}
