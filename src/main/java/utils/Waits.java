package utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Waits {
    WebDriver driver;
    WebDriverWait wait ;

    public Waits(WebDriver driver){
        this.driver = driver;
    }

    public void waitElementToBeClickableByLocator(By by){
        WebDriverWait  wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitElementVisibility(By by){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
