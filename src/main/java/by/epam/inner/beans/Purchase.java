package by.epam.inner.beans;

import by.epam.inner.enums.Field;
import by.epam.inner.exceptions.NotPossitiveArgumentException;

import java.util.Objects;

public class Purchase {
    private Product product;
    private int number;

    public Purchase(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public Purchase(String[] tokens) {
        setProduct(tokens[Field.NAME.ordinal() - 1],
                String.valueOf((int) (Float.parseFloat(tokens[Field.PRICE.ordinal() - 1]) * 100))
        );
        setNumber(tokens[Field.NUMBER.ordinal() - 1]);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(String name,String price) {
        product = new Product(name,price);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if(number < 0){
            throw new NotPossitiveArgumentException(Field.NUMBER,number);
        }
        this.number = number;
    }

    public void setNumber(String number) {
        setNumber(Integer.parseInt(number));
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
