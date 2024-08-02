package com.feuji.utaf.modules.webUI.pages;

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
    private LoginPage loginPage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public final By signInButton = By.xpath("//div[@class='panel header']/child::ul/child::li[@class='authorization-link']/a");
    public final By loginWelcomeText = By.xpath("//div[@class='panel header']/descendant::li[@class='greet welcome']/span[starts-with(text(),'Welcome,')]");
    public final By lumaLogoImage = By.cssSelector(".logo > img");
    public final By mensTab = By.xpath("//nav[@class='navigation']/ul/child::li/a/span[starts-with(text(),'Men')]");
    public final By mensTopTab = By.xpath("//span[text()='Men']/ancestor::li/ul/descendant::span[text()='Tops']");
    public final By mensJacketTab = By.xpath("//span[text()='Men']/ancestor::li/ul/descendant::span[text()='Tops']/ancestor::li/ul/li/a/span[text()='Jackets']");



    public void clickOnSignInButton() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(signInButton).click();
    }

    public void validateWelcomeTextAfterLogin(){
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginWelcomeText));

        String welcomeTextFromUi = driver.findElement(loginWelcomeText).getText();
        System.out.println("welcomeTextFromUi :"+welcomeTextFromUi);
        Assert.assertTrue(welcomeTextFromUi.startsWith("Welcome,"),"The Welcome Text doesn't start with Welcome");
    }

    public void hoverOnMensSectionAndClickOnJacketTab() throws InterruptedException {
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(mensTab)).perform();
        actions.moveToElement(driver.findElement(mensTopTab)).perform();
        actions.moveToElement(driver.findElement(mensJacketTab)).perform();

        driver.findElement(mensJacketTab).click();

        Thread.sleep(10000);
    }

}
