package org.xTrFile.methods;
import org.xTrFile.helper.ElementHelper;
import org.xTrFile.helper.StoreHelper;
import org.xTrFile.model.ElementInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
public class Method {

    private static Logger logger = Logger.getLogger(String.valueOf(Method.class));
    WebDriver driver;
    FluentWait<WebDriver> wait;

    public Method(WebDriver driver) {

        this.driver = driver;
        wait = setFluentWait(30);
    }

    public FluentWait<WebDriver> setFluentWait(long timeout) {

        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(Exception.class);
        return fluentWait;
    }

    public ElementInfo getElementInfo(String key) {

        return StoreHelper.INSTANCE.findElementInfoByKey(key);
    }

    public By getBy(String key) {

        logger.info("Element " + key + " value is held");
        return ElementHelper.getElementInfoToBy(getElementInfo(key));
    }

    public WebElement findElement(String key) {

        By by = getBy(key);
        logger.info("Element " + by.toString() + " by have value");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findElements(String key) {

        By by = getBy(key);
        logger.info("Element " + by.toString() + " by have value");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void clickElement(String key) {

        findElement(key).click();
        logger.info("Elemente clicked.");
    }

    public void sendKeys(String key, String text) {

        findElement(key).sendKeys(text);
        logger.info("Elemente " + text + " text written.");
    }

    public void waitElementIsVisible(String key) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(key)));
        logger.info("Element visible");
    }


    public boolean isElementVisible(String key) {

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(key)));
            return true;
        } catch (Exception e) {
            logger.info("Element not visible");
            return false;
        }
    }

    public boolean isElementInVisible(String key) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy(key)));
            return true;
        } catch (Exception e) {
            logger.info("Element visible");
            return false;
        }
    }

    public boolean isElementClickable(String key) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(getBy(key)));
            return true;
        } catch (Exception e) {
            logger.info("Element not clickable");
            return false;
        }
    }

    public void clearElement(String key) {
        findElement(key).clear();
    }

    public String getAttribute(String key, String attribute) {

        return findElement(key).getAttribute(attribute);
    }

    public String getText(String key) {

        return findElement(key).getText();
    }

    public void waitByMilliSeconds(long milliSeconds) {

        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitBySeconds(long seconds) {

        logger.info(seconds + " waiting for seconds...");
        waitByMilliSeconds(seconds * 1000);
    }

    public boolean isUrlContains(String url,int count){

        int againCount = 0;
        boolean isContains = false;
        String takenUrl = "";
        logger.info(" expected url : " + url);

        while (!isContains){

            takenUrl = driver.getCurrentUrl();

            if (againCount == count){
                System.err.println(takenUrl + " url i expected " + url + " does not contain the value !!!");
                logger.info(" Real URL : " + takenUrl);
                return false;
            }

            if (takenUrl != null){
                isContains = takenUrl.contains(url);
            }
            againCount++;
        }
        logger.info(" Real url : " + takenUrl);
        return true;
    }

    public void selectRandomlyInList(String key){

        Random rnd = new Random();
        List<WebElement> list = findElements(key);

        for (int i=0;i<list.size();i++){
            list.get(rnd.nextInt(list.size())).click();
            break;
        }
    }

    public boolean waitDisplayed(By by){

        WebDriverWait wait = new WebDriverWait(driver,Duration.of(30, ChronoUnit.SECONDS));
        wait.ignoring(java.util.NoSuchElementException.class, StaleElementReferenceException.class);

        try{
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        }catch (Exception e){
            logger.finer(e.getMessage());
            return false;
        }
        return true;
    }
}
