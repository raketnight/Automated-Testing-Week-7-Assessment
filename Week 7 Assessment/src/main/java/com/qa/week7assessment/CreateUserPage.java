package com.qa.week7assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreateUserPage {


    @FindBy(id = "username" )
    private WebElement username;

    @FindBy(name = "password1")
    private WebElement password1;

    @FindBy(name = "password2")
    private WebElement password2;

    @FindBy(name = "fullname")
    private WebElement fullname;

    @FindBy(id="yui-gen1-button")
    private WebElement createUserButton;


    public void enterUsername(String text){
        username.sendKeys(text);
    }

    public void enterPassword1(String text){
        password1.sendKeys(text);
    }

    public void enterPassword2(String text){
        password2.sendKeys(text);
    }

    public void enterFullname(String text){
        fullname.sendKeys(text);
    }

    public void clickCreateUser(){
        createUserButton.click();
    }



}
