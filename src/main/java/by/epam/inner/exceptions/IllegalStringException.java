package by.epam.inner.exceptions;

import by.epam.inner.Constants;
import by.epam.inner.enums.Field;

public class IllegalStringException extends IllegalFieldException {
    private final String illegalString;

    public IllegalStringException(Field field, String illegalString) {
        super(field);
        this.illegalString = illegalString;
    }


    @Override
    protected String type() {
        return illegalString + Constants.EMPTY_STRING_MES;
    }
}
