package by.epam.inner.util;

import by.epam.inner.Constants;
import by.epam.inner.exceptions.PatternArgumentException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    static Pattern pattern = Pattern.compile("(\\d+)\\.?(\\d*)");

    public static int parseInt(String s) {
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            throw new PatternArgumentException(Constants.WRONG_ARGUMENT_PATTERN);
        }
        String g1 = matcher.group(1);
        String g2 = matcher.group(2);
        return Integer.parseInt(g1) * 100 + (!g2.isEmpty() ? Integer.parseInt(g2) : 0);
    }
}

