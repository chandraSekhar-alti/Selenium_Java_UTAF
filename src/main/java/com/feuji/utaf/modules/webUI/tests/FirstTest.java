package com.feuji.utaf.modules.webUI.tests;


import com.feuji.utaf.base.uiBaseTest;
import com.feuji.utaf.modules.webUI.pages.HomePage;
import org.testng.annotations.Test;
public class FirstTest extends uiBaseTest {


    @Test
    public void sampleTest() throws InterruptedException{

        System.out.println("Hey this is first test case !!!!");
        HomePage homePage = new HomePage(driver);
        homePage.hoverOnMensSectionAndClickOnJacketTab();
//        Assert.fail();
    }
}
