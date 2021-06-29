package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Waits;

import java.util.List;

public class SentPage {
    WebDriver driver;
    Waits waits;

    private final String allSentMailsLocator = "//div[text() = 'To: ']//ancestor::tr[@role = 'row']";
    @FindBy(xpath = allSentMailsLocator)
    private List<WebElement> allSentMails;

    public SentPage (WebDriver driver, Waits waits){
        this.waits = waits;
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public int getSentMailsCount(){
        waits.waitElementVisibility(By.xpath(allSentMailsLocator));
        return allSentMails.size();
    }
}
