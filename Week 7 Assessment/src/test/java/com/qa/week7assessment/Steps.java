package com.qa.week7assessment;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.zip.CheckedOutputStream;

public class Steps {

    private WebDriver driver;
    ExtentReports reports = new ExtentReports(Constants.reportSaveLocation, true);
    ExtentTest task1; ExtentTest task2; ExtentTest task3; ExtentTest task4;
    JenkinsLoginPage jenkinsLoginPage = PageFactory.initElements(driver, JenkinsLoginPage.class);
    JenkinsHomepage jenkinsHomepage = PageFactory.initElements(driver,JenkinsHomepage.class);
    ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
    ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);
    CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gnana\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();

            task1 = reports.startTest("Starting Task 1 report: Automation of the creation of a user");

            task2 = reports.startTest("Starting Task 2 report: Viewing a user on the database");

            task3 = reports.startTest("Starting Task 3 report: Updating the FullName of selected users");

    }

    @After
    public void tearDown(){
        driver.quit();

        if(Constants.task1Pass && Constants.task2Pass && Constants.task3Pass){
        reports.flush();
        }
    }
///////////////////////////////////////TASK1///////////////////////////////////////////////////////////////////
    @Given("^that you are on the create UserScreen$")
    public void that_you_are_on_the_create_UserScreen() throws Throwable {
        //Initialising all the required pages
        JenkinsLoginPage jenkinsLoginPage = PageFactory.initElements(driver, JenkinsLoginPage.class);
        JenkinsHomepage jenkinsHomepage = PageFactory.initElements(driver,JenkinsHomepage.class);
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);
        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);

        //logging into jenkins website with admin
        driver.get(Constants.jenkinsUrl);
        task1.log(LogStatus.INFO, "Navigating to page with title"+driver.getTitle());
        task1.log(LogStatus.INFO,"Entering in admin login details");
        jenkinsLoginPage.enterJenkinsUsername(Constants.jenkinsUsername);
        jenkinsLoginPage.enterJenkinsPassword(Constants.jenkinsPassword);
        jenkinsLoginPage.clickJenkinsSubmit();

        //navigating to the create user screen
        jenkinsHomepage.goToManageAccountsPage();

        manageJenkinsPage.goToManageUsersPage();

        manageUsersPage.goToCreateUserPage();

        if(driver.getTitle().equals("Create User [Jenkins]")){
            task1.log(LogStatus.INFO, "Successfully navigated to the create user screen");
        }
        Assert.assertEquals("Create User [Jenkins]",driver.getTitle());

    }

    @When("^the User details are entered on the Create UserScreen$")
    public void the_User_details_are_entered_on_the_Create_UserScreen() throws Throwable {

        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);
        task1.log(LogStatus.INFO,"Entering a test case account created by me");
        createUserPage.enterUsername(Constants.testUsername);
        createUserPage.enterPassword1(Constants.testPassword);
        createUserPage.enterPassword2(Constants.testPassword);
        createUserPage.enterFullname(Constants.testFullname);
    }

    @When("^the details are submitted on the Create UserScreen$")
    public void the_details_are_submitted_on_the_Create_UserScreen() throws Throwable {

        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);
        task1.log(LogStatus.INFO,"Submitting the account for creation");
        createUserPage.clickCreateUser();
    }

    @Then("^the Username should be visible on the UsersScreen$")
    public void the_Username_should_be_visible_on_the_UsersScreen() throws Throwable {

        ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);

        Assert.assertEquals(Constants.testUsername,manageUsersPage.waitForSpecificUser(driver,Constants.testUsername).getText() );
        if(Constants.testUsername.equals(manageUsersPage.findSpecificUser(driver, Constants.testUsername))){
            task1.log(LogStatus.PASS, "The test case account submitted by ME was successfully accepted");
        }
        else {
            task1.log(LogStatus.FAIL,"The test case account created by ME was NOT accepted");
        }


    }

    @When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, and \"([^\"]*)\" Fullname are entered on the Create UserScreen$")
    public void the_User_details_username_password_confirm_Password_and_Fullname_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);
        task1.log(LogStatus.INFO,"Entering in username: "+arg1+" and password: "+arg2+" through Cucumber parameterisation");
        createUserPage.enterUsername(arg1);
        createUserPage.enterPassword1(arg2);
        createUserPage.enterPassword2(arg3);
        createUserPage.enterFullname(arg4);

    }

    @Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
    public void the_username_should_be_visible_on_the_UsersScreen(String arg1) throws Throwable {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);
        Assert.assertEquals(arg1,manageUsersPage.waitForSpecificUser(driver,arg1).getText());
        if (arg1.equals(manageUsersPage.waitForSpecificUser(driver,arg1).getText())){
            task1.log(LogStatus.PASS, "Cucumber parameterisation account "+arg1+" was accepted");
        }
        else{
            task1.log(LogStatus.FAIL,"Cucumber parameterisation account "+arg1+" was NOT accepted");
        }
        Constants.task1Pass = true;
    }

