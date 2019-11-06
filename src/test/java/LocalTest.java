import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class LocalTest {
    WebDriver driver;

    @BeforeMethod
    public void StartUp(){
        System.setProperty("webdirver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
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
