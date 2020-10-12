package com.demo;

import com.pocFramework.PageFactoryClass;
import org.openqa.selenium.WebDriver;

public class DemoVerification extends PageFactoryClass {
    /**
     * To get the PageFactory of the DOM
     *
     * @param driver WebDriver
     */
    public DemoVerification(WebDriver driver) {
        super(driver);
    }
}
