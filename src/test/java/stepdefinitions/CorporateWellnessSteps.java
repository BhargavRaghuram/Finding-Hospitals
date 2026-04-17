package stepdefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.CorporateWellnessPage;

public class CorporateWellnessSteps {

    private WebDriver driver;
    private String warning;

    @Given("user opens Practo Corporate Wellness page")
    public void user_opens_practo_corporate_wellness_page() {
        driver = core.DriverFactory.getDriver();
        new CorporateWellnessPage(driver).open();
    }

    @When("user schedules a demo with invalid details")
    public void user_schedules_a_demo_with_invalid_details() {
        warning = new CorporateWellnessPage(driver).submitInvalidFormAndGetWarning();
    }

    @Then("user captures and prints the warning message")
    public void user_captures_and_prints_the_warning_message() {
        System.out.println("=== CORPORATE WELLNESS WARNING MESSAGE ===");
        System.out.println(warning);
        System.out.println("=========================================");
    }
}
