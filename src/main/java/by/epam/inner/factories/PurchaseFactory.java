package by.epam.inner.factories;

import by.epam.inner.Constants;
import by.epam.inner.beans.PriceDiscountPurchase;
import by.epam.inner.beans.Purchase;
import by.epam.inner.exceptions.CsvLineException;

public class PurchaseFactory {

    private static enum PurchasesKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(String[] tokens) {
                return new Purchase(tokens);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            Purchase getPurchase(String[] tokens) {
                return new PriceDiscountPurchase(tokens);
            }
        };
        abstract Purchase getPurchase(String[] tokens);
    }

    public static Purchase getClassFromFactory(String csvLine) throws CsvLineException {
        PurchasesKind id = PurchasesKind.GENERAL_PURCHASE;
        String[] tokens = csvLine.substring(csvLine.indexOf(Constants.WORDS_SEPARATOR) + 1).split(Constants.WORDS_SEPARATOR);
        if(tokens.length > 4){
            throw new CsvLineException(new IllegalStateException(Constants.MORE_THAN_FOUR_TOKENS_MES),csvLine);
        }
        if(tokens.length < 3){
            throw new CsvLineException(new IllegalStateException(Constants.LESS_THAN_THREE_TOKENS_MES),csvLine);
        }
        if(tokens.length == 4){
            id = PurchasesKind.PRICE_DISCOUNT_PURCHASE;
        }
        Purchase purchase;
        try {
            purchase = id.getPurchase(tokens);
        } catch (IllegalArgumentException e){
            throw new CsvLineException(e,csvLine);
        }
        return purchase;
    }
}
