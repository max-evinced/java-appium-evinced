package com.maxdobeck;

import static org.junit.Assert.assertTrue;
import com.evinced.appium.sdk.core.EvincedAppiumSdk;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.other.CommandTimeouts;
import io.appium.java_client.ios.options.simulator.Permissions;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class EvincedSetupTest
{
    protected static IOSDriver driver;
    public static EvincedAppiumSdk evincedSdk;
    
    // @Test
    // public void shouldStartiOSDocsDriver()
    // {
    //     DesiredCapabilities capabilities = new DesiredCapabilities();
    //     evincedSdk = new EvincedAppiumSdk(new IOSDriver(new URL("http://127.0.0.1:4723"), capabilities));

    // }

    @Test
    public void shouldStartBaseOptionsDriver() throws MalformedURLException
    {
        BaseOptions options = new BaseOptions()
            .setPlatformName("myplatform")
            .setAutomationName("mydriver")
            .amend("mycapability1", "capvalue1")
            .amend("mycapability2", "capvalue2");
        // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
        AppiumDriver driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
        
        EvincedAppiumSdk evincedSdk = new EvincedAppiumSdk(driver);
        assertTrue( true );
    }

    // @Test
    // public void shouldStartXCUIOptionsDriver()
    // {
    //     XCUITestOptions options = new XCUITestOptions();
    //     IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
    //     evincedSdk = new EvincedAppiumSdk(driver);        // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
        
    //     EvincedAppiumSdk evincedSdk = new EvincedAppiumSdk(driver);
    //     assertTrue( true );
    // }


    @Test
    public void shouldInstantiateEvincedObject()
    {
        assertTrue( true);
    }

}
