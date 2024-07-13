package pl.loma.xm.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Slf4j
@Listeners(TestListener.class)
abstract class TestCore {

    protected static final String BASE_URL = "https://www.xm.com/";
    private final WebDriverRegistry webDriverRegistry = new WebDriverRegistry();
    private final ChromeOptions chromeOptions = new ChromeOptions();
    @Getter(AccessLevel.PACKAGE) private WebDriver driver;

    @BeforeSuite
    public void initTestSuite() {
        setOptions();
        log.info("Running tests against: {}", BASE_URL);
    }

    @BeforeClass
    public void initWebDriver() {
        if (driver == null) {
            log.info("Starting WebDriver");
            driver = new ChromeDriver(chromeOptions);
            webDriverRegistry.set(driver);
            setTimeouts(driver);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            log.info("Closing WebDriver");
            driver.quit();
            driver = null;
            webDriverRegistry.set(null);
        }
    }

    private void setTimeouts(WebDriver driver) {
        var timeouts = driver.manage().timeouts();
        timeouts.pageLoadTimeout(Constants.WAIT_TIMEOUT);
        timeouts.implicitlyWait(Constants.WAIT_TIMEOUT);
        timeouts.scriptTimeout(Constants.WAIT_TIMEOUT);
    }

    private void setOptions() {
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--dns-prefetch-disable");
    }

}
