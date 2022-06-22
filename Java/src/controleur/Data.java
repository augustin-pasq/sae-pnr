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
    private final Object[] data;
    private boolean isAdmin = false;

    /**
     * Constructor
     * @param data the data to pass
     */
    public Data(Object... data) {
        this.data = data;
    }

    /**
     * Get the data at specified index
     * @param i the index
     * @return the data at index i
     */
    public Object get(int i) {
        return data[i];
    }

    /**
     * Get all the data
     * @return all the data
     */
    public Object[] getAll() {
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
     * Check if the user is an admin
     * @return true if the user is an admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Set the admin status
     * @param isAdmin true if the user is an admin
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Get the data as a string
     * @return the data as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object s : data) {
            sb.append(s);
            sb.append(", ");
        }
        return sb.toString();
    }
}
