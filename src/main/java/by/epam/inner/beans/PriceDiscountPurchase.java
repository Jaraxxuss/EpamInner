package by.epam.inner.beans;

import by.epam.inner.exceptions.PatternArgumentException;
import by.epam.inner.util.Utils;
import by.epam.inner.enums.Field;
import by.epam.inner.exceptions.IllegalBynException;

public class PriceDiscountPurchase extends Purchase {

    private final Byn priceDiscount;

    public PriceDiscountPurchase(String[] tokens) {
        super(tokens);
        Byn priceDiscount;
        try{
            priceDiscount = new Byn(Utils.parseInt(tokens[Field.DISCOUNT.ordinal() - 1]));
        } catch (PatternArgumentException e){
            throw new IllegalBynException(Field.DISCOUNT,tokens[Field.DISCOUNT.ordinal() - 1]);
        }

        if(priceDiscount.compareTo(getProduct().getPrice()) > 0){
            throw new IllegalBynException(Field.DISCOUNT,priceDiscount);
        }
        this.priceDiscount = priceDiscount;
    }

    public PriceDiscountPurchase(Product product, int number, Byn priceDiscount) {
        super(product,number);
        this.priceDiscount = priceDiscount;
    }

    @Override
    public Byn totalCost() {
        return super.baseCost().sub(priceDiscount.mul(getNumber()));
    }

    @Override
    protected String fieldsToString() {
        return String.format("%s;%s",super.fieldsToString(),priceDiscount);
    }

}