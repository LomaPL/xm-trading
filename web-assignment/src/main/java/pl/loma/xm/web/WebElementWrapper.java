package pl.loma.xm.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

record WebElementWrapper(WebElement parent) implements WrapsElement {

    @Override
    public WebElement getWrappedElement() {
        return parent;
    }

}
