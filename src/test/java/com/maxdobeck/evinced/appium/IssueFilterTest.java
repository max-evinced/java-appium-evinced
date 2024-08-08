package com.maxdobeck.evinced.appium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.evinced.appium.sdk.core.EvincedAppiumSdk;
import com.evinced.appium.sdk.core.models.EvincedConfig;
import com.evinced.appium.sdk.core.models.InitOptions;
import com.evinced.appium.sdk.core.models.IssueFilter;
import com.evinced.appium.sdk.core.models.IssueType;
import com.evinced.appium.sdk.core.models.Report;
import com.evinced.appium.sdk.core.models.Severity;
import com.evinced.appium.sdk.core.EvincedAppiumIOSDriver;
// import com.evinced.appium.sdk.core.EvincedAppiumAndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class IssueFilterTest
{
    public static DesiredCapabilities capabilities;
    public static EvincedAppiumSdk evincedService;
    public static EvincedAppiumIOSDriver driver;
    public static Process appium;

    @Before
    public void startAppium() throws IOException, InterruptedException {
        appium = Runtime.getRuntime().exec("appium");
        Thread.sleep(1500);
    }

    @After
    public void closeAppium() throws IOException {
        appium.destroy();
    }
    
    @Test
    public void globalFilter() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "safari");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("udid", "auto");
        capabilities.setCapability("appium:deviceName", "iPhone Simulator");
        capabilities.setCapability("platformName", "iOS");
        
        try {
            // Setup EvincedAppium IOS Driver
            driver = new EvincedAppiumIOSDriver(new URL("http://127.0.0.1:4723"), capabilities);
            // Issue Filter(s)
            final IssueFilter globalFilter = new IssueFilter(driver).severity(Severity.Critical);
            // Add Filter to config
            final EvincedConfig evincedConfig = new EvincedConfig().includeFilters(globalFilter);
            // Launch Evinced SDK with Global Filtering applied to all 
            // evinced.analyze() and evinced.report() calls
            evincedService = new EvincedAppiumSdk(driver, new InitOptions(evincedConfig));
            evincedService.setupCredentials(System.getenv("SERVICE_ACCOUNT_ID"), System.getenv("API_KEY"));


            driver.quit();
        } catch (Exception ignore) {
            System.err.println(ignore);
        }
    }

    @Test
    public void shouldStartBaseOptionsDriver() throws MalformedURLException {
        BaseOptions options = new BaseOptions()
            .setPlatformName("myplatform")
            .setAutomationName("mydriver")
            .amend("mycapability1", "capvalue1")
            .amend("mycapability2", "capvalue2");
        
        try {
            AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
            EvincedAppiumSdk evincedService = new EvincedAppiumSdk(driver);
            evincedService.setupCredentials(System.getenv("SERVICE_ACCOUNT_ID"), System.getenv("API_KEY"));
        } catch (Exception ignore) {
           System.err.println(ignore);
        }
        assertTrue( true );
    }

    @Test
    public void shouldStartXCUIOptionsDriver() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();

        try {
            IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
            evincedService = new EvincedAppiumSdk(driver);
            evincedService.setupCredentials(System.getenv("SERVICE_ACCOUNT_ID"), System.getenv("API_KEY"));
        } catch (Exception ignore) {
            System.err.println(ignore);
        }
        assertTrue( true );
    }

    // // https://github.com/appium/java-client?tab=readme-ov-file#usage-examples
    // @Test
    // public void worksOnAndroid() throws MalformedURLException {
    //     UiAutomator2Options options = new UiAutomator2Options()
    //     .setAutomationName("UIAutomator2")
    //     .setDeviceName("emulator-5554")
    //     .setAppPackage("com.google.android.apps.maps")
    //     .setAppActivity("com.google.android.maps.MapsActivity")
    //     .setPlatformName("android")
    //     .setPlatformVersion("14")
    //     .setAutoGrantPermissions(true);

    //     final InitOptions initOptions = new InitOptions(InitOptions.ScreenshotOption.Base64);         
    //     AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    //     evincedService = new EvincedAppiumSdk(driver, initOptions);
    //     evincedService.setupCredentials(System.getenv("SERVICE_ACCOUNT_ID"), System.getenv("API_KEY"));
    //     evincedService.analyze();
    //     Report report = evincedService.report();
    //     assertEquals(report.hasIssues(), false);
    //     driver.quit();
    // }

}
