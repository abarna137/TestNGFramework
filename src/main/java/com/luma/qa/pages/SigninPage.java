package com.luma.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SigninPage extends BasePage{

    private By emailTxtbox = By.id("email");
    private By passwordTxtbox = By.id("pass");
    private By signinBtn = By.xpath("//button[@class='action login primary']/span[text()='Sign In']");
    private By forgotPwdBtn = By.xpath("//a[@class='action remind']");

    public SigninPage(WebDriver driver) {
        super(driver);
    }

    public HomePage signIn(String email, String password) {
        util.doSendKeys(emailTxtbox, email);
        util.doSendKeys(passwordTxtbox, password);
        util.doClick(signinBtn);
        return new HomePage(driver);
    }

    public boolean isForgotPasswordLinkExist() {
        return util.waitForElementVisible(forgotPwdBtn, 5).isDisplayed();
    }
}
