package com.qa.week7assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfilePage {
    @FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='add description'])[1]/following::div[1]")
    private WebElement userProfileId;

    @FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='Delete'])[1]/following::h1[1]")
    private WebElement userFullname;

    @FindBy(linkText = "Configure")
    private WebElement configureUserButton;

    public WebElement getUserProfileId(){
        return userProfileId;
    }

    public WebElement getUserFullname(){
        return userFullname;
    }

    public void clickConfigureUserButton(){
        configureUserButton.click();
    }
}
