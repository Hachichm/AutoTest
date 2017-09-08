import org.apache.xpath.SourceTree;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class Basic {


    WebDriver driver;

    @BeforeClass
    public static void myBeforeClassMethod() {
        System.out.println("BeforeClass");
    }

    @Before
    public void myBeforeMethod() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.qa.com");

        //System.out.println("Before");
    }

    @Test
    public void myTestMethod() {
        assertEquals(driver.getTitle(), "IT Training | Project Management Training | Business Skills Training | QA");
        //System.out.println("Test");
    }

    @After
    public void myAfterMethod() {
        driver.quit();
        //System.out.println("After");
    }

    @AfterClass
    public static void myAfterClassMethod() {
        System.out.println("AfterClass");
    }

}
