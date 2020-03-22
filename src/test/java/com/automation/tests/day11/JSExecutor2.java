package com.automation.tests.day11;

import com.util.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class JSExecutor2 {
    private WebDriver driver;
    private JavascriptExecutor js;

    private String URL = "http://practice.cybertekschool.com/";

    @BeforeMethod
    public void setup(){
        WebDriverManager.firefoxdriver().version("74").setup();
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtil.wait(3);
    }

    @Test(description = "get title of the page using JavaScriptExecutor")
    public void verifyTitle(){
        String expected = "Practice";
        js = (JavascriptExecutor) driver;
        String actual = (String)js.executeScript("return document.title");
        String cookies = (String)js.executeScript("return document.cookie");
        System.out.println("cookies = " + cookies);

        Assert.assertEquals(expected, actual, "expected does not match actual");

    }

    @Test
    public void clickTest(){
        WebElement element = driver.findElement(By.linkText("Multiple Buttons"));
        //element.click();
        js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click()", element);

        BrowserUtil.wait(3);

        WebElement button6 = driver.findElement(By.cssSelector("#disappearing_button"));

        js.executeScript("arguments[0].click()", button6);

        BrowserUtil.wait(2);

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "Now it's gone!");
    }

    @Test
    public void textInputTest(){
        //
        driver.findElement(By.linkText("Form Authentication")).click();
        BrowserUtil.wait(3);
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginbtn = driver.findElement(By.id("wooden_spoon"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //to get text from input box - read attribute "value"
        //to enter text - set attribute "value"
        //.setAttribute('value', 'text') - enter some text
        js.executeScript("arguments[0].setAttribute('value', 'tomsmith')" , username);
        js.executeScript("arguments[0].setAttribute('value', 'SuperSecretPassword')", password);
        js.executeScript("arguments[0].click()", loginbtn);
        BrowserUtil.wait(3);

        String actual = driver.findElement(By.cssSelector("h4")).getText();
        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        Assert.assertEquals(actual, expected);

    }

    @Test(testName = "clickAllButtons")
    void clickAllButtons(){
        driver.findElement(By.linkText("Multiple Buttons")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> list = driver.findElements(By.xpath("//button[@class='btn btn-primary']"));;
        for (WebElement each : list) {
            js.executeScript("arguments[0].click()", each);
            BrowserUtil.wait(2);
        }
    }

    @Test(description = "scrolldown example")
    public void scrollToElement(){
    WebElement link = driver.findElement(By.cssSelector("a[href*=cybertek]"));
    js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true)", link);
    Assert.assertTrue(link.isDisplayed());

    }

    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(5);
        driver.quit();
    }
}
