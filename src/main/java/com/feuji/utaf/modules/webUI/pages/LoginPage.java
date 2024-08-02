package com.feuji.utaf.modules.webUI.pages;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    public WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public final By userNameInputField = By.xpath("//div[@class='login-container']/descendant::form/descendant::input[@id='email']");
    public final By userPasswordInputField = By.xpath("//div[@class='login-container']/descendant::form/descendant::input[@id='pass']");
    public final By logInButton = By.xpath("//div[@class='login-container']/descendant::form/descendant::div[@class='primary']/button");


    public void logInToApplication(String userName, String password) {
        homePage = new HomePage(driver);
        homePage.clickOnSignInButton();

        // implementing explicit waits
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameInputField));

        driver.findElement(userNameInputField).sendKeys(userName);
        driver.findElement(userPasswordInputField).isDisplayed();
        driver.findElement(userPasswordInputField).sendKeys(password);
        driver.findElement(logInButton).click();
        logger.info("Login to application successfully");
    }


}
