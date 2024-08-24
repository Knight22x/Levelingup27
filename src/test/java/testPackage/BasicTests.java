package testPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class BasicTests {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
//    wait = new FluentWait<>(driver)
//            .withTimeout(Duration.ofSeconds(30))
//            .pollingEvery(Duration.ofMillis(300))
//            .ignoring(NotFoundException.class)
//                .ignoring(ElementNotInteractableException.class)
//                .ignoring(StaleElementReferenceException.class);

    @Test
    public void basicNavigationTest() {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("w");
        driver.quit();
    }

    @Test
    public void browserActionsTest() {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get("w");
        driver.quit();
    }


    @Test
    public void taskOne() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com/ncr");
        String expectedTitle = "Google";
        String pageTitle = driver.getTitle();
        assertEquals(pageTitle, expectedTitle);
        driver.quit();
    }

    @Test
    public void taskTwo() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com/ncr");
        WebElement logo = driver.findElement(By.className("lnXdpd"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void taskThree() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com/ncr");
        WebElement searchBox = driver.findElement(By.id("APjFqb"));
        searchBox.sendKeys("Selenium WebDriver");
        //searchBox.submit(); not supported anymore but still works though
        searchBox.sendKeys(Keys.RETURN);
        WebElement firstResult = driver.findElement(By.xpath("//h3"));
        //softAssert.assertEquals(firstResult.getText(), "Selenium - Web Browser Automation");
        assertEquals(firstResult.getText(), "Selenium - Web Browser Automation");
    }

    @Test
    public void taskFour() {
        driver = new FirefoxDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.navigate().to("https://duckduckgo.com/");
        WebElement searchBox = driver.findElement(By.id("searchbox_input"));
        searchBox.sendKeys("TestNG");
        searchBox.sendKeys(Keys.RETURN);
//        By forthResult = By.xpath("(//h3[@class='LC20lb MBeuO DKV0Md'])[4]");
//        //By forthResult = By.xpath("//h3[text()='org.testng']");
//        //By forthResultx = By.xpath("(//h3[contains(@class, 'LC20lb') and contains(@class, 'MBeuO') and contains(@class, 'DKV0Md')])[4]");
//        wait.until(d -> driver.findElement(forthResult).isDisplayed());
//        String actualText = driver.findElement(forthResult).getText();
//        assertEquals("TestNG Tutorial", actualText);
//        //wrong
        // not a cool wait
        // List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h3")));
        // Cooler wait
        wait.until(d -> {
            By fourthResultText = By.xpath("(//article)[4]//h2");
            //  (//article)[4]//h2
            //  (//article//h2)[4]
            //
            String actual = driver.findElement(fourthResultText).getText();
            Assert.assertEquals(actual, "TestNG Tutorial");
            return true;

        });
        driver.quit();
    }

    @Test
    public void taskFive() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.google.com/ncr");
        WebElement searchBox = driver.findElement(By.id("APjFqb"));
        searchBox.sendKeys("Cucumber IO");
        searchBox.sendKeys(Keys.RETURN);
        wait.until(d -> {
            By secondPageLink = By.xpath("//a[@aria-label='Page 2']");
            // Click the link to go to the second page
            driver.findElement(secondPageLink).click();
            By secondResultLink = By.xpath("//div[@data-hveid='CBQQAA']//a[@href]");
            String href = driver.findElement(secondResultLink).getAttribute("href");
            Assert.assertTrue(href.contains("cucumber"));
            System.out.println(href);
            return true;
        });

    }
}



