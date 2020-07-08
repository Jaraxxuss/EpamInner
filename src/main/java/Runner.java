import by.epam.inner.Constants;
import by.epam.inner.beans.Byn;
import by.epam.inner.beans.PriceDiscountPurchase;
import by.epam.inner.beans.Product;
import by.epam.inner.beans.Purchase;
import by.epam.inner.enums.Field;
import by.epam.inner.exceptions.CsvLineException;
import by.epam.inner.exceptions.IllegalDateException;
import by.epam.inner.factories.PurchaseFactory;
import by.epam.inner.ifaces.EntryChecker;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Runner {

    private static final Logger LOGGER = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        final String BREAD_NAME = "bread";
        final String MEAT_NAME = "meat";
        final String POTATO_NAME = "potato";
        final String MILK_NAME = "milk";
        final int YEAR_TO_FIND = 2018;
        final Product BREAD_155 = new Product(BREAD_NAME,new Byn(155));
        final Product BREAD_170 = new Product(BREAD_NAME,new Byn(170));
        final Purchase breadToFind1 = new PriceDiscountPurchase(BREAD_155, 5,new Byn(20));
        final Purchase breadToFind2 = new Purchase(BREAD_170,5);
        try (Scanner scr = new Scanner(new FileReader(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(args[0])).getFile()))) {
            Map<Purchase, LocalDate> lastPurchaseMap = new HashMap<>();
            Map<Purchase, LocalDate> firstPurchaseMap = new HashMap<>();
            List<PriceDiscountPurchase> pricePurchases = new ArrayList<>();
            Map<DayOfWeek, List<Purchase>> enumeratedMap = new EnumMap<>(DayOfWeek.class);
            while (scr.hasNextLine()) {
                String csvLine = scr.nextLine();
                String[] tokens = csvLine.split(Constants.WORDS_SEPARATOR);
                try {
                    try {
                        LocalDate date = LocalDate.parse(tokens[Field.DATE.ordinal()], Constants.DATE_TIME_FORMATTER);
                        Purchase purchase = PurchaseFactory.getClassFromFactory(csvLine);
                        lastPurchaseMap.put(purchase, date);
                        if (firstPurchaseMap.get(purchase) == null) {
                            firstPurchaseMap.put(purchase, date);
                        }
                        if (purchase instanceof PriceDiscountPurchase) {
                            pricePurchases.add((PriceDiscountPurchase) purchase);
                        }
                        DayOfWeek dayOfWeek = date.getDayOfWeek();
                        if (enumeratedMap.get(dayOfWeek) == null) {
                            enumeratedMap.put(dayOfWeek, new ArrayList<>());
                        }
                        enumeratedMap.get(dayOfWeek).add(purchase);


                    } catch (DateTimeParseException e) {
                        throw new CsvLineException(new IllegalDateException(e,Field.DATE,tokens[Field.DATE.ordinal()]),csvLine);
                    }

                } catch (CsvLineException e) {
                    LOGGER.error(e);
                }
            }

            System.out.println(lastPurchaseMap.get(breadToFind1));

            printMapPurchase(lastPurchaseMap, Constants.HEADER_LAST_PURCHASE_MAP);
            printMapPurchase(firstPurchaseMap, Constants.HEADER_FIRST_PURCHASE_MAP);
            printMapPurchase(enumeratedMap, Constants.HEADER_ENUMERATED_PURCHASE_MAP);
            outputSearchResults(Constants.HEADER_SEARCH_RES, breadToFind1, Constants.IN_FIRST_MAP, search(firstPurchaseMap, breadToFind1));
            outputSearchResults(Constants.HEADER_SEARCH_RES, breadToFind2, Constants.IN_LAST_MAP, search(lastPurchaseMap, breadToFind2));
            outputSearchResults(Constants.HEADER_FOR_WDAY_RES, DayOfWeek.MONDAY, Constants.IN_ENUM_MAP, search(enumeratedMap, DayOfWeek.MONDAY));
            for (Map.Entry<DayOfWeek, List<Purchase>> weekDayListEntry : enumeratedMap.entrySet()) {
                System.out.println(weekDayListEntry.getKey() + " " + totalCost(weekDayListEntry.getValue()));
            }
            System.out.println(Constants.TOTAL_COST_HEADER + totalCost(pricePurchases));
            delete(lastPurchaseMap, new EntryChecker<Purchase, LocalDate>() {
                @Override
                public boolean check(Map.Entry<Purchase, LocalDate> entry) {
                    return entry.getKey().getProduct().getName().equals(MEAT_NAME);
                }
            });
            delete(firstPurchaseMap, new EntryChecker<Purchase, LocalDate>() {
                @Override
                public boolean check(Map.Entry<Purchase, LocalDate> entry) {
                    return entry.getValue().getDayOfWeek() == DayOfWeek.FRIDAY;
                }
            });
            delete(firstPurchaseMap, new EntryChecker<Purchase, LocalDate>() {
                @Override
                public boolean check(Map.Entry<Purchase, LocalDate> entry) {
                    return entry.getKey().getProduct().getName().equals(POTATO_NAME) && entry.getValue().getYear() == YEAR_TO_FIND;
                }
            });

            delete(enumeratedMap, new EntryChecker<DayOfWeek, List<Purchase>>() {
                @Override
                public boolean check(Map.Entry<DayOfWeek, List<Purchase>> entry) {
                    for (Purchase purchase : entry.getValue()) {
                        if(purchase.getProduct().getName().equals(MILK_NAME)){
                            return true;
                        }
                    }
                    return false;
                }
            });

            printMapPurchase(lastPurchaseMap, Constants.HEADER_LAST_PURCHASE_MAP);
            printMapPurchase(firstPurchaseMap, Constants.HEADER_FIRST_PURCHASE_MAP);
            printMapPurchase(enumeratedMap, Constants.HEADER_ENUMERATED_PURCHASE_MAP);


        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        }

    }

    private static <K, V> void printMapPurchase(Map<K, V> map, final String header) {
        final String SEPARATOR = " => ";
        System.out.println(header);
        for (Map.Entry<K, V> o : map.entrySet()) {
            System.out.println(o.getKey() + SEPARATOR + o.getValue());
        }
        System.out.println();
    }

    private static <K, V> V search(Map<K, V> map, K toFind) {
        return map.get(toFind);
    }

    private static <K,V> void delete(Map<K,V> map, EntryChecker<K,V> checker) {
        Iterator<Map.Entry<K,V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            if (checker.check(iterator.next())){
                iterator.remove();
            }
        }
    }

    private static Byn totalCost(List<? extends Purchase> purchases) {
        Byn total = new Byn();
        for (Purchase purchase : purchases) {
            total = total.add(purchase.totalCost());
        }
        return total;
    }

    private static <T, V> void outputSearchResults(final String header, final T toFind, final String where, final V result) {
        System.out.println(header + toFind + where + (result == null ? Constants.RESULT_NOT_FOUND_MESSAGE : result));
    }
}
