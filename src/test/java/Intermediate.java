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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
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
        driver = new FirefoxDriver();
       // driver.navigate().to("https://thedemosite.co.uk");


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
    public void myTestMethod() throws IOException, InterruptedException {
        ExtentTest extentTest = reportManager.setUpTest();

        driver.navigate().to("http://thedemosite.co.uk");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String imagePath = ScreenShot.take(driver, "image");

        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/Example_Spreadsheet.xlsx");

        int[] numberOfRows = {0};
        for (int rowNo : numberOfRows){
            sheetReader.readRow(rowNo, "sheet1");
        }

        List<String> row = sheetReader.readRow(0, "sheet1");

//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(30, SECONDS)
//                .pollingEvery(5, SECONDS)
//                .ignoring(NoSuchElementException.class);
//
//        WebElement foo = wait.until(new Function<WebDriver, WebElement>()
//        {
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(By.id("foo"));
//            }
//        });

        driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
       // Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")).sendKeys(row.get(0));
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys(row.get(1));
        //Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[2]/small/a")).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys(row.get(0));
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys(row.get(1));
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();
        //Thread.sleep(1000);
        try{
            assertEquals(row.get(2), driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
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
