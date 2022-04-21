package tdd.numberbaseball.exception;

public class GuessNumberException extends RuntimeException {
    public GuessNumberException(String message) {
        super(message);
    }
}