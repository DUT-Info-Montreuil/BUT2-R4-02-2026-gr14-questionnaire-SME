package universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions;

public class CSVInexistantException extends Exception {

    public CSVInexistantException() {
        super("Le fichier CSV demande est introuvable.");
    }

    public CSVInexistantException(String message) {
        super(message);
    }

    public CSVInexistantException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVInexistantException(Throwable cause) {
        super(cause);
    }
}
