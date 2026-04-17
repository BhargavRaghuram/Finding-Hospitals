package stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class Hooks {

    public static ExtentTest test;

    @Before
    public void before(Scenario scenario) {
        WebDriver driver = DriverFactory.initDriver();
        test = ExtentManager.getExtent().createTest(scenario.getName());
        test.log(Status.INFO, "Scenario started");
    }

    @After
    public void after(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        String shot = null;
        if (driver != null) {
            shot = ScreenshotUtil.capture(driver, scenario.getName());
        }

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed");
        } else {
            test.log(Status.PASS, "Scenario passed");
        }

        if (shot != null) {
            try { test.addScreenCaptureFromPath(shot); } catch (Exception ignored) {}
        }

        ExtentManager.getExtent().flush();
        DriverFactory.quitDriver();
    }
}
