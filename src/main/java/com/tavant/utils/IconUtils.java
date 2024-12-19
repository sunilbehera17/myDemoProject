/*
 * Copyright (c) 2024 Sunil
 * Automation Framework Selenium
 */

package com.tavant.utils;

import static com.tavant.constants.FrameworkConstants.*;

import com.tavant.enums.Browser;

public final class IconUtils {

    private IconUtils() {
        super();
    }

    public static String getBrowserIcon() {
        if (BrowserInfoUtils.getBrowserInfo().contains(Browser.CHROME.toString())) {
            return ICON_BROWSER_CHROME;
        } else if (BrowserInfoUtils.getBrowserInfo().contains(Browser.EDGE.toString())) {
            return ICON_BROWSER_EDGE;
        } else if (BrowserInfoUtils.getBrowserInfo().contains(Browser.FIREFOX.toString())) {
            return ICON_BROWSER_FIREFOX;
        } else {
            return BrowserInfoUtils.getBrowserInfo();
        }
    }

    public static String getOSIcon() {

        String operationSystem = BrowserInfoUtils.getOSInfo().toLowerCase();
        if (operationSystem.contains("win")) {
            return ICON_OS_WINDOWS;
        } else if (operationSystem.contains("nix") || operationSystem.contains("nux") || operationSystem.contains("aix")) {
            return ICON_OS_LINUX;
        } else if (operationSystem.contains("mac")) {
            return ICON_OS_MAC;
        }
        return operationSystem;
    }
}
