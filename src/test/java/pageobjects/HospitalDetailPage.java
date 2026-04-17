//package pageobjects;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import core.BasePage;
//
//public class HospitalDetailPage extends BasePage {
//
//    private final By title = By.cssSelector("h1");
//
//    public HospitalDetailPage(WebDriver driver) {
//        super(driver);
//    }
//
//    public String getHospitalName() {
//        try {
//            return waitVisible(title).getText().trim();
//        } catch (Exception e) {
//            return driver.getTitle();
//        }
//    }
//
//    public double getRatingFromPageSource() {
//        String src = driver.getPageSource();
//        Pattern p = Pattern.compile("([0-5]\\.[0-9])\\s*\\((?:\\d+\\s+)?(?:patient stories|rated)", Pattern.CASE_INSENSITIVE);
//        Matcher m = p.matcher(src);
//        if (m.find()) return Double.parseDouble(m.group(1));
//
////        Pattern p2 = Pattern.compile("\"rating\"\\s*:\s*([0-5]\\.[0-9])", Pattern.CASE_INSENSITIVE);
//        Pattern p2 = Pattern.compile(
//        	    "\"rating\"\\s*:\\s*((?:[0-4]\\.\\d)|(?:5\\.0))",
//        	    Pattern.CASE_INSENSITIVE
//        	);
//        Matcher m2 = p2.matcher(src);
//        if (m2.find()) return Double.parseDouble(m2.group(1));
//
//        Pattern p3 = Pattern.compile("([0-5]\\.[0-9])");
//        Matcher m3 = p3.matcher(src);
//        if (m3.find()) return Double.parseDouble(m3.group(1));
//        return 0.0;
//    }
//
//    public boolean isOpen24x7() {
//        String src = driver.getPageSource().toLowerCase();
//        return src.contains("open 24x7") || src.contains("open 24 x 7") || src.contains("open 24/7") || src.contains("open 24 hrs");
//    }
//
//    public boolean hasParking() {
//        String src = driver.getPageSource().toLowerCase();
//        // Practo sometimes lists amenities; we keep it permissive.
//        return src.contains("parking") || src.contains("car park") || src.contains("car parking") || src.contains("visitor parking");
//    }
//}




package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.BasePage;

public class HospitalDetailPage extends BasePage {

    /* =============================
       Locators
       ============================= */

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

