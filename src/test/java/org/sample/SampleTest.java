package org.sample;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
public class SampleTest {
	
    public WebDriver driver = null;
    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions capability = new ChromeOptions();
		capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
		capability.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(capability);
        driver.get("http://127.0.0.1:80/todo/list");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    // @Test
    // public void Test() {
	// 	String title = driver.getTitle();
	// 	System.out.println(title);
    // }
    
    //=====================
    @Test (priority=1)
        public void getTitle() {
            String title = driver.getTitle();
            System.out.print("Test- Get Title: ");
            System.out.println(title);
        }

    @Test (priority=2)	
        public void addItem() {
            driver.findElement(By.xpath("//a[@href='/ToDo/Create']")).click();
            driver.findElement(By.xpath("//input[@id='Content']")).sendKeys("Test Auto");
            driver.findElement(By.xpath("//input[@value='Create']")).click();
            String tempVal = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
            System.out.print("Test- Add Item: ");
            System.out.println(tempVal);
        }

    @Test (priority=3)	
        public void updateItem() {
            List <WebElement> rows = driver.findElements(By.xpath("//*[@class='table']/tbody/tr/td[1]"));
            int n = rows.size();
            driver.findElement(By.xpath("//*[@class='table']/tbody/tr["+n+"]/td[2]/a[1]")).click();
            driver.findElement(By.xpath("//input[@id='Content']")).sendKeys("Test Auto Updated");
            driver.findElement(By.xpath("//input[@value='Save']")).click();
            String tempVal = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
            System.out.print("Test- Update Item: ");
            System.out.println(tempVal);
        }

    @Test (priority=4)	
        public void deleteItem() {
            List <WebElement> rows = driver.findElements(By.xpath("//*[@class='table']/tbody/tr/td[1]"));
            int n = rows.size();
            driver.findElement(By.xpath("//*[@class='table']/tbody/tr["+n+"]/td[2]/a[2]")).click();
            String tempVal = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
            System.out.print("Test- Delete Item: ");
            System.out.println(tempVal);
        }
    //=====================


    @AfterTest
    public void tearDown() {
        try {
            Thread.sleep(2000);
            driver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}