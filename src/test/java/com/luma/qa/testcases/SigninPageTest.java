package com.luma.qa.testcases;

import com.luma.qa.testcases.base.TestBase;
import com.luma.qa.constants.AppConstants;
import com.luma.qa.pages.HomePage;
import com.luma.qa.pages.SigninPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SigninPageTest extends TestBase {
    SigninPage signinPage;
    HomePage homePage;

    public SigninPageTest() {
        super();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initialization();
        this.homePage = getHomePage();
        this.signinPage = homePage.navigateToSigninPage();
    }

    @Test(dataProvider = "getUserDataForSignin", groups = "HomePageTest")
    public void signinTest(String emailid, String password) {
        homePage = signinPage.signIn(emailid, password);
        String title = homePage.getHomePageTitle();
        Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void forgotPasswordLinkExist() {
        Assert.assertTrue(signinPage.isForgotPasswordLinkExist());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }
}
