import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProductTest {
    private static Product product;

    @BeforeAll
    public static void setup() {
        product = new Product("Product_Title", new BigDecimal(52.37), "Product_Description", 89);
    }

    @Test
    public void productShouldNotReturnNull() {
        Assertions.assertNotNull(product);
    }

    @Test
    public void productShouldReturnCorrectTitle() {
        Assertions.assertEquals("Product_Title", product.getTitle());
    }

    @Test
    public void productShouldReturnCorrectPrice() {
        BigDecimal price = new BigDecimal(52.37);
        boolean equals = product.getUnitPrice().compareTo(price) == 0;

        Assertions.assertTrue(equals);
    }

    @Test
    public void productShouldReturnCorrectDescription() {
        Assertions.assertEquals("Product_Description", product.getDescription());
    }

    @Test
    public void productShouldReturnCorrectCalories() {
        Assertions.assertEquals(89, product.getKcal_per_100g());
    }
}
