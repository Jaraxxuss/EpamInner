package by.epam.inner.beans;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void equals1() {
        Product product1 = new Product("bread",new Byn(155));
        Product product2 = new Product("bread",new Byn(155));

        assertEquals(product1,product2);
    }
}