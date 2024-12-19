package com.tavant.mpv.page;

import org.openqa.selenium.By;

import com.tavant.common.CommonPageMPV;
import com.tavant.report.ExtentReportManager;
import com.tavant.utils.LogUtils;

import static com.tavant.keywords.WebUI.*;

public class Menu extends CommonPageMPV {

private By sHamburgerMenuIcon = By.xpath("//button[@class='mobile-menu icon-menu-hamburger font-lg']");
private By sHamurgetMenuCloseAlternate2 = By.xpath("(//button[contains(@class,'menu-hamburger') and contains(@aria-label,'Close')])");
private By sHamburgerMenuIconAlternate1 = By.xpath("//pv-menu-button/button");
private By sHamburgerMenuIconAlternate2 = By.xpath("//button[@id='open-mpv-menu']");
private By sHamburgerMenuIconAlternate3 = By.xpath("(//button[contains(@class,'menu-hamburger')])[1]");
private By sHamburgerMenuClose = By.xpath("//button[@aria-label='Close Navigation Menu']");
private By sHamburgerMenuCloseAlternate = By.xpath("//button[@aria-label='Close Main Menu']");
private By sMenuPrefix = By.xpath("//ul[@class='menu-list']");
private By sMenuPrefixExchange = By.xpath("//div[@class='mpv-menu open']");
private By sMenuItemLogin = By.xpath(sMenuPrefix + "//*[contains(text(),'Login')]");
private By sMenuItemLogout = By.xpath("//*[@data-qa='menu-btn-logout']");
private By sMenuItemLogoutSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'Logout')]");
private By sMenuItemBuyTicketsSecond = By.xpath("(" + sMenuPrefixExchange + "//*[contains(text(),'Buy Tickets')])[1]");
private By sMenuItemMyTicketsSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'My Tickets')]");
private By sMenuItemMyTicketsHomeSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'HOME')]");
private By sMenuItemMyAccountSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'My Account')]");
private By sMenuItemFaqSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'FAQ')]");
private By sMenuItemMyAccountPaymentInfoSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'Payment Info')]");
private By sMenuItemMyAccountUserProfileSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'User Profile')]");
private By sMenuItemMyAccountManageFriendsSecond = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'Manage Friends')]");
private By sMenuItemBuyTickets = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[1]");
private By sMenuItemBuyTicketsFirstOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[2]");
private By sMenuItemBuyTicketsSecondOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[3]");
private By sMenuItemBuyReplayTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'Buy Replay Tickets')]");
private By sMenuItemVouchers = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
private By sMenuItemMyTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'My Tickets')]");
private By sMenuItemMyAccount = By.xpath("//*[@data-qa='menu-btn-myAccount']");
private By sMenuItemFaq = By.xpath(sMenuPrefix + "//*[contains(text(),'FAQ')]");
private By sMenuItemDebug = By.xpath(sMenuPrefix + "//*[contains(text(),'DEBUG')]");
private By sMenuItemVoucher = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
private By sMenuItemMyAccountUserProfile = By.xpath(sMenuPrefix + "//*[contains(text(),'User Profile')]");
private By sMenuItemMyAccountPaymentInfo = By.xpath(sMenuPrefix + "//*[contains(text(),'Payment Info')]");
private By sMenuItemMyAccountManageFriends = By.xpath("//*[contains(text(),'Manage Friends')]");
private By sMenuItemShoppingCart = By.xpath(sMenuPrefix + "//*[contains(text(),'Shopping Cart')]");
private By sMenuItemUpgradePackageSeats = By.xpath(sMenuPrefix + "//*[contains(text(),'Upgrade Package Seats')]");
private By sMenuItemMyTicketsHome = By.xpath(sMenuPrefix + "//*[contains(text(),'HOME')]");
private By sMenuItemExchange = By.xpath(sMenuPrefix + "//*[contains(text(),'EXCHANGE')]");
private By sMenuItemExchange2 = By.xpath(sMenuPrefixExchange + "//*[contains(text(),'EXCHANGE')]");
private By sMenuItemRenew = By.xpath(sMenuPrefix + "//*[contains(text(),'Renew/Make Payments')]");
private By sSavedCard = By.xpath("//li[@ng-click='vm.selectAccount(paymentMethod)']");
private By sExpiryYr = By.xpath("//span[@class='payment-expiration ng-binding']");
private By sEditIcon = By.xpath("//a[@class='edit-link ng-scope']");
private By sCardHolderName = By.xpath("//div//input[@id='card-nameoncard']");
private By sExpiryYearSelect = By.xpath("id=expiry-yr");
private By sCountrySelect = By.xpath("id=card-country");
private By sStateSelect = By.xpath("id=card-state");
private By sPostalCodeInput = By.xpath("id=card-zip");
private By sUpdateCard = By.xpath("(//div[@ng-if='!vm.account.isNew']//button)[2]");
private By sRemoveCard = By.xpath("(//div[@ng-if='!vm.account.isNew']//button)[1]");
private By sUpdateCardMsg = By.xpath("//li[@class='ng-scope']//*[contains(text(),'The card has been updated.')]");
private By sDeleteCardMsg = By.xpath("//li[@class='ng-scope']//*[contains(text(),'The card has been removed.')]");
private By sRememberMeValue = By.xpath("//div//label//input[@capture='RememberMe_true']");
private By sMenuItemMyAccountChangePassword = By.xpath(sMenuPrefix + "//*[contains(text(),'Change Password')]");
private By sMenuItemMyTicketsLoyalty = By.xpath(sMenuPrefix + "//*[contains(text(),'Loyalty')]");
private By sMenuItemTermsAndConditions_Mobile = By.xpath(sMenuPrefix + "//*[contains(text(),'Terms & Conditions') and not (@target)]");
private By sMenuItemPrivacyPolicy_Mobile = By.xpath(sMenuPrefix + "//*[contains(text(),'Privacy Policy') and not (@target)]");
private By sMenuItemTermsOfUse_Mobile = By.xpath(sMenuPrefix + "//*[contains(text(),'Terms of Use') and not (@target)]");
private By sMenuItemViewWebVersion_Mobile = By.xpath(sMenuPrefix + "//*[contains(text(),'View Web Version') and not (@target)]");
private By sMenuItemCopyright2019_Mobile = By.xpath("//div[contains (@class,'mpv-mobile-only')]//*[contains(text(),'2024')]");
private By sMenuItemMyticketsBeforeUpgradeSeats = By.xpath(sMenuPrefix + "//*[contains(text(),'Upgrade Package Seats')]//preceding::button//*[contains(text(),'My Tickets')]");
private By sMenuItemVouchetsAfterUpgradeSeats = By.xpath(sMenuPrefix + "//*[contains(text(),'Upgrade Package Seats')]//following::button//*[contains(text(),'Vouchers')]");
private By sHamburgerMenu = By.xpath("//button[@class='mpv-theme-header-secondary icon icon-menu-hamburger font-lg']");
private By sMyTickets = By.xpath("(//button[@class='clear-button font-primary-semibold font-sm hassubmenu'])[3]");
private By sExchanges = By.xpath("(//button[@class='clear-button font-primary-semibold font-sm submenuitem'])[5]");
private By sSignUpErr1 = By.xpath("//p[text()='Please complete all required fields.']");
private By sSignUpErr2 = By.xpath("(//p[text()='Password does not match'])[2]");








    public boolean fnVerifyHamburgerMenuIcon() throws Exception {
        if (waitForElement(sHamburgerMenuIconAlternate2, 2000)
                || waitForElement(sHamburgerMenuIconAlternate3, 2000)) {
            return true;
        } else {
            LogUtils.info("Hamburger menu icon is NOT found");
            return false;
        }
    }

    public boolean fnClickHamburgerMenuIcon() throws Exception {
        if (fnVerifyHamburgerMenuIcon()) {
            if (isElementVisible(sHamburgerMenuIcon,2)) {
                clickElement("Clicked on the menu",sHamburgerMenuIcon);
            } else if (isElementVisible(sHamburgerMenuIconAlternate1,1)) {
                clickElement("Clicked on the menu",sHamburgerMenuIconAlternate1);
            } else if (isElementVisible(sHamburgerMenuIconAlternate2,2)) {
                clickElement("Clicked on the menu",sHamburgerMenuIconAlternate2);
            }
            return true;
        } else {
            LogUtils.info("Unable to click on Hamburger menu icon");
            return false;
        }
    }
    
    public void fnSwitchMenuItemsXpath() throws Exception {
        if (waitForElement(By.xpath("//ul[@class='menu-list']"), 1000)) {
            sMenuPrefix = By.xpath("//ul[@class='menu-list']");
            sMenuItemLogin = By.xpath(sMenuPrefix + "//*[contains(text(),'Login')]");
            sMenuItemLogout = By.xpath(sMenuPrefix + "//*[contains(text(),'Logout')]");
            sMenuItemBuyTickets = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[1]");
            sMenuItemBuyTicketsFirstOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[2]");
            sMenuItemBuyTicketsSecondOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[3]");
            sMenuItemBuyReplayTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'Buy Replay Tickets')]");
            sMenuItemVouchers = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
            sMenuItemMyTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'My Tickets')]");
            sMenuItemMyAccount = By.xpath(sMenuPrefix + "//*[contains(text(),'My Account')]");
            sMenuItemFaq = By.xpath(sMenuPrefix + "//*[contains(text(),'FAQ')]");
            sMenuItemDebug = By.xpath(sMenuPrefix + "//*[contains(text(),'DEBUG')]");
            sMenuItemVoucher = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
            sMenuItemMyAccountUserProfile = By.xpath(sMenuPrefix + "//*[contains(text(),'User Profile')]");
            sMenuItemMyAccountPaymentInfo = By.xpath(sMenuPrefix + "//*[contains(text(),'Payment Info')]");
            sMenuItemMyAccountManageFriends = By.xpath(sMenuPrefix + "//*[contains(text(),'Manage Friends')]");
            sMenuItemShoppingCart = By.xpath(sMenuPrefix + "//*[contains(text(),'Shopping Cart')]");
            sMenuItemMyAccountChangePassword = By.xpath(sMenuPrefix + "//*[contains(text(),'Change Password')]");
            sMenuItemMyTicketsLoyalty = By.xpath(sMenuPrefix + "//*[contains(text(),'Loyalty')]");
            sMenuItemTermsAndConditions_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Terms & Conditions') and not (@target)]");
            sMenuItemPrivacyPolicy_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Privacy Policy') and not (@target)]");
            sMenuItemTermsOfUse_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Terms of Use') and not (@target)]");
            sMenuItemViewWebVersion_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'View Web Version') and not (@target)]");
            sMenuItemUpgradePackageSeats = By.xpath(sMenuPrefix + "//*[contains(text(),'Upgrade Package Seats')]");
            sMenuItemMyTicketsHome = By.xpath(sMenuPrefix + "//*[contains(text(),'HOME')]");
        } else if (waitForElement(By.xpath("//div[@id='mpv-menu']"), 1000)
                || (waitForElement(By.xpath("//div[@class='mpv-rows']"), 1000))) {
            sMenuPrefix = By.xpath("//div[@id='mpv-menu']");
            sMenuItemLogin = By.xpath(sMenuPrefix + "//*[contains(text(),'Login')]");
            sMenuItemLogout = By.xpath(sMenuPrefix + "//*[contains(text(),'Logout')]");
            sMenuItemBuyTickets = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[1]");
            sMenuItemBuyTicketsFirstOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[2]");
            sMenuItemBuyTicketsSecondOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[3]");
            sMenuItemBuyReplayTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'Buy Replay Tickets')]");
            sMenuItemVouchers = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
            sMenuItemMyTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'My Tickets')]");
            sMenuItemMyAccount = By.xpath(sMenuPrefix + "//*[contains(text(),'My Account')]");
            sMenuItemFaq = By.xpath(sMenuPrefix + "//*[contains(text(),'FAQ')]");
            sMenuItemDebug = By.xpath(sMenuPrefix + "//*[contains(text(),'DEBUG')]");
            sMenuItemVoucher = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
            sMenuItemMyAccountUserProfile = By.xpath(sMenuPrefix + "//*[contains(text(),'User Profile')]");
            sMenuItemMyAccountPaymentInfo = By.xpath(sMenuPrefix + "//*[contains(text(),'Payment Info')]");
            sMenuItemMyAccountManageFriends = By.xpath(sMenuPrefix + "//*[contains(text(),'Manage Friends')]");
            sMenuItemShoppingCart = By.xpath(sMenuPrefix + "//*[contains(text(),'Shopping Cart')]");
            sMenuItemMyAccountChangePassword = By.xpath(sMenuPrefix + "//*[contains(text(),'Change Password')]");
            sMenuItemMyTicketsLoyalty = By.xpath(sMenuPrefix + "//*[contains(text(),'Loyalty')]");
            sMenuItemTermsAndConditions_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Terms & Conditions') and not (@target)]");
            sMenuItemPrivacyPolicy_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Privacy Policy') and not (@target)]");
            sMenuItemTermsOfUse_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Terms of Use') and not (@target)]");
            sMenuItemViewWebVersion_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'View Web Version') and not (@target)]");
            sMenuItemUpgradePackageSeats = By.xpath(sMenuPrefix + "//*[contains(text(),'Upgrade Package Seats')]");
            sMenuItemMyTicketsHome = By.xpath(sMenuPrefix + "//*[contains(text(),'HOME')]");
        } else {
            sMenuPrefix = By.xpath("//div[@id='mpv-menu']");
            sMenuItemLogin = By.xpath(sMenuPrefix + "//*[contains(text(),'Login')]");
            sMenuItemLogout = By.xpath(sMenuPrefix + "//*[contains(text(),'Logout')]");
            sMenuItemBuyTickets = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[1]");
            sMenuItemBuyTicketsFirstOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[2]");
            sMenuItemBuyTicketsSecondOrg = By.xpath("(" + sMenuPrefix + "//*[contains(text(),'Buy Tickets')])[3]");
            sMenuItemBuyReplayTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'Buy Replay Tickets')]");
            sMenuItemVouchers = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
            sMenuItemMyTickets = By.xpath(sMenuPrefix + "//*[contains(text(),'My Tickets')]");
            sMenuItemMyAccount = By.xpath(sMenuPrefix + "//*[contains(text(),'My Account')]");
            sMenuItemFaq = By.xpath(sMenuPrefix + "//*[contains(text(),'FAQ')]");
            sMenuItemDebug = By.xpath(sMenuPrefix + "//*[contains(text(),'DEBUG')]");
            sMenuItemVoucher = By.xpath(sMenuPrefix + "//*[contains(text(),'Vouchers')]");
            sMenuItemMyAccountUserProfile = By.xpath(sMenuPrefix + "//*[contains(text(),'User Profile')]");
            sMenuItemMyAccountPaymentInfo = By.xpath(sMenuPrefix + "//*[contains(text(),'Payment Info')]");
            sMenuItemMyAccountManageFriends = By.xpath(sMenuPrefix + "//*[contains(text(),'Manage Friends')]");
            sMenuItemShoppingCart = By.xpath(sMenuPrefix + "//*[contains(text(),'Shopping Cart')]");
            sMenuItemMyAccountChangePassword = By.xpath(sMenuPrefix + "//*[contains(text(),'Change Password')]");
            sMenuItemMyTicketsLoyalty = By.xpath(sMenuPrefix + "//*[contains(text(),'Loyalty')]");
            sMenuItemTermsAndConditions_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Terms & Conditions') and not (@target)]");
            sMenuItemPrivacyPolicy_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Privacy Policy') and not (@target)]");
            sMenuItemTermsOfUse_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'Terms of Use') and not (@target)]");
            sMenuItemViewWebVersion_Mobile = By.xpath(sMenuPrefix
                    + "//*[contains(text(),'View Web Version') and not (@target)]");
            sMenuItemUpgradePackageSeats = By.xpath(sMenuPrefix + "//*[contains(text(),'Upgrade Package Seats')]");
            sMenuItemMyTicketsHome = By.xpath(sMenuPrefix + "//*[contains(text(),'HOME')]");
        }
    }


    public boolean fnSelectMenuItem(String sMenuItem) throws Exception {
        sleep(1);
        if (!fnClickHamburgerMenuIcon()) {
            return false;
        }
        sleep(1);
        //fnSwitchMenuItemsXpath();
        switch (sMenuItem.toUpperCase()) {
        case "LOGIN":
            if (waitForElement(sMenuItemLogin, 20000)) {
                clickElement("Clicked on the menu Login",sMenuItemLogin);
                return true;
            } else {
                LogUtils.info("Unable to find Hamburger menu item Login");
                return false;
            }

        case "LOGOUT":
            System.out.println("Clicking : LOGOUT");
            if (waitForElement(sMenuItemLogout, 10000)) {
                clickElement("Clicked on the menu logout",sMenuItemLogout);
                sleep(1);
                return true;
            } else {
                LogUtils.info("Unable to find Hamburger menu item Logout");
                return false;
            }

        case "VOUCHERS":
            if (waitForElement(sMenuItemMyTickets, 10000)) {
                clickElement("Clicked on the menu My Ticket",sMenuItemMyTickets);
                if (waitForElement(sMenuItemVoucher, 3000)) {
                    clickElement("Clicked on the menu Item voucher",sMenuItemVoucher);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item MyTickets->Vouchers");
                    return false;
                }
            } else {
                LogUtils.info("Unable to find Hamburger menu item MyTickets");
                return false;
            }

        case "BUY TICKETS":
            if (waitForElement(sMenuItemBuyTickets, 10000)) {
                clickElement("Clicked on the menu Buy Ticket",sMenuItemBuyTickets);
                return true;
            } else {
                LogUtils.info("Unable to find Hamburger menu item BUY TICKETS");
                return false;
            }

        case "BUY TICKETS-1":
            if (waitForElement(sMenuItemBuyTickets, 10000)) {
                clickElement("Clicked on the menu Buy ticket",sMenuItemBuyTickets);
                System.out.println(sMenuItemBuyTicketsFirstOrg);
                if (waitForElement(sMenuItemBuyTicketsFirstOrg, 3000)) {
                    clickElement("Clicked on the menu Buy Ticket",sMenuItemBuyTicketsFirstOrg);
                } else {
                    LogUtils.info("Unable to find Hamburger menu item BUY TICKETS-Org1");
                    return false;
                }
                return true;
            } else {
                LogUtils.info("Unable to find Hamburger menu item BUY TICKETS");
                return false;
            }
        case "BUY TICKETS-2":
            if (waitForElement(sMenuItemBuyTickets, 10000)) {
                clickElement("Clicked on the menu Buy Ticket",sMenuItemBuyTickets);
                System.out.println(sMenuItemBuyTicketsSecondOrg);
                if (waitForElement(sMenuItemBuyTicketsSecondOrg, 3000)) {
                    clickElement("Clicked on the menu Buy Ticket",sMenuItemBuyTicketsSecondOrg);
                } else {
                    LogUtils.info("Unable to find Hamburger menu item BUY TICKETS-Org2");
                    return false;
                }
                return true;
            } else {
                LogUtils.info("Unable to find Hamburger menu item BUY TICKETS");
                return false;
            }

        // Added by Saravana
        case "USERPROFILE":
            if (waitForElement(sMenuItemMyAccount, 10000)) {
                clickElement("",sMenuItemMyAccount);
                if (waitForElement(sMenuItemMyAccountUserProfile, 3000)) {
                    clickElement("",sMenuItemMyAccountUserProfile);
                    sleep(3);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item MyAccount-UserProfile");
                    return false;
                }
            } else {
                LogUtils.info("Unable to find Hamburger menu item MyAccount");
                return false;
            }
        case "PAYMENTINFO":
            if (waitForElement(sMenuItemMyAccount, 10000)) {
                clickElement("",sMenuItemMyAccount);
                if (waitForElement(sMenuItemMyAccountPaymentInfo, 3000)) {
                    clickElement("",sMenuItemMyAccountPaymentInfo);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item MyAccount-PaymentInfo");
                    return false;
                }
            } else {
                LogUtils.info("Unable to find Hamburger menu item MyAccount");
                return false;
            }
        case "MANAGEFRIENDS":
            System.out.println("Clicking Managefriends");
            if (waitForElement(sMenuItemMyAccount, 10000)) {
                clickElement("Click My Account",sMenuItemMyAccount);
                if (waitForElement(sMenuItemMyAccountManageFriends, 10000)) {
                    clickElement("Manage Friend",sMenuItemMyAccountManageFriends);
                    sleep(3);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item MyAccount Manage Friends");
                    return false;
                }
            } else {
                LogUtils.info("Unable to find Hamburger menu item MyAccount");
                return false;
            }

        // Added by Nima-04/02/2019
        case "MYTICKETS":
            if (waitForElement(sMenuItemMyTickets, 5000)) {
                clickElement("",sMenuItemMyTickets);
                return true;
            } else {
                LogUtils.info("Unable to find Hamburger menu item MyTickets");
                return false;
            }
        case "LOYALTY":
            if (waitForElement(sMenuItemMyAccount, 10000)) {
                clickElement("",sMenuItemMyAccount);
                if (waitForElement(sMenuItemMyTicketsLoyalty, 3000)) {
                    clickElement("",sMenuItemMyTicketsLoyalty);
                    sleep(3);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item MyTickets->Loyalty ");
                    return false;
                }
            } else {
                LogUtils.info("Unable to find Hamburger menu item MyTickets");
                return false;
            }

        case "SHOPPINGCART":
            clickElement("",sMenuItemShoppingCart);
            return true;
        case "REMEMBERME":
            if (waitForElement(sRememberMeValue, 15000)) {
                ExtentReportManager.pass("RememberMe checkbox is checked by default:");
                return true;
            } else {
                LogUtils.info("RememberMe checkbox is NOT checked by default");
                return false;
            }
        // Added by Hariharan M(12-04-2019)
        case "CHANGEPASSWORD":
            if (waitForElement(sMenuItemMyAccount, 10000)) {
                clickElement("",sMenuItemMyAccount);
                if (waitForElement(sMenuItemMyAccountChangePassword, 3000)) {
                    clickElement("",sMenuItemMyAccountChangePassword);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item MyAccount- Change password");
                    return false;
                }
            } else {
                LogUtils.info("Unable to find Hamburger menu item MyAccount");
                return false;
            }
        // Added by Chris on 1/6
        case "UPGRADEPACKAGESEATS":
            if (!waitForElement(sMenuItemLogin, 5000)) {
                fnSelectMenuItem("MYTICKETS");
                if (waitForElement(sMenuItemUpgradePackageSeats, 5000)) {
                    clickElement("",sMenuItemUpgradePackageSeats);
                    sleep(3);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item 'Upgrade Package Seats'");
                    return false;
                }
            } else {
                LogUtils.info("Menu item 'Upgrade Package Seats' is available only in logged in session");
                return false;
            }
        case "HOME":
            if (waitForElement(sMenuItemMyTickets, 10000)) {
                clickElement("",sMenuItemMyTickets);
                if (waitForElement(sMenuItemMyTicketsHome, 3000)) {
                    clickElement("",sMenuItemMyTicketsHome);
                    sleep(3);
                    return true;
                } else {
                    LogUtils.info("Unable to find Hamburger menu item 'MyTickets->Home'");
                    return false;
                }
            } else {
                LogUtils.info("Unable to find Hamburger menu item 'MyTickets'");
                return false;
            }
        default:
            LogUtils.info("Invalid Hamburger menu item: " + sMenuItem);
            return false;
        }
    }
}
