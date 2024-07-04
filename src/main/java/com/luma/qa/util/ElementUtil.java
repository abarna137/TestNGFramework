package com.luma.qa.util;

import com.luma.qa.exception.ElementException;
import com.luma.qa.logger.Log;
import com.luma.qa.pages.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ElementUtil extends BasePage {
    TimeUtil timeUtil;
    private static WebDriver driver;
    private final String DEFAULT_ELEMENT_TIME_OUT_MESSAGE = "Time out... Element is not found...";
    private final String DEFAULT_ALERT_TIME_OUT_MESSAGE = "Time out... Alert is not found...";

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
    }

    public static void takeScreenshotAtEndOfTest() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        try {
            FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            Log.error("Error capturing screenshot", e);
            throw new RuntimeException(e);
        }
    }

    private void nullBlankCheck(String value) {
        if (value == null || value.isEmpty()) {
            throw new ElementException(value + " --- value text can not be null or blank");
        }
    }

    public By getBy(String locatorType, String locatorValue) {
        By locator = null;

        switch (locatorType.toLowerCase().trim()) {
            case "id":
                locator = By.id(locatorValue);
                break;
            case "name":
                locator = By.name(locatorValue);
                break;
            case "classname":
                locator = By.className(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
                break;
            case "css":
                locator = By.cssSelector(locatorValue);
                break;
            case "linktext":
                locator = By.linkText(locatorValue);
                break;
            case "partiallinktext":
                locator = By.partialLinkText(locatorValue);
                break;
            case "tagname":
                locator = By.tagName(locatorValue);
                break;

            default:
                break;
        }

        return locator;

    }

    public WebElement getElement(String locatorType, String locatorValue) {
        return driver.findElement(getBy(locatorType, locatorValue));

    }

    public WebElement getElement(By locator) {
        WebElement element = null;

        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            throw new ElementException("Element is not present on the page");
        }

        return element;
    }

    public void doSendKeys(By locator, String value) {
        nullBlankCheck(value);
        getElement(locator).clear();
        getElement(locator).sendKeys(value);
    }

    public void doSendKeys(WebElement ele, String value) {
        nullBlankCheck(value);
        ele.clear();
        ele.sendKeys(value);
    }

    public void doSendKeys(By locator, String value, int timeOut) {
        nullBlankCheck(value);
        waitForElementVisible(locator, timeOut).sendKeys(value);
    }

    public void doSendKeys(String locatorType, String locatorValue, String value) {
        getElement(locatorType, locatorValue).sendKeys(value);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public void doClick(WebElement element) {
        element.click();
    }

    public void doClick(By locator, int timeOut) {
        waitForElementVisible(locator, timeOut).click();
    }

    public String doGetElementText(By locator) {
        return getElement(locator).getText();
    }

    public String doElementGetAttribute(By locator, String attrName) {
        return getElement(locator).getAttribute(attrName);
    }

    public boolean isElementDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

    public boolean isElementExist(By locator) {
        return getElements(locator).size() == 1;
    }

    public boolean multipleElementsExist(By locator) {
        return !getElements(locator).isEmpty();
    }

    public boolean multipleElementsExist(By locator, int elementCount) {
        return getElements(locator).size() == elementCount;
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public int getElementsCount(By locator) {
        return getElements(locator).size();
    }

    public ArrayList<String> getElementsTextList(By locator) {
        List<WebElement> eleList = getElements(locator);
        ArrayList<String> eleTextList = new ArrayList<String>();

        for (WebElement e : eleList) {
            String text = e.getText();
            if (!text.isEmpty()) {
                eleTextList.add(text);
            }
        }

        return eleTextList;
    }

    public ArrayList<String> getElementAttributeList(By locator, String attrName) {
        List<WebElement> eleList = getElements(locator);
        ArrayList<String> eleAttrList = new ArrayList<String>();

        for (WebElement e : eleList) {
            String attrValue = e.getAttribute(attrName);
            if (!attrValue.isEmpty()) {
                eleAttrList.add(attrValue);
            }
        }

        return eleAttrList;

    }

    public void doSelectByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void doSelectByVisibleText(By locator, String visibleText) {
        nullBlankCheck(visibleText);
        Select select = new Select(getElement(locator));

        try {
            select.selectByVisibleText(visibleText);
        } catch (NoSuchElementException e) {
            Log.error("Visble text is not present in the drop down");
            throw new ElementException("text not present");
        }

    }

    public void doSelectByValue(By locator, String value) {
        nullBlankCheck(value);
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public List<String> getDropDownOptionsTextList(By locator) {
        List<WebElement> optionsList = getDropDownOptionsList(locator);
        List<String> optionsTextList = new ArrayList<String>();

        for (WebElement e : optionsList) {
            String optionText = e.getText();
            optionsTextList.add(optionText);
        }
        return optionsTextList;
    }

    public List<WebElement> getDropDownOptionsList(By locator) {
        Select select = new Select(getElement(locator));
        return select.getOptions();
    }

    public int getDropDownValuesCount(By locator) {
        return getDropDownOptionsList(locator).size();
    }

    public void doSelectDropDownValue(By locator, String value) {
        nullBlankCheck(value);
        List<WebElement> optionsList = getDropDownOptionsList(locator);
        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

    public void printSelectDropDownValue(By locator) {
        List<WebElement> optionsList = getDropDownOptionsList(locator);
        for (WebElement e : optionsList) {
            String text = e.getText();
            System.out.println(text);
        }
    }

    public void handleMenuSubMenuLevel2(WebElement parentMenuEle, WebElement SubMenuEle) throws InterruptedException {
        Actions act = new Actions(driver);
        act.moveToElement(parentMenuEle).perform();
        Thread.sleep(1500);
        doClick(SubMenuEle);

    }

    public void doActionsClick(By locator) {
        Actions act = new Actions(driver);
        act.click(getElement(locator)).perform();
    }

    public void doActionsSendKeys(By locator, String value) {
        Actions act = new Actions(driver);
        act.sendKeys(getElement(locator), value).perform();
    }

    public void clickWhenReady(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }


    public WebElement waitForElementPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }

    public WebElement waitForElementVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementVisible(WebElement ele, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitForElementsPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
                .withMessage(DEFAULT_ELEMENT_TIME_OUT_MESSAGE);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public String waitForTitleContains(String titleFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
                return driver.getTitle();
            }
        } catch (Exception e) {
            System.out.println("title is not found within : " + timeOut);
        }
        return null;

    }

    public String waitForTitleIs(String title, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.titleIs(title))) {
                return driver.getTitle();
            }
        } catch (Exception e) {
            System.out.println("title is not found within : " + timeOut);
        }
        return driver.getTitle();

    }

    public String waitForURLContains(String urlFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
                return driver.getCurrentUrl();
            }
        } catch (Exception e) {
            System.out.println("url fraction is not found within : " + timeOut);
        }
        return driver.getCurrentUrl();

    }

    public String waitForURLIs(String url, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.urlToBe(url))) {
                return driver.getCurrentUrl();
            }
        } catch (Exception e) {
            System.out.println("url is not found within : " + timeOut);
        }
        return driver.getCurrentUrl();

    }

    public Alert waitForJSAlert(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText(int timeOut) {
        return waitForJSAlert(timeOut).getText();
    }

    public void acceptAlert(int timeOut) {
        waitForJSAlert(timeOut).accept();
    }

    public void dismissAlert(int timeOut) {
        waitForJSAlert(timeOut).dismiss();
    }

    public void alertSendKeys(int timeOut, String value) {
        waitForJSAlert(timeOut).sendKeys(value);
    }

    public boolean waitForWindow(int totalNumberOfWindowsToBe, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.numberOfWindowsToBe(totalNumberOfWindowsToBe));
    }

    public void waitForFrameAndSwitchToIt(By frameLocator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public void waitForFrameAndSwitchToIt(int frameIndex, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }

    public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    public WebElement retryingElement(By locator, int timeOut) {

        WebElement element = null;
        int attempts = 0;

        while (attempts < timeOut) {

            try {
                element = getElement(locator);
                System.out.println("element is found...." + locator + " in attempt " + attempts);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("element is not found.... " + " in attempts " + attempts);
                timeUtil.defaultTime();
            }
            attempts++;
        }

        if (element == null) {
            System.out.println("element is not found.....tried for " + timeOut + " times " + " with the interval of "
                    + 500 + " milliseconds...");
            throw new ElementException("No Such Element");
        }

        return element;

    }

    public WebElement retryingElement(By locator, int timeOut, int intervalTime) {

        WebElement element = null;
        int attempts = 0;

        while (attempts < timeOut) {

            try {
                element = getElement(locator);
                System.out.println("element is found...." + locator + " in attempt " + attempts);
                break;
            } catch (NoSuchElementException e) {
                System.out.println("element is not found.... " + " in attempts " + attempts);
                timeUtil.applyWait(intervalTime);
            }
            attempts++;

        }

        if (element == null) {
            System.out.println("element is not found.....tried for " + timeOut + " times " + " with the interval of "
                    + intervalTime + " seconds...");
            throw new ElementException("No Such Element");
        }
        return element;
    }

    public void pressEnterKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void pressDownArrowKey() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN);
    }
}
