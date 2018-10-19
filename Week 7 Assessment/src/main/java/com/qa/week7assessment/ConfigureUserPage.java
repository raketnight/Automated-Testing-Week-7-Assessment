package com.qa.week7assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigureUserPage {
    @FindBy(name = "_.fullName")
    private WebElement fullnameField;

    @FindBy(id ="yui-gen2-button" )
    private WebElement saveChangesButton;

    public void changeFullname(String text){
        fullnameField.clear();
        fullnameField.sendKeys(text);
    }

    public void clickSaveChanges(){
        saveChangesButton.click();
    }

}
