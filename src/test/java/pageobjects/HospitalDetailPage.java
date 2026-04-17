
package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.BasePage;

public class HospitalDetailPage extends BasePage {


    // Hospital name at top of page
    private final By hospitalName =
            By.xpath("//h1");

    // ✅ Visible rating shown near star icons (ex: 4.0)
    private final By visibleRating =
            By.xpath("(//span[contains(text(),'.')])[1]");

    // Open 24x7 text
    private final By open24x7 =
            By.xpath("//*[contains(text(),'Open 24')]");

    public HospitalDetailPage(WebDriver driver) {
        super(driver);
    }

    /* =============================
       Page Actions
       ============================= */

    public String getHospitalName() {
        return waitVisible(hospitalName).getText().trim();
    }

    /**
     * ✅ FIXED rating logic
     * Reads only the visible hospital rating (ex: 4.0)
     */
    public double getRating() {
        try {
            WebElement ratingElement = waitVisible(
                    By.xpath("//span[contains(text(),'.') and string-length(text())<=3]")
            );
            return Double.parseDouble(ratingElement.getText().trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * ✅ Open 24x7 validation
     */
    public boolean isOpen24x7() {
        return isPresent(open24x7);
    }
}

