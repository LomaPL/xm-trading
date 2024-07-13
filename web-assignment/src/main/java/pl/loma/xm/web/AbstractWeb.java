package pl.loma.xm.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.loma.xm.core.Constants;

abstract sealed class AbstractWeb permits WebComponent, WebPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractWeb(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Constants.WAIT_TIMEOUT);
    }

    public void scrollToElement(WebElement element) {
        executeJavaScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});",
                element);
    }

    protected Object executeJavaScript(String script, Object... args) {
        if (driver instanceof JavascriptExecutor js) {
            return js.executeScript(script, args);
        }
        throw new IllegalStateException("JavaScript execution not supported by browser");
    }

}
