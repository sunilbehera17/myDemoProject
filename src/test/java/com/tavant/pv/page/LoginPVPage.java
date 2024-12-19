package com.tavant.pv.page;


import com.tavant.common.CommonPagePV;
import com.tavant.constants.FrameworkConstants;
import com.tavant.helpers.ExcelHelpers;
import com.tavant.models.SignInModel;
import com.tavant.utils.DecodeUtils;

import org.openqa.selenium.By;

import static com.tavant.keywords.WebUI.*;
import java.util.Hashtable;

public class LoginPVPage extends CommonPagePV {

	private String pageTitle = "auto2 | Tickets.com Provenue® - Login";
	public By inputEmail = By.xpath("//*[@id='username']"); 
	public By inputPassword = By.xpath("//*[@id='password']");
	public By buttonSignIn =By.xpath("//button[normalize-space()='Login']");
	public By pvLogo=By.xpath("//*[@id='mainHeading']//a[@title='Home']/div");

	
    ExcelHelpers excelHelpers = new ExcelHelpers();
    
    


	public void signIn() {
		
		excelHelpers.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_PV, "PVLogin");
        openWebsite(FrameworkConstants.URL_PV);
        verifyEquals(getPageTitle(), pageTitle, "The title of sign in page not match.");
        clearText("Clear the email field",inputEmail);
        clearText("Clear the password",inputPassword);
        setText("Enter email",inputEmail, excelHelpers.getCellData("testSignInData", "EMAIL"));
        setText("Enter password",inputPassword, excelHelpers.getCellData("testSignInData", "PASSWORD"));
        clickElement("Login Button",buttonSignIn);
        waitForPageLoaded();
        verifyElementVisible(pvLogo, "Logo is not visible. ");

        
    }

}
