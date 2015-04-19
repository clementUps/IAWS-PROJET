package Exception.requete;

/**
 * Created by clement on 19/04/2015.
 */
public class NullRequeteException extends Exception {
    /**
     * Crée une nouvelle instance de NombreNonValideException
     */
    public NullRequeteException() {}
    /**
     * Crée une nouvelle instance de NombreNonValideException
     * @param message Le message détaillant exception
     */
    public NullRequeteException(String message) {
        super(message);
    }
    /**
     * Crée une nouvelle instance de NombreNonValideException
     * @param cause L'exception à l'origine de cette exception
     */
    public NullRequeteException(Throwable cause) {
        super(cause);
    }
    /**
     * Crée une nouvelle instance de NombreNonValideException
     * @param message Le message détaillant exception
     * @param cause L'exception à l'origine de cette exception
     */
    public NullRequeteException(String message, Throwable cause) {
        super(message, cause);
    }
}
