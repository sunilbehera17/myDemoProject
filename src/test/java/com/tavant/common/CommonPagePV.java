package com.tavant.common;

import com.tavant.keywords.WebUI;

import com.tavant.pv.page.LoginPVPage;

import org.openqa.selenium.By;

public class CommonPagePV {

	private LoginPVPage loginPVPage;

    public By dropdownAccount = By.xpath("//a[@id='user-dropdown']//span[2]");
    public By buttonSignOut = By.xpath("//a[normalize-space()='Sign Out']");


    public LoginPVPage signOut() {
        WebUI.clickElement("dropdown Button",dropdownAccount);
        WebUI.clickElement("Signout Button",buttonSignOut);
        return new LoginPVPage();
    }

    public LoginPVPage getLoginPVPage() {
        if (loginPVPage == null) {
        	loginPVPage = new LoginPVPage();
        }
        return loginPVPage;
    }




	/*
	 * public LoginMPVPage getLoginMPVPage() { if (loginPage == null) { loginPage =
	 * new LoginMPVPage(); } return loginPage; }
	 */



}
