package org.xTrFile.driver;

import com.thoughtworks.gauge.*;
import org.xTrFile.utils.ReadProperties;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.logging.Logger;

public class Driver {

    private static java.util.logging.Logger logger = Logger.getLogger(String.valueOf(Driver.class));
    protected static WebDriver driver = null;
    public static Properties configProp = ReadProperties.readProp("config.properties");
    public static String baseUrl;
    public static String browserName;

    @BeforeSuite
    public void beforeSuite(ExecutionContext executionContext) throws IOException {

        logger.info("*************************************************************************" + "\r\n");
        logger.info("------------------------TEST PLAN-------------------------");

    }

    @BeforeSpec
    public void beforeSpec(ExecutionContext executionContext) {
        logger.info("=========================================================================" + "\r\n");
        logger.info("------------------------SPEC-------------------------");
        logger.info("SPEC FILE NAME: " + executionContext.getCurrentSpecification().getFileName());
        logger.info("SPEC NAME: " + executionContext.getCurrentSpecification().getName());

    }

    @BeforeScenario
    public void beforeScenario(ExecutionContext executionContext) throws MalformedURLException, Exception {

        logger.info("_________________________________________________________________________" + "\r\n");
        logger.info("------------------------SCENARIO-------------------------");
        logger.info("SCENARIO NAME: " + executionContext.getCurrentScenario().getName());
        logger.info("SCENARIO TAG: " + executionContext.getCurrentScenario().getTags().toString());

        baseUrl = configProp.getProperty("baseUrl");
        browserName = configProp.getProperty("browserName");

        driver = LocalExec.LocalExec(browserName);
        driver.get(baseUrl);
    }

    @BeforeStep
    public void beforeStep(ExecutionContext executionContext) {
        logger.info(executionContext.getCurrentStep().getDynamicText());
    }

    @AfterStep
    public void afterStep(ExecutionContext executionContext) throws IOException {

        if (executionContext.getCurrentStep().getIsFailing()) {

            logger.severe(executionContext.getCurrentSpecification().getFileName());
            logger.severe("Message: " + executionContext.getCurrentStep().getErrorMessage() + "\r\n"
                    + executionContext.getCurrentStep().getStackTrace());
        }
    }

    @AfterScenario
    public void afterScenario(ExecutionContext executionContext) {

        quitDriver();
        if (executionContext.getCurrentScenario().getIsFailing()) {

            logger.info("TEST FAILED");
        } else {

            logger.info("TEST PASS");
        }

        logger.info("_________________________________________________________________________" + "\r\n");
    }

    @AfterSpec
    public void afterSpec(ExecutionContext executionContext) {

        logger.info("=========================================================================" + "\r\n");
    }

    @AfterSuite
    public void afterSuite(ExecutionContext executionContext) {

        logger.info("*************************************************************************" + "\r\n");
    }

    private void quitDriver(){

        if(driver != null){
            driver.quit();
            logger.info("Driver close.");
        }
    }

}
