package com.luma.qa.pages;

import com.luma.qa.constants.AppConstants;
import com.luma.qa.pojo.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private By firstNameTxtbox = By.id("firstname");
    private By lastNameTxtbox = By.id("lastname");
    private By emailTxtbox = By.id("email_address");
    private By passwordTxtbox = By.id("password");
    private By confirmPwdTxtbox = By.id("password-confirmation");
    private By createAccountBtn = By.xpath("//button/span[text()='Create an Account']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage createUser(String firstName, String lastName, String mailId, String password) {
        util.doSendKeys(firstNameTxtbox, firstName);
        util.doSendKeys(lastNameTxtbox, lastName);
        util.doSendKeys(emailTxtbox, mailId);
        util.doSendKeys(passwordTxtbox, password);
        util.doSendKeys(confirmPwdTxtbox, password);
        util.doClick(createAccountBtn);
        util.waitForTitleIs(AppConstants.MY_ACCOUNT_PAGE_TITLE, 5);
        return new MyAccountPage(driver);
    }

    public MyAccountPage createUser(UserData randomUser) {
        util.doSendKeys(firstNameTxtbox, randomUser.firstName);
        util.doSendKeys(lastNameTxtbox, randomUser.lastName);
        util.doSendKeys(emailTxtbox, randomUser.emailId);
        util.doSendKeys(passwordTxtbox, randomUser.password);
        util.doSendKeys(confirmPwdTxtbox, randomUser.password);
        util.doClick(createAccountBtn);
        util.waitForTitleIs(AppConstants.MY_ACCOUNT_PAGE_TITLE, 5);
        return new MyAccountPage(driver);
    }
}
