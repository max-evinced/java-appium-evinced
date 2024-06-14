package com.maxdobeck.evinced.appium;

import static org.junit.Assert.assertTrue;
import com.evinced.appium.sdk.core.EvincedAppiumSdk;
import com.evinced.appium.sdk.core.EvincedAppiumIOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.other.CommandTimeouts;
import io.appium.java_client.ios.options.simulator.Permissions;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class EvincedSetupTest
{
    public static DesiredCapabilities capabilities;
    public static EvincedAppiumSdk evincedService;
    public static EvincedAppiumIOSDriver driver;
    
    @Test
    public void shouldStartiOSDocsDriver() throws MalformedURLException
    {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "safari");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:deviceName", "iPhone Simulator");
        capabilities.setCapability("platformName", "iOS");
        driver = new EvincedAppiumIOSDriver(new URL("http://127.0.0.1:4723"), capabilities);
        evincedService = new EvincedAppiumSdk(driver);
        driver.quit();
    }

    // @Test
    // public void shouldStartBaseOptionsDriver() throws MalformedURLException
    // {
    //     BaseOptions options = new BaseOptions()
    //         .setPlatformName("myplatform")
    //         .setAutomationName("mydriver")
    //         .amend("mycapability1", "capvalue1")
    //         .amend("mycapability2", "capvalue2");
    //     // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
    //     AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
        
    //     EvincedAppiumSdk evincedService = new EvincedAppiumSdk(driver);
    //     assertTrue( true );
    // }

    // @Test
    // public void shouldStartXCUIOptionsDriver() throws MalformedURLException
    // {
    //     XCUITestOptions options = new XCUITestOptions();

    //     IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
    //     evincedService = new EvincedAppiumSdk(driver);        // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
        
    //     assertTrue( true );
    // }


    // @Test
    // public void shouldInstantiateEvincedObject()
    // {
    //     assertTrue( true);
    // }

}
