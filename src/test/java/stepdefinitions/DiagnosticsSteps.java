package stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.LabTestsPage;

public class DiagnosticsSteps {

    private WebDriver driver;
    private List<String> cities;

    @Given("user opens Practo lab tests page")
    public void user_opens_practo_lab_tests_page() {
        driver = core.DriverFactory.getDriver();
        new LabTestsPage(driver).openLabTests();
    }

    @When("user captures the top cities list")
    public void user_captures_the_top_cities_list() {
        cities = new LabTestsPage(driver).captureTopCities();
    }

    @Then("user prints the top cities")
    public void user_prints_the_top_cities() {
        System.out.println("=== TOP CITIES (Diagnostics/Lab Tests) ===");
        if (cities == null || cities.isEmpty()) {
            System.out.println("Top cities not found. Practo may load it dynamically based on geo/cookies.");
        } else {
            for (String c : cities) {
                System.out.println(c);
            }
        }
        System.out.println("========================================");
    }
}
