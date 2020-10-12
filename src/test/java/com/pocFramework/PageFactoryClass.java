package com.pocFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;


/**
 * Created by Rutu shah.
 * Date: 9th October 2020
 * Time:
 * Web POC_Framework
 */

public abstract class PageFactoryClass extends BaseClass {

    /**
     * To get the PageFactory of the DOM
     *
     * @param driver WebDriver
     */
    public PageFactoryClass(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                10);
        PageFactory.initElements(finder, this);
    }
}
