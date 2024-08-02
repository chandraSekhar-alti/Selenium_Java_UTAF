package com.feuji.utaf.modules.webUI.pages.LoginPage;

public interface LoginPage {

    void clickOnSignInButton();

    void enterUserName(String userName);

    void enterPassword(String userPassword);

    void clickOnLoginButton();

    void signOutFromApplication();

    void validateApplicationUrl();
}
