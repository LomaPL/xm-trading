package pl.loma.xm.web.component.stocks;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebComponent;

public final class CountryFilter extends WebComponent {

    @FindBy(css = "[data-value='Norway']")
    private WebElement norway;

    public CountryFilter(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @Step
    public void selectNorway() {
        scrollToElement(norway);
        norway.click();
    }

}
