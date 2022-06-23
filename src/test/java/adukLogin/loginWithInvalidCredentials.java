package adukLogin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utility.Constant;
import java.net.URL;


public class loginWithInvalidCredentials {
    public static final String AUTOMATE_USERNAME = "aobrien_HrBoXL";
    public static final String AUTOMATE_ACCESS_KEY = "dBYL5U3ysZUsxEVnkCz7";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    String username = System.getenv("BROWSERSTACK_USERNAME");
    String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");

    @Test
    public static void main(String[] args) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "iphone");
        caps.setCapability("device", "iPhone 11");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "14.0");
        caps.setCapability("name", "Login with invalid credentials"); // test name
        caps.setCapability("build", "Compliance regression"); // CI/CD job or build name
        final WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        try {
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(Constant.adukURL);
            // basic auth login
            driver.findElement(By.id("user")).sendKeys("admin");
            driver.findElement(By.id("password")).sendKeys("matrix4215");
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            // accept cookies
            driver.findElement(By.xpath("//button[@class='optanon-allow-all accept-cookies-button']")).click();
            // click login header button
            driver.findElement(By.xpath("//a[@class='link link-main-header']")).click();
            // enter username
            driver.findElement(By.id("nickname")).sendKeys("thisuserisfake55");
            // enter password
            driver.findElement(By.id("password")).sendKeys("fakeuser55");
            // click login button
            driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/div/button")).click();

            // expected error message text
            String expectedErrorMessage = "Incorrect nickname/password combination.";
            // check expected error message against actual error message
            String errorMessage = driver.findElement(By.xpath("//*[@id=\"dialog\"]/div/div[2]/div/app-login/form/app-alert/div/ul/li")).getText();
            if (expectedErrorMessage.equalsIgnoreCase(errorMessage)) {
                markTestStatus("passed", "Expected error message matches actual error message", driver);
                }
             } catch(Exception e) {
                markTestStatus("failed", "Expected error message does not match actual error message", driver);
                }
        driver.quit();
}

    @AfterTest
    // This method accepts the status, reason and WebDriver instance and marks the test on BrowserStack
    public static void markTestStatus(String status, String reason, WebDriver driver) {
        final JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+ status + "\", \"reason\": \"" + reason + "\"}}");
    }
}
