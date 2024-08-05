package com.feuji.utaf.modules.webUI.pages.LoginPage;


import com.feuji.utaf.helpers.ReadPropertyFile;
import com.feuji.utaf.modules.webUI.pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Properties;

public class LoginPageImpl implements LoginPage {
    private static final Logger logger = LogManager.getLogger(LoginPageImpl.class);
    public WebDriver driver;
    private HomePage homePage;

    public LoginPageImpl(WebDriver driver) {
        this.driver = driver;
    }

    private final By userNameInputField = By.xpath("//div[@class='login-container']/descendant::form/descendant::input[@id='email']");
    private final By userPasswordInputField = By.xpath("//div[@class='login-container']/descendant::form/descendant::input[@id='pass']");
    private final By logInButton = By.xpath("//div[@class='login-container']/descendant::form/descendant::div[@class='primary']/button");


    @Override
    public void clickOnSignInButton() {
        homePage = new HomePage(driver);
        homePage.clickOnSignInButton();
    }


    @Override
    public void enterUserName(String userName) {
        Wait <WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInputField));
        driver.findElement(userNameInputField).sendKeys(userName);
    }

    @Override
    public void enterPassword(String userPassword) {
        driver.findElement(userPasswordInputField).isDisplayed();
        driver.findElement(userPasswordInputField).sendKeys(userPassword);
    }

    @Override
    public void clickOnLoginButton() {
        driver.findElement(logInButton).click();
        logger.info("Login to application successfully");
    }

    @Override
    public void signOutFromApplication(){
        homePage = new HomePage(driver);
        homePage.clickOnSignOutButton();
    }

    @Override
    public void validateApplicationUrl(){
        String propertiesFilePath = "properties/config.properties";
        Properties properties = ReadPropertyFile.readProperties(propertiesFilePath);
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = properties.getProperty("appUrl");
        Assert.assertEquals(actualUrl,expectedUrl,"Url doesn't matched expected URL :"+expectedUrl+" actualUrl :"+expectedUrl);
    }
}
