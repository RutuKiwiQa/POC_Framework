package com.pocFramework;

import com.demo.DemoIndexPage;
import com.demo.DemoVerification;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rutu shah.
 * Date: 9th October 2020
 * Time:
 * Web POC_Framework
 */

public class BaseClass {
    public static WebDriver driver;
    protected DemoIndexPage demoIndexPage;
    protected DemoVerification demoVerification;
    protected static int _logStep = 1;
    public static String url = "https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin";

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void beforeTest(String browser) {
            DesiredCapabilities capability;

            if(browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                capability = DesiredCapabilities.firefox();
                capability.setCapability("marionette", true);
                capability.setJavascriptEnabled(true);
                driver = new FirefoxDriver(capability);

            }else if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                driver = new ChromeDriver();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("chrome.switches", "--disable-extensions");
                options.addArguments("disable-infobars");
                options.addArguments("test-type");
                options.addArguments("--js-flags=--expose-gc");
                options.addArguments("--enable-precise-memory-info");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--disable-default-apps");
                options.addArguments("--start-maximized");
                options.addArguments("--ignore-ssl-errors=yes");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--allow-running-insecure-content");
                options.addArguments("incognito");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("password_manager_enabled", false);
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("profile.default_content_setting_values.plugins", 1);
                prefs.put("profile.content_settings.plugin_whitelist.adobe-flash-player", 1);
                prefs.put("profile.content_settings.exceptions.plugins.*,*.per_resource.adobe-flash-player", 1);
                options.setExperimentalOption("prefs", prefs);
            }

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            demoIndexPage = new DemoIndexPage(driver);
            demoVerification = new DemoVerification(driver);


            driver.get(url);

//            setDriver(driver);
            System.out.println("======" + url + "=========");
        }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) {

        String testName;

        try {
            testName = testResult.getName();
            if (testResult.getStatus() == ITestResult.FAILURE) {

                System.out.println();
                System.out.println("TEST FAILED - " + testName);
                System.out.println();
                System.out.println("ERROR MESSAGE: " + testResult.getThrowable());
                System.out.println("\n");

                Reporter.setCurrentTestResult(testResult);
                String screenshotName = System.currentTimeMillis() + "_" + testName;

                WebDriver augmentedDriver = new Augmenter().augment(driver);
                File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

                String nameWithExtension = screenshotName + ".png";

                String reportFolder = "test-output" + File.separator;
                String screenshotsFolder = "screenshots";
                File screenshotFolder = new File(reportFolder + screenshotsFolder);
                if (!screenshotFolder.getAbsoluteFile().exists()) {
                    screenshotFolder.mkdir();
                }
                FileUtils.copyFile(screenshot,
                        new File(screenshotFolder + File.separator + nameWithExtension).getAbsoluteFile());
                log("Step Failure<br>Please check attached screenshot : <br><br> " +
                        "<img src='../test-output/screenshots/" + nameWithExtension + "' width='683' height='384'/>");

            } else if ((testResult.getStatus() == ITestResult.SUCCESS)) {
                System.out.println("TEST PASSED - " + testName + "\n");
            } else if ((testResult.getStatus() == ITestResult.SKIP)) {
                System.out.println("TEST SKIPPED - " + testName + "\n");
            }

            driver.close();
            driver.quit();

        } catch (Exception throwable) {
            System.err.println("Exception ::\n" + throwable);
        }
    }

    public static void log(String message) {
        Reporter.log("<br>" + message + "<br>");
        System.out.println(message);
    }

    public static void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
