package com.feuji.utaf.modules.webUI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartPage {

    WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    private final By cartButton = By.cssSelector("a[class='action showcart']");
    private final By cartSymbolNumberText = By.xpath("//div[@class='minicart-wrapper']/descendant::span[@class='counter-number']");
    private final By proceedToCheckoutButton = By.xpath("//div[@class='primary']/button[text()='Proceed to Checkout']");
    private final By nextButton = By.xpath("//div[@id='shipping-method-buttons-container']/descendant::button/span");
    private final By placeOrderButton = By.xpath("//div[@class='actions-toolbar']/descendant::button[@title='Place Order']");
    private final By thankyouMessageBox = By.cssSelector("h1[class='page-title'] > span");
    private final By fixedShippingTypeRadioButton = By.xpath("//td[text()='Fixed']/ancestor::tr[@class='row']/td/input");
    private final By loaderImgAfterClickOnNextButton = By.xpath("//body/div[@class='loading-mask']/div/img");
    private final By orderNumber = By.cssSelector("a[class='order-number'] > strong");


    public String getNumberOfCartItems(){
        driver.findElement(cartButton).isDisplayed();
            if ((driver.findElement(cartSymbolNumberText).getText()).equalsIgnoreCase("")){
                return "0";
            }else {
                return driver.findElement(cartSymbolNumberText).getText();
            }
    }

    public void clickOnCartButton(){
        driver.findElement(cartButton).isDisplayed();
        driver.findElement(cartButton).click();
    }

    public void clickOnProceedToCheckoutButton(){
        driver.findElement(proceedToCheckoutButton).isDisplayed();
        driver.findElement(proceedToCheckoutButton).click();
    }

    public void checkCartPageUrl(String cartPageUrlText){
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains(cartPageUrlText));
    }

    public void selectShippingMethod(){
        if(driver.findElement(fixedShippingTypeRadioButton).isDisplayed()){
            driver.findElement(fixedShippingTypeRadioButton).click();
        }
    }

    public void clickOnNextButtonInCartPage(){
        Wait <WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nextButton));
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        driver.findElement(nextButton).click();
        checkTheVisibilityOfSpinLoader();
    }

    public void clickOnPlaceOrderButton(){
        Wait <WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        driver.findElement(placeOrderButton).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        driver.findElement(placeOrderButton).click();
        checkTheVisibilityOfSpinLoader();
    }

    public void checkThankyouMessageText(){
        Wait <WebDriver> wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait1.until(d -> driver.findElement(thankyouMessageBox)).isDisplayed();
        String thankyouText = driver.findElement(thankyouMessageBox).getText();
        Assert.assertEquals(thankyouText,"Thank you for your purchase!");
    }

    public void checkTheVisibilityOfSpinLoader(){
        Wait <WebDriver> wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loaderImgAfterClickOnNextButton));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderImgAfterClickOnNextButton));
    }

    public String getOrderNumber(){
        driver.findElement(orderNumber).isDisplayed();
        return driver.findElement(orderNumber).getText();
    }

}
