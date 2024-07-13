package pl.loma.xm.core;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;

@Slf4j
public final class AllureTestListener implements TestLifecycleListener {

    private final ScreenShooter screenShooter = ScreenShooter.getInstance();

    @Override
    public void beforeTestStop(TestResult result) {
        @NonNull var driver = new WebDriverRegistry().get();
        if (Status.FAILED == result.getStatus() || Status.BROKEN == result.getStatus()) {
            saveScreenshot(driver);
        }
        attachConsoleLogs(driver);
        attachURL(driver);
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        log.info("Taking screenshot");
        return screenShooter.takeScreenshot(driver);
    }

    @Attachment(value = "URL", type = "text/uri-list")
    public String attachURL(WebDriver driver) {
        var currentUrl = driver.getCurrentUrl();
        log.info("URL is: {}", currentUrl);
        return currentUrl;
    }

    public void attachConsoleLogs(WebDriver driver) {
        final var logs = collectConsoleLogs(driver);
        if (!logs.isBlank()) {
            Allure.addAttachment("Console Logs", logs);
        }
    }

    private String collectConsoleLogs(WebDriver driver) {
        log.info("Collecting browser logs");
        final var logs = new StringBuilder();
        final var logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (final var logEntry : logEntries) {
            logs.append(logEntry.getMessage());
            logs.append("\n");
            logs.append("------------------------------------------\n");
        }
        return logs.toString();
    }

}
