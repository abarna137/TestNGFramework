package com.luma.qa.pages;

import com.luma.qa.constants.AppConstants;
import com.luma.qa.logger.Log;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage{

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchResultPageTitle(String searchText) {
        String title = util.waitForTitleContains(AppConstants.SEARCH_RESULT_PAGE_TITLE, 5);
        Log.info("Page title : " + title);
        System.out.println("Page title: "+title);
        if(title.contains(searchText)) {
            return title;
        } else {
            return "Assertion failure";
        }
    }
}
