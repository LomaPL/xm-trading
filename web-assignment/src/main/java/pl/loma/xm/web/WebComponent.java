package pl.loma.xm.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import pl.loma.xm.core.Constants;

public abstract non-sealed class WebComponent extends AbstractWeb {

    private final WebElement parent;
    private final WebElementWrapper webElementWrapper;

    public WebComponent(WebDriver driver, WebElement parent) {
        super(driver);
        this.parent = parent;
        webElementWrapper = new WebElementWrapper(parent);
        initWebElements();
    }

    public WebElement getWrappedElement() {
        return webElementWrapper.getWrappedElement();
    }

    public boolean isLoaded() {
        return getWrappedElement().isDisplayed();
    }

    private void initWebElements() {
        ElementLocatorFactory factory = new AjaxElementLocatorFactory(parent, Constants.FACTORY_TIMEOUT);
        PageFactory.initElements(factory, this);
    }

}
