package by.epam.inner.exceptions;

import by.epam.inner.enums.Field;

public class IllegalNumberException extends IllegalFieldException {

    public IllegalNumberException(Throwable cause, Field field) {
        super(cause, field);
    }

    public IllegalNumberException(Field field) {
        super(field);
    }

    @Override
    protected String type() {
        return getCause().getMessage();
    }
}
