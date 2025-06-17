package ScrollBar.handle;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ScrollBarHandle {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/#top");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void ScrollBarUpDown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement ele = driver.findElement(By.xpath("(//a[text()= \"Dummy Content for Testing.\"])[1]"));
        js.executeScript("arguments[0].click();", ele);
        Thread.sleep(5000);

    }
    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        if(driver != null){
            driver.quit();
        }
    }
}
