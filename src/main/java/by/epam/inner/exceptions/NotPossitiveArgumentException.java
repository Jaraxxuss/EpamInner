package by.epam.inner.exceptions;

import by.epam.inner.Constants;
import by.epam.inner.enums.Field;

public class NotPossitiveArgumentException extends IllegalFieldException {

    private final int notPositiveValue;

    public NotPossitiveArgumentException(Field field, int notPositiveValue) {
        super(field);
        this.notPositiveValue = notPositiveValue;
    }

    @Override
    protected String type() {
        return notPositiveValue + Constants.NOT_POS_VAL_MES;
    }
}
