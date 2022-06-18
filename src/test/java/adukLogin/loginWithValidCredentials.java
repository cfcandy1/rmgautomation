package adukLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utility.Constant;
import java.time.Duration;


public class loginWithValidCredentials {
    static WebDriver driver;

    @BeforeTest
    public static void setUp() {
        // set chrome driver property
        System.setProperty("webdriver.chrome.driver", "/Users/andrew/Downloads/Selenium/chromedriver");
        driver = new ChromeDriver();

        // get URL
        driver.get(Constant.adukURL);

        // basic auth login
        driver.findElement(By.id("user")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("matrix4213");
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        // accept cookies
        driver.findElement(By.xpath("//button[@class='optanon-allow-all accept-cookies-button']")).click();

    }
    @Test
    public static void validCredentialsLogin() {
        // click login header button
        driver.findElement(By.xpath("//a[@class='link link-main-header']")).click();
        // enter username
        driver.findElement(By.id("nickname")).sendKeys("stagtestrmg1");
        // enter password
        driver.findElement(By.id("password")).sendKeys("qwertz12strmg");

        // login button enabled
        Boolean nn = driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/div/button")).isEnabled();
        if(nn) {
            System.out.println("Yes ! Element is enabled");
        }
        else {
            System.out.println("NO ! Element is not Present");
        }

        // click login button
        driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/div/button")).click();
        // verify login success
        Boolean Display = driver.findElement(By.xpath("//div[@class='item item-userpic ng-star-inserted']")).isDisplayed();
        System.out.println("Element displayed is :" + Display);
    }
    @AfterTest
    public void tearDown(){
        // close browser
        driver.close();
        driver.quit();
        }
    }

