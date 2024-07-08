package com.luma.qa.testcases;

import com.luma.qa.testcases.base.TestBase;
import com.luma.qa.constants.AppConstants;
import com.luma.qa.listener.Retry;
import com.luma.qa.pages.HomePage;
import com.luma.qa.pages.MyAccountPage;
import com.luma.qa.pages.RegisterPage;
import com.luma.qa.util.RandomDataUtil;
import org.testng.Assert;
import org.testng.annotations.*;


public class RegisterPageTest extends TestBase {
    RegisterPage registerPage;
    HomePage homePage;
    RandomDataUtil randomDataUtil;

    public RegisterPageTest() {
        super();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initialization();
        navigateToRegisterPage();
    }

    public void navigateToRegisterPage() {
        this.homePage = getHomePage();
        this.registerPage = homePage.navigateToRegisterPage();
    }

    @Test(dataProvider = "getUserData", retryAnalyzer = Retry.class)
    public void createUserFromDataProvider(String firstName, String lastName, String emailId, String password) {
        MyAccountPage accountPage = registerPage.createUser(firstName, lastName, emailId, password);
        String title = accountPage.getAccountPageTitle();
        Assert.assertEquals(title, AppConstants.MY_ACCOUNT_PAGE_TITLE);
    }

    @Test(groups = "RetryTest", retryAnalyzer = Retry.class)
    public void createUserUsingRandomData() {
        randomDataUtil = new RandomDataUtil();
        MyAccountPage accountPage = registerPage.createUser(randomDataUtil.createRandomUser());
        String title = accountPage.getAccountPageTitle();
        Assert.assertEquals(title, AppConstants.MY_ACCOUNT_PAGE_TITLE);
    }
    
    @Test(dataProvider = "getUserDataFromExcel", enabled = false)
    public void createUserFromExcel(String firstName, String lastName, String emailId, String password) {
        MyAccountPage accountPage = registerPage.createUser(firstName, lastName, emailId, password);
        String title = accountPage.getAccountPageTitle();
        Assert.assertEquals(title, AppConstants.MY_ACCOUNT_PAGE_TITLE);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }
}
