import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import org.openqa.selenium.interactions.Actions;

public class App {
    public static void main(String[] args) {
        Eyes eyes = new Eyes();                                        // Note 1
        String apiKey = System.getenv("APPLITOOLS_API_KEY");
        eyes.setApiKey(apiKey);                                        // Note 2

        WebDriver innerDriver = new ChromeDriver();                    // Note 3
        //
        //Add optional global setup/defaults before the eyes.open      // Note 4
        //
        RectangleSize viewportSize = new RectangleSize(/*width*/ 1024, /*height*/ 768 );
        WebDriver driver = eyes.open(innerDriver,
                "DemoApp", "DemoLoginWindow", viewportSize);      // Note 5
        try {
            String website = "https://demo.applitools.com";
            driver.get(website);
            eyes.checkWindow("Login window");
            new Actions(driver).click(driver.findElement(By.id("log-in"))).build().perform();
            eyes.("Login Window");
            TestResults testResult = eyes.close(false);                // Note 8
        } finally {
            eyes.abortIfNotClosed();                                   // Note 9
        }
        innerDriver.quit();                                            // Note 10
    }
}
