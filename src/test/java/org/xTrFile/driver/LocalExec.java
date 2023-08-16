package org.xTrFile.driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.Locale;
public class LocalExec {
    private static WebDriver driver;

    public static WebDriver LocalExec(String browser) throws Exception {

        //    SetBrowserForOS.setDriverPath(browser);

        switch (browser.toLowerCase(Locale.ENGLISH)){

            case "chrome" :
                driver = getChromeDriver();
                break;
            case "firefox" :
                driver = getFirefoxDriver();
                driver.manage().window().fullscreen();
                break;
            default:
                throw new Exception("fail");
        }
        return driver;
    }

    private static ChromeDriver getChromeDriver(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.addArguments("ignore-certificate-errors");
        chromeOptions.addArguments("disable-translate");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--start-fullscreen");
        chromeOptions.addArguments("disable-plugins");
        //chromeOptions.addArguments("--disable-local-storage");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return new ChromeDriver(chromeOptions);
    }

    private static FirefoxDriver getFirefoxDriver(){

        FirefoxOptions firefoxOptions = new FirefoxOptions();

        return new FirefoxDriver(firefoxOptions);
    }
}
