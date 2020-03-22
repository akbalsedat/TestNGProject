package com.automation.tests.day12;

import com.util.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImplicitWait {
    private WebDriver driver;
    private JavascriptExecutor js;

    private String URL = "http://practice.cybertekschool.com/dynamic_loading/2";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void waitTest(){
        driver.findElement(By.tagName("button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement finishElement = driver.findElement(By.xpath("//div/div/div/div/div/h4"));
        System.out.println(finishElement.getText());
    }



    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(5);
        driver.quit();
    }

}
