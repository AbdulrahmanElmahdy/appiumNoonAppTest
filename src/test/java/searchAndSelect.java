import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class searchAndSelect {

    AppiumDriverLocalService appiumDriverLocalService;
    AndroidDriver androidDriver;
    WebDriverWait webDriverWait;


    @BeforeMethod
    public void startServer()
    {
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withArgument(GeneralServerFlag.BASEPATH,"/wd/hub")
                .usingPort(4723)
                .build();

        appiumDriverLocalService.start();

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setDeviceName("Pixel 7 Pro")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAppPackage("com.noon.buyerapp")
                .setAppActivity("com.noon.buyerapp.MainActivity");

        androidDriver = new AndroidDriver(appiumDriverLocalService.getUrl(),uiAutomator2Options);
    }

    @Test
    public void searchAdSelect() {
        webDriverWait = new WebDriverWait(androidDriver, Duration.ofSeconds(20));

        webDriverWait.until(presenceOfElementLocated(AppiumBy.className("android.widget.EditText"))).click();

        webDriverWait.until(presenceOfElementLocated(AppiumBy.className("android.widget.EditText"))).sendKeys("RAM DDR4");

        webDriverWait = new WebDriverWait(androidDriver, Duration.ofSeconds(20));

        webDriverWait.until(presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup"))).click();

        webDriverWait.until(presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]"))).click();

        webDriverWait.until(presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]"))).click();

    }

    @AfterMethod
    public void stopServer()
    {
        appiumDriverLocalService.stop();
    }

}
