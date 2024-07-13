package pl.loma.xm.web.component.common;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebComponent;

public final class CookieModal extends WebComponent {

    @FindBy(css = "button.js-acceptDefaultCookie")
    private WebElement acceptDefaultCookie;

    public CookieModal(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @Step
    public void acceptDefaultCookies() {
        acceptDefaultCookie.click();
    }

}
