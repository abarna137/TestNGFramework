package com.luma.qa.pages;

import org.openqa.selenium.WebDriver;

public class BagsPage extends BasePage {
    public BagsPage(WebDriver driver) {
        super(driver);
    }

    public String getBagsPageTitle() {
        return util.getPageTitle();
    }
}
