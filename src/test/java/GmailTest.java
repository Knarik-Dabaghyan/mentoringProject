import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Waits;

public class GmailTest {
    WebDriver driver;
    Waits waits;
    SoftAssert softAssert;
    LoginEmailPage loginEmailPage;
    GmailMainPage gmailMainPage;
    SentPage sentPage;
    MailCreatingPage mailCreatingPage;
    DraftPage draftPage;
    private final String mailSubjectText = "hello";
    private final String mailBodyText = "helloooo";
    private final String otherUserMail = "knarikdabaghyan@gmail.com";
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
    }

    @Test()
    public void loginTest() {
        loginEmailPage.enterEmail(userMail);
        LoginPasswordPage loginPasswordPage = new LoginPasswordPage(driver, waits);
        loginPasswordPage.enterPassword();
        Assert.assertTrue(gmailMainPage.isInGmailPage(), "It's not Gmail main page");
    }

    @Test(dependsOnMethods = "loginTest")
    public void createMailAndSaveInDrafts() {
        gmailMainPage.openSentMails();
        sentMailsBeforeSendinNewMail = sentPage.getSentMailsCount();
        int draftsQuantityBeforeCreatingNewMail = gmailMainPage.getDraftsQuantity();
        gmailMainPage.clickOnComposeButton();
        mailCreatingPage.createNewMail(mailSubjectText, mailBodyText,otherUserMail);
        mailCreatingPage.clickOnSetSaveAndCloseButton();
        draftsQuantityAfterCreatingNewMail = gmailMainPage.getDraftsQuantity();
        Assert.assertEquals(draftsQuantityAfterCreatingNewMail - 1, draftsQuantityBeforeCreatingNewMail, "The message isn't saved in drafts");
    }

    @Test(dependsOnMethods ={ "loginTest", "createMailAndSaveInDrafts"})
    public void verifyMailContentTest() {
        gmailMainPage.openDraftsPage();
        draftPage.openLastMailFromDrafts();
        softAssert = new SoftAssert();
        softAssert.assertEquals(mailBodyText, mailCreatingPage.getTextFromMailBody(), "Actual body are different from Expected");
        softAssert.assertEquals(otherUserMail, mailCreatingPage.getTextFromSendToFiled(), "Users whom mails was sent are different");
        softAssert.assertAll();
    }

    @Test(dependsOnMethods ={ "loginTest", "createMailAndSaveInDrafts", "verifyMailContentTest" })
    public void sentMailTest() {
        mailCreatingPage.sendMail();
        int draftsQuantityAfterSendingMail = gmailMainPage.getDraftsQuantity();
        Assert.assertEquals(draftsQuantityAfterSendingMail, draftsQuantityAfterCreatingNewMail - 1, "After sending mail, mail isn't disappeared from drafts");
        gmailMainPage.openSentMails();
        int sentMailsAfterSendingNewMail = sentPage.getSentMailsCount();
        Assert.assertEquals(sentMailsBeforeSendinNewMail + 1, sentMailsAfterSendingNewMail, "Sent mail isn't in Sent folder");
    }

    @AfterClass
    public void tearDown() {
        gmailMainPage.signOut();
        driver.quit();
    }

}