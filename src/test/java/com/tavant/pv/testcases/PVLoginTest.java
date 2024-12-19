/*
 * Copyright (c) 2024 Sunil
 * Automation Framework Selenium
 */

package com.tavant.pv.testcases;

import com.tavant.common.BaseTest;

import org.testng.SkipException;
import org.testng.annotations.Test;





public class PVLoginTest extends BaseTest {

    @Test(groups = { "Login","PVLogin", "Smoke", "Regression","Invalid" })
	  public void TC_PVLoginCase1() {
    	throw new SkipException("Skipping this test case.");
    	//getLoginPVPage().signIn();
    	
	  
	  }
    
    
    public class PVLoginTest2 extends BaseTest {

        @Test(groups = { "Login","PVLogin", "Smoke", "Regression","Invalid", "WIP", "One" })
    	  public void TC_PVLoginCase2() {
        	getLoginPVPage().signIn();
    	  
    	  }
    }
    
    public class PVLoginTest3 extends BaseTest {

        @Test(groups = { "Login","PVLogin", "Smoke", "Regression","Valid", "WIP","One" })
    	  public void TC_PVLoginCase3() {
        	getLoginPVPage().signIn();
    	  
    	  }
    }
	 
    
	 
	

}
