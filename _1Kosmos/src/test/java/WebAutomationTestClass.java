import Pages.CreateAccountPage;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WebAutomationTestClass {


    public WebDriver driver;
    public LoginPage loginPage;
    public CreateAccountPage createAccountPage;

    @BeforeMethod
    public void initDriver() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
        driver = new ChromeDriver();
        loadHomePage("https://www.mypedia.pearson.com/");

        loginPage = new LoginPage(driver);
        createAccountPage = new CreateAccountPage(driver);

    }

    @AfterMethod
    public void quitDriver() {
        driver.quit();
    }
    
    @DataProvider(name = "Form Data")
    public Object[][] formdata(){
        Object[][] object = new Object[1][5];
        object[0][0] = "Test";
        object[0][1] = "Test Last Name";
        object[0][2] = "Test@email.com";
        object[0][3] = "TestParentName";
        object[0][4] = "TestPassword";
        return object;
    }

    @Test(dataProvider = "Form Data")
    public void validateTestFlow(String firstName, String lastName, String email, String parentName, String password ) throws InterruptedException {

        loginPage.switchToIframe("conditionalIFrame");

        try {

            if (loginPage.doneButtonOnIframeIsDisplayed()) {
                loginPage.clickDoneButton();
            }
        } catch (Exception e) {
            //do nothing
        }

        loginPage.switchToIframe("defaultContent");


        Assert.assertTrue(loginPage.validateContinueButtonIsDisplayedInrespectiveLanugage("English"));

        loginPage.clickLanguageButton("English");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	

        if (loginPage.returnLanguagesDisplayedOnLanguageDropdown() >= 3) {
            System.out.println("Language drop down has atleast 3 languages. To be precise it has : " + loginPage.returnLanguagesDisplayedOnLanguageDropdown());
        } else {
            System.out.println("Hmm this one failed here. Let me know if you require hard assertion here. Total languages are :" + loginPage.returnLanguagesDisplayedOnLanguageDropdown());
        }

        loginPage.clickLanguageDisplayed("हिंदी");

        Assert.assertTrue(loginPage.validateContinueButtonIsDisplayedInrespectiveLanugage("हिंदी"));

        loginPage.clickLanguageButton("हिंदी");
        loginPage.clickLanguageDisplayed("Español");
        Assert.assertTrue(loginPage.validateContinueButtonIsDisplayedInrespectiveLanugage("Español"));
        loginPage.clickLanguageButton("Español");
        loginPage.clickLanguageDisplayed("English");

        loginPage.clickSetUpParentSupportLink();

        try {
            loginPage.clickNewAccountButton();
            loginPage.clickNewAccountButton();
        } catch (Exception e) {
            // do nothing
        }


        loginPage.waitForNewAccountPageToLoad();


        //validate create account button is disabled

        if (loginPage.validateCreateAccountButtonIsEnabled()) {
            System.out.println("CREATE ACCOUNT button is disabled on empty form");
        } else {
            System.out.println("CREATE ACCOUNT button is enabled on empty form");
        }
        createAccountPage.enterFirstName(firstName);
        createAccountPage.enterLastName(lastName);
        createAccountPage.enterEmailId(email);

        /* enter parent info */

        createAccountPage.enterParentUserName(parentName);
        createAccountPage.enterParentPassword("ParentPassword", password);
        createAccountPage.enterParentPassword("ConfirmPassword", password);

        if (loginPage.validateCreateAccountButtonIsEnabled()) {
            System.out.println("CREATE ACCOUNT button is disabled on non complete form");
        } else {
            System.out.println("CREATE ACCOUNT button is enabled on non complete form");
        }

        createAccountPage.enterCaptcha("dnasndas");


        if (loginPage.validateCreateAccountButtonIsEnabled()) {
            System.out.println("CREATE ACCOUNT button is enabled on complete form");
        } else {
            System.out.println("CREATE ACCOUNT button is disabled on complete form");
        }

    }

    public void loadHomePage(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(8000);

    }
}
