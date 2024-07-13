package pl.loma.xm.core;

import org.openqa.selenium.WebDriver;

public final class WebDriverRegistry {

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    public void set(WebDriver driver) {
        THREAD_LOCAL.set(driver);
    }

    public WebDriver get() {
        return THREAD_LOCAL.get();
    }

}
