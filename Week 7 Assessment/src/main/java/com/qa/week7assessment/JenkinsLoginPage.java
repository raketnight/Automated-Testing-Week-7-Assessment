package com.qa.week7assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsLoginPage {

    @FindBy(id = "j_username")
    private WebElement jenkinsUsername;

    @FindBy(name = "j_password")
    private WebElement jenkinsPassword;

    @FindBy(name = "Submit")
    private WebElement jenkinsSubmit;

    public void enterJenkinsUsername(String text){
        jenkinsUsername.sendKeys(text);
    }

    public void enterJenkinsPassword(String test){
        jenkinsPassword.sendKeys(test);
    }

    public void clickJenkinsSubmit(){
        jenkinsSubmit.click();
    }
}
