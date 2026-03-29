package universite_Paris8.iut.qdev.tp2026.gr14.utils.exceptions;

public class DonneesCorrompuesException extends Exception {

    public DonneesCorrompuesException() {
        super("Les donnees du questionnaire sont corrompues ou invalides.");
    }

    public DonneesCorrompuesException(String message) {
        super(message);
    }

    public DonneesCorrompuesException(String message, Throwable cause) {
        super(message, cause);
    }

    public DonneesCorrompuesException(Throwable cause) {
        super(cause);
    }
}