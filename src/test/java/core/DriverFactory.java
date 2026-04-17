package core;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.ConfigReader;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static WebDriver initDriver() {
        String browser = ConfigReader.get("browser", "edge").toLowerCase();
        WebDriver driver;

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
            default:
                driver = new EdgeDriver();
                break;
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        tlDriver.set(driver);
        return driver;
    }

    public static void quitDriver() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
            }
        } finally {
            tlDriver.remove();
        }
    }
}