/////////////////////////////////////////////////TASK2///////////////////////////////////////////////////////////////////
    @Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
    public void the_username_is_visible_on_the_UsersScreen(String arg1) throws Throwable {
        JenkinsLoginPage jenkinsLoginPage = PageFactory.initElements(driver, JenkinsLoginPage.class);
        JenkinsHomepage jenkinsHomepage = PageFactory.initElements(driver,JenkinsHomepage.class);
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);

        //logging into jenkins website with admin
        driver.get(Constants.jenkinsUrl);
        //task1.log(LogStatus.INFO, "Navigating to page with title"+driver.getTitle());
        //task1.log(LogStatus.INFO,"Entering in admin login details");
        jenkinsLoginPage.enterJenkinsUsername(Constants.jenkinsUsername);
        jenkinsLoginPage.enterJenkinsPassword(Constants.jenkinsPassword);
        jenkinsLoginPage.clickJenkinsSubmit();

        jenkinsHomepage.goToManageAccountsPage();

        manageJenkinsPage.goToManageUsersPage();
        task2.log(LogStatus.INFO,"Navigated to the users page");
        Assert.assertEquals(arg1,manageUsersPage.waitForSpecificUser(driver,arg1).getText());
        if(arg1.equals( manageUsersPage.waitForSpecificUser(driver,arg1).getText())){
            task2.log(LogStatus.PASS, "The expected username was visible on the users screen");
        }
        else {
            task2.log(LogStatus.FAIL, "The expected username was NOT visible on the users screen");
        }
    }

    @When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
    public void the_username_is_clicked_on_the_UserScreen(String arg1) throws Throwable {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);
        manageUsersPage.waitForSpecificUser(driver,arg1).click();
        task2.log(LogStatus.INFO, "Clicking on the chosen username");

    }

    @Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
    public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1) throws Throwable {
        UserProfilePage userProfilePage = PageFactory.initElements(driver, UserProfilePage.class);
        Assert.assertEquals("Jenkins User ID: "+arg1,userProfilePage.getUserProfileId().getText());
        if(("Jenkins User ID: "+arg1).equals(userProfilePage.getUserProfileId().getText())){
            task2.log(LogStatus.PASS,"The expected username: "+arg1+" was found on the Profile Screens page");
        }
        else {
            task2.log(LogStatus.FAIL, "The expected username: "+arg1+" was NOT found on the Profile Screens page");
        }
        Constants.task2Pass = true;
    }
/////////////////////////////////////////////////TASK3////////////////////////////////////////////////////////////////////////
    @Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
    public void the_Username_s_profile_page_has_been_loaded(String arg1) throws Throwable {
        JenkinsLoginPage jenkinsLoginPage = PageFactory.initElements(driver, JenkinsLoginPage.class);
        JenkinsHomepage jenkinsHomepage = PageFactory.initElements(driver,JenkinsHomepage.class);
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver,ManageUsersPage.class);

        //logging into jenkins website with admin
        driver.get(Constants.jenkinsUrl);
        //task3.log(LogStatus.INFO, "Navigating to page with title"+driver.getTitle());
        //task3.log(LogStatus.INFO,"Entering in admin login details");

        jenkinsLoginPage.enterJenkinsUsername(Constants.jenkinsUsername);
        jenkinsLoginPage.enterJenkinsPassword(Constants.jenkinsPassword);
        jenkinsLoginPage.clickJenkinsSubmit();

        jenkinsHomepage.goToManageAccountsPage();

        manageJenkinsPage.goToManageUsersPage();
        manageUsersPage.findSpecificUser(driver, arg1).click();
        task3.log(LogStatus.INFO,"Loading the profile page of: "+arg1);
    }

    @Given("^the configure button has been clicked on the profile page$")
    public void the_configure_button_has_been_clicked_on_the_profile_page() throws Throwable {
        UserProfilePage userProfilePage = PageFactory.initElements(driver, UserProfilePage.class);
        userProfilePage.clickConfigureUserButton();
        task3.log(LogStatus.INFO,"Clicking the configure button of the above profile page");
    }

    @When("^I change the old FullName on the Configure Page to a new FullName \"([^\"]*)\"$")
    public void i_change_the_old_FullName_on_the_Configure_Page_to_a_new_FullName(String arg1) throws Throwable {
        ConfigureUserPage configureUserPage = PageFactory.initElements(driver, ConfigureUserPage.class);
        configureUserPage.changeFullname(arg1);
        task3.log(LogStatus.INFO, "Changing the fullname of the the above user to the new name of: "+arg1);
    }

    @When("^I save the changes to the Configure Page$")
    public void i_save_the_changes_to_the_Configure_Page() throws Throwable {
        ConfigureUserPage configureUserPage = PageFactory.initElements(driver, ConfigureUserPage.class);
        configureUserPage.clickSaveChanges();
        task3.log(LogStatus.INFO,"Saving the new fullname");
    }

    @Then("^the Configure Page should show the NewFullName \"([^\"]*)\"$")
    public void the_Configure_Page_should_show_the_NewFullName(String arg1) throws Throwable {
        UserProfilePage userProfilePage = PageFactory.initElements(driver, UserProfilePage.class);
        Assert.assertEquals(arg1, userProfilePage.getUserFullname().getText());
        if(arg1.equals(userProfilePage.getUserFullname().getText())){
            task3.log(LogStatus.PASS,"The user "+arg1+" has now had their fullname updated successfully");
        }
        else {
            task3.log(LogStatus.FAIL, "The user "+arg1+" has NOT had their fullname updated successfully");
        }
        Constants.task3Pass = true;
    }

}
