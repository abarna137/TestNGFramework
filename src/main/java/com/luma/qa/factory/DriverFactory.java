package com.luma.qa.factory;

import com.luma.qa.config.ConfigReader;
import com.luma.qa.exception.BrowserException;
import com.luma.qa.logger.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DriverFactory {
    WebDriver driver;
    public ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    OptionsManager optionsManager;
//    static final String CONFIG_PROP_FILE = "./src/test/resources/config/config.properties";
//    static final String UAT_CONFIG_PROP_FILE = "./src/test/resources/config/config.uat.properties";
//    static final String QA_CONFIG_PROP_FILE = "./src/test/resources/config/config.qa.properties";

//    public Properties init_prop() {
//        // envName=qa,stage,prod,uat,dev
//        // mvn clean install -Denv="uat" -DsuiteXmlFiles="//"
//        FileInputStream inputStream;
//        properties = new Properties();
//        String environment = System.getProperty("env");
//        System.out.println("Running in environment: "+environment);
//        Log.info("Executing tests in "+environment);
//        try {
//            if(environment==null) {
//                inputStream = new FileInputStream(CONFIG_PROP_FILE);
//                properties.load(inputStream);
//            } else {
//                switch (environment) {
//                    case "qa":
//                        inputStream = new FileInputStream(QA_CONFIG_PROP_FILE);
//                        properties.load(inputStream);
//                        break;
//                    case "uat":
//                        inputStream = new FileInputStream(UAT_CONFIG_PROP_FILE);
//                        properties.load(inputStream);
//                        break;
//                    default:
//                        Log.error("Please pass valid environment name..."+environment);
//                        throw new EnvironmentException("Invalid environment.."+environment);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            Log.error("Config file Not found..", e);
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            Log.error("Error loading config file...", e);
//            throw new RuntimeException(e);
//        }
//
//        return properties;
//    }

    public WebDriver initDriver(ConfigReader properties) {
        String browserName = properties.getBrowserName();
        Log.info("Browser name is.. "+browserName);
        optionsManager = new OptionsManager(properties);
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;
            case "edge":
                tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;
            default:
                Log.error("Please pass the right browser...." + browserName);
                throw new BrowserException("NO BROWSER FOUND..." + browserName);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.of(20, ChronoUnit.SECONDS));
        getDriver().manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        Log.info("Launching url..");
        getDriver().get(properties.getUrl());
        return getDriver();
    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    public String getScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
        String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
                + ".png";

        File destination = new File(path);

        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            Log.error("Error while capturing screenshot", e);
        }

        return path;
    }
}
