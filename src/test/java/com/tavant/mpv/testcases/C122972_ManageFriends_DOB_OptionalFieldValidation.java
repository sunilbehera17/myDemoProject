package com.tavant.mpv.testcases;

import static com.tavant.keywords.WebUI.openWebsite;

import org.testng.annotations.Test;

import com.tavant.common.BaseTest;
import com.tavant.constants.FrameworkConstants;


public class C122972_ManageFriends_DOB_OptionalFieldValidation extends BaseTest{
	
	
	
	
	  @Test(groups = { "Regression","ManageFriend","Smoke","Valid" }) 
	  public void TC_ManageFriends_DOB_OptionalFieldValidation() throws Exception 
	  {
	  openWebsite(FrameworkConstants.URL_CMS_USER);
	  getLoginMPVPage().openLoginPage(); 
	  getCreateAccount().fnCreateAccount();
	  getMenuPage().fnSelectMenuItem("MANAGEFRIENDS");
	  getManageFriends().fnClickAddNewFriend();
	  //client.managefriends.fnVerifyDOBFieldsbyEnteringBoundryValues();
	  getManageFriends().fnFillNewFriendDetails("FirstFriend");
	  getMenuPage().fnSelectMenuItem("LOGOUT");
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  }
	 

	

}
