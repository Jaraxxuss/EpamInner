package by.epam.inner.beans;

import by.epam.inner.util.Utils;
import by.epam.inner.enums.Field;
import by.epam.inner.exceptions.IllegalStringException;

import java.util.Objects;

public class Product {
    private final String name;
    private final Byn price;

    public Product(String name, Byn price) {
        if(name == null || name.isEmpty()){
            throw new IllegalStringException(Field.NAME,name);
        }
        this.name = name;
        this.price = price;
    }

    public Product(String name, String price) {
        this(
                name,
                new Byn(Utils.parseInt(price))
        );
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
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
