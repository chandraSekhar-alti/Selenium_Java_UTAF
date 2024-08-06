package com.feuji.utaf.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;


/**
 * Utility class for Web UI operations.
 * This class provides methods to perform various WebDriver actions such as switching to a new tab,
 * opening specified URLs in new tabs, and retrieving window handles.
 */
public class WebUiUtils {

    // Instance of WebDriver
    private WebDriver driver;

    // Logger instance for logging information
    private final Logger logger = LogManager.getLogger(WebUiUtils.class);

    /**
     * Switches to a new tab and navigates to the specified URL.
     *
     * @param driver The current WebDriver instance.
     * @param url The URL to navigate to in the new tab.
     * @return The WebDriver instance for the new tab.
     */
    public WebDriver switchToNewTabAndOpenSpecifiedUrl(WebDriver driver, String url) {
        // Switch to a new tab
        WebDriver newTabDriver = driver.switchTo().newWindow(WindowType.TAB);

        // Navigate to the specified URL
        newTabDriver.navigate().to(url);

        // Log the successful navigation and the title of the new tab
        logger.info("Successfully switched to new tab and tab title is {}", newTabDriver.getTitle());

        // Return the WebDriver instance for the new tab
        return newTabDriver;
    }

    /**
     * Switches to a new tab without navigating to any URL.
     *
     * @param driver The current WebDriver instance.
     * @return The WebDriver instance for the new tab.
     */
    public WebDriver switchToNewTab(WebDriver driver) {
        // Switch to a new tab and return the WebDriver instance for the new tab
        return driver.switchTo().newWindow(WindowType.TAB);
    }

    /**
     * Retrieves the window handles of all open windows/tabs.
     *
     * @param driver The current WebDriver instance.
     * @return An array of window handles.
     */
    public Object[] getWindowHandles(WebDriver driver) {
        // Get the window handles of all open windows/tabs
        Object[] windowHandles = driver.getWindowHandles().toArray();

        // Log the retrieved window handles
        logger.info("Getting window handles. All the windows present are: {}", windowHandles);

        // Return the array of window handles
        return windowHandles;
    }
}
