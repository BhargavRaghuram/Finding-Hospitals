package pageobjects;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.BasePage;

public class LabTestsPage extends BasePage {

    private final By selectCityBtn =
            By.xpath("//*[text()='Select a city' or contains(text(),'Select city')]");

    private final By topCitiesHeader =
            By.xpath("//*[text()='TOP CITIES']");

    private final By topCityNames =
            By.xpath("//*[text()='TOP CITIES']/following-sibling::*//*[self::div or self::span or self::p]");

    public LabTestsPage(WebDriver driver) {
        super(driver);
    }

    public void openLabTests() {
        driver.get("https://www.practo.com/tests");
        waitVisible(By.tagName("body"));
    }

    public List<String> captureTopCities() {

        WebElement selectCity = waitVisible(selectCityBtn);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ✅ KEY FIX: avoid navbar interception
        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            selectCity
        );
        js.executeScript(
            "arguments[0].click();",
            selectCity
        );

        waitVisible(topCitiesHeader);

        Set<String> cities = new LinkedHashSet<>();
        List<WebElement> elements = driver.findElements(topCityNames);

        for (WebElement e : elements) {
            String name = e.getText().trim();
            if (isValidCity(name)) {
                cities.add(name);
            }
        }

        return new ArrayList<>(cities);
    }

    private boolean isValidCity(String text) {
        if (text == null) return false;
        if (text.length() < 3 || text.length() > 20) return false;
        return text.matches("[A-Za-z ]+");
    }
}
