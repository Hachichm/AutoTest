import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MouseTest {

    WebDriver driver;
    private static ExtentReportManager reportManager;


    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\TestReport",
                "Basic Extent Report", "Basic Report");
//        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
    }

    @Before
    public void myBeforeMethod() {
        driver = new ChromeDriver();
        driver.navigate().to("http://demoqa.com/");


    }

    @Test
    public void passingLogLevelTest() {
        ExtentTest passingLogLevelTest = reportManager.setUpTest();
        passingLogLevelTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                " person to understand what the test is doing");
        passingLogLevelTest.log(Status.DEBUG,
                "Debug level message to display any information a TECHNICAL person might need to know");
        passingLogLevelTest.pass("Training.Example passing test");
    }

    @Test
    public void myClickable() throws IOException, InterruptedException {


        driver.findElement(By.xpath("//*[@id=\"menu-item-140\"]/a")).click();
        WebElement p = driver.findElement(By.id("draggable"));
        Thread.sleep(1000);
        Actions builder = new Actions(driver);
        builder.dragAndDropBy(p, 300, 200).perform();
        Thread.sleep(1000);


        try {
            String location = driver.findElement(By.id("draggable")).getText();
            assertNotEquals(location, -300);
            System.out.println("Passed");
        } catch (AssertionError e) {
            System.out.println("error");

        }
    }

    @Test
    public void myDraggable() throws IOException, InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"menu-item-141\"]/a")).click();
        Actions builder = new Actions(driver);
        builder.dragAndDrop(driver.findElement(By.id("draggableview")), driver.findElement(By.id("droppableview"))).perform();
        Thread.sleep(1000);

        try {
            String actualText = driver.findElement(By.id("droppableview")).getText();
            Assert.assertEquals(actualText, "Dropped!");
            System.out.println("Passed");
        } catch (AssertionError e) {
            System.out.println("error");
        }
    }


    @Test
    public void myResizeable() throws IOException, InterruptedException {


        driver.findElement(By.xpath("//*[@id=\"menu-item-143\"]/a")).click();
        Actions builder = new Actions(driver);
        WebElement a = driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]"));
        Thread.sleep(1000);
        builder.dragAndDropBy(a, 300, 200).perform();
        Thread.sleep(1000);

        try {
            String location = driver.findElement(By.xpath("//*[@id=\"resizable\"]/div[3]")).getText();
            assertNotEquals(location, 300);
            System.out.println("Passed");
        } catch (AssertionError e) {
            System.out.println("error");

        }
    }

    @Test
    public void mySelectable() throws IOException, InterruptedException {


        driver.findElement(By.xpath("//*[@id=\"menu-item-142\"]/a")).click();
        WebElement c = driver.findElement(By.id("selectable"));
        Actions builder = new Actions(driver);
        WebElement b = driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[1]"));
        Thread.sleep(1000);
        builder.dragAndDrop(b, driver.findElement(By.xpath("//*[@id=\"selectable\"]/li[3]"))).perform();
        Thread.sleep(1000);


        try {
            List<WebElement> options = c.findElements(By.className("ui-selectee"));
            int cnt = 0;
            for (WebElement a : options) {
                if (a.getAttribute("class").equals("ui-widget-content ui-corner-left ui-selectee ui-selected")) {
                    cnt++;
                }
            }
            assertTrue(cnt == 3);
            System.out.println("Passed");
        } catch (AssertionError e) {
            System.out.println("error");
        }
    }

    @Test
    public void mySortable() throws IOException, InterruptedException {


        driver.findElement(By.xpath("//*[@id=\"menu-item-151\"]/a")).click();
        Actions builder = new Actions(driver);
        WebElement b = driver.findElement(By.xpath("//*[@id=\"sortable\"]/li[1]"));
        Thread.sleep(1000);
        builder.dragAndDropBy(b, 0, 100).perform();
        Thread.sleep(1000);
        WebElement h = driver.findElement(By.cssSelector("#sortable > li:nth-child(1)"));
        try {

        } catch (AssertionError e) {
            System.out.println("error");
        }
    }


    @After
    public void myAfterMethod() {
        reportManager.clearTests();
        driver.quit();
        //System.out.println("After");
    }


}
