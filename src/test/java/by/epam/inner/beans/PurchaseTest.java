package by.epam.inner.beans;

import org.junit.Test;

import static org.junit.Assert.*;

public class PurchaseTest {

    @Test
    public void equals1() {
        Product product1 = new Product("bread",new Byn(155));
        Product product2 = new Product("bread",new Byn(155));

        Purchase purchase1 = new Purchase(product1,3);
        Purchase purchase2 = new PriceDiscountPurchase(product2,4,new Byn(90));

        assertEquals(purchase1,purchase2);
    }
}