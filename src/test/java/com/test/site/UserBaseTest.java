package com.test.site;

import com.appium.manager.AppiumParallelTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class UserBaseTest extends AppiumParallelTest {
    JSonParser jSonParser = new JSonParser();

    @BeforeMethod(alwaysRun = true) public void startApp(Method name) throws Exception {
        driver = startAppiumServerInParallel(name.getName());
    }

    @AfterMethod(alwaysRun = true) public void killServer(ITestResult result)
        throws InterruptedException, IOException {
        //Moving the method inside the dependency in the next release
        endLogTestResults(result);
        getDriver().quit();
        //deleteAppIOS("com.tesco.sample");
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    @BeforeClass(alwaysRun = true) public void beforeClass() throws Exception {
        System.out.println("Before Class" + Thread.currentThread().getId());
    }

    @AfterClass(alwaysRun = true) public void afterClass()
        throws InterruptedException, IOException {
        System.out.println("After Class" + Thread.currentThread().getId());
        //Moving the method inside the dependency in the next release
        //killAppiumServer();
    }

    public void getUserName() {
        String[] crds = Thread.currentThread().getName().toString().split("_");
        System.out.println("**********" + crds[1]);
        JSONObject user = jSonParser.getUserData(Integer.parseInt(crds[1]));
        System.out.println(user.get("userName"));
    }

    public DesiredCapabilities iosNative1() throws Exception {
        DesiredCapabilities iOSCapabilities = new DesiredCapabilities();
        System.out.println("Setting iOS Desired Capabilities:");
        iOSCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");

        File iosAppFile = new File(prop.getProperty("IOS_APP_PATH"));
        String ipaPath = iosAppFile.getAbsolutePath();
        System.out.println("Using IPA from " + ipaPath);
        if (!iosAppFile.exists()) {
            System.out.println("********************  ERROR ********************");
            System.out
                    .println("iOS: Unable to find the IPA at location "
                            + iosAppFile.getAbsolutePath());
            System.out.println("********************  ERROR ********************");
        }

        iOSCapabilities.setCapability(MobileCapabilityType.APP, iosAppFile);
        iOSCapabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID,
                prop.getProperty("BUNDLE_ID"));
        iOSCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
        iOSCapabilities.setCapability(MobileCapabilityType.UDID, device_udid);
        return iOSCapabilities;
    }

    public synchronized DesiredCapabilities androidNative1() {
        System.out.println("Setting Android Desired Capabilities:");
        DesiredCapabilities androidCapabilities = new DesiredCapabilities();
        androidCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        androidCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.X");
        androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
            prop.getProperty("APP_ACTIVITY"));
        androidCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
            prop.getProperty("APP_PACKAGE"));
        androidCapabilities.setCapability("browserName", "");
        androidCapabilities
            .setCapability(MobileCapabilityType.APP, prop.getProperty("ANDROID_APP_PATH"));
        androidCapabilities.setCapability(MobileCapabilityType.UDID, device_udid);
        return androidCapabilities;
    }
}
