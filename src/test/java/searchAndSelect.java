import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By; // Added for AppiumBy constants
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

    private static final int APPIUM_PORT = 4723;
    private static final String APPIUM_BASE_PATH = "/wd/hub";
    private static final String DEVICE_NAME = "Pixel 7 Pro";
    private static final String APP_PACKAGE = "com.noon.buyerapp";
    private static final String APP_ACTIVITY = "com.noon.buyerapp.MainActivity";

    private static class Locators {
        private static final AppiumBy SEARCH_EDIT_TEXT = (AppiumBy) AppiumBy.className("android.widget.EditText");
        private static final AppiumBy FIRST_SEARCH_RESULT_ITEM = (AppiumBy) AppiumBy.id("com.noon.buyerapp:id/placeholder_first_search_result");
        private static final AppiumBy SECOND_CLICK_ITEM = (AppiumBy) AppiumBy.id("com.noon.buyerapp:id/placeholder_second_item_filter_or_detail");
        private static final AppiumBy THIRD_CLICK_ITEM = (AppiumBy) AppiumBy.id("com.noon.buyerapp:id/placeholder_third_item_confirm_or_add");
    }

    AppiumDriverLocalService appiumDriverLocalService;
    AndroidDriver androidDriver;
    WebDriverWait webDriverWait;


    @BeforeMethod
    public void startServer()
    {
        // Initialize Appium driver local service
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withArgument(GeneralServerFlag.BASEPATH, APPIUM_BASE_PATH)
                .usingPort(APPIUM_PORT)
                .build();

        appiumDriverLocalService.start();

        // Setup UiAutomator2 options for Android
        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setDeviceName(DEVICE_NAME)
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY);

        // Initialize AndroidDriver
        androidDriver = new AndroidDriver(appiumDriverLocalService.getUrl(),uiAutomator2Options);
        // Initialize WebDriverWait
        webDriverWait = new WebDriverWait(androidDriver, Duration.ofSeconds(20));
    }

    @Test
    public void searchAndSelectTest() { // Renamed test method
        // webDriverWait is already initialized in startServer()

        // Click on the search input field
        webDriverWait.until(presenceOfElementLocated(Locators.SEARCH_EDIT_TEXT)).click();

        // Type the search query
        webDriverWait.until(presenceOfElementLocated(Locators.SEARCH_EDIT_TEXT)).sendKeys("RAM DDR4");

        // Click on the first search result item
        webDriverWait.until(presenceOfElementLocated(Locators.FIRST_SEARCH_RESULT_ITEM)).click();

        // Click on a filter or a specific item detail
        webDriverWait.until(presenceOfElementLocated(Locators.SECOND_CLICK_ITEM)).click();

        // Click to confirm selection or add to cart
        webDriverWait.until(presenceOfElementLocated(Locators.THIRD_CLICK_ITEM)).click();

    }

    @AfterMethod
    public void stopServer()
    {
        // Stop the Appium server
        appiumDriverLocalService.stop();
    }

}
