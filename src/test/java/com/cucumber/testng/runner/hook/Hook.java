package com.cucumber.testng.runner.hook;

import com.cucumber.testng.runner.steps.GmailSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import project.driver.DriverSingleton;

public class Hook {
    WebDriver driver;

    @After
    public void quiteDriver() {
        DriverSingleton.closeDriver();
    }

    @Before
    public void createDriver() {
        driver = DriverSingleton.getDriver();
        GmailSteps.driver=driver;
    }
}
