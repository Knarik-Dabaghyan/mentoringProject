package project.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    public void onTestFailure(ITestResult iTestResult) {
        TakeScreenshot screenshot=new TakeScreenshot();
        screenshot.saveScreenshot();
    }
}
