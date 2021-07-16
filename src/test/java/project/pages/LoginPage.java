package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project.utils.Waits;

import project.model.User;

public class LoginPage {
    private final WebDriver driver;
    private Waits waits;
    private final String nextButtonLocator = "//div[@id='identifierNext']";
    private final String passwordLocator = "password";
    private final String nextButtonPasswordLocator = "passwordNext";

    @FindBy(id = nextButtonPasswordLocator)
    private WebElement nextButtonPassword;

    @FindBy(name = passwordLocator)
    private WebElement passwordFiled;

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement emailFiled;

    @FindBy(xpath = nextButtonLocator)
    private WebElement nextButton;

    public LoginPage(WebDriver driver, Waits waits) {
        this.driver = driver;
        this.waits = waits;
        PageFactory.initElements(driver, this);
    }

    public void login(User user) {
        waits.waitElementToBeClickableByLocator(By.xpath(nextButtonLocator));
        emailFiled.sendKeys(user.getUsername());
        nextButton.click();
        waits.waitElementToBeClickableByLocator(By.id(nextButtonPasswordLocator));
        passwordFiled.sendKeys(user.getPassword());
        waits.waitElementToBeClickableByLocator(By.id(nextButtonPasswordLocator));
        nextButtonPassword.click();
    }


    public void clickNextButtonEmailPage() {
        nextButton.click();
    }

    public void clickNextButtonPasswordPage() {
        nextButtonPassword.click();
    }
}
