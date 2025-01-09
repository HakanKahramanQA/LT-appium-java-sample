import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.github.ashwith.flutter.FlutterFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.URL;

class vanilla_android {
  
  private static AppiumDriver driver;

  public static void main(String[] args) {
    try {
      // DesiredCapabilities for LambdaTest setup
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("deviceName", ".*"); // Use a regex pattern to match any device name
      capabilities.setCapability("platformVersion", "13"); // Platform version of Android device
      capabilities.setCapability("automationName", "flutter"); // Set automation name as Flutter
      capabilities.setCapability("platformName", "android");
      capabilities.setCapability("isRealMobile", true); // Real device testing
      //            capabilities.setCapability("app", "lt://APP10160301221736351094568553"); // App URL on LambdaTest
      capabilities.setCapability("app", "flutterapk"); // App URL on LambdaTest
      capabilities.setCapability("appiumVersion", "2.2.1"); // Version of Appium
      capabilities.setCapability("video", true); // Enable video recording
      capabilities.setCapability("visual", true); // Enable visual logs
      capabilities.setCapability("w3c", false); // Disable W3C compliance for better compatibility
      capabilities.setCapability("autoAcceptAlerts", true); // Enable app profiling
      capabilities.setCapability("autoGrantPermissions", true); // Enable app profiling

      // Initialize the Appium driver to connect with LambdaTest
      AndroidDriver driver = new AndroidDriver(
        new URL("https://username:accesskey@mobile-hub.lambdatest.com/wd/hub"),
        //      new URL(
        //        "https://mobileQA:aMu9eQwRwWSCLnSsbALDYdGcSj7tuaDkTmDXSCDlYjyDiWWpsb@stage-mobile-hub.lambdatestinternal.com/wd/hub"),
        capabilities);

      driver.context("FLUTTER");

      FlutterFinder finder = new FlutterFinder(driver);
      finder.byValueKey("next_route_key").click();
      driver.context("NATIVE_APP");
      WebElement ele = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]"));
      String text = ele.getText();
      Boolean checkText = text.equalsIgnoreCase("This is 2nd route");
      Assert.assertTrue(checkText, "Text not displayed as expected");// Replace with the actual ValueKey of the button
      driver.quit();
    } catch (Exception e) {
      if (driver != null) {
        ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
      }
      e.printStackTrace();
    }
  }

}