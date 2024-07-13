package pl.loma.xm.web.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebPage;
import pl.loma.xm.web.component.stocks.CountryFilter;
import pl.loma.xm.web.component.stocks.StocksTable;

public final class StocksPage extends WebPage {

    @FindBy(css = ".table-country-filter")
    private WebElement countryFilter;

    @FindBy(css = "[type='search']")
    private WebElement searchInput;

    @FindBy(css = "[id^='DataTables_Table_']")
    private WebElement table;

    public StocksPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public CountryFilter getCountryFilter() {
        return new CountryFilter(driver, countryFilter);
    }

    @Step
    public void enterSearchQuery(String query) {
        searchInput.sendKeys(query);
    }

    @Step
    public StocksTable getStocksTable() {
        return new StocksTable(driver, table);
    }

}
