package controleur;

/**
 * The exception thrown when the internet is not available
 */
public class NoInternetException extends Exception {
    public NoInternetException(String msg) {
        super(msg);
    }
}
