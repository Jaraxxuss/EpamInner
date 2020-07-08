package by.epam.inner.exceptions;

import by.epam.inner.Constants;
import by.epam.inner.beans.Byn;
import by.epam.inner.enums.Field;

public class IllegalBynException extends IllegalFieldException {

    private final Byn byn;

    public IllegalBynException(Field field, Byn byn) {
        super(field);
        this.byn = byn;
    }

    @Override
    protected String type() {
        return byn + Constants.WRONG_VALUE_MES;
    }
}
