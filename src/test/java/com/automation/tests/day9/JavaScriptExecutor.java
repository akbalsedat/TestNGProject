package com.automation.tests.day9;

import com.util.BrowserUtil;
import com.util.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptExecutor {
    private RemoteWebDriver driver;
    //private JavascriptExecutor js;
    @BeforeMethod
    public void setup(){
        WebDriverManager.firefoxdriver().version("74").setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void scrolldownTest(){
        driver.get("http://practice.cybertekschool.com/infinite_scroll");
        driver.manage().window().maximize();
        BrowserUtil.wait(4);
        //js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector("#page-footer"));
        driver.executeScript("arguments[0].scrollIntoView(true);", element);
        //((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250)");
        BrowserUtil.wait(5);

        //go back to the top of the page and click on Home button
        WebElement homeElement = driver.findElement(By.linkText("Home"));
        driver.executeScript("arguments[0].scrollIntoView(true)", homeElement);
        BrowserUtil.wait(3);
        driver.executeScript("arguments[0].click();", homeElement);

    }

    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(5);
        driver.quit();
    }
}
