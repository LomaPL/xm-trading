package pl.loma.xm.core;

import io.qameta.allure.Step;
import org.openqa.selenium.Dimension;
import pl.loma.xm.web.page.HomePage;

public abstract class TestCase extends TestCore {

    @Step
    public HomePage openHomePage(Dimension windowSize) {
        var driver = getDriver();
        if (windowSize != null) {
            driver.manage().window().setSize(windowSize);
        } else {
            driver.manage().window().maximize();
        }
        driver.get(BASE_URL);
        return new HomePage(driver);
    }

}
