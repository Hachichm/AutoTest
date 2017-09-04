import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Intermediate {

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
       // driver.navigate().to("https://thedemosite.co.uk");

        //System.out.println("Before");
    }

    @Test
    public void passingLogLevelTest(){
        ExtentTest passingLogLevelTest = reportManager.setUpTest();
        passingLogLevelTest.log(Status.INFO,"Info level message to show information that allows a NON-TECHNICAL" +
                " person to understand what the test is doing");
        passingLogLevelTest.log(Status.DEBUG,
                "Debug level message to display any information a TECHNICAL person might need to know");
        passingLogLevelTest.pass("Training.Example passing test");
    }

    @Test
    public void myTestMethod() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();
        extentTest.log(Status.WARNING,"Used to report an issue that may cause problems within a system");
        driver.navigate().to("http://thedemosite.co.uk");
        String imagePath = ScreenShot.take(driver, "image");



        driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")).sendKeys("Mahmoud");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys("Mahmoud");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
        driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys("Mahmoud");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys("Mahmoud");
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();
        try{
            assertEquals("**Successful Login**", driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
            extentTest.log(Status.INFO,"Info level message to show information that allows a NON-TECHNICAL" +
                    " person to understand what the test is doing");
            extentTest.pass("Passed");
        } catch (AssertionError e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
            extentTest.log(Status.ERROR,"Used to report an issue that will cause problems within a system");
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.log(Status.FATAL,"Used to report an issue that will fail/break the system");
        }
        //System.out.println("Test");
    }

    @After
    public void myAfterMethod() {
        reportManager.clearTests();
        driver.quit();
        //System.out.println("After");
    }


//    @AfterClass
//    public static void myAfterClassMethod() {
//        System.out.println("AfterClass");
//    }
}
