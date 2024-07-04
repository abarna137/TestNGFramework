package com.luma.qa.pages;

import com.luma.qa.constants.AppConstants;
import com.luma.qa.logger.Log;
import org.openqa.selenium.WebDriver;

public class WomenCollectionsPage extends BasePage{

    public WomenCollectionsPage(WebDriver driver) {
        super(driver);
    }

    public String getWomenCollectionPageTitle() {
        String title = util.waitForTitleIs(AppConstants.WOMEN_COLLECTION_PAGE_TITLE, 5);
        Log.info("Page title : " + title);
        return title;
    }
}
