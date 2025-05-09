package GroupTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class GroupsTest {
    WebDriver driver = new ChromeDriver();

    @Test(groups = "regrasion")
    public void selectTest(){
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Select objSelect = new Select(driver.findElement(By.id("dropdown-class-example")));
        objSelect.selectByVisibleText("Option2");
    }

    @Test(groups = "smoke")
    public void autoSuggestion(){
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
}
