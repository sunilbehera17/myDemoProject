package com.tavant.mpv.testcases;

import static com.tavant.keywords.WebUI.openWebsite;

import org.testng.annotations.Test;

import com.tavant.common.BaseTest;
import com.tavant.constants.FrameworkConstants;


public class C156889_MLBSignUp extends BaseTest {

	@Test(groups = { "SignUp","Smoke","Invalid"  })
    public void TC_C156889_MLBSignUp() throws Exception {	
		openWebsite(FrameworkConstants.URL_CMS_USER);
		  getLoginMPVPage().openLoginPage(); 
		  getCreateAccount().fnCreateAccount();
	    //client.createAccount.fnUIcheck();
		  getMenuPage().fnSelectMenuItem("LOGOUT");
	}
	
	public class C156889_MLBSignUp2 extends BaseTest {

		@Test(groups = { "SignUp","Smoke","Valid"  })
	    public void TC_C156889_MLBSignUp() throws Exception {	
			openWebsite(FrameworkConstants.URL_CMS_USER);
			  getLoginMPVPage().openLoginPage(); 
			  getCreateAccount().fnCreateAccount();
		    //client.createAccount.fnUIcheck();
			  getMenuPage().fnSelectMenuItem("LOGOUT");
		}
	
	
}
}