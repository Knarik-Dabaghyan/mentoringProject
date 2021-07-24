package project.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverCreator implements WebDriverCreator {
    private static WebDriver driver;
    public WebDriver CreateWebDriver() {
        WebDriverManager.edgedriver().setup();
        return driver = new EdgeDriver();

    }
}
