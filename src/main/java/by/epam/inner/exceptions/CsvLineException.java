package by.epam.inner.exceptions;

import by.epam.inner.Constants;

public class CsvLineException extends Exception {

    private final String csvLine;

    public CsvLineException(Throwable cause, String csvLine) {
        super(cause);
        this.csvLine = csvLine;
    }

    @Override
    public String getMessage() {
        return csvLine + Constants.SEPARATOR + getCause().getMessage();
    }
}
