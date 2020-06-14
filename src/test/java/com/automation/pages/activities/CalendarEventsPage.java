package com.automation.pages.activities;

import com.automation.pages.AbstractPageBase;
import com.automation.utilities.BrowserUtil;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CalendarEventsPage extends AbstractPageBase {

    @FindBy(css= "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    private WebElement startDate;

    @FindBy(css ="[id^= 'time_selector_oro_calendar_event_form_start-uid-']")
    private WebElement startTime;

    @FindBy(css ="[id^='time_selector_oro_calendar_event_form_end-uid-']")
    private WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy(css = "[title='Rich Text Area. Press ALT-F9 for menu. Press ALT-F10 for toolbar. Press ALT-0 for help']")
    private WebElement descriptionFrame;

    @FindBy(css = "#tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css= "[class='btn btn-success action-button']")
    private WebElement saveAndCloseButton;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//div")
    private WebElement generalInfoDescription;



    public void enterCalendarEventTitle(String titleValue){
        BrowserUtil.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
    }

    public void enterCalendarEventDescription(String description){
        BrowserUtil.waitForPageToLoad(20);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        wait.until(ExpectedConditions.visibilityOf(descriptionTextArea)).sendKeys(description);
        Driver.getDriver().switchTo().defaultContent();

    }

    public void clickOnSaveAndClose(){
        wait.until(ExpectedConditions.visibilityOf(saveAndCloseButton)).click();
    }

    public String getGeneralInfoTitleText(){
        BrowserUtil.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='control-label'])[1]")));
        return generalInfoTitle.getText();
    }

    public String getGeneralInfoDescriptionText(){
        BrowserUtil.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div//div")));
        return generalInfoDescription.getText();
    }

//############################################################################

    public List<String> getColumnNames(){
        BrowserUtil.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        return BrowserUtil.getTextFromWebElements(columnNames);
    }

    public String getStartTime(){
        BrowserUtil.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^= 'time_selector_oro_calendar_event_form_start-uid-']")));
        return startTime.getAttribute("value");
    }

    public String getEndTime(){
        BrowserUtil.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_end-uid-']")));
        return endTime.getAttribute("value");
    }

    /**
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Default owner name should be current user/ */

    public void clickToCreateCalendarEvent(){
        BrowserUtil.wait(5);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
    }

    public String getOwnerName(){
        BrowserUtil.waitForPageToLoad(10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    public String getStartDate(){
        BrowserUtil.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        return startDate.getAttribute("value");
    }


}
