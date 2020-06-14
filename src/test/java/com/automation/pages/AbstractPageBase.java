package com.automation.pages;

import com.automation.utilities.BrowserUtil;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public  abstract class AbstractPageBase{
    protected WebDriverWait wait  = new WebDriverWait(Driver.getDriver(), 30);

    @FindBy(css = "#user-menu > a")
    protected WebElement currentUser;

    public  AbstractPageBase(){

        PageFactory.initElements(Driver.getDriver(), this);
    }

    public String getCurrentUserName(){
        BrowserUtil.waitForPageToLoad(5);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }

    public void navigateTo(String tabName, String moduleName){
        String tabNameXpath = "//span[@class='title title-level-1'][contains(text(),'"+tabName+"')]";
        String moduleXpath = "//span[@class='title title-level-2'][contains(text(),'"+moduleName+"')]";

        // You might need to change this when you like put wait time for another tabName
       wait.until(ExpectedConditions.presenceOfElementLocated(By.
               xpath("//span[@class='title title-level-1'][contains(text(),'Activities')]")));
        WebElement tabElement = Driver.getDriver().findElement(By.xpath(tabNameXpath));
        WebElement moduleElement = Driver.getDriver().findElement(By.xpath(moduleXpath));

        Actions actions = new Actions(Driver.getDriver());
        BrowserUtil.wait(3);
        actions.moveToElement(tabElement).build().perform();
        BrowserUtil.wait(3);
        moduleElement.click();
        BrowserUtil.wait(3);

    }
}
