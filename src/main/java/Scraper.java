import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Scraper
 *
 * Creates a new Product for each product on the site and adds them to a list of products
 * Returns the list of products as a JSON string
 */

public class Scraper {
    private Document document;
    private String pageTitle;
    private ArrayList<Product> products;
    private final String PRODUCT_SELECTOR = "div.productNameAndPromotions";

    public Scraper(String url) {
        try {
            this.document = Jsoup.connect(url).get();
            this.pageTitle = document.title();
            this.products = new ArrayList<Product>();
            findAllProducts();
        } catch (IllegalArgumentException e) {
            System.err.println("Malformed URL! Please check URL is correct.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAllProducts() {
        Elements productLinks = document.select(PRODUCT_SELECTOR);
        for (Element productLink : productLinks) {
            products.add(new Product(productLink));
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0.00);
        for (Product product : products) {
            total = total.add(product.getUnitPrice());
        }
        return total;
    }

    public BigDecimal getVAT() {
        BigDecimal total = getTotal();
        BigDecimal vat = total.divide(new BigDecimal(1.2), RoundingMode.HALF_UP);
        return total.subtract(vat);
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getResults() {
        HashMap total = new HashMap();
        total.put("gross", this.getTotal());
        total.put("vat", this.getVAT());

        HashMap results = new HashMap();
        results.put("results", this.products);
        results.put("total", total);

        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        return gson.toJson(results);
    }
}
