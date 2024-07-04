package com.luma.qa.pages;

import com.luma.qa.logger.Log;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class HomePage extends BasePage {

    @FindBy(className = "logo")
    WebElement lumaLogo;

    @FindBy(xpath = "//span[text()=\"What's New\"]")
    WebElement newCollectionLink;

    @FindBy(xpath = "//span[text()='Women']")
    WebElement womenCollectionLink;

    @FindBy(xpath = "//span[text()='Gear']")
    WebElement gearPageLink;

    @FindBy(linkText = "Privacy and Cookie Policy")
    WebElement privacyPolicyLink;

    @FindBy(linkText = "Notes")
    WebElement notesLink;

    @FindBy(linkText = "Subscribe")
    WebElement subscribeLink;

    @FindBy(id="search")
    WebElement searchBox;

    @FindBy(xpath = "//span[text()='Bags']")
    WebElement bagsLink;

    @FindBy(xpath = "//div[@id='search_autocomplete']/ul/li[1]")
    WebElement firstSearchResult;

    @FindBy(linkText = "Create an Account")
    WebElement createAccountLink;

    @FindBy(partialLinkText = "Sign In")
    WebElement signInLink;

    By menuHeader = By.xpath("//a[@class='level-top ui-corner-all']//span");

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyLogoDisplayed() {
        return util.waitForElementVisible(lumaLogo, 10).isDisplayed();
    }

    public boolean subscribeLinkExist() {
        return util.waitForElementVisible(subscribeLink, 10).isDisplayed();
    }

    public boolean notesLinkExist() {
        return util.waitForElementVisible(notesLink, 10).isDisplayed();
    }

    public boolean privacyPolicyLinkExist() {
        return util.waitForElementVisible(privacyPolicyLink, 10).isDisplayed();
    }

    public NewCollectionsPage clickNewCollectionLink() {
        util.doClick(newCollectionLink);
        return new NewCollectionsPage(driver);
    }

    public WomenCollectionsPage clickWomenCollectionLink() {
        util.doClick(womenCollectionLink);
        return new WomenCollectionsPage(driver);
    }

    public SearchResultPage doSearch(String itemToSearch) {
        Log.info("Searching for .. "+itemToSearch);
        util.doSendKeys(searchBox, itemToSearch);
        util.waitForElementVisible(firstSearchResult, 5);
        util.doClick(firstSearchResult);
        return new SearchResultPage(driver);
    }

    public BagsPage clickBagsLink() {
        try {
            util.handleMenuSubMenuLevel2(gearPageLink, bagsLink);
        } catch (InterruptedException e) {
            log.error("Menu handling interrupted..", e);
            throw new RuntimeException(e);
        }
        return new BagsPage(driver);
    }

    public List<String> getHomePageMenuList() {
        List<WebElement> menuEleList = util.getElements(menuHeader);
        List<String> menuList = new ArrayList<>();
        for(WebElement e:menuEleList) {
            String menu = e.getText();
            menuList.add(menu);
        }
        return menuList;
    }

    public RegisterPage navigateToRegisterPage() {
        util.doClick(createAccountLink);
        return new RegisterPage(driver);
    }

    public SigninPage navigateToSigninPage() {
        util.doClick(signInLink);
        return new SigninPage(driver);
    }

    public String getHomePageTitle() {
        return util.getPageTitle();
    }
}
