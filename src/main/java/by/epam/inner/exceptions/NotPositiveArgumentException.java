package by.epam.inner.exceptions;

public class NotPositiveArgumentException extends IllegalArgumentException {

    private final int notPositiveValue;

    public NotPositiveArgumentException(int notPositiveValue) {
        this.notPositiveValue = notPositiveValue;
    }

    public NotPositiveArgumentException(String s, int notPositiveValue) {
        super(s);
        this.notPositiveValue = notPositiveValue;
    }

    public NotPositiveArgumentException(String message, Throwable cause, int notPositiveValue) {
        super(message, cause);
        this.notPositiveValue = notPositiveValue;
    }

    public NotPositiveArgumentException(Throwable cause, int notPositiveValue) {
        super(cause);
        this.notPositiveValue = notPositiveValue;
    }

}
