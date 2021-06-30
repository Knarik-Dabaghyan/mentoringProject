import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Waits;

import static org.testng.Assert.*;

public class GmailTest {
    WebDriver driver;
    Waits waits;
    SoftAssert softAssert;
    LoginEmailPage loginEmailPage;
    GmailMainPage gmailMainPage;
    SentPage sentPage;
    MailCreatingPage mailCreatingPage;
    DraftPage draftPage;
    LoginPasswordPage loginPasswordPage;
    private final String mailSubjectText = "hello";
    private final String mailBodyText = "test message";
    private final String otherUserMail = "knarikdabaghyan@gmail.com";
    private String password="test099@";
    private final String userMail = "mailfortest44@gmail.com";
    int sentMailsBeforeSendinNewMail;
    int draftsQuantityAfterCreatingNewMail;

    @BeforeClass
    public void testSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/");
        waits = new Waits(driver);
        loginEmailPage = new LoginEmailPage(driver, waits);
        gmailMainPage = new GmailMainPage(driver, waits);
        sentPage = new SentPage(driver, waits);
        mailCreatingPage = new MailCreatingPage(driver, waits);
        draftPage = new DraftPage(driver, waits);
        loginPasswordPage = new LoginPasswordPage(driver, waits);
    }

    @Test()
    public void gmailTest() {
        loginEmailPage.enterEmail(userMail);
        loginEmailPage.clickNextButton();
        loginPasswordPage.enterPassword(password);
        loginPasswordPage.clickNextButton();
        assertTrue(gmailMainPage.isInGmailPage(), "It's not Gmail main page");

        gmailMainPage.openSentMails();
        sentMailsBeforeSendinNewMail = sentPage.getSentMailsCount();
        int draftsQuantityBeforeCreatingNewMail = gmailMainPage.getDraftsQuantity();
        gmailMainPage.clickOnComposeButton();
        mailCreatingPage.enterOtherUserEmail(otherUserMail);
        mailCreatingPage.enterSubjectText(mailSubjectText);
        mailCreatingPage.enterBodyText(mailBodyText);
        mailCreatingPage.clickOnSetSaveAndCloseButton();
        draftsQuantityAfterCreatingNewMail = gmailMainPage.getDraftsQuantity();
        assertEquals(draftsQuantityAfterCreatingNewMail - 1, draftsQuantityBeforeCreatingNewMail, "The message isn't saved in drafts");

        gmailMainPage.openDraftsPage();
        draftPage.openLastMailFromDrafts();
        softAssert = new SoftAssert();
        softAssert.assertEquals(mailBodyText, mailCreatingPage.getTextFromMailBody(), "Actual body are different from Expected");
        softAssert.assertEquals(otherUserMail, mailCreatingPage.getTextFromSendToFiled(), "Users whom mails was sent are different");
        softAssert.assertAll();

        mailCreatingPage.clickSendButton();
        int draftsQuantityAfterSendingMail = gmailMainPage.getDraftsQuantity();
        assertEquals(draftsQuantityAfterSendingMail, draftsQuantityAfterCreatingNewMail - 1, "After sending mail, mail isn't disappeared from drafts");
        gmailMainPage.openSentMails();
        int sentMailsAfterSendingNewMail = sentPage.getSentMailsCount();
        assertEquals(sentMailsBeforeSendinNewMail + 1, sentMailsAfterSendingNewMail, "Sent mail isn't in Sent folder");

        gmailMainPage.signOut();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}