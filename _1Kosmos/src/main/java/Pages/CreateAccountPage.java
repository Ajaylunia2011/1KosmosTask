package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateAccountPage {

    WebDriver driver;


    @FindBy(how = How.XPATH, using = "//input[contains(@id,'undefined-undefined-Firstname')]")
    private WebElement firstName;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'undefined-undefined-Lastname')]")
    private WebElement lastName;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'undefined-undefined-Emailaddress')]")
    private WebElement emailId;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'sername')]")
    private WebElement parentUseerName;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'undefined-undefined-ValidateCaptcha')]")
    private WebElement captcha;

    @FindBy(how = How.XPATH, using = "//input[contains(@id,'assword')]")
    private List<WebElement> parentPassword;

    public CreateAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void enterFirstName(String name){
        firstName.sendKeys(name);
    }

    public void enterLastName(String name){
        lastName.sendKeys(name);
    }

    public void enterEmailId(String email){
        emailId.sendKeys(email);
    }

    public void enterParentUserName(String name){
        parentUseerName.sendKeys(name);
    }

    public void enterParentPassword(String passwordLevel, String password){
        if(passwordLevel.equals("ParentPassword"))
            parentPassword.get(0).sendKeys(password);
        else
            parentPassword.get(1).sendKeys(password);
    }

    public void enterCaptcha(String captchaText){
        captcha.sendKeys(captchaText);
    }
}
