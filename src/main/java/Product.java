import org.jsoup.nodes.Element;

import java.math.BigDecimal;

/**
 * Product
 *
 * Holds various data about a product which will be returned to the scraper
 */

public class Product {
    private String title;
    private BigDecimal unit_price;
    private Integer kcal_per_100g;
    private String description;

    public Product(Element link) {
        Extractor productPage = new Extractor(link);
        this.title = productPage.getProductName();
        this.unit_price = productPage.getProductPrice();
        this.description = productPage.getProductDescription();
        int kcal = productPage.getProductCalories();
        this.kcal_per_100g = (kcal > 0) ? kcal : null;
    }

    // Constructor used for testing
    public Product(String title, BigDecimal unit_price, String description, int kcal_per_100g) {
        this.title = title;
        this.unit_price = unit_price;
        this.description = description;
        this.kcal_per_100g = kcal_per_100g;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getUnitPrice() {
        return unit_price;
    }

    public String getDescription() {
        return description;
    }

    public int getKcal_per_100g() {
        return kcal_per_100g;
    }
}
