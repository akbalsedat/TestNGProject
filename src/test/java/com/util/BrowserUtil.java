package com.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
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
            textValues.add(element.getText());
        }

        return textValues;
    }
}
