import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extractor
 *
 * Extracts data about a product from a product page
 * Contains methods for returning the extracted data
 */

public class Extractor {
    private Document page;
    private String pageTitle;
    private String pageLink;
    private final String PRODUCT_SUMMARY = "div.productSummary";
    private final String PRODUCT_INFORMATION = "div.section";
    Element productSummary;
    Element productInformation;
    Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");

    public Extractor(Element link) {
        this.pageLink = link.selectFirst("a").attr("abs:href");
        try {
            this.page = Jsoup.connect(pageLink).get();
            this.pageTitle = page.title();
            this.productSummary = page.selectFirst(PRODUCT_SUMMARY);
            this.productInformation = page.selectFirst(PRODUCT_INFORMATION).getElementById("information");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProductName() {
        String title = productSummary.selectFirst("h1").text();
        return title;
    }

    public BigDecimal getProductPrice() {
        String priceStr = productSummary.selectFirst("p.pricePerUnit").text();
        Matcher matcher = pattern.matcher(priceStr);
        if(matcher.find()) {
            priceStr = matcher.group();
        }
        BigDecimal price = new BigDecimal(priceStr).setScale(2, RoundingMode.HALF_UP);
        return price;
    }

    public String getProductDescription() {
        String description = productInformation.selectFirst("p").text();
        return description;
    }

    public int getProductCalories() {
        String calories = productInformation.select("tr:nth-of-type(2)").select("td:nth-of-type(1)").text();
        Matcher matcher = pattern.matcher(calories);
        if (matcher.find()) {
            calories = matcher.group();
        }

        if (calories.length() > 0) {
            return Integer.parseInt(calories);
        } else {
            return -1;
        }
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getPageLink() {
        return pageLink;
    }
}
