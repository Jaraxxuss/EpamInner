package by.epam.inner.beans;

import by.epam.inner.enums.Field;
import by.epam.inner.exceptions.IllegalNumberException;
import by.epam.inner.exceptions.NotPositiveArgumentException;

import java.util.Objects;

public class Purchase {
    private final Product product;
    private final int number;

    public Purchase(Product product, int number) {
        this.product = product;
        if(number <= 0){
            throw new IllegalNumberException(new NotPositiveArgumentException(number),Field.NUMBER);
        }
        this.number = number;
    }

    public Purchase(String[] tokens) {
        this(
                new Product(tokens[Field.NAME.ordinal() - 1],tokens[Field.PRICE.ordinal() - 1]),
                Integer.parseInt(tokens[Field.NUMBER.ordinal() - 1])
        );
    }

    public Product getProduct() {
        return product;
    }


    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.format("%s;%s",fieldsToString(),totalCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if(!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return product.equals(purchase.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    public Byn totalCost() {
        return baseCost();
    }

    protected final Byn baseCost(){
        return product.getPrice().mul(number);
    }

    protected String fieldsToString(){
        return String.format("%s;%d",product,number);
    }
}
