package com.qa.week7assessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManageUsersPage {

    @FindBy(linkText = "Create User")
    private WebElement createUserPage;

    public WebElement getCreateUserPage(){
        return createUserPage;
    }

    public void goToCreateUserPage(){
        createUserPage.click();
    }

    public WebElement waitForSpecificUser(WebDriver driver, String username){
        WebElement element = (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(username)));
        return element;
    }

    public WebElement findSpecificUser(WebDriver driver, String username){
        WebElement element = driver.findElement(By.linkText(username));
        return element;
    }
}
