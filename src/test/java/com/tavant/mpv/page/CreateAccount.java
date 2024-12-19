package com.tavant.mpv.page;

import org.openqa.selenium.By;

import com.tavant.common.CommonPageMPV;
import com.tavant.constants.FrameworkConstants;
import com.tavant.helpers.ExcelHelpers;
import com.tavant.report.ExtentReportManager;
import com.tavant.utils.LogUtils;

import static com.tavant.keywords.WebUI.*;

public class CreateAccount extends CommonPageMPV {

    private By sCreateAccountButton = By.name("SignUp");
    private By sFirstNameFieldInCreateAccountPage = By.xpath("//input[@id='firstName']");
    private By sFirstNameLabel = By.xpath("//input[@id='firstName']/preceding-sibling::label");
    private By sLastNameFieldInCreateAccountPage = By.id("lastName");
    private By sLastNameLabel = By.xpath("//input[@id='lastName']/preceding-sibling::label");
    private By sEmailFieldInCreateAccountPage = By.id("email");
    private By sEmailLabel = By.xpath("//input[@id='email']/preceding-sibling::label");
    private By sDOBMonthFieldInCreateAccountPage = By.xpath("//div[@class='day-month-picker-item ng-scope month']//select");
    private By sDOBDayFieldInCreateAccountPage = By.xpath("//div[@class='day-month-picker-item ng-scope day']//select");
    private By sDOBYearFieldInCreateAccountPage = By.xpath("//div[@class='day-month-picker-item ng-scope year']//select");
    private By sGenderFieldInCreateAccountPage = By.id("gender");
    private By sPasswordFieldInCreateAccountPage = By.xpath("//*[@id='password']");
    private By sPasswordLabel = By.xpath("//input[@id='password']/preceding-sibling::label");
    private By sConfirmPasswordFieldInCreateAccountPage = By.id("confirmPassword");
    private By sConfirmPasswordLabel = By.xpath("//input[@id='confirmPassword']/preceding-sibling::label");
    private By sAgreeTermsCheckBoxInCreateAccountPage = By.xpath("//span[@class='accept-terms']/../../input");
    private By sSignUpButtonInCreateAccountPage = By.xpath("//*[@data-qa='signupPage-btn-signup']");
    private By sAccountCreatedWelcomeMsg1 = By.xpath("//p[@class='user-welcome ng-scope'][1]");
    private By sAccountCreatedWelcomeMsg2 = By.xpath("//p[@class='user-welcome user-welcome__username ng-scope']");
    private By sContinueButtonInAccountCreatedWelcomePage = By.xpath("//button[contains(text(),'Continue')]");
    private By sDuplicateEmailErrorPopUp = By.xpath("//span[@class='ng-scope ng-binding']");
    private By sPasswordHint = By.xpath("//div[@class='popover-content']/child::span[@id='password-hint']");
    private By cacheOkButton = By.xpath("//button[text()='OK']");
    
    

    private ExcelHelpers excel = new ExcelHelpers();

    public void fnClickCreateAccountButton() throws Exception {
        clickElement("Create Account Button", sCreateAccountButton);
    }

    public String fnCreateAccount() throws Exception {
        String sFirstName = CommonPageMPV.generateUniqueUsername();
        String sLastName = "MPV";
        String sEmail = sFirstName + "@email.com";
        String sDOB = "01-23-1979";
        String sGender = "MALE";
        String sPassword = "T1ckets1";
        String sConfirmPassword = "T1ckets1";
        boolean bAgreeTerms = true;
        fnCreateAccount(sFirstName, sLastName, sEmail, sDOB, sGender, sPassword, sConfirmPassword, bAgreeTerms);
        return sEmail;
    }

    public void fnCreateAccount(String sFirstName, String sLastName, String sEmail, String sDOB, String sGender,
                                 String sPassword, String sConfirmPassword, boolean bAgreeTerms) throws Exception {
        fnClickCreateAccountButton();
        

        setText("Enter First name",sFirstNameFieldInCreateAccountPage, sFirstName);
        setText("Enter Second name",sLastNameFieldInCreateAccountPage, sLastName);
        setText("Enter email",sEmailFieldInCreateAccountPage, sEmail);
        setText("Enter Password name",sPasswordFieldInCreateAccountPage, sPassword);
        clickElement("Agree to Terms Checkbox", sAgreeTermsCheckBoxInCreateAccountPage);
        waitForElementClickable("Wait for Sign Up Button",sSignUpButtonInCreateAccountPage);
        clickElement("Sign Up Button", sSignUpButtonInCreateAccountPage);
        
        
        if (!isElementVisible(sDuplicateEmailErrorPopUp, 5)) {
            fnVerifyAccountCreation();
            clickElement("Continue Button", sContinueButtonInAccountCreatedWelcomePage);
        }
    }

