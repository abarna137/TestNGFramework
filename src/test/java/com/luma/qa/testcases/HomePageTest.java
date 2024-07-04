package com.luma.qa.testcases;

import com.luma.qa.testcases.base.TestBase;
import com.luma.qa.constants.AppConstants;
import com.luma.qa.logger.Log;
import com.luma.qa.pages.BagsPage;
import com.luma.qa.pages.HomePage;
import com.luma.qa.pages.SearchResultPage;
import com.luma.qa.pages.WomenCollectionsPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageTest extends TestBase {
    public HomePage homePage;
    public HomePageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = getHomePage();
    }

    @Test(priority = 1, groups = "HomePageTest")
    public void logoDisplayTest() {
        Log.info("Verifying logo display");
        Assert.assertTrue(homePage.verifyLogoDisplayed());
    }

    @Test(priority=2, groups = "HomePageTest")
    public void subscribeLinkDisplayTest() {
        Log.info("Verifying subscribe link display");
        Assert.assertTrue(homePage.subscribeLinkExist());
    }

    @Test(priority=3, groups = "HomePageTest")
    public void notesLinkDisplayTest() {
        Log.info("Verifying notes link display");
        Assert.assertTrue(homePage.notesLinkExist());
    }

    @Test(priority=4, groups = "HomePageTest")
    public void policyLinkDisplayTest() {
        Log.info("Verifying privacy policy link display");
        Assert.assertTrue(homePage.privacyPolicyLinkExist());
    }

    @Test(priority=1, groups = "HomePageTest")
    public void navigateToWomenCollectionPageTest() {
        Log.info("Verifying navigation to Women's collection page");
        WomenCollectionsPage page = homePage.clickWomenCollectionLink();
        String title = page.getWomenCollectionPageTitle();
        Assert.assertEquals(title, AppConstants.WOMEN_COLLECTION_PAGE_TITLE);
    }

    @Test
    public void homePageMenuTest() {
        List<String> menuList = homePage.getHomePageMenuList();
        List<String> expectedMenuList = new ArrayList<>(Arrays.asList("What's New", "Women", "Men", "Gear", "Training", "Sale"));
        Assert.assertTrue(menuList.containsAll(expectedMenuList));
    }

    @Test(groups = "HomePageTest")
    public void searchTest() {
        SearchResultPage resultPage = homePage.doSearch("yoga");
        String title = resultPage.getSearchResultPageTitle("yoga");
        Assert.assertTrue(title.contains(AppConstants.SEARCH_RESULT_PAGE_TITLE));
    }

    @Test(groups = "HomePageTest")
    public void navigateToBagsPageTest() {
        BagsPage bagsPage = homePage.clickBagsLink();
        String title = bagsPage.getBagsPageTitle();
        Assert.assertEquals(title, AppConstants.BAGS_PAGE_TITLE);
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}
