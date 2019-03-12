# Grocery Scraper
A Java console application that scrapes a portion of a grocery website and returns a JSON array containing information of all the products on the page.

## Getting Started
### Prerequisites
This project was built with Java 8 and Maven, please ensure that these are correctly installed on your system and with correct `PATH` environment variables.

For help with installing or setting the `PATH`, follow the links below:
* [Java setup](https://www.java.com/en/download/help/path.xml)
* [Maven setup](https://maven.apache.org/install.html)

### Dependencies
The project uses the following libraries:
* [jsoup](https://github.com/jhy/jsoup) - Used to connect to and parse the website
* [Gson](https://github.com/google/gson) - Used to represent the scraped data as a JSON array
* [JUnit](https://github.com/junit-team/junit5) - Used for unit testing

Dependencies are downloaded and managed by Maven so there is no requirement to download them separately.

## Building and Running
Clone the repository using the following command
```bash
git clone https://github.com/burhan-u/grocery-scraper.git
```

### Building
To build the application, execute the following in the root directory
```bash
mvn clean package
```
This will create an executable jar file after compiling and running tests.

### Running
To run the application, execute the following
```bash
java -jar target/grocery-scraper-1.0-SNAPSHOT-jar-with-dependencies.jar
```
Alternatively, you can run the application in your IDE after importing it as a Maven project.

## Testing
To run the unit tests, execute the following
```bash
mvn test
```

## Notes
Although the application in its current state does accomplish its main goal, due to time constraints it has been kept fairly basic and it may contain unforeseen issues. It can be built upon and improved in many ways, the following is a non exhaustive list of improvements that could be made.

* More thorough and complete unit tests
* Exception handling
* Adding mock websites to make tests reliable
* Export the scraped data to a JSON file
* Accept command line arguments for the URL to scrape
* Modularise the code further
