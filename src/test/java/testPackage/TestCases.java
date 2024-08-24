package testPackage;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class TestCases {

    WebDriver driver;
    Wait<WebDriver> wait;
    By searchBox = By.id("searchbox_input");

    @Test
    public void taskThreeUpdated() {
        driver.findElement(searchBox).sendKeys("Selenium WebDriver" + Keys.RETURN);
        wait.until(d -> {
            By firstResultLink = By.xpath("(//article)[1]//h2/a");
            String href = driver.findElement(firstResultLink).getAttribute("href");
            Assert.assertTrue(href.contains("https://www.selenium.dev/documentation/webdriver/"));
            return true;
        });
    }

    @Test
    public void taskFourUpdated() {
        driver.findElement(searchBox).sendKeys("TestNG" + Keys.RETURN);
        wait.until(d -> {
            By fourthResultText = By.xpath("(//article)[4]//h2");
            String actual = driver.findElement(fourthResultText).getText();
            Assert.assertEquals(actual, "TestNG Tutorial");
            return true;
        });
    }

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(AssertionError.class);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.navigate().to("https://duckduckgo.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
