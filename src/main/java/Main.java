/**
 * Main
 *
 * Application starting point
 * Creates a new Scraper for a given URL
 * Gets results from scraper and prints it out as a JSON string
 */

public class Main {
    public static void main(String[] args) {
        final String URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

        Scraper scraper = new Scraper(URL);
        System.out.println(scraper.getResults());
    }
}
