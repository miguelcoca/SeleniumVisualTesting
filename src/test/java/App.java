import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResults;
import org.openqa.selenium.interactions.Actions;

import java.security.PublicKey;

public class App {
    private WebDriver driver;
    private Eyes eyes;
    private String testName;
    private String website = "https://demo.applitools.com";

    @Before
    public void Setup(){
        WebDriver tmpDriver = new ChromeDriver();
        eyes = new Eyes();
        String apiKey = System.getenv("APPLITOOLS_API_KEY");
        eyes.setApiKey(apiKey);
        driver = eyes.open(tmpDriver,"Demo App", testName, new RectangleSize(1024,768));
    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void starting(Description description){
            testName = description.getDisplayName();
        }
    };

    @After
    public void tearDown(){
        eyes.abortIfNotClosed();
        driver.quit();
    }

    @Test
    public void loginWithValidCredential(){
        driver.navigate().to(website);
        eyes.checkWindow("Login form");

        driver.findElement(By.id("username")).sendKeys("UserName");
        driver.findElement(By.id("password")).sendKeys("MyPassword");
        driver.findElement(By.id("log-in")).click();

        eyes.checkWindow("Legged In");
        eyes.close();
    }
}
