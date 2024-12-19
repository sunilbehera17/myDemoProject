package com.tavant.mpv.page;

import com.tavant.common.CommonPageMPV;
import com.tavant.constants.FrameworkConstants;
import com.tavant.helpers.ExcelHelpers;
import com.tavant.helpers.PropertiesHelpers;

import static com.tavant.keywords.WebUI.*;

import org.openqa.selenium.By;

public class LoginMPVPage extends CommonPageMPV {

	ExcelHelpers excel = new ExcelHelpers();
	
	private By humgurgerMenu = By.xpath("//*[@data-qa='homePage-open-mpv-menu']");
    private By closeAdvertisementPopup = By.xpath("//button[@data-key='website-popup']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By buttonCopyAdminAcc = By.xpath("//button[normalize-space()='Copy']");
    private By buttonSubmitLogin = By.xpath("//button[normalize-space()='Log In']");
    private By titleLoginPage = By.xpath("//*[normalize-space() = 'Log in or sign up to create an account to access your tickets and account.']");
    private By messageRequiredEmail = By.xpath("//*[contains(text(),'Enter Email Address')]");
    private By inputEmail = By.xpath("//*[@data-qa='loginPage-input-email']");
    private By inputPassword = By.xpath("//*[@data-qa='loginPage-input-password']");
    private By messageAccDoesNotExist = By.xpath("//*[normalize-space() = 'Your username or password is incorrect.']");
    private By messageRequiredPassword = By.xpath("//*[normalize-space() = 'Enter Password']");
    private By cookiesOkButton = By.xpath("//*[@class='cc-btn cc-dismiss']");
  
    public void clickOkCookies() {
        clickElement("Close Cookies",cookiesOkButton);
    }

    public void openLoginPage() {
    	
        clickOkCookies();
        clickElement("Hamburger Menu",humgurgerMenu);
        clickElement("Hamburger Login",buttonLogin);
        waitForPageLoaded();
        
        verifyElementVisible(titleLoginPage, "Log in or sign up to create an account to access your tickets and account.");
    }

  

    public void loginFailWithEmailNull() {
    	excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "Login");
        openLoginPage();
        clickElement("Login Button",buttonSubmitLogin);
        waitForPageLoaded();
        verifyEquals(getTextElement(messageRequiredEmail).trim(), "Enter Email Address", "");
    }

    public void loginFailWithEmailDoesNotExist() {
    	excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "Login");
        openLoginPage();
        setText("Enter email",inputEmail, excel.getCellData("loginFailWithEmailDoesNotExist", "email"));
        setText("Enter Password",inputPassword, excel.getCellData("loginFailWithEmailDoesNotExist", "password"));
        clickElement("Login Button",buttonSubmitLogin);
        waitForPageLoaded();
        verifyElementVisible(messageAccDoesNotExist, "Your username or password is incorrect.");
    }

    public void loginFailWithNullPassword() {
    	excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "Login");
        openLoginPage();
        setText("Enter email",inputEmail, excel.getCellData("loginFailWithNullPassword", "email"));
        clickElement("Login Button",buttonSubmitLogin);
        waitForPageLoaded();
        verifyElementPresent(messageRequiredPassword, "No warning password input");
    }

    public void loginFailWithIncorrectPassword() {
    	excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "Login");
        openLoginPage();
        setText("Enter email",inputEmail, excel.getCellData("loginFailWithIncorrectPassword", "email"));
        
        clearText("Clear the Password Field",inputPassword);
        setText("Enter Password",inputPassword, excel.getCellData("loginFailWithIncorrectPassword", "password"));
        
        clickElement("Login Button",buttonSubmitLogin);
        waitForPageLoaded();
        verifyElementVisible(messageAccDoesNotExist, "Your username or password is incorrect.");
    }

    public void loginSuccessWithCustomerAccount() {
    	excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "Login");
        openLoginPage();
        setText("Enter email",inputEmail, excel.getCellData("loginSuccessWithCustomerAccount", "email"));
        clearText("Clear the Password Field",inputPassword);
        setText("Enter Password",inputPassword, excel.getCellData("loginSuccessWithCustomerAccount", "password"));
        clickElement("Login Button",buttonSubmitLogin);
        waitForPageLoaded();
        waitForElementVisible(DashboardPage.titleDashboard);
        verifyElementVisible(DashboardPage.titleDashboard, "Hi, Tesdata Testdat");
    }

    
}


