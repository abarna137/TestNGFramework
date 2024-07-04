package com.luma.qa.pages;

import com.luma.qa.util.ElementUtil;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor
public class BasePage {
    protected WebDriver driver;
    protected ElementUtil util;

    protected BasePage(WebDriver driver) {
        this.driver=driver;
        util = new ElementUtil(driver);
    }

    public BasePage(ElementUtil util) {
        this.util = util;
    }
}
