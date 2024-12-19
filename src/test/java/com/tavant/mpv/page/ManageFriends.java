
package com.tavant.mpv.page;

import org.openqa.selenium.By;

import com.tavant.common.CommonPageMPV;
import com.tavant.constants.FrameworkConstants;
import com.tavant.helpers.ExcelHelpers;
import static com.tavant.keywords.WebUI.*;


public class ManageFriends extends CommonPageMPV{
	ExcelHelpers excel = new ExcelHelpers();

	private By sBtnAddFriend = By.xpath("//button[text()='Add Friend']");
	private By sFirstNameInput = By.xpath("//input[@id='firstName']");
	private By sLastNameInput = By.xpath("//input[@id='lastName']");
	private By sEmailInput = By.xpath("//label[text()='Email']/following-sibling::input");
	private By sConfirmEmailInput = By.xpath("//label[text()='Confirm Email']/following-sibling::input");
	private By sBtnCreateFriend = By.xpath("//button[normalize-space() ='Create Friend']");
	private By sDeleteIcon = By.xpath("//span[@ng-click='vm.deleteBuddy(buddy)']");
	private By sFirstFriendNameinManageFriends = By.xpath("(//div[@class='first-line ng-binding'])[1]");

	private By sDOBMonthFieldInCreateAccountPage = By.xpath("//div[@class='day-month-picker-item ng-scope month']//select");
	private By sDOBDayFieldInCreateAccountPage = By.xpath("//div[@class='day-month-picker-item ng-scope day']//select");
	private By sDOBYearFieldInCreateAccountPage = By.xpath("//div[@class='day-month-picker-item ng-scope year']//select");
	private By sGenderFieldInCreateAccountPage = By.xpath("//select[@id='gender']");

	
	
	
	public void fnClickAddNewFriend() throws Exception {
	    clickElement("Add New Friend Button", sBtnAddFriend);
	    waitForPageLoaded(); // Optional, depends on whether there's a page transition after clicking
	}
	
	
	
	public void fnFillNewFriendDetails(String sFriendList) throws Exception {
		
        excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "ManageFriends");
        
        
	        switch (sFriendList.toUpperCase()) {
	            case "FIRSTFRIEND":
	                setText("Enter First name",sFirstNameInput, excel.getCellData("FirstFriendFirstName","Value"));
	                setText("Enter Last name",sLastNameInput, excel.getCellData("FirstFriendLastName","Value"));
	                setText("Enter email ",sEmailInput, excel.getCellData("FirstFriendEmail","Value"));
	                waitForPageLoaded();
	                clickElement("Confirm Email Input", sConfirmEmailInput);
	                setText("Enter Confirm Password name",sConfirmEmailInput, excel.getCellData("FirstFriendConfirmEmail","Value"));
	                break;
	            case "SECONDFRIEND":
	            	setText("Enter First name",sFirstNameInput, excel.getCellData("FirstFriendFirstName","Value"));
	                setText("Enter Last name",sLastNameInput, excel.getCellData("FirstFriendLastName","Value"));
	                setText("Enter email ",sEmailInput, excel.getCellData("FirstFriendEmail","Value"));
	                setText("Enter confirm password",sConfirmEmailInput, excel.getCellData("SecondFriendConfirmEmail","Value"));
	                break;
	            default:
	                break;
	        }

	        // Click on Create Friend button
	        sleep(2);
	        clickElement("Create Friend Button", sBtnCreateFriend);
	    
	}
	
	public void fnDeleteFriend() throws Exception {
		
        excel.setExcelFile(FrameworkConstants.EXCEL_TESTDATA_MPV, "ManageFriends");
		
	        if (getTextElement(sFirstFriendNameinManageFriends).equalsIgnoreCase(
	        		excel.getCellData("FirstFriendNameinManageFriendsExpected","Value"))) 
	        
	        {
	            clickElement("Delete Friend Icon", sDeleteIcon);

	            
	                
	            
	        }
	    


	
}
}