package com.tavant.common;

import static com.tavant.keywords.WebUI.*;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import com.tavant.mpv.page.CreateAccount;
import com.tavant.mpv.page.LoginMPVPage;
import com.tavant.mpv.page.ManageFriends;
import com.tavant.mpv.page.Menu;

public class CommonPageMPV extends CommonPagePV{
	
    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By messageNotify = By.xpath("//span[@data-notify='message']");
    private By buttonEdit = By.xpath("(//a[@title='Edit'])[1]");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By inputSearch = By.xpath("//input[@id='search']");
    public By avatarProfile = By.xpath("//span[contains(@class,'avatar avatar-sm')]");
    public By buttonCookies = By.xpath("//button[normalize-space()='Ok. I Understood']");
    public static By sPopUpCloseButton = By.xpath("//span[@class='icon-remove']");
    public By sPopUpCloseButton1 = By.xpath("//button[@class='icon-remove']");

    private LoginMPVPage loginPage;
    private ManageFriends manageFriendPage;
    private CreateAccount createAccountPage;
    private Menu menuPage;

    public Menu getMenuPage() {
        if (menuPage == null) {
        	menuPage = new Menu();
        }
        return menuPage;
    }
    public LoginMPVPage getLoginMPVPage() {
        if (loginPage == null) {
            loginPage = new LoginMPVPage();
        }
        return loginPage;
    }

    

    public ManageFriends getManageFriends() {
        if (manageFriendPage == null) {
        	manageFriendPage = new ManageFriends();
        }
        return manageFriendPage;
    }
    
    public CreateAccount getCreateAccount() {
        if (createAccountPage == null) {
        	createAccountPage = new CreateAccount();
        }
        return createAccountPage;
    }
    
    

    public void enterDataOnSearchDataTable(String value) {
        setText(inputSearch, value, Keys.ENTER);
    }

    public void clickEditButton() {
        clickElement("Edit Button",buttonEdit);
    }
    public void clickSaveButton() {
        clickElement("Save Button",buttonSave);
    }

    public String getMessageNotify() {
        return getTextElement(messageNotify);
    }

    public CommonPageMPV clickMenuProducts() {
        clickElement("Menu Button",menuProducts);
        return this;
    }


    public static String generateUniqueUsername() {
        // Get the current timestamp in milliseconds
        long timestamp = System.currentTimeMillis();
        
        // Generate a random number between 10 and 99
        Random random = new Random();
        int randomNum = 10 + random.nextInt(90); // Random number between 10 and 99
        
        // Construct the username using "MPV" + timestamp + random number
        String username = "MPV" + timestamp + "_" + randomNum;
        
        return username;
    }
    
    
    

    
    public static void fnClosePopUpGeneric() throws Exception {

    	//if (waitForElementVisible(sPopUpCloseButton, 10000) || waitForElementVisible(sPopUpCloseButton1, 10000)) {
            
            // Sleep for a small delay to allow the pop-up to load fully (using sleep method)
            sleep(5000);
            
         
                // If tool is Selenium, use JavaScript to click the close button
                getJsExecutor().executeScript("arguments[0].click();", getWebElement(sPopUpCloseButton));
           // } 
        }
    
    

}
 
