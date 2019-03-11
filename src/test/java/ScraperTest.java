import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


public class ScraperTest {
    private static String URL;
    private static Scraper scraper;

    @BeforeAll
    public static void setup() {
        URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
        scraper = new Scraper(URL);
    }

    @Test
    public void scraperShouldNotReturnNull() {
        Assertions.assertNotNull(scraper);
    }

    @Test
    public void scraperShouldReturnCorrectPageTitle() {
        Assertions.assertEquals("Berries, cherries & currants | Sainsbury's", scraper.getPageTitle());
    }

    @Test
    public void scraperTitleShouldNotMatch() {
        Scraper scraper2 = new Scraper("https://jsainsburyplc.github.io/serverside-test/");

        Assertions.assertNotEquals("Berries, cherries & currants | Sainsbury's", scraper2.getPageTitle());
    }

    @Test
    public void scraperGrossShouldNotBeZero() {
        BigDecimal zero = new BigDecimal(0);
        boolean equals = scraper.getTotal().compareTo(zero) == 0;

        Assertions.assertFalse(equals);
    }

    @Test
    public void scraperVatShouldNotBeZero() {
        BigDecimal zero = new BigDecimal(0);
        boolean equals = scraper.getVAT().compareTo(zero) == 0;

        Assertions.assertFalse(equals);
    }

    @Test
    public void scraperProductListShouldNotBeEmpty() {
        Assertions.assertNotEquals(0, scraper.getProducts().size());
    }

    @Test
    public void scraperProductListShouldBeSeventeen() {
        Assertions.assertEquals(17, scraper.getProducts().size());
    }

    @Test
    public void scraperJsonShouldNotBeEmpty() {
        Assertions.assertFalse(scraper.getResults().isEmpty());
    }
}
