package com.luma.qa.testcases.base;

import com.luma.qa.config.ConfigReader;
import com.luma.qa.config.IntegrationTestApplication;
import com.luma.qa.constants.AppConstants;
import com.luma.qa.factory.DriverFactory;
import com.luma.qa.listener.WebEventListener;
import com.luma.qa.logger.Log;
import com.luma.qa.pages.HomePage;
import com.luma.qa.util.ExcelUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

@SpringBootTest(classes = IntegrationTestApplication.class)
public class TestBase extends AbstractTestNGSpringContextTests {

    @Autowired
    ConfigReader configReader;
    DriverFactory driverFactory;
    public WebDriver driver;
    SoftAssert softAssert;
    public WebDriverListener eventListener;
    public EventFiringDecorator<WebDriver> eventFiringDecorator;
    ExcelUtil excelUtil;

    public TestBase() {
        driverFactory = new DriverFactory();
    }

//    @BeforeTest
//    @Parameters({"browser", "testname"})
//    public void setProperties(String browser, String testName) {
//        String browserName = configReader.getBrowserName();
//        if(browserName!=null) {
//            properties.setProperty("browser", browserName);
//            properties.setProperty("testname", testName);
//        }
//    }

    public void initialization() {
        driver = driverFactory.initDriver(configReader);
        softAssert = new SoftAssert();
        eventListener = new WebEventListener();
        eventFiringDecorator = new EventFiringDecorator<>(eventListener);
        eventFiringDecorator.decorate(driver);
    }

    public void quitDriver() {
        Log.info("Quitting driver");
        driver.quit();
    }

    @DataProvider
    public Object[][] getUserData() {
        return new Object[][]{
                {"tanay","sharma","tanay.sharma@gmail.com","tanay.123"},
                {"gowri","patel","gowri.patel@gmail.com","patel@343"},
                {"kashish","mehta","kashish.mehta@gmail.com","jublee@344"},
                {"indra","shetty","indra.sh@gmail.com","shush%345"},
                {"nyna","mittal","nynam@gmail.com","nynamina@3456"}
        };
    }

    @DataProvider
    public Object[][] getUserDataFromExcel() {
        return excelUtil.getTestData(AppConstants.USERDATA_SHEET);
    }

    @DataProvider
    public Object[][] getUserDataForSignin() {
        return new Object[][]{
                {"tanay.sharma@gmail.com","tanay.123"},
                {"gowri.patel@gmail.com","patel@343"},
                {"kashish.mehta@gmail.com","jublee@344"},
                {"indra.sh@gmail.com","shush%345"},
                {"nynam@gmail.com","nynamina@3456"}
        };
    }

    public HomePage getHomePage() {
        return new HomePage(driver);
    }

}