    public boolean fnVerifyAccountCreation() throws Exception {
        if (isElementVisible(sAccountCreatedWelcomeMsg1, 2)) {
            String sWelcomeMessage1 = getTextElement(sAccountCreatedWelcomeMsg1);
            String sWelcomeMessage2 = getTextElement(sAccountCreatedWelcomeMsg2);
            ExtentReportManager.pass(" Account created successfully for user " + sWelcomeMessage1.split(" ")[1] + " "
                    + sWelcomeMessage1.split(" ")[2] + " with username " + sWelcomeMessage2.split(" ")[3]);
            return true;
        } else {
            LogUtils.info("Account creation failed");
            return false;
        }
    }

    public void fnVerifyPasswordHintNotification(boolean bExpectedStatus) throws Exception {
        if (isElementVisible(sPasswordHint, 3)) {
            ExtentReportManager.pass(" User Able to see password hint notification");
        } else {
            LogUtils.info("User Not Able to see Password Hint Notification");
        }
    }

    public void fnDuplicateEmailValidation() throws Exception {
        if (isElementVisible(sDuplicateEmailErrorPopUp, 3000)) {
            ExtentReportManager.pass(" Account already exists error pop up is displayed\n" + getTextElement(sDuplicateEmailErrorPopUp));
            CommonPageMPV.fnClosePopUpGeneric();
        } else {
            LogUtils.info("Account already exists error pop up is not displayed");
        }
    }

    public void fnVerifyCreateAccountPageLoad() throws Exception {
        if (isElementVisible(sCreateAccountButton, 5)) {
            ExtentReportManager.pass(" Create Account page is displayed as expected");
        } else {
            LogUtils.info("Create Account page is not displayed");
        }
    }

    public void fnUIcheck() throws Exception {
    	excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "MLBSignUpUI");
    	
        String sFirstNameLabelExpected = excel.getCellData("FirstNameLabelExpected", "Value");
        String sFirstNamePlaceholderExpected = excel.getCellData("FirstNamePlaceholderExpected", "Value");
        String sLastNameLabelExpected = excel.getCellData("LastNameLabelExpected", "Value");
        String sLastNamePlaceholderExpected = excel.getCellData("LastNamePlaceholderExpected", "Value");
        String sEmailLabelExpected = excel.getCellData("EmailLabelExpected", "Value");
        String sEmailPlaceholderExpected = excel.getCellData("EmailPlaceholderExpected", "Value");
        String sPasswordLabelExpected = excel.getCellData("PasswordLabelExpected", "Value");
        String sPasswordPlaceholderExpected = excel.getCellData("PasswordPlaceholderExpected", "Value");
        String sConfirmPasswordLabelExpected = excel.getCellData("ConfirmPasswordLabelExpected", "Value");
        String sConfirmPasswordPlaceholderExpected = excel.getCellData("ConfirmPasswordPlaceholderExpected", "Value");

        validateText(sFirstNameLabel, sFirstNameLabelExpected);
        //validateText(getProperty(sFirstNameFieldInCreateAccountPage, "placeholder"), sFirstNamePlaceholderExpected);
        validateText(sLastNameLabel, sLastNameLabelExpected);
        //validateText(getProperty(sLastNameFieldInCreateAccountPage, "placeholder"), sLastNamePlaceholderExpected);
        validateText(sEmailLabel, sEmailLabelExpected);
        //validateText(getProperty(sEmailFieldInCreateAccountPage, "placeholder"), sEmailPlaceholderExpected);
        validateText(sPasswordLabel, sPasswordLabelExpected);
        //validateText(getProperty(sPasswordFieldInCreateAccountPage, "placeholder"), sPasswordPlaceholderExpected);

        validateVisibility(sAgreeTermsCheckBoxInCreateAccountPage, "Terms and conditions check box");
        validateVisibility(sSignUpButtonInCreateAccountPage, "SignUp button");
    }

    private void validateText(By element, String expectedText) {
        if (getTextElement(element).equals(expectedText)) {
            ExtentReportManager.pass(" Expected text is displayed: " + expectedText);
        } else {
            LogUtils.info("Expected: " + expectedText + "\nActual: " + getTextElement(element));
        }
    }

    private void validateVisibility(By element, String elementName) {
        if (isElementVisible(element,2)) {
        	ExtentReportManager.pass(elementName + " is displayed");
        } else {
        	LogUtils.info(elementName + " is not displayed");
        }
    }

}
