package pageobjects;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.BasePage;

public class HospitalsListingPage extends BasePage {

    private final By hospitalLinks = By.xpath("//a[contains(@href,'/bangalore/hospital/')]");

    public HospitalsListingPage(WebDriver driver) {
        super(driver);
    }

    public void openBangaloreHospitals() {
        driver.get("https://www.practo.com/bangalore/hospitals");
        waitVisible(By.tagName("body"));
        // scroll a bit to load more cards (if lazy-loaded)
        for (int i = 0; i < 3; i++) {
            scrollToBottom();
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
    }

    public List<String> getHospitalDetailUrls(int max) {
        List<WebElement> links = findAll(hospitalLinks);
        Set<String> urls = new LinkedHashSet<>();
        for (WebElement l : links) {
            String href = l.getAttribute("href");
            if (href != null && href.contains("/bangalore/hospital/")) {
                urls.add(href.split("\\?")[0]);
            }
            if (urls.size() >= max) break;
        }
        return new ArrayList<>(urls);
    }
}
