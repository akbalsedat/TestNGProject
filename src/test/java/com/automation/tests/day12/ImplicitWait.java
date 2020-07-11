package com.automation.tests.day12;

import com.automation.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ImplicitWait {
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    private String URL = "http://practice.cybertekschool.com/dynamic_loading/2";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void waitTest(){
        wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
        //driver.findElement(By.tagName("button")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div/div/div/div/div/h4"))));
        WebElement finishElement = driver.findElement(By.xpath("//div/div/div/div/div/h4"));
        System.out.println(finishElement.getText());
    }



    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(5);
        driver.quit();
    }

}
