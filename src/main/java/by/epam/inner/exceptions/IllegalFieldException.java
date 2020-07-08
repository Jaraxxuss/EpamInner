package by.epam.inner.exceptions;

import by.epam.inner.enums.Field;

public abstract class IllegalFieldException extends IllegalArgumentException {
    private final Field field;

    public IllegalFieldException(Throwable cause, Field field) {
        super(cause);
        this.field = field;
    }

    public IllegalFieldException(Field field) {
        this.field = field;
    }

    @Override
    public String getMessage() {
        return type() + " at field#" + field.name();
    }

    protected abstract String type();
}
