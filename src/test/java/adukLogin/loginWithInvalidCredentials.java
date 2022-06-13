package adukLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class loginWithInvalidCredentials {

    static WebDriver driver;

    @BeforeTest
    public static void setUp() {
        // set chrome driver property
        System.setProperty("webdriver.chrome.driver", "/Users/andrew/Downloads/Selenium/chromedriver");
        driver = new ChromeDriver();
        // get URL
        driver.get("https://beta.admiralcasino.co.uk/en");
        // set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // basic auth login
        driver.findElement(By.id("user")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("matrix4213");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // accept cookies
        driver.findElement(By.xpath("//button[@class='optanon-allow-all accept-cookies-button']")).click();
    }

    @Test (priority = 1)
    public static void invalidCredentials() {
        // click login header button
        driver.findElement(By.xpath("//a[@class='link link-main-header']")).click();
        // enter username
        driver.findElement(By.id("nickname")).sendKeys("thisuserisfake55");
        // enter password
        driver.findElement(By.id("password")).sendKeys("fakeuser55");

        // login button enabled
        Boolean nn = driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/div/button")).isEnabled();
        if (nn) {
            System.out.println("Yes ! Element is enabled");
        } else {
            System.out.println("NO ! Element is not Present");
        }

        // click login button
        driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/div/button")).click();
    }
    @Test (priority = 2)
    public static void verifyErrorMessage(){
        // expected error message text
        String expectedErrorMessage = "Incorrect nickname/password combination.";

        // check expected error message against actual error message
        String errorMessage = driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/app-alert/div/ul/li")).getText();
        if(expectedErrorMessage.equalsIgnoreCase(errorMessage))
            System.out.println("The expected error message is same as actual error message --- "+errorMessage);
        else
            System.out.println("The expected error message doesn't match the actual error message --- "+errorMessage);
    }
    @AfterTest
    public void tearDown(){
        // close browser

        driver.close();
        driver.quit();
    }
}
