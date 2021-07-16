package project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import project.utils.Waits;

public class SentPage {
    WebDriver driver;
    Waits waits;

    private final String allSentMailsLocator = "//div[text() = 'To: ']//ancestor::tr[@role = 'row']";
    @FindBy(xpath = allSentMailsLocator)
    private List<WebElement> allSentMails;

    public SentPage(WebDriver driver, Waits waits) {
        this.waits = waits;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getSentMailsCount() {
        try {
            waits.waitElementVisibility(By.xpath(allSentMailsLocator));
        } catch (TimeoutException exception) {
            return 0;
        }
        return allSentMails.size();

    }
}
