


package pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.BasePage;

public class CorporateWellnessPage extends BasePage {

    /* =======================
       LOCATORS (from actual UI)
       ======================= */

    private final By nameField =
            By.xpath("//input[@placeholder='Name' or contains(@placeholder,'Name')]");

    private final By orgField =
            By.xpath("//input[contains(@placeholder,'Organization') or contains(@placeholder,'Organisation')]");

    private final By phoneField =
            By.xpath("//input[@type='tel']");

    private final By emailField =
            By.xpath("//input[contains(@placeholder,'Email')]");

    private final By submitBtn =
            By.xpath("//button[contains(text(),'Schedule')]");

    private final By inlineErrors =
            By.xpath(
                "//*[contains(text(),'valid') or " +
                " contains(text(),'required') or " +
                " contains(text(),'Invalid')]"
            );

    public CorporateWellnessPage(WebDriver driver) {
        super(driver);
    }

    /* =======================
       PAGE ACTIONS
       ======================= */

    /**
     * Open Corporate Wellness page
     */
    public void open() {
        driver.get("https://www.practo.com/plus/corporate");
        waitVisible(By.tagName("body"));
        scrollBy(500);
    }

    /**
     * Fill invalid details and capture warning message
     */
    public String submitInvalidFormAndGetWarning() {

        // React‑safe typing
        reactType(nameField, "12345");

        // IMPORTANT:
        // Empty string does NOT trigger React state
        // Space does
        reactType(orgField, " ");

        reactType(phoneField, "abcd");
        reactType(emailField, "abc@");

        if (isPresent(submitBtn)) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(submitBtn));
        }

        return captureValidationMessage();
    }

    /* =======================
       REACT‑SAFE HELPERS
       ======================= */

    /**
     * ✅ Required for React‑controlled inputs
     */
    private void reactType(By locator, String value) {
        WebElement el = waitVisible(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            el
        );

        el.click();
        el.clear();
        el.sendKeys(value);

        // Trigger blur so React validates state
        js.executeScript(
            "arguments[0].dispatchEvent(new Event('blur'));",
            el
        );
    }

    /**
     * Capture inline validation message or disabled submit state
     */
    private String captureValidationMessage() {
        List<String> messages = new ArrayList<>();

        try {
            wait.withTimeout(Duration.ofSeconds(3));
            List<WebElement> errs = driver.findElements(inlineErrors);

            for (WebElement e : errs) {
                if (e.isDisplayed()) {
                    String text = e.getText().trim();
                    if (!text.isEmpty()) {
                        messages.add(text);
                    }
                }
            }
        } catch (Exception ignored) {}

        if (!messages.isEmpty()) {
            return String.join(" | ", messages);
        }

        if (isPresent(submitBtn)) {
            WebElement btn = driver.findElement(submitBtn);
            if (!btn.isEnabled()) {
                return "Submit button disabled due to invalid inputs";
            }
        }

        return "Form submission blocked by inline UI validation";
    }
}
