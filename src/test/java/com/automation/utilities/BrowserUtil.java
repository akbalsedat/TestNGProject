package com.automation.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowserUtil {

    public static void wait(int x){
        try{
            Thread.sleep(1000 * x);
        }
        catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static List<String> getTextFromWebElements(List<WebElement> collection){
        List<String> textValues = new ArrayList<>();
        for (WebElement element:
                collection) {
            if(!element.getText().isEmpty())
                textValues.add(element.getText());
        }
        return textValues;
    }

    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((
                JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }


    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void scrollTo(WebElement element){
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * @param name screenshot name
     * @return path to screen shot
     */
    public static String getScreenshot(String name) {
        name = name + "_"  + new Date().toString().replace(" ", "_").replace(":","_");
        String path = System.getProperty("user.dir") + "\\test-output\\" + name + ".png";
        System.out.println(path);
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE); // screen shot itself
        File destination = new File(path); // where we store taken screenshots
        try {
            // copy file to the previously specified location
            FileUtils.copyFile(source, destination);
        }catch (IOException e){
            e.printStackTrace();
        }
        return path;
    }
}
