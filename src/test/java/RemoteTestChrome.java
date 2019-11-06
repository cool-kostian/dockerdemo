import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteTestChrome {
    WebDriver driver;

    @BeforeMethod
    public void StartUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);


        var hubUrl = new URL("http://localhost:5000/wd/hub");
        driver = new RemoteWebDriver(hubUrl, capabilities);




        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(7000);
        driver.quit();
    }
    @Test
    public void searchGoogle(){
        driver.get("http://google.com/");

        var searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("docker");
        searchField.sendKeys(Keys.RETURN);

        var dockerHub = driver.findElements(By.linkText("Docker Hub"));

        Assert.assertTrue(dockerHub.size()>0);

    }
}
