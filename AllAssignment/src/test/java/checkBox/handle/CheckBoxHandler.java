package checkBox.handle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
public class CheckBoxHandler {
    WebDriver driver;
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/#top");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @Test
    public void testSelectAndDeselectAllCheckBoxes() {
        selectAllCheckBoxes();
        deselectAllCheckBoxes();
    }private void selectAllCheckBoxes() {
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        }
    }private void deselectAllCheckBoxes() {
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                checkBox.click();
            }
        }
    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        if (driver != null) {
            driver.quit();
        }
    }
}
