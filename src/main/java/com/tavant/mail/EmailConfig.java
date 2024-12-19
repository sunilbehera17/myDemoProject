/*
 * Copyright (c) 2024 Sunil
 * Automation Framework Selenium
 */

package com.tavant.mail;

import static com.tavant.constants.FrameworkConstants.REPORT_TITLE;

/**
 * Data for Sending email after execution
 */
public class EmailConfig {

    //Remember to create an app password (App Password) for Gmail before sending
    //If you use Hosting's email, it's normal
    //Enable Override Report and Send mail in config file => src/test/resources/config/config.properties
    //OVERRIDE_REPORTS=yes
    //send_email_to_users=yes

    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "587";

    public static final String FROM = "sunil.behera17@gmail.com";
    public static final String PASSWORD = "******";

    public static final String[] TO = {"sunil.behera@tavant.com"};
    public static final String SUBJECT = REPORT_TITLE;
}
