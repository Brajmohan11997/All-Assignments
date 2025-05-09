package ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class extentReport {
    static WebDriver driver;
    static ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp(){
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReportFile.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void test(){
        test = extent.createTest("open the Web Application");
        driver.get("https://www.amazon.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        test.pass("Web Application open successful");
        test.fail("Web Application open unsuccessful");

        test = extent.createTest("Search the product");
        WebElement inputSearch = driver.findElement(By.xpath("//input[@placeholder=\"Search Amazon\"]"));
        inputSearch.sendKeys("Laptop");
        inputSearch.submit();
        String ExpectedURL = "https://www.amazon.com/s?k=Laptop&ref=nb_sb_noss";
        String ActualURL = driver.getCurrentUrl();
        Assert.assertEquals(ExpectedURL, ActualURL);
        test.pass("We correct the laptop page");
        test.fail("We did not found the laptop page");
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
        extent.flush();
    }
}
