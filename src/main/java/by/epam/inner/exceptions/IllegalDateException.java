package by.epam.inner.exceptions;

import by.epam.inner.Constants;
import by.epam.inner.enums.Field;

public class IllegalDateException extends IllegalFieldException {


    private final String date;

    public IllegalDateException(Throwable e, Field date, String token) {
        super(e, date);
        this.date = token;
    }

    @Override
    protected String type() {
        return date + Constants.WRONG_VALUE_MES;
    }
}
