package com.feuji.utaf.base;

import com.feuji.utaf.helpers.ReadPropertyFile;
import com.feuji.utaf.helpers.browserFactory;
import com.feuji.utaf.modules.webUI.pages.HomePage;
import com.feuji.utaf.modules.webUI.pages.LoginPage.LoginPageImpl;
import com.feuji.utaf.modules.webUI.pages.LoginPage.LoginPage;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class UiBaseTest {
    public WebDriver driver;
    private browserFactory browserFactory;
    private Wait<WebDriverWait> wait;
    private static final Logger logger = LogManager.getLogger(UiBaseTest.class);
    private LoginPageImpl loginPageImpl;
    public boolean skipLogout = false;
    private LoginPage loginPage;


    @BeforeMethod
    public void setUp() {
        String propertiesFilePath = "properties/config.properties";
        Properties properties = ReadPropertyFile.readProperties(propertiesFilePath);
        String applicationUrl = properties.getProperty("appUrl");
        String appUserName = properties.getProperty("userEmail");
        String appUserPassword = properties.getProperty("userPassword");

        logger.info("Running Global setup function before test case");

        browserFactory = new browserFactory();
        browserFactory.browserSetUp();
        driver = browserFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        loginPage = new LoginPageImpl(driver);
        HomePage homePage = new HomePage(driver);

        driver.navigate().to(applicationUrl);
        homePage.validateTheAppLogoVisibility();
        logger.info("Navigated to application successfully");

        loginPage.validateApplicationUrl();
        loginPage.clickOnSignInButton();
        loginPage.enterUserName(appUserName);
        loginPage.enterPassword(appUserPassword);
        loginPage.clickOnLoginButton();
        homePage.validateWelcomeTextAfterLogin();

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        logger.info("Running tearDown function after test case");

        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = captureScreenshot(driver, result.getName());
            Allure.addAttachment("page screenshot", FileUtils.openInputStream(screenshot));
        }

        if (!skipLogout) {
            loginPage.signOutFromApplication();
        }

        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
            logger.info("driver set to null successfully");
        }
    }


    private static File captureScreenshot(WebDriver driver, String testName) {
        logger.info("Capturing the screenshot :: take screenshot");
        String screenshotPath = null;
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            screenshotPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Allure_Failure_Screenshot\\" + testName + "_screenshot.png";
            logger.info("screenshot saved at :- {}", screenshotPath);
            FileUtils.copyFile(src, new File(screenshotPath));
        } catch (IOException e) {
            logger.error(e);
        }
        return src;
    }

    public WebDriver getDriver() {
        System.out.println("Getting driver from the UI Base test case");
        return this.driver;
    }

}
