package com.cucumber.testng.runner.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import project.model.User;
import project.pages.GmailMainPage;
import project.pages.LoginPage;
import project.service.UserCreator;
import project.utils.Waits;

import static org.testng.Assert.assertEquals;

public class GmailSteps {
    Waits waits;
    private LoginPage loginPage;
    private GmailMainPage gmailMainPage;
    public static WebDriver driver;

    @Given("^The user opens Gmail website (.*)$")
    public void theUserOpensGmailWebsiteLink(String src) {

        waits = new Waits(driver);
        loginPage = new LoginPage(driver, waits);
        gmailMainPage = new GmailMainPage(driver, waits);
        driver.get(src);
    }

    @When("the user login Gmail website")
    public void loginGmailWebsite() {
        User testUser = UserCreator.getCredentialsFromProperty();
        loginPage.login(testUser);
    }

    @When("user hover inbox button")
    public void hoverInboxButton() {
        gmailMainPage.hoverInboxButton();
    }

    @Then("the hover text is match inbox")
    public void checkHoverText() {
        assertEquals(gmailMainPage.getHoverText(), "Inbox", "Hover text does not match");
    }

}
