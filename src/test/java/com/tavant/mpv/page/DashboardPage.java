package com.tavant.mpv.page;

import org.openqa.selenium.By;

public class DashboardPage {
    public static By titleDashboard = By.xpath("//*[@id='hero']/div[@class='hero-logo mpv-theme-hero-logo-new']");
    public static By menuHome = By.xpath("//a[contains(text(),'Home')]");
    public static By inputSearchProduct = By.xpath("//input[@id='search']");
    public static By buttonSubmitSearch = By.xpath("//div[@class='input-group-append d-none d-lg-block']//button[@type='submit']");
    
}
