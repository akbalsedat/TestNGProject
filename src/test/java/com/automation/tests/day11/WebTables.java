package com.automation.tests.day11;

import com.automation.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WebTables {
    private WebDriver driver;
    private JavascriptExecutor js;
    private String URL = "http://practice.cybertekschool.com/";

    @BeforeMethod
    public void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        //headless mode makes execution faster
        //the execution procedure is same except file uploading
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setHeadless(true);
//        driver = new FirefoxDriver(firefoxOptions);

        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtil.wait(3);
    }

    @Test
    public void getColumnNames(){
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        BrowserUtil.wait(2);

        List<String> expected = Arrays.asList("Last Name", "First Name", "Email", "Due", "Web Site", "Action");
        //th - represents table header cells
        List<WebElement> columnNames = driver.findElements(By.xpath("//*[@id=\"table1\"]//thead//tr//th"));
        Assert.assertEquals(expected, BrowserUtil.getTextFromWebElements(columnNames));
    }

    @Test
    public void verifyRowCount(){
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        BrowserUtil.wait(2);
        //--//tbody//tr - to get all row elements, excluding table header
        List<WebElement> rows = driver.findElements(By.xpath("//table[1]//tbody//tr"));
        // size of the collection equals to number of elements
        Assert.assertEquals(rows.size(), 4);
    }
    @Test
    public void getParticularColumnElementsTest(){
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        BrowserUtil.wait(2);

        List<WebElement> columnList = driver.findElements(By.xpath("//table[1]//tbody/tr/td[5]"));
//        System.out.println(BrowserUtil.getTextFromWebElements(columnList));


        WebElement deleteElementof4thRow = driver.findElement(By.xpath("//table[1]//td[text()='http://www.jdoe.com']/..//a[2]"));
        deleteElementof4thRow.click();
        BrowserUtil.wait(3);
        List<WebElement> rows = driver.findElements(By.xpath("//table[1]//tbody//tr"));
        // size of the collection equals to number of elements
        Assert.assertEquals(rows.size(), 3, "Size is not equal to 3");
        Assert.assertTrue(driver.findElements(By.xpath("//table[1]//td[text()='http://www.jdoe.com']")).isEmpty()); // findElemetns return only one element


    }
    @AfterMethod
    public void teardown(){
        BrowserUtil.wait(5);
        driver.quit();
    }


}
