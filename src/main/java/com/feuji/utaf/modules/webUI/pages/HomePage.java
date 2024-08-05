package com.feuji.utaf.modules.webUI.pages;

import com.feuji.utaf.modules.webUI.pages.LoginPage.LoginPageImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    public WebDriver driver;
    private WebDriverWait wait;
    private LoginPageImpl loginPageImpl;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By signInButton = By.xpath("//div[@class='panel header']/child::ul/child::li[@class='authorization-link']/a");
    private final By loginWelcomeText = By.xpath("//div[@class='panel header']/descendant::li[@class='greet welcome']/span[starts-with(text(),'Welcome,')]");
    private final By lumaLogoImage = By.cssSelector(".logo > img");
    private final By mensTab = By.xpath("//nav[@class='navigation']/ul/child::li/a/span[starts-with(text(),'Men')]");
    private final By mensTopTab = By.xpath("//span[text()='Men']/ancestor::li/ul/descendant::span[text()='Tops']");
    private final By mensJacketTab = By.xpath("//span[text()='Men']/ancestor::li/ul/descendant::span[text()='Tops']/ancestor::li/ul/li/a/span[text()='Jackets']");
    private final By logoutDropDownButton = By.xpath("//div[@class='panel header']/descendant::li[@class='customer-welcome']/span/button");
    private final By logoutButton = By.xpath("//div[@aria-hidden='false']/ul/li/a[normalize-space()='Sign Out']");


    public void clickOnSignInButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(signInButton).click();
    }

    public void validateWelcomeTextAfterLogin(){
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginWelcomeText));
        String welcomeTextFromUi = driver.findElement(loginWelcomeText).getText();
        Assert.assertTrue(welcomeTextFromUi.startsWith("Welcome,"),"The Welcome Text doesn't start with Welcome");
    }

    public void hoverOnMensSectionAndClickOnJacketTab() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(mensTab)).perform();
        actions.moveToElement(driver.findElement(mensTopTab)).perform();
        actions.moveToElement(driver.findElement(mensJacketTab)).perform();
        driver.findElement(mensJacketTab).click();
    }

    public void validateTheAppLogoVisibility(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lumaLogoImage));
    }

    public void clickOnSignOutButton(){
        driver.findElement(lumaLogoImage).click();
        driver.findElement(logoutDropDownButton).isDisplayed();
        driver.findElement(logoutDropDownButton).click();
        driver.findElement(logoutButton).isDisplayed();
        driver.findElement(logoutButton).click();
    }

}
