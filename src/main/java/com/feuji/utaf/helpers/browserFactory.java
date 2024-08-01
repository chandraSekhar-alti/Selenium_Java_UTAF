package com.feuji.utaf.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class browserFactory {

    public WebDriver driver;
    private final String propertiesFilePath = "properties/config.properties";
    private static final Logger logger = LogManager.getLogger(browserFactory.class);

    public void browserSetUp() {

        Properties properties = ReadPropertyFile.readProperties(propertiesFilePath);
        String browserName = properties.getProperty("browser");
        String runMode = properties.getProperty("runMode");
        int viewPortHeight = Integer.parseInt(properties.getProperty("viewPortHeight"));
        int viewPortWidth = Integer.parseInt(properties.getProperty("viewPortWidth"));

        logger.info("runMode : {}" , runMode);
        Dimension browserSize = new Dimension(viewPortWidth, viewPortHeight);

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (runMode.equalsIgnoreCase("headless")) {
                options.addArguments("--" + runMode + "=new");
                System.out.println("Running in headless mode");
                driver = new ChromeDriver(options);

            } else {
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(browserSize);
            }


        } else if (browserName.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (runMode.equalsIgnoreCase("headless")) {
                options.addArguments("--headless=new");
                driver = new FirefoxDriver(options);
            } else {
                driver = new FirefoxDriver();
                driver.manage().window().setSize(browserSize);
            }

        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();

            EdgeOptions options = new EdgeOptions();
            if (runMode.equalsIgnoreCase("headless")) {
                options.addArguments("--" + runMode + "=new");
                System.out.println("Running in headless mode");
                driver = new EdgeDriver(options);
            } else {
                driver = new EdgeDriver(options);
                driver.manage().window().setSize(browserSize);
            }

        } else {
            System.out.println("invalid browser type");
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
