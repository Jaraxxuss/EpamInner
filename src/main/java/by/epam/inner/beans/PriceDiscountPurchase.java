package by.epam.inner.beans;

import by.epam.inner.enums.Field;
import by.epam.inner.exceptions.IllegalBynException;

public class PriceDiscountPurchase extends Purchase {

    private Byn priceDiscount;

    public PriceDiscountPurchase(String[] tokens) {
        super(tokens);
        setPriceDiscount(
                String.valueOf((int) (Float.parseFloat(tokens[Field.DISCOUNT.ordinal() - 1]) * 100))
        );
    }

    public PriceDiscountPurchase(Product product, int number, Byn priceDiscount) {
        super(product,number);
        this.priceDiscount = priceDiscount;
    }

    public void setPriceDiscount(Byn priceDiscount) throws IllegalBynException {
        if(priceDiscount.compareTo(Byn.ZERO) < 0){
            throw new IllegalBynException(Field.DISCOUNT,priceDiscount);
        }
        this.priceDiscount = priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        setPriceDiscount(new Byn(priceDiscount));
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
