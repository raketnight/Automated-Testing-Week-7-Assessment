package com.qa.week7assessment;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import gherkin.lexer.Pa;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertEquals;

public class Task1Test {
    WebDriver driver;
    ExtentReports report;
    ExtentTest task1;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gnana\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        report = new ExtentReports(Constants.reportSaveLocation,true);
        task1 = report.startTest("Commencing task 1 automation with user defined account");
    }

    @Test
    public void userCreationAutomation(){
        JenkinsLoginPage jenkinsLoginPage = PageFactory.initElements(driver, JenkinsLoginPage.class);
        JenkinsHomepage jenkinsHomepage = PageFactory.initElements(driver,JenkinsHomepage.class);
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);
        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);

        driver.get(Constants.jenkinsUrl);
        jenkinsLoginPage.enterJenkinsUsername(Constants.jenkinsUsername);
        jenkinsLoginPage.enterJenkinsPassword(Constants.jenkinsPassword);
        jenkinsLoginPage.clickJenkinsSubmit();

        /*if(jenkinsHomepage.getHomepageWelcomeMessage().getText().equals("Welcome to Jenkins!")){
            task1.log(LogStatus.INFO,"Successfully navigated to the Jenkins Homepage");
        }
        else{
            task1.log(LogStatus.INFO, "Did not navigate to the Jenkins Homepage");
        }*/

        jenkinsHomepage.goToManageAccountsPage();

        manageJenkinsPage.goToManageUsersPage();

        manageUsersPage.goToCreateUserPage();

        createUserPage.enterUsername(Constants.testUsername);
        createUserPage.enterPassword1(Constants.testPassword);
        createUserPage.enterPassword2(Constants.testPassword);
        createUserPage.enterFullname(Constants.testFullname);
        createUserPage.clickCreateUser();

        assertEquals("Create User [Jenkins]",driver.getTitle());

        if(driver.getTitle().equals("Create User [Jenkins]")){
            task1.log(LogStatus.PASS, "Successfully created the test user");
        }

        if(driver.getCurrentUrl().equals("http://localhost:8080/securityRealm/addUser")){
            task1.log(LogStatus.FAIL,"The test user was not created");
        }

    }

    @After
    public void tearDown(){
        driver.quit();
        report.flush();
    }

}
