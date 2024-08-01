package com.feuji.utaf.modules.webUI.tests;


import com.feuji.utaf.base.uiBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
public class FirstTest extends uiBaseTest {


    @Test
    public void sampleTest(){

        System.out.println("Hey this is first test case !!!!");
        Assert.fail();
    }
}
