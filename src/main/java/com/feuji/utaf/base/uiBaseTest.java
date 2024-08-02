package com.feuji.utaf.base;

import com.feuji.utaf.helpers.ReadPropertyFile;
import com.feuji.utaf.helpers.browserFactory;
import com.feuji.utaf.modules.webUI.pages.HomePage;
import com.feuji.utaf.modules.webUI.pages.LoginPage;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class uiBaseTest {
    public static WebDriver driver;
    private browserFactory browserFactory;
    private Properties properties;
    private final String propertiesFilePath = "properties/config.properties";
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(uiBaseTest.class);
    private LoginPage loginPage;
    private HomePage homePage;


    private String applicationUrl;
    private String appUserName;
    private String appUserPassword;

    @BeforeSuite
    public void envVariablesSetupBeforeTestExecution(){
        properties = ReadPropertyFile.readProperties(propertiesFilePath);
        applicationUrl = properties.getProperty("appUrl");
        appUserName = properties.getProperty("userEmail");
        appUserPassword = properties.getProperty("userPassword");
    }

    @BeforeMethod
    public void setUp(){
        logger.info("Running Global setup function before test case");

        browserFactory = new browserFactory();
        browserFactory.browserSetUp();
        driver = browserFactory.getDriver();

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.navigate().to(applicationUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.lumaLogoImage));
        logger.info("Navigated to application successfully");

        loginPage.logInToApplication(appUserName,appUserPassword);
        homePage.validateWelcomeTextAfterLogin();

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        logger.info("Running tearDown function after test case");

        if (result.getStatus() == ITestResult.FAILURE){
            File screenshot =captureScreenshot(driver,result.getName());
            Allure.addAttachment("page screenshot",FileUtils.openInputStream(screenshot));
        }

        if (driver!= null){
            driver.quit();
            logger.info("driver set to null successfully");
        }
    }


    private static File captureScreenshot(WebDriver driver, String testName){
        logger.info("Capturing the screenshot :: take screenshot");
        String screenshotPath = null;
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try{
            screenshotPath = System.getProperty("user.dir") +"\\src\\test\\resources\\Allure_Failure_Screenshot\\" + testName +"_screenshot.png";
            logger.info("screenshot saved at :- {}", screenshotPath);
            FileUtils.copyFile(src, new File(screenshotPath));
        } catch (IOException e) {
            logger.error(e);
        }
        return src;
    }

}
