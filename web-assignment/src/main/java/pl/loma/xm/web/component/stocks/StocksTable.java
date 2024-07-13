package pl.loma.xm.web.component.stocks;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebComponent;

import java.util.List;

public final class StocksTable extends WebComponent {

    @FindBy(css = "tbody tr")
    private List<WebElement> rows;

    public StocksTable(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @Step
    public StocksRow getStocksRowByMT5Symbol(String symbol) {
        return rows.stream()
                .map(row -> new StocksRow(driver, row, symbol))
                .filter(row -> row.getSymbol().equalsIgnoreCase(symbol))
                .findFirst()
                .orElseThrow();
    }

}
