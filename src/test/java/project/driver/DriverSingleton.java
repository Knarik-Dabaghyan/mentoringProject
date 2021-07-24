package project.driver;

import org.openqa.selenium.WebDriver;

public class DriverSingleton {

    private static WebDriver driver;


    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            WebDriverCreator creator;
            switch (System.getProperty("browser", "chrome")) {
                case "firefox": {
                    creator = new FirefoxDriverCreator();
                    break;
                }
                case "edge": {
                    creator = new EdgeDriverCreator();
                    break;
                }
                default: {
                    creator = new ChromeDriverCreator();
                }
            }
            driver = creator.CreateWebDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
