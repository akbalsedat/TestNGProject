package com.automation.tests.vytrack.fleet;

import com.automation.pages.LoginPage;
import com.automation.pages.fleet.VehiclesPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewVehiclesPageTest extends AbstractTestBase {

    @Test
    public void verifyTitle(){
        LoginPage loginPage = new LoginPage();
        VehiclesPage vehiclesPage = new VehiclesPage();

        loginPage.login();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title title-level-1'][contains(text(),'Fleet')]")));
        vehiclesPage.navigateTo("Fleet", "Vehicles");
        String actual = Driver.getDriver().getTitle();
        Assert.assertEquals(actual, "All - Car - Entities - System - Car - Entities - System");
    }
}
