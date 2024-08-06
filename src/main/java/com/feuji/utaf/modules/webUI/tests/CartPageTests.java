package com.feuji.utaf.modules.webUI.tests;


import com.feuji.utaf.base.UiBaseTest;
import com.feuji.utaf.helpers.ReadJsonFile;
import com.feuji.utaf.modules.webUI.pages.CartPage;
import com.feuji.utaf.modules.webUI.pages.HomePage;
import com.feuji.utaf.modules.webUI.pages.ProductPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;


public class CartPageTests extends UiBaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private static final Logger logger = LogManager.getLogger(com.feuji.utaf.helpers.browserFactory.class);
    private final String jsonFilePath = "src/main/resources/data/CartTestData.json";
    private String productSize;
    private String productColour;
    private String productCount;

    @Test(priority = 0)
    public void addingItemIntoCartTest() throws FileNotFoundException {
        driver = getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        JSONObject jsonObject = ReadJsonFile.readJsonFile(jsonFilePath);
        productSize = jsonObject.getJSONObject("addingItemIntoCartTestData").getString("productSize");
        productColour = jsonObject.getJSONObject("addingItemIntoCartTestData").getString("productColour");
        productCount = jsonObject.getJSONObject("addingItemIntoCartTestData").getString("ProductCount");

        homePage.hoverOnMensSectionAndClickOnJacketTab();
        productPage.clickOnProduct();
        String numberOfCartItemsBeforeAddingItemsToCart = cartPage.getNumberOfCartItems();
        productPage.selectSize(productSize);
        productPage.selectProductColour(productColour);
        productPage.setProductCountInCart(productCount);
        productPage.clickOnAddCartButton();
        String numberOfCartItems = cartPage.getNumberOfCartItems();
        Assert.assertEquals(Integer.parseInt(numberOfCartItemsBeforeAddingItemsToCart),Integer.parseInt(numberOfCartItems)-Integer.parseInt(productCount));
    }



    @Test(priority = 1)
    public void completeProcessOfPurchasingAnItem() {
        driver = getDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        homePage.hoverOnMensSectionAndClickOnJacketTab();
        productPage.clickOnProduct();
        String numberOfCartItemsBeforeAddingItemsToCart = cartPage.getNumberOfCartItems();
        productPage.selectSize(productSize);
        productPage.selectProductColour(productColour);
        productPage.setProductCountInCart(productCount);
        productPage.clickOnAddCartButton();
        String numberOfCartItems = cartPage.getNumberOfCartItems();
        Assert.assertEquals(Integer.parseInt(numberOfCartItemsBeforeAddingItemsToCart),Integer.parseInt(numberOfCartItems)-Integer.parseInt(productCount));
        cartPage.clickOnCartButton();
        cartPage.clickOnProceedToCheckoutButton();
        cartPage.checkCartPageUrl("checkout/#shipping");
        cartPage.selectShippingMethod();
        cartPage.clickOnNextButtonInCartPage();
        cartPage.clickOnPlaceOrderButton();
        cartPage.checkThankyouMessageText();
        String orderNumber = cartPage.getOrderNumber();
        logger.info("Order Number is : {}", orderNumber);
    }
}
