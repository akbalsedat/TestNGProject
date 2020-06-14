package com.automation.tests.vytrack.activities;

import com.automation.pages.LoginPage;
import com.automation.pages.activities.CalendarEventsPage;
import com.automation.tests.vytrack.AbstractTestBase;
import com.automation.utilities.DateTimeUtilities;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class NewCalendarEventsTests extends AbstractTestBase {
    /**
     * Test Case: Default options
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Default owner name should be current user/ */

    @Test
    public void defaultOptionsTest(){
         LoginPage loginPage = new LoginPage();;
         CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("default Options Test");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        Assert.assertEquals(calendarEventsPage.getOwnerName(), calendarEventsPage.getCurrentUserName());
        Assert.assertEquals(calendarEventsPage.getStartDate(), DateTimeUtilities.getCurrentDate("MMM dd, yyyy"));
        test.pass("Default option was created successfully!");
    }

    /**
     * Test Case: Time difference
     * Login as sales manager
     * Go to Activities --> Calendar Events
     * Click on Create Calendar Event
     * Verify that difference between start and end time is 1 hour
     **/
    @Test
    public void timeDifferenceTest(){
        LoginPage loginPage = new LoginPage();;
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Time Difference Test");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");

        calendarEventsPage.clickToCreateCalendarEvent();

        String startTime = calendarEventsPage.getStartTime();
        String endTime = calendarEventsPage.getEndTime();
        String format = "h:m a"; // format 5:15 Am for example

        long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);

        Assert.assertEquals(actual, 1, "Time difference is not correct.");
        test.pass("Time difference test was created successfully!");
    }

    /**
     * ::::use qa1::::
     * Test Case: Verify calendar events table
     * Login as store manager
     * Go to Activities --> Calendar Events
     * And verify that column names displayed:
     * |TITLE            |
     * |CALENDAR         |
     * |START            |
     * |END              |
     * |RECURRENT        |
     * |RECURRENCE       |
     * |INVITATION STATUS|
     */
    @Test
    public void verifyColumnNames(){
        LoginPage loginPage = new LoginPage();;
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();
        test = report.createTest("Time Difference Test");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        List<String> expected = Arrays.asList("TITLE", "CALENDAR", "START", "END", "RECURRENT",
                "RECURRENCE", "INVITATION STATUS");
        Assert.assertEquals(calendarEventsPage.getColumnNames(), expected);
        test.pass("verify column names was created successfully!");
    }

    //    public Object[] eve
    @Test(dataProvider = "calendarEvents")
    public void createCalendarEventTest(String title, String description) {
        LoginPage loginPage = new LoginPage();;
        CalendarEventsPage calendarEventsPage = new CalendarEventsPage();

        //only for extent report. To create a test in html report
        test = report.createTest("Create calendar event");
        loginPage.login();
        calendarEventsPage.navigateTo("Activities", "Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        calendarEventsPage.enterCalendarEventTitle(title);
        calendarEventsPage.enterCalendarEventDescription(description);
        calendarEventsPage.clickOnSaveAndClose();
        //verify that calendar event info is correct
        Assert.assertEquals(calendarEventsPage.getGeneralInfoTitleText(), title);
        Assert.assertEquals(calendarEventsPage.getGeneralInfoDescriptionText(), description);
        //for extent report. specify that test passed in report (if all assertions passed)
        test.pass("Calendar event was created successfully!");
    }

    @DataProvider
    public Object[][] calendarEvents() {
        return new Object[][]{
                {"Retrospective meeting", "Going ove the last sprint"},
                {"Emergency meeting!!", "PO wants to talk to whole scrum team"}
        };
    }


}
