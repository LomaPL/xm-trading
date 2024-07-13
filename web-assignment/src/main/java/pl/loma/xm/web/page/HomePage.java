package pl.loma.xm.web.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebPage;
import pl.loma.xm.web.component.common.CookieModal;
import pl.loma.xm.web.component.common.TopMenu;

public final class HomePage extends WebPage {

    @FindBy(id = "cookieModal")
    private WebElement cookieModal;

    @FindBy(id = "main-nav")
    public WebElement topMenu;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public CookieModal getCookieModal() {
        return new CookieModal(driver, cookieModal);
    }

    @Step
    public TopMenu getTopMenu() {
        return new TopMenu(driver, topMenu);
    }

}


