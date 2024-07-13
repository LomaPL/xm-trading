package pl.loma.xm.web.component.stocks;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.loma.xm.web.WebComponent;
import pl.loma.xm.web.page.OrklaPage;

public final class StocksRow extends WebComponent {

    private final String companyName;

    @Getter
    private final String description;
    @Getter
    private final String symbol;
    @Getter
    private final String minSpread;
    @Getter
    private final String minMaxTradeSize;
    @Getter
    private final String marginRequirement;
    @Getter
    private final String swapLong;
    @Getter
    private final String swapShort;
    @Getter
    private final String limitStopLevel;

    @FindBy(css = "[data-xm-qa-name='symbolWithDescription']")
    private WebElement symbolAndDescriptionElement;

    @FindBy(css = "[data-xm-qa-name='symbol']")
    private WebElement symbolElement;

    @FindBy(css = "[data-xm-qa-name='minSpread']")
    private WebElement minSpreadElement;

    @FindBy(css = "[data-xm-qa-name='minMaxTradeSize']")
    private WebElement minMaxTradeSizeElement;

    @FindBy(css = "[data-xm-qa-name='marginRequirement']")
    private WebElement marginRequirementElement;

    @FindBy(css = "[data-xm-qa-name='swapLong']")
    private WebElement swapLongElement;

    @FindBy(css = "[data-xm-qa-name='swapShort']")
    private WebElement swapShortElement;

    @FindBy(css = "[data-xm-qa-name='limitStopLevel']")
    private WebElement limitStopLevelElement;

    @FindBy(css = "[data-xm-qa-name='url']")
    private WebElement readMoreColumnElement;

    private boolean isExpanded = false;

    StocksRow(WebDriver driver, WebElement parent, String companyName) {
        super(driver, parent);
        this.companyName = companyName;
        description = getSymbolAndDescriptionText();
        symbol = getSymbolText();
        minSpread = getMinSpreadText();
        minMaxTradeSize = getMinMaxTradeSizeText();
        marginRequirement = getMarginRequirementText();
        swapLong = getSwapLongText();
        swapShort = getSwapShortText();
        limitStopLevel = getLimitStopLevelText();
    }

    @Step
    public OrklaPage clickReadMore() {
        if (!readMoreColumnElement.isDisplayed()) {
            expandRow();
        }
        final var readMoreLocator = By.cssSelector(".btn[href*='" + companyName.toLowerCase() + "']");
        var url = driver.findElements(readMoreLocator).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow()
                .getAttribute("href");
        driver.get(url); // Workaround for sometimes failing navigation by clicking the button
        return new OrklaPage(driver);
    }

    private String getSymbolAndDescriptionText() {
        return symbolAndDescriptionElement.getText();
    }

    private String getSymbolText() {
        return symbolElement.getText();
    }

    private String getMinSpreadText() {
        return minSpreadElement.getText();
    }

    private String getMinMaxTradeSizeText() {
        return minMaxTradeSizeElement.getText();
    }

    private String getMarginRequirementText() {
        return marginRequirementElement.getText();
    }

    private String getSwapLongText() {
        return swapLongElement.getText();
    }

    private String getSwapShortText() {
        if (!swapShortElement.isDisplayed()) {
            expandRow();
        }
        return swapShortElement.getAttribute("innerText");
    }

    private String getLimitStopLevelText() {
        if (!limitStopLevelElement.isDisplayed()) {
            expandRow();
        }
        return limitStopLevelElement.getAttribute("innerText");
    }

    private void expandRow() {
        if (!isExpanded) {
            scrollToElement(symbolAndDescriptionElement);
            symbolAndDescriptionElement.click();
            isExpanded = true;
        }
    }

}
