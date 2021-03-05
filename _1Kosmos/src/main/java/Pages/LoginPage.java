package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import sun.awt.windows.ThemeReader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    public WebDriver driver;

    @FindBy(how = How.ID, using = "contentIframe")
    private WebElement conditionalIFrame;

    @FindBy(how = How.XPATH, using = "//span[@id='spanDone']")
    private WebElement doneButtonOnIFrame;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Continue')]")
    private WebElement continueButton;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'अग्रसर रहें')]")
    private WebElement continueButtonInHindi;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Continuar')]")
    private WebElement continueButtonInSpanish;

    @FindBy(how = How.XPATH, using = "//div[@class='childSupportLink']/a")
    private WebElement setUpParentSupportLink;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'NEW ACCOUNT')]")
    private WebElement newAccountButton;

    @FindBy(how = How.XPATH, using = "//button//span[contains(text(),'CREATE ACCOUNT')]")
    private WebElement createAccountButton;

    @FindBy(how = How.XPATH, using = "//span[@role='menuitem']")
    private List<WebElement> languageFromDropDown;


    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void getHomePage(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(20000);
    }

    public void switchToIframe(String iFrameLocatorName) {

        if (iFrameLocatorName.equals("defaultContent")) {
            driver.switchTo().defaultContent();
        } else if (iFrameLocatorName.equals("conditionalIFrame")) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.switchTo().frame(conditionalIFrame);
        }
    }

    public boolean doneButtonOnIframeIsDisplayed(){
        if(doneButtonOnIFrame.isDisplayed())
            return true;
        else
            return false;
    }


    public void clickDoneButton(){
        doneButtonOnIFrame.click();
    }

    public boolean validateContinueButtonIsDisplayedInrespectiveLanugage(String language){

        WebElement buttonLocator;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        if(language.equals("English")){
            buttonLocator = continueButton;
        }
        else if(language.equals("हिंदी")){
            buttonLocator =continueButtonInHindi;
        }
        else{
            buttonLocator =continueButtonInSpanish;
        }

        if(buttonLocator.isDisplayed())
            return true;
        else
            return false;
    }

    public void clickLanguageButton(String language) throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(By.xpath("//div[@class='accountDetailsLangDropDown']//div[contains(text(),'"+language+"')]")).click();

    }

    public int returnLanguagesDisplayedOnLanguageDropdown(){
        return languageFromDropDown.size();
    }

    public void clickLanguageDisplayed(String language) throws InterruptedException {
        Thread.sleep(2000);

        if(language.equals("English")){
            languageFromDropDown.get(0).click();
        }
        else if(language.equals("हिंदी")){
            languageFromDropDown.get(1).click();
        }
        else if(language.equals("Español")){
            languageFromDropDown.get(2).click();
        }
    }

    public void clickSetUpParentSupportLink(){
        setUpParentSupportLink.click();
    }

    public void clickNewAccountButton(){
        newAccountButton.click();
    }

    public void waitForNewAccountPageToLoad() throws InterruptedException {
        Thread.sleep(20000);
    }

    public boolean validateCreateAccountButtonIsEnabled(){
        if(createAccountButton.isEnabled())
            return true;
        else
            return false;

    }




}
