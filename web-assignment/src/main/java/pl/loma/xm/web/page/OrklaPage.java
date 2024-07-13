package pl.loma.xm.web.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebPage;
import pl.loma.xm.web.component.company.TradingConditionsTable;

public final class OrklaPage extends WebPage {

    @FindBy(id = "instrument-inner-page")
    private WebElement tradingConditionsTable;

    public OrklaPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public TradingConditionsTable getTradingConditionsTable() {
        return new TradingConditionsTable(driver, tradingConditionsTable);
    }

}
