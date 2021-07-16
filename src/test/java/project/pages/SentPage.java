package project.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project.utils.Waits;

import java.util.List;

public class SentPage {
    WebDriver driver;
    Waits waits;
    Logger logger = LogManager.getRootLogger();

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
        logger.info("sent mails count is"+allSentMails.size());
        return allSentMails.size();
    }
}
