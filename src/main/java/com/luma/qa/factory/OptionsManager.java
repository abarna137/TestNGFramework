package com.luma.qa.factory;

import com.luma.qa.config.ConfigReader;
import com.luma.qa.logger.Log;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private ConfigReader properties;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;

    public OptionsManager(ConfigReader properties) {
        this.properties = properties;
    }

    public ChromeOptions getChromeOptions() {
        chromeOptions = new ChromeOptions();
        if(Boolean.parseBoolean(properties.getHeadless())) {
            Log.info("Running chrome in headless mode");
            chromeOptions.addArguments("--headless");
        }
        if(Boolean.parseBoolean(properties.getIncognito())) {
            Log.info("Running chrome in incognito mode");
            chromeOptions.addArguments("--incognito");
        }
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        firefoxOptions = new FirefoxOptions();
        if(Boolean.parseBoolean(properties.getHeadless())) {
            Log.info("Running firefox in headless mode");
            firefoxOptions.addArguments("--headless");
        }
        if(Boolean.parseBoolean(properties.getIncognito())) {
            Log.info("Running firefox in incognito mode");
            firefoxOptions.addArguments("--incognito");
        }
        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions() {
        edgeOptions = new EdgeOptions();
        if(Boolean.parseBoolean(properties.getHeadless())) {
            Log.info("Running edge in headless mode");
            edgeOptions.addArguments("--headless");
        }
        if(Boolean.parseBoolean(properties.getIncognito())) {
            Log.info("Running edge in headless mode");
            edgeOptions.addArguments("--inprivate");
        }
        return edgeOptions;
    }
}
