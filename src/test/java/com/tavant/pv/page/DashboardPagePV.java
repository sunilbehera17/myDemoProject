package com.tavant.pv.page;


import com.tavant.common.CommonPagePV;

import static com.tavant.keywords.WebUI.*;

import org.openqa.selenium.By;

public class DashboardPagePV extends CommonPagePV {

    public DashboardPagePV() {
        super();
    }

    public String pageText = "Dashboard";
    public String pageUrl = "/dashboard";

    public By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    public By menuClients = By.xpath("//span[normalize-space()='Clients']");
    public By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    public By menuTasks = By.xpath("//span[normalize-space()='Tasks']");

    public DashboardPagePV openDashboardPage() {
        clickElement("Menu Button",menuDashboard);
        return this;
    }





}
