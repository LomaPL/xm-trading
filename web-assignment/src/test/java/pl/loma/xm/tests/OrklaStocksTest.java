package pl.loma.xm.tests;

import io.qameta.allure.Owner;
import lombok.AllArgsConstructor;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pl.loma.xm.core.TestCase;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
@Owner("Kamil Lominski")
public class OrklaStocksTest extends TestCase {

    private final Dimension windowSize;

    @Test
    public void tradingDataShouldMatch() {
        final var company = "Orkla";

        var homePage = openHomePage(windowSize);
        homePage.getCookieModal().acceptDefaultCookies();

        var topMenu = homePage.getTopMenu();
        assertThat(topMenu.isLoaded()).isTrue();

        topMenu.expandTradingMenu();
        assertThat(topMenu.isStocksOptionVisible()).isTrue();

        var stocksPage = topMenu.goToStocks();
        stocksPage.getCountryFilter().selectNorway();
        stocksPage.enterSearchQuery(company);
        var orklaStocksData = stocksPage.getStocksTable().getStocksRowByMT5Symbol(company);

        var orklaPage = orklaStocksData.clickReadMore();
        var tradingConditionsTable = orklaPage.getTradingConditionsTable();
        assertThat(orklaStocksData.getMarginRequirement()).isEqualTo(tradingConditionsTable.getMarginRequirement());
        assertThat(orklaStocksData.getSymbol()).isEqualTo(tradingConditionsTable.getSymbol());
        assertThat(orklaStocksData.getDescription()).contains(tradingConditionsTable.getDescription());
        assertThat(orklaStocksData.getMinSpread()).isEqualTo(tradingConditionsTable.getLowSpread());
        assertThat(orklaStocksData.getSwapLong()).isEqualTo(tradingConditionsTable.getLongSwapValue());
        assertThat(orklaStocksData.getSwapShort()).isEqualTo(tradingConditionsTable.getShortSwapValue());
        assertThat(orklaStocksData.getLimitStopLevel()).isEqualTo(tradingConditionsTable.getLimitAndStopLevels());
        assertThat(orklaStocksData.getMinMaxTradeSize()).isEqualTo(tradingConditionsTable.getMinMaxTradeSize());
    }

    @Factory
    public static Object[] testFactory() {
        return new Object[]{
                new OrklaStocksTest(null),
                new OrklaStocksTest(new Dimension(1024, 768)),
                new OrklaStocksTest(new Dimension(800, 600)),
        };
    }

}
