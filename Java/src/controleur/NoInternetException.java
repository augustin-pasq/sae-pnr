package controleur;

/**
 * The exception thrown when the internet is not available
 */
public class NoInternetException extends Exception {

    /**
     * Constructs a new exception with the specified message
     * @param msg the message of the exception
     */
    public NoInternetException(String msg) {
        super(msg);
    }
}
