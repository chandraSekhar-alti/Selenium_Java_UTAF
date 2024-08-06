package com.feuji.utaf.modules.webUI.tests;

import com.feuji.utaf.base.UiBaseTest;
import com.feuji.utaf.helpers.WebUiUtils;
import com.feuji.utaf.modules.webUI.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class WindowsAndTabsTests extends UiBaseTest {

    WebDriver driver;
    private HomePage homePage;
    private WebUiUtils webUiUtils;

    @Test
    public void openNewTabAndNavigateToUrl()  {
        driver = getDriver();
        homePage = new HomePage(driver);
        webUiUtils = new WebUiUtils();

        homePage.clickOnPracticeApiTestingFooterText();

        /**
         * It will open a new Tab in current windows and navigate to the specified Url
         *  opens new tab by using switchToNewTab method
         *  opens a new website in the new tab
         */
        driver = webUiUtils.switchToNewTab(driver);
        driver.navigate().to("https://www.amazon.com");
        System.out.println("driver.getTitle() : "+driver.getTitle());
    }

    @Test
    public void openMultipleTabsAndPerformActionOnDesiredTab(){
        driver = getDriver();
        webUiUtils = new WebUiUtils();

        /**
         * Opens multiple tabs in same Windows
         *  1. opens base application
         *  2. navigates to multiple applications in multiple new tabs
         *  3. get all the window handles
         *  4. sets driver to desired window
         */

        // Opening multiple new tabs and applications
        driver = webUiUtils.switchToNewTab(driver);
        driver.navigate().to("https://www.amazon.com");

        driver = webUiUtils.switchToNewTab(driver);
        driver.navigate().to("https://www.myntra.com");

        driver = webUiUtils.switchToNewTab(driver);
        driver.navigate().to("https://www.ajio.com");

        //getting driver window handles
        Object[] windows = webUiUtils.getWindowHandles(driver);

        //sets driver to desired window tab
        driver.switchTo().window((String) windows[0]);
        System.out.println("updated driver title is : "+driver.getTitle());
    }
}
