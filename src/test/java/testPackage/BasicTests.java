package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class BasicTests {

    @Test
    public void baseTests() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.quit();
    }
    @Test
    public void taskOne() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/ncr");
        String expectedTitle= "Google";
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, expectedTitle);
        driver.quit();
    }
@Test
    public void taskTwo() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/ncr");
        WebElement logo = driver.findElement(By.className("lnXdpd"));
        Assert.assertTrue(logo.isDisplayed());
    }

}
