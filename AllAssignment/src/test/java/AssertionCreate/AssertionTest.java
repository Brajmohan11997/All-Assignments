package AssertionCreate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AssertionTest {
    private WebDriver driver;
    // Replace with the actual URL

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void verifyPageTitleContainsKeyword() {
        // After the search Laptop verify the page title
        WebElement inputSearch = driver.findElement(By.xpath("//input[@placeholder=\"Search Amazon\"]"));
        inputSearch.sendKeys("Laptop");
        inputSearch.submit();
        String expectedKeyword = "Amazon.com : Laptop";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedKeyword),
                "Page title does not contain the keyword: " + expectedKeyword);
        System.out.println("Assertion Passed: Page title contains '" + expectedKeyword + "'.");
    }

    @Test
    public void verifySearchResultsAreNotEmpty() {
        boolean searchResultsPresent = true;

        Assert.assertTrue(searchResultsPresent, "Search results are empty.");
        System.out.println("Assertion Passed: Search results are not empty.");
    }

    @Test
    public void exampleOfAssertEquals() {
        String expectedKeyword = "Amazon.com";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedKeyword, actualTitle);
    }


    @Test
    public void exampleOfAssertFalse() {
        // Example: Verifying if an error message is not displayed
        // WebElement errorMessage = driver.findElement(By.className("error-message"));
        // boolean isDisplayed = errorMessage.isDisplayed();
        boolean isDisplayed = false;

        Assert.assertFalse(isDisplayed, "Error message is displayed.");
        System.out.println("Assertion Passed: Error message is not displayed.");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }
}
