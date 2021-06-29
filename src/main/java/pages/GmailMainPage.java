package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Waits;

public class GmailMainPage {
    WebDriver driver;
    Waits waits;

    private final String gmailPageIdentifierLocator = "//a[@title = 'Gmail' and @class = 'gb_ke gb_pc gb_ie']";
    @FindBy(xpath = gmailPageIdentifierLocator)
    private WebElement gmailPageIdentifier;

    @FindBy(xpath = "//div[text() = 'Compose']")
    private WebElement composeButton;

    @FindBy(xpath = "//div[@data-tooltip = 'Sent']")
    private WebElement sentButton;

    private final String draftsButtonLocator = "//div[@data-tooltip = 'Drafts']";
    @FindBy(xpath = draftsButtonLocator)
    private WebElement draftsButton;

    private final String draftsQuantityLocator = "//a[contains(text(),'Drafts')]/parent::span/following-sibling::div";
    @FindBy(xpath = draftsQuantityLocator)
    private WebElement draftsQuantity;

    @FindBy(xpath ="//a[@class = 'gb_C gb_Ma gb_h']" )
    private WebElement userButton;

    private final String signOutButtonLocator = "//a[text() = 'Sign out']";
    @FindBy(xpath = signOutButtonLocator)
    private WebElement signOutButton;

    public GmailMainPage(WebDriver driver, Waits waits) {
        this.waits = waits;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getDraftsQuantity(){
        waits.waitElementToBeClickableByLocator(By.xpath(draftsQuantityLocator));
        return Integer.parseInt(draftsQuantity.getText());
    }

    public void clickOnComposeButton() {
        composeButton.click();
    }

    public void openDraftsPage(){
        waits.waitElementToBeClickableByLocator(By.xpath(draftsButtonLocator));
        draftsButton.click();
    }

    public boolean isInGmailPage() {
        waits.waitElementVisibility(By.xpath(gmailPageIdentifierLocator));
        return gmailPageIdentifier.isDisplayed();
    }

    public void openSentMails(){
        waits.waitElementToBeClickableByLocator(By.xpath(draftsQuantityLocator));
        sentButton.click();
    }

    public void signOut(){
        userButton.click();
        waits.waitElementToBeClickableByLocator(By.xpath(signOutButtonLocator));
        signOutButton.click();
    }
}
