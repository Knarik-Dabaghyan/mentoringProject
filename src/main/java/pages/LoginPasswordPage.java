package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Waits;

public class LoginPasswordPage {

    private WebDriver driver;
    private Waits waits;
    private final String passwordLocator = "password";
    private final String nextButtonLocator = "passwordNext";

    @FindBy(id = nextButtonLocator)
    private WebElement nextButton;

    @FindBy(name = passwordLocator)
    private WebElement passwordFiled;


    public LoginPasswordPage(WebDriver driver, Waits waits) {
        this.waits = waits;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterPassword(String password) {
        waits.waitElementToBeClickableByLocator(By.id(nextButtonLocator));
        passwordFiled.sendKeys(password);
    }

    public void clickNextButton() {
        nextButton.click();
    }
}
