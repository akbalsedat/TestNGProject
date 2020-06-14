package com.automation.pages.fleet;

import com.automation.pages.AbstractPageBase;
import com.automation.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehiclesPage extends AbstractPageBase {
    @FindBy(partialLinkText = "Create Car")
    private WebElement createCarElement;

    public void clickToCreateCar(){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(createCarElement)).click();
    }
}
