package com.qa.week7assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsHomepage {

    @FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='add description'])[1]/following::div[1]")
    private WebElement homepageWelcomeMessage;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageAccountsPage;

    public WebElement getHomepageWelcomeMessage(){
        return homepageWelcomeMessage;
    }

    public WebElement getManageAccountsPage(){
        return manageAccountsPage;
    }

    public void goToManageAccountsPage(){
        manageAccountsPage.click();
    }
}
