package com.automation.pages;

import com.automation.utilities.BrowserUtil;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{

    @FindBy(id="prependedInput")
    private WebElement username;

    @FindBy(id="prependedInput2")
    private WebElement password;

    @FindBy(id="_submit")
    private WebElement login;

    @FindBy(partialLinkText = "Forgot your password?")
    private WebElement forgotPassword;

    @FindBy(css = ".alert.alert-error > div")
    private WebElement warningMessage;

    public LoginPage(){
        // to connect our webdriver, page class and page factory
        // PageFactory - used to use @FindBy annotations
        // PageFactory - helps to find elements easier
        PageFactory.initElements(Driver.getDriver(), this);
    }

    /**
     ** Method to login, version #1
     ** Login as a specific user
     * @param usernameValue
     * @param passwordValue
     */

    public void login(String usernameValue, String passwordValue){
        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue, Keys.ENTER);

        BrowserUtil.wait(5);
    }

    /**
     * Method to login, version #2
     * Login as a default user
     * Credentials will be retrieved from configuration.properties file
     */
    public void login(){
        username.sendKeys(ConfigurationReader.getPropertyLocal("store_manager"));
        password.sendKeys(ConfigurationReader.getPropertyLocal("password"), Keys.ENTER);

    }

    public String getWarningMessageText(){
           return warningMessage.getText();
    }
}
