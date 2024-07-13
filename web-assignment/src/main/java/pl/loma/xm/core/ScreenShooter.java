package pl.loma.xm.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Synchronized;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenShooter {

    private static ScreenShooter instance;

    @Synchronized
    public static ScreenShooter getInstance() {
        if (instance == null) {
            instance = new ScreenShooter();
        }
        return instance;
    }

    public byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
