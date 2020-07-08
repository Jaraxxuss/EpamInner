package by.epam.inner.exceptions;

public class PatternArgumentException extends IllegalArgumentException {
    public PatternArgumentException() {
    }

    public PatternArgumentException(String message) {
        super(message);
    }

    public PatternArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatternArgumentException(Throwable cause) {
        super(cause);
    }
}
