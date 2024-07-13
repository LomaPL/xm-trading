package pl.loma.xm.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import pl.loma.xm.core.Constants;

public abstract non-sealed class WebPage extends AbstractWeb {

    public WebPage(WebDriver driver) {
        super(driver);
        initWebElements();
    }

    private void initWebElements() {
        ElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, Constants.FACTORY_TIMEOUT);
        PageFactory.initElements(factory, this);
    }

}
