package by.epam.inner;

import java.time.format.DateTimeFormatter;

public class Constants {

    public static final String WORDS_SEPARATOR = ";";
    public static final String SEPARATOR = " >> ";
    public final static String RESULT_NOT_FOUND_MESSAGE = "not found";
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd");
    public final static String HEADER_LAST_PURCHASE_MAP = "Last purchase map:";
    public final static String HEADER_FIRST_PURCHASE_MAP = "First purchase map:";
    public final static String HEADER_ENUMERATED_PURCHASE_MAP = "Enumerated map:";
    public final static String HEADER_SEARCH_RES = "dates for ";
    public final static String IN_LAST_MAP = " in lastMap is ";
    public final static String IN_FIRST_MAP = " in firstMap is ";
    public final static String HEADER_FOR_WDAY_RES = "Purchases for weekday ";
    public static final String IN_ENUM_MAP = " in enumMap is";
    public static final String WRONG_VALUE_MES = " wrong value ";
    public static final String EMPTY_STRING_MES = " empty string ";
    public static final String NOT_POS_VAL_MES = " not positive ";
    public static final String MORE_THAN_FOUR_TOKENS_MES = "contains more than 4 tokens";
    public static final String LESS_THAN_THREE_TOKENS_MES = "contains less than 3 tokens";
    public static final String TOTAL_COST_HEADER = "total cost in List ";
}
