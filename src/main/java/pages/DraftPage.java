package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Waits;

import java.util.List;

public class DraftPage {
    WebDriver driver;
    Waits waits;

    private final String allMailsDraftsLocator = "//span[text() = 'Draft']//ancestor::tr[@role = 'row']";
    @FindBy(xpath = allMailsDraftsLocator)
    private List<WebElement> allMailsInDrafts;

    private final String allMailsSubjectLocator = "//span[@class = 'bog']/span";
    @FindBy(xpath = allMailsSubjectLocator)
    private List<WebElement> allMailsSubject;

    public  DraftPage(WebDriver driver, Waits wait){
        this.waits = wait;
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void openLastMailFromDrafts(){
          waits.waitElementToBeClickableByLocator(By.xpath(allMailsDraftsLocator));
          allMailsInDrafts.get(0).click();
    }

    public String getLastMailSubject(){
        waits.waitElementVisibility(By.xpath(allMailsDraftsLocator));
        return allMailsSubject.get(1).getText();
    }
}
