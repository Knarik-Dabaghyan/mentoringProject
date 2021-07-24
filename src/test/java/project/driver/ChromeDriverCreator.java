package project.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverCreator implements WebDriverCreator {

    private static WebDriver driver;
    public WebDriver CreateWebDriver() {
        WebDriverManager.chromedriver().setup();
        return  driver = new ChromeDriver();

    }
}