import by.epam.inner.beans.Byn;
import by.epam.inner.beans.Product;
import by.epam.inner.beans.Purchase;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RunnerTest {

    @Test
    public void main() {
        Map<Purchase, LocalDate> lastPurchaseMap = new HashMap<>();
        Purchase purchase = new Purchase(new Product("bread",new Byn(155)),2);
        lastPurchaseMap.put(new Purchase(new Product("bread",new Byn(155)),2),LocalDate.of(2020,6,12));
        lastPurchaseMap.put(new Purchase(new Product("bread",new Byn(155)),2),LocalDate.of(2020,6,13));
        for (Map.Entry<Purchase, LocalDate> purchaseLocalDateEntry : lastPurchaseMap.entrySet()) {
            System.out.println(purchaseLocalDateEntry.getKey() + ">>" + purchaseLocalDateEntry.getValue());
        }
        System.out.println(lastPurchaseMap.get(new Purchase(new Product("bread",new Byn(155)),2)));
        assertTrue(true);
    }
}