package project.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import project.utils.Waits;

public class DraftPage {
    WebDriver driver;
    Waits waits;
    Logger logger = LogManager.getRootLogger();

    private final String allMailsDraftsLocator = "//span[text() = 'Draft']//ancestor::tr[@role = 'row']";
    @FindBy(xpath = allMailsDraftsLocator)
    private List<WebElement> allMailsInDrafts;

    private final String allMailsSubjectLocator = "//span[@class = 'bog']/span";
    @FindBy(xpath = allMailsSubjectLocator)
    private List<WebElement> allMailsSubject;

    public DraftPage(WebDriver driver, Waits wait) {
        this.waits = wait;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLastMailFromDrafts() {
        waits.waitElementToBeClickableByLocator(By.xpath(allMailsDraftsLocator));
        allMailsInDrafts.get(0).click();
        logger.info("Open last mail from draft");

    }

}
