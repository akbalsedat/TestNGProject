package com.automation.tests.day12;

import com.util.BrowserUtil;
import com.util.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WebOrders {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        wait = new WebDriverWait(driver, 10);
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders");
        driver.manage().window().maximize();
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test", Keys.ENTER);
    }

    /**
     * Go to web orders page
     * Click on "Check All" button
     * Verify that all records are selected
     */
    @Test
    public void checkBoxTest(){
    driver.findElement(By.id("ct100_MainContent_btnCheckAll")).click();

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("[type=checkbox"));
        for (WebElement each:
             checkboxes) {
            Assert.assertTrue(each.isSelected());
        }

    }
    /**
     * until 5:37
     * :: TASK for 10 minutes ::
     * Go to web orders page
     * Verify that Steve Johns zip code is 21233
     * Then update his zip code to 20002
     * Then verify that Steve Johns zip code is 20002
     */
    @Test
    public void verifyZipCode(){
        BrowserUtil.wait(5);
    WebElement zipCodeElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[@class=\"SampleTable\"]//tr[4]//td[9]"))));
    Assert.assertEquals("21233", wait.until(ExpectedConditions.visibilityOf(zipCodeElement)).getText());
    driver.findElement(By.xpath("//table[@class=\"SampleTable\"]//tr[4]//td[13]")).click();
        BrowserUtil.wait(5);
    WebElement newZipCode = wait.until(ExpectedConditions.visibilityOf(driver.findElement((By.id("ctl00_MainContent_fmwOrder_TextBox5")))));
    newZipCode.clear();
    BrowserUtil.wait(5);
    newZipCode.sendKeys("2002");
    driver.findElement(By.xpath("//a[@id='ctl00_MainContent_fmwOrder_UpdateButton']")).click();

    Assert.assertEquals("2002", driver.findElement(By.xpath("//td[contains(text(), '2002')]")).getText());

    }

    @Test (description = "Fill up an order form by the data that is acquired from a text file")
    public void readFromFileTest() throws IOException {
        List<String> allData = Files.readAllLines(Paths.get("Canvas.txt"));
        // click on Order
        driver.findElement(By.xpath("//a[contains(text(),'Order')]")).click();
        //get quantity input box, clear it first and then fill out with info from the file
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE, allData.get(0), Keys.ENTER);
        // fill out customer name
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtName")).sendKeys(allData.get(1));

        for (int index = 2; index < 7; index++) {
            driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox" + index)).sendKeys(allData.get(index));
            if (index == 5) {
                // choose card type
                List<WebElement> cardTypes = driver.findElements(By.cssSelector("[name*='ctl00$MainContent$fmwOrder$cardList']"));
                cardTypes.get(0).click(); // click on visa
            }
        }
        // fill out expire date
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(allData.get(7));
        // click on Process button
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_InsertButton")).click();
        // get approved element of approved message
        WebElement approvedElement = driver.findElement(By.cssSelector("div > strong"));
        Assert.assertTrue(approvedElement.isDisplayed());

    }

    @AfterMethod
    public void teardown(){
       BrowserUtil.wait(3);
        driver.quit();
    }


}
