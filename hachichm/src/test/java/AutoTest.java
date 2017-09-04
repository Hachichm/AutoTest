import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AutoTest {


    WebDriver driver;

    @Before
    public void setUp(){

        driver = new ChromeDriver();
        driver.navigate().to("https://www.qa.com");
    }


    @Test
    public void myTest()
    {
        assertEquals(driver.getTitle(), "IT Training | Project Management Training | Business Skills Training | QA");

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }



}
