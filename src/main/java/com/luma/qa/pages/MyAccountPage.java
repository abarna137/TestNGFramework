package com.luma.qa.pages;

import com.luma.qa.constants.AppConstants;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getAccountPageTitle() {
        return util.waitForTitleIs(AppConstants.MY_ACCOUNT_PAGE_TITLE, 10);
    }
}
