package pl.loma.xm.web.component.company;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebComponent;

import java.util.List;

public final class TradingConditionsTable extends WebComponent {

    @FindBy(css = "[data-xm-qa-name='margin_requirement__value']")
    private WebElement marginRequirement;

    @FindBy(css = "[data-xm-qa-name='symbols__value']")
    private WebElement symbol;

    @FindBy(css = "[data-xm-qa-name='description__value']")
    private WebElement description;

    @FindBy(css = "[data-xm-qa-name='spreads_as_low_as__value']")
    private List<WebElement> lowSpread;

    @FindBy(css = "[data-xm-qa-name='min_max_trade_size__value']")
    private WebElement minMaxTradeSize;

    @FindBy(css = "[data-xm-qa-name='swap_value_in_margin_currency_long__value']")
    private WebElement longSwapValue;

    @FindBy(css = "[data-xm-qa-name='swap_value_in_margin_currency_short__value']")
    private WebElement shortSwapValue;

    @FindBy(css = "[data-xm-qa-name='limit_and_stop_levels__title'].ltr") // Duplicate data-xm-qa-name in html
    private WebElement limitAndStopLevels;

    public TradingConditionsTable(WebDriver driver, WebElement parent) {
        super(driver, parent);
        scrollToElement(parent);
    }

    @Step
    public String getMarginRequirement() {
        return marginRequirement.getText();
    }

    @Step
    public String getSymbol() {
        return symbol.getText();
    }

    @Step
    public String getDescription() {
        return description.getText();
    }

    @Step
    public String getLowSpread() {
        return lowSpread.stream().filter(WebElement::isDisplayed).findFirst().orElseThrow().getText();
    }

    @Step
    public String getMinMaxTradeSize() {
        return minMaxTradeSize.getText();
    }

    @Step
    public String getLongSwapValue() {
        return longSwapValue.getText();
    }

    @Step
    public String getShortSwapValue() {
        return shortSwapValue.getText();
    }

    @Step
    public String getLimitAndStopLevels() {
        return limitAndStopLevels.getText();
    }

}
