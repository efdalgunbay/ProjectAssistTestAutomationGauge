package org.xTrFile.step;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.xTrFile.driver.Driver;
import org.xTrFile.methods.Method;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;
public class StepDefination extends Driver {

    Logger logger = Logger.getLogger(String.valueOf(StepDefination.class));
    Method methods;

    public StepDefination() {
        methods = new Method(driver);
    }


    @Step("<key> click on element")
    public void TenanModuleNavclickElement(String key) {
        methods.clickElement(key);
    }

    @Step("Write the text <text> to the <key> element")
    public void sendKeysElement(String key, String text) {
        methods.sendKeys(key, text);
    }

    @Step("<key> Checking whether the element is visible")
    public void controlIsElementVisible(String key) {
        Assert.assertTrue("Element is not visible", methods.isElementVisible(key));
    }

    @Step("<key> Checking if the element is not visible")
    public void controlIsElementInVisible(String key) {
        Assert.assertTrue("Element visible", methods.isElementInVisible(key));
    }

    @Step("<by> element is clickable")
    public void controlIsElementClickable(String key) {
        Assert.assertTrue("Element is not clickable", methods.isElementClickable(key));
    }

    @Step("<key> clear the text field of the element")
    public void clearToElement(String key) {
        methods.clearElement(key);
    }

    @Step("<key> text value of the element <expectedText> Is it equal to the value")
    public void checkElementTextEquality(String key, String expectedText) {

        methods.waitElementIsVisible(key);

        String actualText;
        if (methods.getAttribute(key, "value") != null) {
            actualText = methods.getAttribute(key, "value").trim().replace("\r", "").replace("\n", "");
        } else {
            actualText = methods.getText(key).trim().replace("\r", "").replace("\n", "");
        }

        logger.info("expected text: " + expectedText);
        logger.info("received text: " + actualText);
        Assert.assertEquals("Text values are not equal", expectedText.replace(" ", ""), actualText.replace(" ", ""));
        logger.info("Text values are equal");
    }

    @Step({"<key> text value of the element <expectedText> it different from the value"})
    public void checkElementNotEqual(String key, String expectedText) {

        methods.waitElementIsVisible(key);
        String actualText = methods.getText(key).trim().replace("\r", "").replace("\n", "");

        logger.info("expected text: " + expectedText);
        logger.info("received text: " + actualText);
        Assert.assertNotEquals("Text values are equal", expectedText.replace(" ", ""), actualText.replace(" ", ""));
        logger.info("Text values are not equal");
    }

    @Step("<key> text value of the element <expectedText> does it contain the value")
    public void getElementTextContains(String key, String expectedText) {
        methods.waitElementIsVisible(key);

        String actualText;
        if (methods.getAttribute(key, "value") != null) {
            actualText = methods.getAttribute(key, "value").trim().replace("\r", "").replace("\n", "");
        } else {
            actualText = methods.getText(key).trim().replace("\r", "").replace("\n", "");
        }

        logger.info("expected text: " + expectedText);
        logger.info("received text: " + actualText);
        Assert.assertTrue("Does not contain expected value", actualText.replace(" ", "").contains(expectedText.replace(" ", "")));
        logger.info("Contains the expected value.");
    }

    @Step("<seconds> wait a second")
    public void waitBySeconds(long seconds) {
        methods.waitBySeconds(seconds);
    }

    @Step("current url <expectedUrl> does it contain the value")
    public void isUrlContains(String url) {
        Assert.assertTrue("Current url does not contain expected url", methods.isUrlContains(url, 10));
    }

    @Step("<key> on the list <index> click on the next element")
    public void clickByIndexOnList(String key, int index) {
        methods.findElements(key).get(index - 1).click();
    }

    @Step("<key> make a random selection within the list")
    public void selectRandomlyInList(String key) {
        methods.selectRandomlyInList(key);
    }

    @Step("<key> Click the element if it exists")
    public void clickElemenIfExist(String key) {
        if (methods.waitDisplayed(methods.getBy(key))) {
            clickElement(key);
        } else {
            logger.info(" ... Searched Element Not Visible ... ");
        }
    }

    private void clickElement(String key) {
    }

    @Step("<key> if the element and <key2> text value <text> if it contains the value <key3> choose from")
    public void selectPackageIfExist(String key, String key2, String text, String key3) {

        if (methods.waitDisplayed(methods.getBy(key))) {

            List<WebElement> packageList = methods.findElements(key2);
            List<WebElement> flights = methods.findElements(key3);

            for (int i = 0; i < packageList.size(); i++) {
                if (packageList.get(i).getText().contains(text.toUpperCase())) {
                    flights.get(i).click();
                    break;
                }
            }

        } else {
            logger.info(" ... no required personnel ... ");
        }
    }

    @Step("new tab")
    public void newtab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://login.microsoftonline.com/e475bd9d-951d-42f8-ba0c-1c929af85c12/oauth2/v2.0/authorize?response_type=id_token&scope=user.read%20user.read.all%20openid%20profile&client_id=61a6e444-2d6d-4efc-9a74-5fbe877f4b21&redirect_uri=https%3A%2F%2Fxtrflitetest.z20.web.core.windows.net%2Flogin&state=eyJpZCI6IjcyNGQ5ZDRmLTQxODMtNDI3Ni05NWM4LWE3ZGQ3MDQ2ZDMxNiIsInRzIjoxNjg3MDk1NjUwLCJtZXRob2QiOiJwb3B1cEludGVyYWN0aW9uIn0%3D&nonce=a89cfa60-4b2f-4158-a22e-b8870049e7b8&client_info=1&x-client-SKU=MSAL.JS&x-client-Ver=1.4.18&client-request-id=e4ce4388-4de3-4b79-8e19-b79212524c5e&response_mode=fragment");


    }

    @Step("new tab2")
    public void newtab2() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get("https://xtrflitetest.z20.web.core.windows.net/login");

    }

    @Step("Write the text <text> go to")
    public void newtab3(String text) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.get(text);

    }
}