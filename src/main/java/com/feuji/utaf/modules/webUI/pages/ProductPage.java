package com.feuji.utaf.modules.webUI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    private final By firstProductInProductsPage = By.xpath("(//ol[@class='products list items product-items']/li/div/a)[1]");
    private final By addToCartButton = By.xpath("//div[@class='actions']/button[@type='submit']/span[text()='Add to Cart']");
    private final By quantityTextInput = By.xpath("//input[@id='qty']");
    private final By cartSpinLoader = By.xpath("//img[@title='Loading...']");



    private static By productColourLocator(String colourText){
        return By.xpath("//span[@class='swatch-attribute-label' and text()='Color']/parent::div/child::div/div[@option-label='"+colourText+"']");
    }

    private static By productSizeLocator(String text){
        return By.xpath("//span[text()='Size']/parent::div[@attribute-code='size']/child::div/div[text()='"+text+"']");
    }

    public void clickOnProduct(){
        driver.findElement(firstProductInProductsPage).isDisplayed();
        driver.findElement(firstProductInProductsPage).click();
    }

    public void selectSize(String sizeAlphabet){
        driver.findElement(productSizeLocator(sizeAlphabet)).isDisplayed();
        driver.findElement(productSizeLocator(sizeAlphabet)).click();
    }

    public void selectProductColour(String colourText){
        driver.findElement(productColourLocator(colourText)).isDisplayed();
        driver.findElement(productColourLocator(colourText)).click();
    }

    public void clickOnAddCartButton(){
        driver.findElement(addToCartButton).isDisplayed();
        driver.findElement(addToCartButton).click();
        driver.findElement(cartSpinLoader).isDisplayed();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartSpinLoader));
    }

    public void setProductCountInCart(String productCount){
        driver.findElement(quantityTextInput).isDisplayed();
        driver.findElement(quantityTextInput).clear();
        driver.findElement(quantityTextInput).sendKeys(productCount);
    }

}
