package by.epam.inner.beans;

import by.epam.inner.enums.Field;
import by.epam.inner.exceptions.IllegalBynException;
import by.epam.inner.exceptions.IllegalStringException;

import java.util.Objects;

public class Product {
    private String name;
    private Byn price;

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, String price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setName(String name) {
        if(name.isEmpty()){
            throw new IllegalStringException(Field.NAME,name);
        }
        this.name = name;
    }

    public void setPrice(String price) {
        setPrice(new Byn(Integer.parseInt(price)));
    }

    public void setPrice(Byn price) {
        if(price.compareTo(Byn.ZERO) < 0){
            throw new IllegalBynException(Field.PRICE,price);
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s;%s",name,price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
