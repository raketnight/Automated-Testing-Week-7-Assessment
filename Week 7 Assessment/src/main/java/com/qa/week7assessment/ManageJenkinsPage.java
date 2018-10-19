package com.qa.week7assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageJenkinsPage {

   @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Install as Windows Service'])[1]/following::dt[1]")
    private WebElement manageUsersPage;

    public WebElement getManageUsersPage(){
        return manageUsersPage;
    }

    public void goToManageUsersPage(){
        manageUsersPage.click();
    }
}
