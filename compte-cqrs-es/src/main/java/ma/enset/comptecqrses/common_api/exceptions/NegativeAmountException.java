package ma.enset.comptecqrses.common_api.exceptions;

public class NegativeAmountException extends RuntimeException {
    public NegativeAmountException() {
        super("Negative amount not allowed!");
    }

    public NegativeAmountException(String message) {
        super(message);
    }
}
