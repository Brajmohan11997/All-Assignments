package dependsOnMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DependOnMethod {
    WebDriver driver;

    @Test
    public void launchBrowser(){
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(dependsOnGroups = "launchBrowser")
    public void selectTest(){
        Select objSelect = new Select(driver.findElement(By.id("dropdown-class-example")));
        objSelect.selectByVisibleText("Option2");
    }

    @Test(dependsOnGroups = "selectTest")
    public void autoSuggestion(){
        WebElement searchField = driver.findElement(By.xpath("//input[@placeholder=\"Type to Select Countries\"]"));

        searchField.sendKeys("Austra");
        // Find the suggestions
        List<WebElement> suggestions = driver.findElements(By.id("ui-id-1"));

        for (WebElement suggest : suggestions){
            if (suggest.getText().equals("Australia")){
                suggest.click();
                break;
            }
        }
    }

    @Test(dependsOnGroups = "autoSuggestion")
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
