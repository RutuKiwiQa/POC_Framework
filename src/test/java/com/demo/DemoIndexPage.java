package com.demo;

import com.pocFramework.ExcelUtils;
import com.pocFramework.PageFactoryClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;

public class DemoIndexPage extends PageFactoryClass {
    /**
     * To get the PageFactory of the DOM
     *
     * @param driver WebDriver
     */
    public DemoIndexPage(WebDriver driver) {
        super(driver);
    }
    //-----------------Variables Declaration--------------------
    public static String excelPath = "./Excel/Data.xlsx"; //relative path of excel

    public static String _userName;
    public static String _password;


    static {
        try {
            _userName = ExcelUtils.getCellData(excelPath,"Sheet1",1,0);
            _password = ExcelUtils.getCellData(excelPath,"Sheet1",1,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //-----------------Locators Definiton------------------------
    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement txtEmail;

    @FindBy(xpath = "//div[@class='Xb9hP']/input[@name='password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//div[@class='VfPpkd-RLmnJb']")
    private WebElement btnNextOnEmail_Password;

    //---------------Method Declaration=--------------------


    public DemoVerification enterEmail(){
        log(_logStep++ + "Enter email.");
        System.out.println(_userName);
        txtEmail.sendKeys(_userName);
        return new DemoVerification(driver);
    }

    public DemoVerification enterPassword(){
        log(_logStep++ + "Enter password.");
        System.out.println(_password);
        txtPassword.sendKeys(_password);
        return new DemoVerification(driver);
    }

    public DemoVerification clickNextButton(){
        log(_logStep++ + "Click on Next button.");
        btnNextOnEmail_Password.click();
        return new DemoVerification(driver);
    }
}
