package pl.loma.xm.web.component.common;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebComponent;
import pl.loma.xm.web.page.StocksPage;

public final class TopMenu extends WebComponent {

    @FindBy(css = ".main_nav_trading")
    private WebElement tradingMenuLarge;

    @FindBy(linkText = "Trading")
    private WebElement tradingMenuSmall;

    @FindBy(css = "button.toggleLeftNav")
    private WebElement toggleMenu;

    @FindBy(linkText = "Stocks")
    private WebElement stocksMenuOption;

    public TopMenu(WebDriver driver, WebElement parent) {
        super(driver, parent);
    }

    @Step
    public void expandTradingMenu() {
        if (toggleMenu.isDisplayed()) {
            toggleMenu.click();
            tradingMenuSmall.click();
        } else {
            tradingMenuLarge.click();
        }
    }

    @Step
    public StocksPage goToStocks() {
        stocksMenuOption.click();
        return new StocksPage(driver);
    }

    @Step
    public boolean isStocksOptionVisible() {
        return stocksMenuOption.isDisplayed();
    }

}
