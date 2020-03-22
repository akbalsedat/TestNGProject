package com.automation.tests.day12;

import com.sun.source.tree.AssertTree;
import com.util.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WarmUpTask {

    private WebDriver driver;
    private JavascriptExecutor js;

    private String URL = "http://practice.cybertekschool.com/";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @Test
    public void warUpTest() {
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        BrowserUtil.wait(2);

        //sort last Name values
        driver.findElement(By.xpath("//table[1]//span[contains(text(), 'Due')]")).click();
        BrowserUtil.wait(2);

        List<WebElement> list = driver.findElements(By.xpath("//table[1]//tbody//td[1]"));
        list.forEach(each -> System.out.println(each.getText()));
        for (int index = 0; index < list.size() - 1; index++) {
            String value = list.get(index).getText();
            String nextValue = list.get(index+1).getText();
            Assert.assertTrue(value.compareTo(nextValue) <= 0);
        }
    }

    @Test
    public void compareDuePaymentsTest() {
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //sort last Name values
        driver.findElement(By.xpath("//table[1]//span[contains(text(), 'Due')]")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        List<WebElement> list = driver.findElements(By.xpath("//table[1]//tbody//td[4]"));
       // list.forEach(each -> System.out.println(each.getText()));

        for (int index = 0; index < list.size() - 1; index++) {
            Double value = Double.parseDouble(list.get(index).getText().substring(1));
            Double nextValue = Double.parseDouble(list.get(index+1).getText().substring(1));
            Assert.assertTrue(value <= nextValue);
        }
    }


    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(5);
        driver.quit();
    }
}
