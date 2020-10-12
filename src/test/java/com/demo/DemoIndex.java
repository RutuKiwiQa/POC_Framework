package com.demo;

import com.pocFramework.BaseClass;
import org.testng.annotations.Test;

public class DemoIndex extends BaseClass {

    @Test
    public void demoTest(){

        log("Verify user can see gmail login screen.");

        demoVerification =  demoIndexPage.enterEmail();
        demoVerification = demoIndexPage.clickNextButton();
        pause(4);
        demoVerification = demoIndexPage.enterPassword();
        demoVerification = demoIndexPage.clickNextButton();
    }
}
