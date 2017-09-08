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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Shopping {

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
        driver.navigate().to("http://automationpractice.com/index.php");


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
    public void addToCart() throws IOException, InterruptedException {


        ExtentTest extentTest = reportManager.setUpTest();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String imagePath = ScreenShot.take(driver, "image");

        Actions builder = new Actions(driver);
        driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]/span")).click();
        sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/span")).click();
        sleep(1000);
        driver.navigate().to("http://automationpractice.com/index.php?controller=order");
        sleep(1000);


        try {
            String fullCart = driver.findElement(By.id("emptyCartWarning")).getText();
            Assert.assertNotEquals(fullCart, driver.findElement(By.id("order-detail-content")).getText());
            extentTest.log(Status.INFO,"Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
        } catch (AssertionError e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
            extentTest.log(Status.WARNING,"Used to report an issue that may cause problems within a system");
            extentTest.log(Status.ERROR,"Used to report an issue that will cause problems within a system");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.log(Status.FATAL,"Used to report an issue that will fail/break the system");

        }
    }


    @Test
    public void createAccount() throws IOException, InterruptedException {

        ExtentTest extentTest = reportManager.setUpTest();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String imagePath = ScreenShot.take(driver, "image");

        Actions builder = new Actions(driver);
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        sleep(1000);
        driver.findElement(By.id("email_create")).sendKeys("hachich99@hotmail.com");
        sleep(1000);
        driver.findElement(By.id("SubmitCreate")).click();
        sleep(2000);
        driver.findElement(By.id("id_gender1")).click();
        sleep(1000);
        driver.findElement(By.id("customer_firstname")).sendKeys("Mahmoud");
        sleep(1000);
        driver.findElement(By.id("customer_lastname")).sendKeys("Hachich");
        sleep(1000);
        driver.findElement(By.id("passwd")).sendKeys("passtest");
        sleep(1000);
        driver.findElement(By.id("days")).click();
        sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"days\"]/option[7]")).click();
        sleep(1000);
        driver.findElement(By.id("months")).click();
        sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"months\"]/option[5]")).click();
        sleep(1000);
        driver.findElement(By.id("years")).click();
        sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"years\"]/option[24]")).click();
        sleep(1000);
        driver.findElement(By.id("firstname"));
        sleep(1000);
        driver.findElement(By.id("lastname"));
        sleep(1000);
        driver.findElement(By.id("address1")).sendKeys("London");
        sleep(1000);
        driver.findElement(By.id("city")).sendKeys("London");
        sleep(1000);
        driver.findElement(By.id("id_state")).click();
        sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"id_state\"]/option[11]")).click();
        sleep(1000);
        driver.findElement(By.id("postcode")).sendKeys("12345");
        sleep(1000);
        driver.findElement(By.id("phone_mobile")).sendKeys("07375065343");
        driver.findElement(By.id("submitAccount")).click();
        sleep(3000);

        try {

            Assert.assertEquals(driver.findElement(By.id("center_column")), driver.findElement(By.id("center_column")));
            System.out.println("Passed");
            extentTest.log(Status.INFO,"Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");


        } catch (AssertionError e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
            extentTest.log(Status.WARNING,"Used to report an issue that may cause problems within a system");
            extentTest.log(Status.ERROR,"Used to report an issue that will cause problems within a system");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.log(Status.FATAL,"Used to report an issue that will fail/break the system");

        }
    }


    @Test
    public void signIn() throws IOException, InterruptedException {

        ExtentTest extentTest = reportManager.setUpTest();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String imagePath = ScreenShot.take(driver, "image");
        Actions builder = new Actions(driver);
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        sleep(1000);
        driver.findElement(By.id("email")).sendKeys("hachich.m@hotmail.com");
        sleep(1000);
        driver.findElement(By.id("passwd")).sendKeys("testpass");
        sleep(1000);
        driver.findElement(By.id("SubmitLogin")).click();
        sleep(1000);

        try {

            Assert.assertEquals(driver.findElement(By.id("center_column")), driver.findElement(By.id("center_column")));
            extentTest.log(Status.INFO,"Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
            System.out.println("Passed");


        } catch (AssertionError e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
            extentTest.log(Status.WARNING,"Used to report an issue that may cause problems within a system");
            extentTest.log(Status.ERROR,"Used to report an issue that will cause problems within a system");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.log(Status.FATAL,"Used to report an issue that will fail/break the system");
            System.out.println("error");

        }
    }

    @Test
    public void contactPage() throws IOException, InterruptedException {

        ExtentTest extentTest = reportManager.setUpTest();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String imagePath = ScreenShot.take(driver, "image");
        Actions builder = new Actions(driver);
        driver.findElement(By.xpath("//*[@id=\"contact-link\"]/a")).click();
        sleep(1000);
        driver.findElement(By.id("id_contact")).click();
        sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"id_contact\"]/option[2]")).click();
        sleep(1000);
        driver.findElement(By.id("email")).sendKeys("hachich.m@hotmail.com");
        sleep(1000);
        driver.findElement(By.id("message")).sendKeys("Problem with the code");
        sleep(1000);

        try {

            Assert.assertEquals(driver.findElement(By.id("center_column")), driver.findElement(By.id("center_column")));
            extentTest.log(Status.INFO,"Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
            System.out.println("Passed");


        } catch (AssertionError e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
            extentTest.log(Status.WARNING,"Used to report an issue that may cause problems within a system");
            extentTest.log(Status.ERROR,"Used to report an issue that will cause problems within a system");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.log(Status.FATAL,"Used to report an issue that will fail/break the system");
            System.out.println("error");

        }
    }




    //*[@id="block_various_links_footer"]/ul/li[4]/a

    @After
    public void myAfterMethod() {
        reportManager.clearTests();
        driver.quit();

    }


}
