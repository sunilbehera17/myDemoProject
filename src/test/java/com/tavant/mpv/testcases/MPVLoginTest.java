package com.tavant.mpv.testcases;

import com.tavant.common.BaseTest;
import com.tavant.constants.FrameworkConstants;


import static com.tavant.keywords.WebUI.openWebsite;


import org.testng.annotations.Test;


public class MPVLoginTest extends BaseTest {


	
	  @Test(groups = { "Login","MPVLogin", "Smoke", "Regression","Invalid" }) 
	  
	  public void TC_LoginFailWithEmailNull()
	  
	  { 
		  
		  openWebsite(FrameworkConstants.URL_CMS_USER);
		  getLoginMPVPage().loginFailWithEmailNull(); 
	
	  }
	
	 
	
	    @Test(groups = { "Login","MPVLogin", "Smoke", "Regression","Invalid"  })
	    
    public void TC_LoginFailWithEmailDoesNotExist() {
    	openWebsite(FrameworkConstants.URL_CMS_USER);
        getLoginMPVPage().loginFailWithEmailDoesNotExist();
    }

	     @Test(groups = {"Login", "MPVLogin", "Smoke", "Regression","Invalid"  })
    public void TC_LoginFailWithNullPassword() {
    	openWebsite(FrameworkConstants.URL_CMS_USER);
        getLoginMPVPage().loginFailWithNullPassword();
    }

    @Test(groups = { "Login","MPVLogin", "Smoke", "Regression","Invalid"  })
    public void TC_LoginFailWithIncorrectPassword() {
    	openWebsite(FrameworkConstants.URL_CMS_USER);
        getLoginMPVPage().loginFailWithIncorrectPassword();
    }

    @Test(groups = { "Login","MPVLogin", "Regression","Valid","WIP"  })
    public void TC_LoginSuccessWithCustomerAccount() {
    	openWebsite(FrameworkConstants.URL_CMS_USER);
        getLoginMPVPage().loginSuccessWithCustomerAccount();
    }


}
